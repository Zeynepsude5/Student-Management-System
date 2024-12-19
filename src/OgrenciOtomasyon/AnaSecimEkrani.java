package OgrenciOtomasyon;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import OgrenciOtomasyon.GirisEkrani;
import java.awt.Color;

class AnaSecimEkrani extends JFrame {
		public AnaSecimEkrani() {
			setTitle("Giriş Sistemi");
			setSize(429, 316);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);

			JPanel panel = new JPanel();

			JButton ogrenciButton = new JButton("Öğrenci");
			ogrenciButton.setBackground(new Color(102, 153, 255));
			ogrenciButton.setForeground(new Color(0, 0, 0));
			ogrenciButton.setBounds(61, 98, 286, 33);
			JButton ogretimUyesiButton = new JButton("Öğretim Üyesi");
			ogretimUyesiButton.setBackground(new Color(102, 153, 255));
			ogretimUyesiButton.setBounds(61, 149, 286, 33);
			JButton memurButton = new JButton("Memur");
			memurButton.setBackground(new Color(102, 153, 255));
			memurButton.setBounds(61, 204, 286, 33);

			ogrenciButton.addActionListener(e -> {
			    new GirisEkrani("Öğrenci").setVisible(true); // Yeni ekranı göster
			    // AnaSecimEkrani.this.dispose();
			});
	        ogretimUyesiButton.addActionListener(e -> {
			    new GirisEkrani("Öğretim Üyesi").setVisible(true); // Yeni ekranı göster
			    // AnaSecimEkrani.this.dispose();
			});
	        memurButton.addActionListener(e -> {
			    new GirisEkrani("Memur").setVisible(true); // Yeni ekranı göster
			    // AnaSecimEkrani.this.dispose();
			});
	        panel.setLayout(null);
	
	        JLabel lblLtfenKullancTrn = new JLabel("Lütfen Kullanıcı Türünü Seçin:", SwingConstants.CENTER);
	        lblLtfenKullancTrn.setBounds(61, 45, 286, 33);
	        panel.add(lblLtfenKullancTrn);
	        panel.add(ogrenciButton);
	        panel.add(ogretimUyesiButton);
	        panel.add(memurButton);
	
	        getContentPane().add(panel);
	    }
	}