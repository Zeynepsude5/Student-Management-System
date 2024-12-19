package OgrenciOtomasyon;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import OgrenciOtomasyon.KayitEkrani;
import java.awt.Font;

class GirisEkrani extends JFrame {
	    public GirisEkrani(String rol) {
	        setTitle(rol + " Giriş Ekranı");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setLocationRelativeTo(null);
	
	        JPanel panel = new JPanel();
	        panel.setLayout(null);
	
	        JLabel label = new JLabel("Kullanıcı ID:");
	        label.setBounds(10, 10, 138, 33);
	        panel.add(label);
	        JTextField kullaniciIdField = new JTextField();
	        kullaniciIdField.setBounds(238, 10, 138, 33);
	        panel.add(kullaniciIdField);
	
	        JLabel label_1 = new JLabel("Şifre:");
	        label_1.setBounds(10, 56, 138, 33);
	        panel.add(label_1);
	        JPasswordField sifreField = new JPasswordField();
	        sifreField.setBounds(238, 56, 138, 33);
	        panel.add(sifreField);
	
	        JButton girisButton = new JButton("Giriş Yap");
	        girisButton.setBounds(120, 114, 138, 33);
	        JButton kayitButton = new JButton("Kayıt Ol");
	        kayitButton.setBounds(238, 170, 138, 33);
	
	        girisButton.addActionListener(e -> {
	            String kullaniciIdStr = kullaniciIdField.getText();
	            String sifre = new String(sifreField.getPassword());

	            try {
	                int kullaniciId = Integer.parseInt(kullaniciIdStr);
	                Kullanici kullanici = KullaniciVeritabani.kullaniciBul(kullaniciId);

	                if (kullanici == null) {
	                    JOptionPane.showMessageDialog(this, "Kullanıcı ID bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
	                } else {
	                    if (kullanici.getSifre().equals(sifre)) {
	                    	 if (kullanici instanceof OgretimUyesi) {
	                             JOptionPane.showMessageDialog(null, "Giriş başarılı. Hoş geldiniz, Öğretim Üyesi!");
	                             dispose();
	                             new OgretimUyesiGUI((OgretimUyesi) kullanici).setVisible(true);
	                         }
	                    } else {
	                        JOptionPane.showMessageDialog(this, "Şifre yanlış. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(this, "Kullanıcı ID rakamlardan oluşmalı!", "Hata", JOptionPane.ERROR_MESSAGE);
	            }
	        });
	        
	        // Kayıt ol butonuna tıklanırsa yeni kayıt ekranı açılır
	        kayitButton.addActionListener(e -> new KayitEkrani(rol).setVisible(true));
	
	        panel.add(girisButton);
	        panel.add(kayitButton);
	
	        getContentPane().add(panel);
	        
	        JLabel lblNewLabel = new JLabel("Henüz kayıt olmadıysanız:");
	        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel.setBounds(10, 170, 218, 33);
	        panel.add(lblNewLabel);
	    }
	}