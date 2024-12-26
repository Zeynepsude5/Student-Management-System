package OgrenciOtomasyon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MemurGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private Memur memur;

    public MemurGUI(Memur memur) {
        this.memur = memur;

        setTitle("Memur Paneli");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel üst kısmı: Hoşgeldiniz Mesajı
        JPanel pnlHeader = new JPanel();
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlHeader.setBackground(new Color(100, 149, 237));
        JLabel lblHosgeldiniz = new JLabel("Hoş geldiniz, " + memur.getIsim() + " " + memur.getSoyisim());
        lblHosgeldiniz.setForeground(Color.WHITE);
        lblHosgeldiniz.setFont(new Font("Arial", Font.BOLD, 16));
        pnlHeader.add(lblHosgeldiniz);
        add(pnlHeader, BorderLayout.NORTH);

        // Panel orta kısmı: Butonlar
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(5, 1, 10, 10));
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnProfilGoruntule = new JButton("Profil Görüntüle");
        JButton btnSifreDegistir = new JButton("Şifre Değiştir");
        JButton btnGorevBilgisi = new JButton("Görev Bilgisi Göster");
        JButton btnMesajGonder = new JButton("Mesaj Gönder");
        JButton btnMesajlariGor = new JButton("Mesajları Gör");

        pnlButtons.add(btnProfilGoruntule);
        pnlButtons.add(btnSifreDegistir);
        pnlButtons.add(btnGorevBilgisi);
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
        btnProfilGoruntule.addActionListener(e -> {
            StringBuilder profilBilgisi = new StringBuilder("Memur Profili:\n");
            profilBilgisi.append("ID: ").append(memur.getKullaniciId()).append("\n");
            profilBilgisi.append("Ad Soyad: ").append(memur.getIsim()).append(" ").append(memur.getSoyisim());
            JOptionPane.showMessageDialog(this, profilBilgisi.toString(), "Profil Bilgileri", JOptionPane.INFORMATION_MESSAGE);
        });

        btnSifreDegistir.addActionListener(e -> {
            String yeniSifre = JOptionPane.showInputDialog("Yeni şifrenizi girin:");
            if (yeniSifre != null && !yeniSifre.trim().isEmpty()) {
                memur.sifreDegister(yeniSifre);
                JOptionPane.showMessageDialog(this, "Şifre başarıyla değiştirildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Geçerli bir şifre giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnGorevBilgisi.addActionListener(e -> {
            String gorevBilgisi = memur.getIsim() + " memur olarak çalışıyor.";
            JOptionPane.showMessageDialog(this, gorevBilgisi, "Görev Bilgisi", JOptionPane.INFORMATION_MESSAGE);
        });

        btnMesajGonder.addActionListener(e -> {
            String receiverIdStr = JOptionPane.showInputDialog("Alıcı ID'sini Girin:");
            String content = JOptionPane.showInputDialog("Mesaj İçeriğini Girin:");

            try {
                int receiverId = Integer.parseInt(receiverIdStr);
                Kullanici receiver = KullaniciVeritabani.kullaniciBul(receiverId);

                if (receiver != null && content != null && !content.isEmpty()) {
                    memur.sendMessage(receiver, content);
                    JOptionPane.showMessageDialog(this, "Mesaj gönderildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Geçersiz alıcı veya içerik!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Geçerli bir alıcı ID giriniz!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnMesajlariGor.addActionListener(e -> {
            StringBuilder mesajlar = new StringBuilder("Mesajlarınız:\n");
            List<Message> messageList = memur.getMessages();

            if (messageList.isEmpty()) {
                mesajlar.append("Henüz mesaj yok.");
            } else {
                for (Message message : messageList) {
                    mesajlar.append(message).append("\n");
                }
            }

            JOptionPane.showMessageDialog(this, mesajlar.toString(), "Mesajlar", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
