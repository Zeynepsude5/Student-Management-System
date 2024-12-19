package OgrenciOtomasyon;

import java.util.HashMap;

public class KullaniciVeritabani {
    private static HashMap<Integer, Kullanici> kullaniciMap = new HashMap<>();
    private static int nextId = 100000;
    
    public static int getYeniId() {
        return nextId++;
    }

    public static void kullaniciEkle(Kullanici kullanici) {
        kullaniciMap.put(kullanici.getKullaniciId(), kullanici);
    }

    public static Kullanici kullaniciBul(int id) {
        return kullaniciMap.get(id);
    }

    public static boolean kullaniciVarMi(int id) {
        return kullaniciMap.containsKey(id);
    }
}