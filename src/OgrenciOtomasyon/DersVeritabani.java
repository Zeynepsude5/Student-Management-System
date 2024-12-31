package OgrenciOtomasyon;

import java.util.ArrayList;
import java.util.HashMap;

public class DersVeritabani {
    private static HashMap<String, Ders> dersMap = new HashMap<>(); // Ders koduna göre dersleri saklar

    public static void dersEkle(Ders ders) {
        if (dersMap.containsKey(ders.getDersKodu())) {
            System.out.println("Ders zaten mevcut: " + ders.getDersAdi());
        } else {
            dersMap.put(ders.getDersKodu(), ders);
            System.out.println("Ders başarıyla eklendi: " + ders.getDersAdi());
        }
    }

    public static Ders dersBul(String dersKodu) {
        return dersMap.get(dersKodu);
    }

    public static void tumDersleriListele() {
        if (dersMap.isEmpty()) {
            System.out.println("Sistemde kayıtlı ders bulunmamaktadır.");
        } else {
            System.out.println("Sistemdeki Dersler:");
            for (Ders ders : dersMap.values()) {
                System.out.println("- " + ders.getDersAdi() + " (" + ders.getDersKodu() + ")");
            }
        }
    }
    
    public static ArrayList<Ders> getTumDersler() {
        return new ArrayList<>(dersMap.values());
    }

    public static void dersSil(String dersKodu) {
        if (dersMap.containsKey(dersKodu)) {
            Ders silinenDers = dersMap.remove(dersKodu);
            System.out.println("Ders silindi: " + silinenDers.getDersAdi());
        } else {
            System.out.println("Bu ders sistemde kayıtlı değil.");
        }
    }
}