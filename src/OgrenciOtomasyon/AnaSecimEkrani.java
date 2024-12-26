package OgrenciOtomasyon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class AnaSecimEkrani extends JFrame {
    public AnaSecimEkrani() {
        setTitle("Kullanıcı Giriş Sistemi");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Lütfen Kullanıcı Türünü Seçin:", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton ogrenciButton = new JButton("Öğrenci");
        ogrenciButton.setFont(new Font("Arial", Font.PLAIN, 18));
        ogrenciButton.setBackground(new Color(102, 153, 255));
        ogrenciButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(ogrenciButton, gbc);

        JButton ogretimUyesiButton = new JButton("Öğretim Üyesi");
        ogretimUyesiButton.setFont(new Font("Arial", Font.PLAIN, 18));
        ogretimUyesiButton.setBackground(new Color(102, 153, 255));
        ogretimUyesiButton.setForeground(Color.WHITE);
        gbc.gridy = 1;
        panel.add(ogretimUyesiButton, gbc);

        // Memur Butonu
        JButton memurButton = new JButton("Memur");
        memurButton.setFont(new Font("Arial", Font.PLAIN, 18));
        memurButton.setBackground(new Color(102, 153, 255));
        memurButton.setForeground(Color.WHITE);
        gbc.gridy = 2;
        panel.add(memurButton, gbc);

        // ActionListeners
        ogrenciButton.addActionListener((ActionEvent e) -> new GirisEkrani("Öğrenci").setVisible(true));
        ogretimUyesiButton.addActionListener((ActionEvent e) -> new GirisEkrani("Öğretim Üyesi").setVisible(true));
        memurButton.addActionListener((ActionEvent e) -> new GirisEkrani("Memur").setVisible(true));

        add(panel, BorderLayout.CENTER);

        JLabel lblAltBilgi = new JLabel("Ogrenci Otomasyon Sistemi", SwingConstants.CENTER);
        lblAltBilgi.setFont(new Font("Arial", Font.ITALIC, 12));
        lblAltBilgi.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblAltBilgi, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            AnaSecimEkrani frame = new AnaSecimEkrani();
            frame.setVisible(true);
        });
    }
}
