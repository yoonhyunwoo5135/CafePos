package pos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NoMembership extends JFrame{
	public NoMembership() {
		setTitle("버튼 누르면 경고창 닫기");
		setSize(443, 198);
		
		JButton btnNewButton = new JButton("쿠폰을 소비할 회원 정보 조회 필요!!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		getContentPane().setLayout(new BorderLayout(0, 0));
		btnNewButton.setForeground(new Color(204, 0, 51));
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 22));
		btnNewButton.setBackground(new Color(255, 255, 153));
		getContentPane().add(btnNewButton);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		NoMembership no = new NoMembership();
	}
}