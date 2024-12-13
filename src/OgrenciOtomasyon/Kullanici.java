package OgrenciOtomasyon;

import java.util.Scanner;

public abstract class Kullanici implements SifreDegisterebilir {
	private int kullaniciId;
	private String isim;
	private String soyisim;
	private String sifre;
	
	public Kullanici(int kullaniciId, String isim, String soyisim, String sifre) {
	    this.kullaniciId = kullaniciId;
	    this.isim = isim;
	    this.soyisim = soyisim;
	    this.sifre = sifre;
	}
	
	public int getKullaniciId() {
	    return kullaniciId;
	}
	
	public String getIsim() {
	    return isim;
	}
	 
	public String getSoyisim() {
	    return soyisim;
	}
	 
	public String getSifre() {
	    return sifre;
	}
	 
	public void setSifre(String sifre) {
	    this.sifre = sifre;
	}
	 
	public abstract void profilGoruntule();
	
	public boolean girisYap(Scanner scanner) {
		System.out.println("şifrenizi giriniz: ");
		String girilenSifre = scanner.nextLine();
		if(girilenSifre.equals(this.sifre)) {
			System.out.println("Giris Başarılı "+ isim + " " + soyisim+ "!");
			return true;
		} else {
            System.out.println("Şifre yanlış. Lütfen tekrar deneyin.");
            return false;
        }
		
	}
	
	public void sifreDegistir(Scanner scanner) {
		System.out.print("Mevcut şifrenizi giriniz: ");
		String mevcutSifre = scanner.nextLine();
		
		if (mevcutSifre.equals(this.sifre)) {
			System.out.print("Yeni Şifre Giriniz: ");
			String yeniSifre = scanner.nextLine();
			
			if (yeniSifre.isEmpty()) {
				System.out.print("Şifre boş olamaz!");
				return;}
			this.sifre=yeniSifre;
			System.out.println("Şifre değiştirildi.");
		}
		else {
			System.out.println("Girdiğiniz Şifre Yanlış. Tekrar deneyin!");
		}
	}
	
}