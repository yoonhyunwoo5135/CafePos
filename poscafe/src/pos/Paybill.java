package pos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Paybill {
	
	public Paybill() {
		JFrame f = new JFrame("카페 포스 시스템");
		f.setSize(1080 , 800);
		f.getContentPane().setLayout(null);
		
		JButton b1 = new JButton("\uB3CC\uC544\uAC00\uAE30");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				f.setVisible(false);
			}
		});
		b1.setBounds(929, 684, 107, 33);
		f.getContentPane().add(b1);
		
		
		
		
		
		
		
		
		
		f.setVisible(true);
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		Paybill pay = new Paybill();
		
		
	}
	
	
	
}
