package OgrenciOtomasyon;

import java.util.ArrayList;

public class OgretimUyesi extends Kullanici implements OgretimUyeIslem {

    private ArrayList<Ders> verilenDersler;

    public OgretimUyesi(int kullaniciId, String isim, String soyisim, String sifre) {
        super(kullaniciId, isim, soyisim, sifre);
        this.verilenDersler = new ArrayList<>();
    }

    @Override
    public String profilBilgileri() {
        StringBuilder profilBilgileri = new StringBuilder();
        profilBilgileri.append("ID: ").append(getKullaniciId()).append("\n");
        profilBilgileri.append("İsim: ").append(getIsim()).append(" ").append(getSoyisim()).append("\n");
        profilBilgileri.append("Verilen Dersler:\n");

        if (verilenDersler.isEmpty()) {
            profilBilgileri.append("Henüz sistemde kayıtlı dersiniz yok.\n");
        } else {
            for (Ders ders : verilenDersler) {
                profilBilgileri.append("- ").append(ders.getDersAdi()).append(" (").append(ders.getDersKodu()).append(")\n");
            }
        }

        return profilBilgileri.toString();
    }

    @Override
    public void sifreDegister(String yeniSifre) {
        setSifre(yeniSifre);
        System.out.println("Şifre başarıyla değiştirildi.");
    }

    @Override
    public void notGir(int ogrNo, String dersKodu, double not) {
        // Öğrenci numarasına göre KullaniciVeritabani'nden kullanıcıyı bul
        Kullanici kullanici = KullaniciVeritabani.kullaniciBul(ogrNo);

        // Kullanıcının varlığını ve öğrenci olup olmadığını kontrol et
        if (kullanici == null || !(kullanici instanceof Ogrenci)) {
            System.out.println("Geçersiz öğrenci numarası: " + ogrNo);
            return;
        }

        // Kullanıcıyı öğrenci tipine dönüştür
        Ogrenci ogrenci = (Ogrenci) kullanici;

        // Öğrencinin aldığı dersler arasında güncellenecek dersi bul
        boolean dersBulundu = false;
        for (Ders alinanDers : ogrenci.alinanDersler) {
            if (alinanDers.getDersKodu().equals(dersKodu)) {
                // Notu güncelle
                alinanDers.setNot(not);
                dersBulundu = true;
                System.out.println("Öğrenci numarası " + ogrNo + " için " + dersKodu + " dersi notu başarıyla güncellendi: " + not);
                break;
            }
        }

        // Ders öğrencinin listesinde bulunamadıysa uyarı ver
        if (!dersBulundu) {
            System.out.println("Öğrenci bu dersi almıyor: " + dersKodu);
        }
    }

    @Override
    public void dersAc(Ders ders) {
        for (Ders mevcutDers : verilenDersler) {
            if (mevcutDers.getDersKodu().equals(ders.getDersKodu())) {
                System.out.println("Bu ders zaten mevcut: " + ders.getDersAdi());
                return;
            }
        }

        verilenDersler.add(ders);
        System.out.println(ders.getDersAdi() + " dersi başarıyla açıldı.");
    }
}