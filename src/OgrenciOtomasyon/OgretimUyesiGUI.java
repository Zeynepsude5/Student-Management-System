package OgrenciOtomasyon;

import javax.swing.*;
import java.awt.*;

public class OgretimUyesiGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private OgretimUyesi ogretimUyesi;

    public OgretimUyesiGUI(OgretimUyesi ogretimUyesi) {
        this.ogretimUyesi = ogretimUyesi;

        setTitle("Öğretim Üyesi Paneli");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        pnlButtons.setLayout(new GridLayout(7, 1, 10, 10));
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnDersAc = new JButton("Ders Aç");
        JButton btnNotGir = new JButton("Not Gir");
        JButton btnProfilGoruntule = new JButton("Profil Görüntüle");
        JButton btnMesajGonder = new JButton("Mesaj Gönder");
        JButton btnMesajlariGor = new JButton("Mesajları Gör");
        JButton btnSifreDegistir = new JButton("Şifre Değiştir");
        JButton btnCikisYap = new JButton("Çıkış Yap");

        pnlButtons.add(btnDersAc);
        pnlButtons.add(btnNotGir);
        pnlButtons.add(btnProfilGoruntule);
        pnlButtons.add(btnMesajGonder);
        pnlButtons.add(btnMesajlariGor);
        pnlButtons.add(btnSifreDegistir);
        pnlButtons.add(btnCikisYap);

        add(pnlButtons, BorderLayout.CENTER);
        
        // Panel alt kısmı: Footer
        JPanel pnlFooter = new JPanel();
        pnlFooter.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlFooter.setBackground(new Color(245, 245, 245));
        JLabel lblFooter = new JLabel("Ogrenci Otomasyon Sistemi © 2024");
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));
        pnlFooter.add(lblFooter);
        add(pnlFooter, BorderLayout.SOUTH);
        
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

        // Çıkış Yap Butonu İşlevi
        btnCikisYap.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Çıkış yapmak istediğinizden emin misiniz?",
                "Çıkış",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Mevcut pencereyi kapat
            }
        });
        
        btnDersAc.addActionListener(e -> {
            // Kullanıcıdan ders bilgilerini alın
            String dersAdi = JOptionPane.showInputDialog(this, "Ders Adı Girin:");
            String dersKodu = JOptionPane.showInputDialog(this, "Ders Kodu Girin:");

            if (dersAdi == null || dersAdi.isEmpty() || dersKodu == null || dersKodu.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ders bilgileri eksik. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Yeni Ders Nesnesi Oluştur
            Ders yeniDers = new Ders(dersAdi, dersKodu, ogretimUyesi);

            // Öğretim üyesine ekle
            ogretimUyesi.dersAc(yeniDers);

            // Ders veritabanına ekle
            DersVeritabani.dersEkle(yeniDers);

            // Kullanıcıya başarı mesajı
            JOptionPane.showMessageDialog(this, "Ders başarıyla açıldı: " + dersAdi, "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        });
        
        btnProfilGoruntule.addActionListener(e -> {
            String profilBilgileri = ogretimUyesi.profilBilgileri();
            JOptionPane.showMessageDialog(
                this,
                profilBilgileri,
                "Profil Bilgileri",
                JOptionPane.INFORMATION_MESSAGE
            );
        });
        
        btnSifreDegistir.addActionListener(e -> {
            String yeniSifre = JOptionPane.showInputDialog(this, "Yeni şifreyi girin:");
            if (yeniSifre != null && !yeniSifre.isEmpty()) {
                ogretimUyesi.sifreDegister(yeniSifre);
                JOptionPane.showMessageDialog(this, "Şifre başarıyla değiştirildi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Şifre boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
