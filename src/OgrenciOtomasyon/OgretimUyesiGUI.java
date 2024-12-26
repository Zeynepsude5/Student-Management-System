package OgrenciOtomasyon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OgretimUyesiGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private OgretimUyesi ogretimUyesi;

    public OgretimUyesiGUI(OgretimUyesi ogretimUyesi) {
        this.ogretimUyesi = ogretimUyesi;

        setTitle("Öğretim Üyesi Paneli");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel üst kısmı: Hoşgeldiniz Mesajı
        JPanel pnlHeader = new JPanel();
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlHeader.setBackground(new Color(70, 130, 180));
        JLabel lblHosgeldiniz = new JLabel("Hoş geldiniz, " + ogretimUyesi.getIsim() + " " + ogretimUyesi.getSoyisim());
        lblHosgeldiniz.setForeground(Color.WHITE);
        lblHosgeldiniz.setFont(new Font("Arial", Font.BOLD, 16));
        pnlHeader.add(lblHosgeldiniz);
        add(pnlHeader, BorderLayout.NORTH);

        // Panel orta kısmı: Butonlar
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(6, 1, 10, 10));
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnDersAc = new JButton("Ders Aç");
        JButton btnNotGir = new JButton("Not Gir");
        JButton btnProfilGoruntule = new JButton("Profil Görüntüle");
        JButton btnMesajGonder = new JButton("Mesaj Gönder");
        JButton btnMesajlariGor = new JButton("Mesajları Gör");

        // Add buttons to the panel
        pnlButtons.add(btnDersAc);
        pnlButtons.add(btnNotGir);
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
        btnDersAc.addActionListener(e -> {
            String dersAdi = JOptionPane.showInputDialog("Ders Adı:");
            String dersKodu = JOptionPane.showInputDialog("Ders Kodu:");
            if (dersAdi != null && dersKodu != null) {
                ogretimUyesi.dersAc(new Ders(dersKodu, dersAdi, ogretimUyesi));
                JOptionPane.showMessageDialog(this, "Ders başarıyla açıldı.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnNotGir.addActionListener(e -> {
            try {
                int ogrNo = Integer.parseInt(JOptionPane.showInputDialog("Öğrenci Numarası:"));
                String dersKodu = JOptionPane.showInputDialog("Ders Kodu:");
                double not = Double.parseDouble(JOptionPane.showInputDialog("Not:"));

                ogretimUyesi.notGir(ogrNo, dersKodu, not);
                JOptionPane.showMessageDialog(this, "Not başarıyla girildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hatalı giriş. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnProfilGoruntule.addActionListener(e -> ogretimUyesi.profilGoruntule());

        btnMesajGonder.addActionListener(e -> {
            String receiverIdStr = JOptionPane.showInputDialog("Alıcı ID'sini Girin:");
            String content = JOptionPane.showInputDialog("Mesaj İçeriğini Girin:");
            try {
                int receiverId = Integer.parseInt(receiverIdStr);
                Kullanici receiver = KullaniciVeritabani.kullaniciBul(receiverId);
                if (receiver != null && content != null && !content.isEmpty()) {
                    ogretimUyesi.sendMessage(receiver, content);
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
            if (ogretimUyesi.getMessages().isEmpty()) {
                mesajlar.append("Henüz mesaj yok.");
            } else {
                for (Message message : ogretimUyesi.getMessages()) {
                    mesajlar.append(message).append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, mesajlar.toString(), "Mesajlar", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}