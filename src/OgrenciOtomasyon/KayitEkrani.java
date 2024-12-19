package OgrenciOtomasyon;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.Font;

class KayitEkrani extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KayitEkrani(String rol) {
        setTitle(rol + " Kayıt Ekranı");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Kullanıcıdan alınacak bilgiler
        JLabel label_1 = new JLabel("İsim:");
        label_1.setBounds(20, 60, 163, 34);
        panel.add(label_1);
        JTextField isimField = new JTextField();
        isimField.setBounds(213, 60, 163, 34);
        panel.add(isimField);

        JLabel label_2 = new JLabel("Soyisim:");
        label_2.setBounds(20, 104, 163, 34);
        panel.add(label_2);
        JTextField soyisimField = new JTextField();
        soyisimField.setBounds(213, 105, 163, 34);
        panel.add(soyisimField);

        JLabel label_3 = new JLabel("Şifre:");
        label_3.setBounds(20, 148, 163, 34);
        panel.add(label_3);
        JPasswordField sifreField = new JPasswordField();
        sifreField.setBounds(213, 149, 163, 34);
        panel.add(sifreField);

        // Kayıt butonu
        JButton kayitTamamlaButton = new JButton("Kayıt Tamamla");
        kayitTamamlaButton.setBounds(226, 205, 150, 34);
		kayitTamamlaButton.setBackground(new Color(102, 153, 255));
        kayitTamamlaButton.addActionListener(e -> {
            try {
                String isim = isimField.getText();
                String soyisim = soyisimField.getText();
                String sifre = new String(sifreField.getPassword());

                if (isim.isEmpty() || soyisim.isEmpty() || sifre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int yeniId = KullaniciVeritabani.getYeniId(); // Yeni ID otomatik oluşturulur
                
                // Kullanıcı rolüne göre doğru sınıfı yarat
                Kullanici yeniKullanici;
                if (rol.equals("Öğrenci")) {
                    yeniKullanici = new Ogrenci(yeniId, isim, soyisim, sifre);
                } else if (rol.equals("Öğretim Üyesi")) {
                    yeniKullanici = new OgretimUyesi(yeniId, isim, soyisim, sifre);
                } else if (rol.equals("Memur")) {
                    yeniKullanici = new Memur(yeniId, isim, soyisim, sifre);
                } else {
                    JOptionPane.showMessageDialog(this, "Geçersiz rol!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                KullaniciVeritabani.kullaniciEkle(yeniKullanici);

                JOptionPane.showMessageDialog(
                    this,
                    rol + " Kayıt Başarılı!\nID: " + yeniId + "\nİsim: " + isim + "\nSoyisim: " + soyisim,
                    "Başarılı",
                    JOptionPane.INFORMATION_MESSAGE
                );

                dispose(); // Pencereyi kapat
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID sayısal bir değer olmalıdır!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(kayitTamamlaButton);

        getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("Lütfen istenen bilgilerinizi giriniz:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(20, 20, 275, 30);
        panel.add(lblNewLabel);
    }
}

	
