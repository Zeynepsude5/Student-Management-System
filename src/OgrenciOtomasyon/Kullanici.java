package OgrenciOtomasyon;

import java.util.Scanner;

public abstract class Kullanici {
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
	 
	// Tüm kullanıcıların ortak işlemleri
	public abstract void profilGoruntule();
	
	public void girisYap(Scanner scanner) {
        System.out.print("Şifreyi girin: ");
        String girilenSifre = scanner.nextLine();

        if (girilenSifre.equals(this.sifre)) {
            System.out.println("Giriş yapıldı. Hoş geldiniz, " + isim + " " + soyisim + "!");
        } else {
            System.out.println("Şifre yanlış. Lütfen tekrar deneyin.");
        }
    } 
}
