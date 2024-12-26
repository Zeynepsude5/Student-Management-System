package OgrenciOtomasyon;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Öğrenci, Öğretim Üyesi ve Memur sınıflarından nesneler oluştur
        Ogrenci ogrenci = new Ogrenci(1, "Ahmet", "Yılmaz", "1234");
        OgretimUyesi ogretimUyesi = new OgretimUyesi(2, "Mehmet", "Kaya", "abcd");
        Memur memur = new Memur(3, "Ayşe", "Demir", "5678");

        Scanner scanner = new Scanner(System.in); // Scanner nesnesi oluştur

        // Öğrenci için giriş yap ve profil görüntüle
        System.out.println("=== Öğrenci Girişi ===");
        ogrenci.girisYap(scanner);
        ogrenci.profilGoruntule();

        // Öğretim Üyesi için giriş yap ve profil görüntüle
        System.out.println("\n=== Öğretim Üyesi Girişi ===");
        ogretimUyesi.girisYap(scanner);
        ogretimUyesi.profilGoruntule();

        // Memur için giriş yap ve profil görüntüle
        System.out.println("\n=== Memur Girişi ===");
        memur.girisYap(scanner);
        memur.profilGoruntule();

        scanner.close(); // Scanner'ı kapat
    }
}