package pos;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class NotEnoughStamp extends JFrame{
	public NotEnoughStamp() {
		setTitle("버튼 누르면 경고창 닫기");
		setSize(443, 198);
		
		JButton btnNewButton = new JButton("회원님의 스탬프 수가 부족합니다!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		getContentPane().setLayout(new BorderLayout(0, 0));
		btnNewButton.setForeground(new Color(204, 0, 51));
		btnNewButton.setBackground(new Color(255, 255, 153));
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 23));
		getContentPane().add(btnNewButton);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		NotEnoughStamp not = new NotEnoughStamp();
	}
}