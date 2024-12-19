package OgrenciOtomasyon;

import java.util.ArrayList;

public class Ogrenci extends Kullanici implements OgrenciIslem {
    
    public ArrayList<Ders> alinanDersler;

    public Ogrenci(int kullaniciId, String isim, String soyisim, String sifre) {
        super(kullaniciId, isim, soyisim, sifre);
        this.alinanDersler = new ArrayList<>();
    }

    @Override
    public void profilGoruntule() {
        System.out.println("Öğrenci Profili: ");
        System.out.println("Öğrenci numarası: " + getKullaniciId());
        System.out.println("İsim: " + getIsim() + " " + getSoyisim());
        System.out.println("Alınan Dersler:");
        if (alinanDersler.isEmpty()) {
            System.out.println("Henüz ders seçilmedi.");
        } else {
            for (Ders ders : alinanDersler) {
                System.out.println(ders.getDersAdi() + " (" + ders.getDersKodu() + ")");
            }
        }
    }

    @Override
    public void sifreDegister(String yeniSifre) {
        setSifre(yeniSifre);
        System.out.println("Şifre başarı ile değiştirildi!");
    }

    @Override
    public void dersSec(Ders ders) {
        for (Ders dersler : alinanDersler) {
            if (dersler.getDersKodu().equals(ders.getDersKodu())) {
                System.out.println("Ders zaten seçilmiş: " + ders.getDersAdi());
                return;
            }
        }
    }

    @Override
    public void dersSil(Ders ders) {
        for (Ders dersler : alinanDersler) {
            if (dersler.getDersKodu().equals(ders.getDersKodu())) {
                alinanDersler.remove(ders);
                System.out.println(ders.getDersAdi() + " dersi kaldırıldı.");
                return;
            }
        }
        System.out.println("Bu ders alınmamış: " + ders.getDersAdi());
    }

    @Override
    public void notOrtalamaHesapla() {
        if (alinanDersler.isEmpty()) {
            System.out.println("Henüz not bulunmamaktadır.");
            return;
        }
        double toplam = 0.0;
        int sayac = 0;
        for (Ders ders : alinanDersler) {
            if (ders.getNot() != null) {
                toplam += ders.getNot();
                sayac++;
            }
        }
        if (sayac == 0) {
            System.out.println("Henüz not bulunmamaktadır.");
        } else {
            System.out.println("Not Ortalaması: " + (toplam / sayac));
        }
    }

    @Override
    public void notSorgula() {
        if (alinanDersler.isEmpty()) {
            System.out.println("Hiçbir ders bulunamadı.");
            return;
        }

        System.out.println("Ders Notları:");
        for (Ders ders : alinanDersler) {
            String notBilgisi = (ders.getNot() != null) ? ders.getNot().toString() : "Henüz not verilmemiş";
            System.out.println(ders.getDersAdi() + " (" + ders.getDersKodu() + "): " + notBilgisi);
        }
    }
}
