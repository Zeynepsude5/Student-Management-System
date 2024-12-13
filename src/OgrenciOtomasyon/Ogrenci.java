package OgrenciOtomasyon;

import java.util.ArrayList;
import java.util.List;

public class Ogrenci extends Kullanici implements SifreDegisterebilir, DersIslemleri, NotIslemleri {
	
	private ArrayList<String> alinanDersler;
	private ArrayList<Double> notlar;
	
	
	public Ogrenci(int kullaniciId, String isim, String soyisim, String sifre) {
	    super(kullaniciId, isim, soyisim, sifre);
	    this.alinanDersler = new ArrayList<>();
	    this.notlar = new ArrayList<>();
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
	    System.out.println("Notlar: ");
	    if (notlar.isEmpty()) {
	    	System.out.println("Henüz ders girilmedi.");
	    }else {
	    	for (int i = 0; i< notlar.size(); i++) {
	    		System.out.println("- Ders: "+ alinanDersler.get(i));
	    	}
	    }
	    
	}
	@Override 
	public void sifreDegister(String yeniSifre) {
		setSifre(yeniSifre);
		System.out.println("Şifre başarı ile değiştirildi!");
	}
	
	@Override
	public void dersEkle(String ders) {
		if (alinanDersler.contains(ders)) {
			System.out.println("Ders mevcut, başka ders seçiniz!");
		}else {
			alinanDersler.add(ders);
			System.out.println(ders + "Ders başarıyla seçildi!");
		}
	}
	
	@Override
	public void dersSil(String ders) {
		if (alinanDersler.remove(ders)) {
			System.out.println(ders + "Ders kaldırıldı");
		}
		else {
			System.out.println("Ders alınmamış");
		}
	}
	@Override
	public List<String> dersListesi(){
		return alinanDersler;
	}
	
	@Override
	public void notGir(String ders, double not) {
		int index = alinanDersler.indexOf(ders);
		if (index == -1) {
			System.out.println("ders seçmeden not ekleyemezsiniz");
		} else {
			if (notlar.size() > index) {
				notlar.set(index, not);
				System.out.println(ders+"dersi için not eklendi: " + not);

			}
		}
	}
	@Override
	public double notOrtalamaHesapla() {
		if(notlar.isEmpty()) {
			System.out.println("Henüz not bulunmamaktadır.");
			return 0.0;
		}
		double toplam = 0.0;
		for (double not : notlar) {
			toplam += not;
		}
		return toplam / notlar.size();
	}

	@Override
	public double notSorgula(String ders) {
		int index = alinanDersler.indexOf(ders);
		if(index == -1){ // index bos ise
			System.out.println("Ders bulunamadı: "+ ders);
			return 0.0;// fonksiyon deger döndürmesi gerekiyor hatalı durumlarda doubleda 0.0 döndürür.
	}
		return notlar.get(index);}

	@Override
	public void dersOnayla(String ders) {
		if (alinanDersler.contains(ders)) {
			System.out.println(ders + "dersi onayla.");
		}else {
			System.out.println("Dersi seçmeden onaylayamazsınız: " + ders);
		}
	}
	

	
}