package OgrenciOtomasyon;

import java.util.ArrayList;

public class Ogrenci extends Kullanici {
	
	private ArrayList<String> alinanDersler;
	
	
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
	        for (String ders : alinanDersler) {
	            System.out.println("- " + ders);
	        }
	    }
	}
}
