package pos;


import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectGender{
	
	public SelectGender() {
		JFrame f = new JFrame("���� �մ� �������� ����");
		f.setSize(500, 300);
		f.getContentPane().setLayout(null);
		
		JButton buttonMan = new JButton("����");
		buttonMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.finish = true;
				f.setVisible(false);
			}
		});
		buttonMan.setBackground(new Color(30, 144, 255));
		buttonMan.setFont(new Font("����", Font.BOLD, 60));
		buttonMan.setBounds(0, 0, 235, 244);
		f.getContentPane().add(buttonMan);
		
		JButton buttonWoman = new JButton("����");
		buttonWoman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.finish = true;
				f.setVisible(false);
			}
		});
		buttonWoman.setBackground(new Color(255, 182, 193));
		buttonWoman.setFont(new Font("����", Font.BOLD, 60));
		buttonWoman.setBounds(243, 0, 235, 244);
		f.getContentPane().add(buttonWoman);
		
		f.setVisible(true);
	}
	public static void main(String[] args) {
		SelectGender sg = new SelectGender();
	}
}