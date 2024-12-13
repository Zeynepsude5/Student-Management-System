package OgrenciOtomasyon;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField kullaniciField;
    private static Ogrenci ogrenci;
    private static OgretimUyesi ogretimUyesi;
    private static Memur memur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setTitle("Ogrenci Otomasyon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKullancAd = new JLabel("Kullanıcı Adı: ");
		lblKullancAd.setBounds(50, 50, 100, 20);
		contentPane.add(lblKullancAd);
		
		JLabel lblifre = new JLabel("Şifre: ");
		lblifre.setBounds(50, 100, 100, 20);
		contentPane.add(lblifre);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 100, 200, 20);
		contentPane.add(passwordField);
		
		kullaniciField = new JTextField();
		kullaniciField.setBounds(150, 50, 200, 20);
		contentPane.add(kullaniciField);
		
		
		JButton btnGiriYap = new JButton("Giriş Yap");
		btnGiriYap.setBounds(50, 200, 120, 30);
		btnGiriYap.setBackground(new Color(238, 238, 238));
        btnGiriYap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = kullaniciField.getText();
                String password = new String(passwordField.getPassword());

                if (ogrenci != null && username.equals(ogrenci.getIsim()) && password.equals(ogrenci.getSifre())) {
                    JOptionPane.showMessageDialog(null, "Öğrenci Girişi Başarılı!");
                    ogrenci.profilGoruntule();
                } else if (ogretimUyesi != null && username.equals(ogretimUyesi.getIsim()) && password.equals(ogretimUyesi.getSifre())) {
                    JOptionPane.showMessageDialog(null, "Öğretim Üyesi Girişi Başarılı!");
                    ogretimUyesi.profilGoruntule();
                } else if (memur != null && username.equals(memur.getIsim()) && password.equals(memur.getSifre())) {
                    JOptionPane.showMessageDialog(null, "Memur Girişi Başarılı!");
                    memur.profilGoruntule();
                } else {
                    JOptionPane.showMessageDialog(null, "Geçersiz kullanıcı adı veya şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
		contentPane.add(btnGiriYap);
		
        JButton btnKayitOl = new JButton("Kayıt Ol");
        btnKayitOl.setBounds(230, 200, 120, 30);
        btnKayitOl.setBackground(new Color(238, 238, 238));
        btnKayitOl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = kullaniciField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Kullanıcı adı ve şifre boş olamaz!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kayıt işlemi
                if (ogrenci == null) {
                    ogrenci = new Ogrenci(1, username, "Soyisim", password);
                    JOptionPane.showMessageDialog(null, "Öğrenci olarak kaydınız başarıyla yapıldı.");
                } else if (ogretimUyesi == null) {
                    ogretimUyesi = new OgretimUyesi(2, username, "Soyisim", password);
                    JOptionPane.showMessageDialog(null, "Öğretim Üyesi olarak kaydınız başarıyla yapıldı.");
                } else if (memur == null) {
                    memur = new Memur(3, username, "Soyisim", password);
                    JOptionPane.showMessageDialog(null, "Memur olarak kaydınız başarıyla yapıldı.");
                }
            }
        });
        contentPane.add(btnKayitOl);
		

	}
}
