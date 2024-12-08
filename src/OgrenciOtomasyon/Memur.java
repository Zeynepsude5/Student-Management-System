package OgrenciOtomasyon;


public class Memur extends Kullanici {
    public Memur(int kullaniciId, String isim, String soyisim, String sifre) {
        super(kullaniciId, isim, soyisim, sifre);
    }

    @Override
    public void profilGoruntule() {
        System.out.println("Memur Profili: ");
        System.out.println("ID: " + getKullaniciId());
        System.out.println("İsim: " + getIsim() + " " + getSoyisim());
    }
}