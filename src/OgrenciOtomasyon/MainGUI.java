package OgrenciOtomasyon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{

	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	            AnaSecimEkrani frame = new AnaSecimEkrani();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}
}