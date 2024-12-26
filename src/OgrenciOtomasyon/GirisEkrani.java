package OgrenciOtomasyon;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class GirisEkrani extends JFrame {

    public GirisEkrani(String rol) {
        setTitle(rol + " Giriş Ekranı");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Panel üst kısmı: Başlık ve Logo
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(new Color(70, 130, 180));

        JLabel lblTitle = new JLabel(rol + " Giriş Ekranı", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setForeground(Color.WHITE);
        pnlHeader.add(lblTitle, BorderLayout.CENTER);

        JLabel lblLogo = new JLabel();
        lblLogo.setHorizontalAlignment(JLabel.RIGHT);
        try {
            String logoPath = System.getProperty("user.home") + "/eclipse-workspace/OgrenciOtomasyon/logo/iuc.jpg";
            File logoFile = new File(logoPath);
            if (logoFile.exists()) {
                ImageIcon logoIcon = new ImageIcon(logoFile.getAbsolutePath());
                Image scaledLogo = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(scaledLogo));
            } else {
                lblLogo.setText("Logo Bulunamadı");
                lblLogo.setForeground(Color.RED);
            }
        } catch (Exception e) {
            lblLogo.setText("Hata: Logo yüklenemedi");
            lblLogo.setForeground(Color.RED);
        }
        pnlHeader.add(lblLogo, BorderLayout.EAST);

        getContentPane().add(pnlHeader, BorderLayout.NORTH);

        // Panel orta kısmı: Form
        JPanel pnlForm = new JPanel();
        pnlForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblKullaniciId = new JLabel("Kullanıcı ID:");
        lblKullaniciId.setBounds(10, 10, 162, 87);
        JTextField kullaniciIdField = new JTextField(10);
        kullaniciIdField.setBounds(177, 41, 162, 25);
        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setBounds(10, 102, 162, 87);
        JPasswordField sifreField = new JPasswordField(10);
        sifreField.setBounds(177, 133, 162, 25);
        pnlForm.setLayout(null);

        pnlForm.add(lblKullaniciId);
        pnlForm.add(kullaniciIdField);
        pnlForm.add(lblSifre);
        pnlForm.add(sifreField);

        getContentPane().add(pnlForm, BorderLayout.CENTER);

        // Panel alt kısmı: Butonlar
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnGirisYap = new JButton("Giriş Yap");
        JButton btnKayitOl = new JButton("Kayıt Ol");
        pnlButtons.add(btnGirisYap);
        pnlButtons.add(btnKayitOl);

        getContentPane().add(pnlButtons, BorderLayout.SOUTH);

        // Button Actions
        btnGirisYap.addActionListener(e -> {
            String kullaniciIdStr = kullaniciIdField.getText();
            String sifre = new String(sifreField.getPassword());

            try {
                int kullaniciId = Integer.parseInt(kullaniciIdStr);
                Kullanici kullanici = KullaniciVeritabani.kullaniciBul(kullaniciId);

                if (kullanici == null) {
                    JOptionPane.showMessageDialog(this, "Kullanıcı ID bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
                } else if (!kullanici.getSifre().equals(sifre)) {
                    JOptionPane.showMessageDialog(this, "Şifre yanlış. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Giriş başarılı. Hoş geldiniz!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                    if (kullanici instanceof Ogrenci) {
                        new OgrenciGUI((Ogrenci) kullanici).setVisible(true);
                    } else if (kullanici instanceof OgretimUyesi) {
                        new OgretimUyesiGUI((OgretimUyesi) kullanici).setVisible(true);
                    } else if (kullanici instanceof Memur) {
                        new MemurGUI((Memur) kullanici).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Geçersiz rol!", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Kullanıcı ID rakamlardan oluşmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnKayitOl.addActionListener(e -> {
            dispose(); // Mevcut giriş ekranını kapatır
            KayitEkrani kayitEkrani = new KayitEkrani(rol);
            kayitEkrani.setVisible(true);
            kayitEkrani.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // Kayıt ekranı kapatıldığında giriş ekranını yeniden aç
                    new GirisEkrani(rol).setVisible(true);
                }
            });
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GirisEkrani frame = new GirisEkrani("Öğrenci");
            frame.setVisible(true);
        });
    }
}
