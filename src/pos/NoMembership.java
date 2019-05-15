package pos;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NoMembership extends JFrame{
	public NoMembership() {
		setTitle("버튼 누르면 경고창 닫기");
		setSize(443, 198);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("쿠폰을 소비할 회원 정보 조회 필요!!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton.setForeground(new Color(204, 0, 51));
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 22));
		btnNewButton.setBackground(new Color(255, 255, 153));
		btnNewButton.setBounds(0, 0, 421, 142);
		getContentPane().add(btnNewButton);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		NoMembership no = new NoMembership();
	}
}