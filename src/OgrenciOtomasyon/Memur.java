package OgrenciOtomasyon;

public class Memur extends Kullanici {
    public Memur(int kullaniciId, String isim, String soyisim, String sifre) {
        super(kullaniciId, isim, soyisim, sifre);
    }

    @Override
    public String profilBilgileri() {
        StringBuilder profilBilgileri = new StringBuilder();
        profilBilgileri.append("ID: ").append(getKullaniciId()).append("\n");
        profilBilgileri.append("İsim: ").append(getIsim()).append(" ").append(getSoyisim()).append("\n");
        profilBilgileri.append("Görev: Memur\n");
        return profilBilgileri.toString();
    }

    public void gorevBilgisiGoster() {
    	System.out.println(	getIsim() + "Memur olarak çalışıyor.");
    }

	@Override
	public void sifreDegister(String yeniSifre) {
		setSifre(yeniSifre);
		System.out.println("Şifre başarıyla değiştirildi.");
	}
}