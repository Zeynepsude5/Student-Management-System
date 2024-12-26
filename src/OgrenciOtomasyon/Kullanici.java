package OgrenciOtomasyon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Kullanici implements ProfilIslem {
    private int kullaniciId;
    private String isim;
    private String soyisim;
    private String sifre;
    private List<Message> messages; // Kullanıcının mesajları

    // Constructor
    public Kullanici(int kullaniciId, String isim, String soyisim, String sifre) {
        this.kullaniciId = kullaniciId;
        this.isim = isim;
        this.soyisim = soyisim;
        this.sifre = sifre;
        this.messages = new ArrayList<>();
    }

    // Mesajlaşma İşlemleri
    public void sendMessage(Kullanici receiver, String content) {
        String timestamp = java.time.LocalDateTime.now().toString();
        Message message = new Message(this.getIsim(), receiver.getIsim(), content, timestamp);
        this.messages.add(message); // Mesajı gönderenin listesine ekle
        receiver.receiveMessage(message); // Mesajı alıcıya gönder
        System.out.println("Mesaj gönderildi: " + content);
    }

    public void receiveMessage(Message message) {
        this.messages.add(message); // Gelen mesajı mesaj listesine ekle
    }

    public List<Message> getMessages() {
        return this.messages; // Mesaj listesini döndür
    }

    public void showMessages() {
        if (messages.isEmpty()) {
            System.out.println("Henüz mesaj yok.");
        } else {
            System.out.println("Mesajlarınız:");
            for (Message message : messages) {
                System.out.println(message);
            }
        }
    }

    // Getter ve Setter Metotları
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

    // Tüm kullanıcıların uygulaması gereken metot
    public abstract void profilGoruntule();

    // Giriş işlemi
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
