package OgrenciOtomasyon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OgretimUyesiGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OgretimUyesi ogretimUyesi;

    public OgretimUyesiGUI(OgretimUyesi ogretimUyesi) {
        this.ogretimUyesi = ogretimUyesi;

        setTitle("Öğretim Üyesi Paneli");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblHosgeldiniz = new JLabel("Hoş geldiniz, " + ogretimUyesi.getIsim() + " " + ogretimUyesi.getSoyisim());
        lblHosgeldiniz.setBounds(20, 20, 300, 30);
        add(lblHosgeldiniz);

        JButton btnDersAc = new JButton("Ders Aç");
        btnDersAc.setBounds(20, 70, 150, 30);
        add(btnDersAc);

        JButton btnNotGir = new JButton("Not Gir");
        btnNotGir.setBounds(20, 120, 150, 30);
        add(btnNotGir);

        JButton btnProfilGoruntule = new JButton("Profil Görüntüle");
        btnProfilGoruntule.setBounds(20, 170, 150, 30);
        add(btnProfilGoruntule);

        btnDersAc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dersAdi = JOptionPane.showInputDialog("Ders Adı:");
                String dersKodu = JOptionPane.showInputDialog("Ders Kodu:");
                if (dersAdi != null && dersKodu != null) {
                    ogretimUyesi.dersAc(new Ders(dersKodu, dersAdi, ogretimUyesi));
                }
            }
        });

        btnNotGir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ogrNo = Integer.parseInt(JOptionPane.showInputDialog("Öğrenci Numarası:"));
                    String dersKodu = JOptionPane.showInputDialog("Ders Kodu:");
                    double not = Double.parseDouble(JOptionPane.showInputDialog("Not:"));
                    
                    ogretimUyesi.notGir(ogrNo, dersKodu, not);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Hatalı giriş. Lütfen tekrar deneyin.");
                }
            }
        });

        btnProfilGoruntule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ogretimUyesi.profilGoruntule();
            }
        });
    }
}
