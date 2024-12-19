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
    /*
    public void ogrenciKaydet(Ogrenci ogrenci) {
        System.out.println("Yeni öğrenci kaydedildi:");
        System.out.println("Öğrenci ID: " + ogrenci.getKullaniciId());
        System.out.println("İsim: " + ogrenci.getIsim() + " " + ogrenci.getSoyisim());
    }
    */
    public void gorevBilgisiGoster() {
    	System.out.println(	getIsim() + "Memur olarak çalışıyor.");
    }

	@Override
	public void sifreDegister(String yeniSifre) {
		setSifre(yeniSifre);
		System.out.println("Şifre başarıyla değiştirildi.");
	}
}