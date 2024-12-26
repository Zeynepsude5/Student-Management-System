package OgrenciOtomasyon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OgrenciGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private Ogrenci ogrenci;

    public OgrenciGUI(Ogrenci ogrenci) {
        this.ogrenci = ogrenci;

        setTitle("Öğrenci Paneli");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel üst kısmı: Hoşgeldiniz Mesajı
        JPanel pnlHeader = new JPanel();
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlHeader.setBackground(new Color(60, 179, 113));
        JLabel lblHosgeldiniz = new JLabel("Hoş geldiniz, " + ogrenci.getIsim() + " " + ogrenci.getSoyisim());
        lblHosgeldiniz.setForeground(Color.WHITE);
        lblHosgeldiniz.setFont(new Font("Arial", Font.BOLD, 16));
        pnlHeader.add(lblHosgeldiniz);
        add(pnlHeader, BorderLayout.NORTH);

        // Panel orta kısmı: Butonlar
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(7, 1, 10, 10));
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnDersSec = new JButton("Ders Seç");
        JButton btnDersSil = new JButton("Ders Sil");
        JButton btnNotSorgula = new JButton("Not Sorgula");
        JButton btnNotOrtalama = new JButton("Not Ortalaması");
        JButton btnProfilGoruntule = new JButton("Profil Görüntüle");
        JButton btnMesajGonder = new JButton("Mesaj Gönder");
        JButton btnMesajlariGor = new JButton("Mesajları Gör");

        pnlButtons.add(btnDersSec);
        pnlButtons.add(btnDersSil);
        pnlButtons.add(btnNotSorgula);
        pnlButtons.add(btnNotOrtalama);
        pnlButtons.add(btnProfilGoruntule);
        pnlButtons.add(btnMesajGonder);
        pnlButtons.add(btnMesajlariGor);

        add(pnlButtons, BorderLayout.CENTER);

        // Panel alt kısmı: Footer
        JPanel pnlFooter = new JPanel();
        pnlFooter.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlFooter.setBackground(new Color(245, 245, 245));
        JLabel lblFooter = new JLabel("Ogrenci Otomasyon Sistemi © 2024");
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));
        pnlFooter.add(lblFooter);
        add(pnlFooter, BorderLayout.SOUTH);

        // Button Actions
        btnDersSec.addActionListener(e -> {
            String dersAdi = JOptionPane.showInputDialog("Ders Adı:");
            String dersKodu = JOptionPane.showInputDialog("Ders Kodu:");
            Ders ders = DersVeritabani.dersBul(dersKodu);
            if (ders == null) {
                JOptionPane.showMessageDialog(this, "Bu ders sistemde kayıtlı değil.", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                ogrenci.dersSec(ders);
                JOptionPane.showMessageDialog(this, "Ders başarıyla seçildi: " + ders.getDersAdi(), "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnDersSil.addActionListener(e -> {
            String dersKodu = JOptionPane.showInputDialog("Silmek istediğiniz dersin kodu:");
            Ders ders = DersVeritabani.dersBul(dersKodu);
            if (ders == null) {
                JOptionPane.showMessageDialog(this, "Bu ders sistemde kayıtlı değil.", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                ogrenci.dersSil(ders);
                JOptionPane.showMessageDialog(this, "Ders başarıyla silindi: " + ders.getDersAdi(), "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnNotSorgula.addActionListener(e -> {
            StringBuilder notBilgisi = new StringBuilder("Not Bilgileri:\n");
            if (ogrenci.alinanDersler.isEmpty()) {
                notBilgisi.append("Henüz ders alınmamış.");
            } else {
                for (Ders ders : ogrenci.alinanDersler) {
                    String not = (ders.getNot() == null) ? "Not girilmemiş" : ders.getNot().toString();
                    notBilgisi.append(ders.getDersAdi()).append(" (").append(ders.getDersKodu()).append("): ").append(not).append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, notBilgisi.toString(), "Not Bilgileri", JOptionPane.INFORMATION_MESSAGE);
        });

        btnNotOrtalama.addActionListener(e -> {
            String mesaj;
            if (ogrenci.alinanDersler.isEmpty()) {
                mesaj = "Henüz ders alınmamış.";
            } else {
                double toplam = 0.0;
                int sayac = 0;
                for (Ders ders : ogrenci.alinanDersler) {
                    if (ders.getNot() != null) {
                        toplam += ders.getNot();
                        sayac++;
                    }
                }
                if (sayac == 0) {
                    mesaj = "Henüz not girilmemiş.";
                } else {
                    mesaj = "Not Ortalaması: " + (toplam / sayac);
                }
            }
            JOptionPane.showMessageDialog(this, mesaj, "Not Ortalaması", JOptionPane.INFORMATION_MESSAGE);
        });

        btnProfilGoruntule.addActionListener(e -> {
            StringBuilder profilBilgisi = new StringBuilder("Öğrenci Profili:\n");
            profilBilgisi.append("Ad Soyad: ").append(ogrenci.getIsim()).append(" ").append(ogrenci.getSoyisim()).append("\n");
            profilBilgisi.append("Öğrenci Numarası: ").append(ogrenci.getKullaniciId()).append("\n");
            profilBilgisi.append("Alınan Dersler:\n");
            if (ogrenci.alinanDersler.isEmpty()) {
                profilBilgisi.append("Henüz ders seçilmemiş.");
            } else {
                for (Ders ders : ogrenci.alinanDersler) {
                    profilBilgisi.append("- ").append(ders.getDersAdi()).append(" (").append(ders.getDersKodu()).append(")\n");
                }
            }
            JOptionPane.showMessageDialog(this, profilBilgisi.toString(), "Profil Bilgileri", JOptionPane.INFORMATION_MESSAGE);
        });

        btnMesajGonder.addActionListener(e -> {
            String receiverIdStr = JOptionPane.showInputDialog("Alıcı ID'sini Girin:");
            String content = JOptionPane.showInputDialog("Mesaj İçeriğini Girin:");
            try {
                int receiverId = Integer.parseInt(receiverIdStr);
                Kullanici receiver = KullaniciVeritabani.kullaniciBul(receiverId);
                if (receiver != null && content != null && !content.isEmpty()) {
                    ogrenci.sendMessage(receiver, content);
                    JOptionPane.showMessageDialog(this, "Mesaj başarıyla gönderildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Geçersiz alıcı veya içerik!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Geçerli bir alıcı ID giriniz!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnMesajlariGor.addActionListener(e -> {
            StringBuilder mesajlar = new StringBuilder("Mesajlarınız:\n");
            if (ogrenci.getMessages().isEmpty()) {
                mesajlar.append("Henüz mesaj yok.");
            } else {
                for (Message message : ogrenci.getMessages()) {
                    mesajlar.append(message).append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, mesajlar.toString(), "Mesajlar", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}