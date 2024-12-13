package OgrenciOtomasyon;

import java.util.ArrayList;

public class OgretimUyesi extends Kullanici {
	
	private ArrayList<String> verilenDersler;

	
    public OgretimUyesi(int kullaniciId, String isim, String soyisim, String sifre) {
        super(kullaniciId, isim, soyisim, sifre);
	    this.verilenDersler = new ArrayList<>();
    }

    @Override
    public void profilGoruntule() {
        System.out.println("Öğretim Üyesi Profili: ");
        System.out.println("ID: " + getKullaniciId());
        System.out.println("İsim: " + getIsim() + " " + getSoyisim());
	    System.out.println("Verilen Dersler:");
	    
	    if (verilenDersler.isEmpty()) {
	        System.out.println("Sistemde kayıtlı dersiniz yok.");
	    } else {
	        for (String ders : verilenDersler) {
	            System.out.println("- " + ders);
	        }
	    }
    }

	@Override
	public void sifreDegister(String yeniSifre) {
		setSifre(yeniSifre);
		System.out.println("Şifre başarıyla değiştirildi.");
	}
	
}