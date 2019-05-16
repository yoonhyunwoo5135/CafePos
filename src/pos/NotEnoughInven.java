package pos;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class NotEnoughInven extends JFrame{
	public NotEnoughInven() {
		setTitle("버튼 누르면 경고창 닫기");
		setSize(443, 198);
		
		JButton btnNewButton = new JButton("재료가 부족합니다! 재고량을 확인해주세요.");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		getContentPane().setLayout(new BorderLayout(0, 0));
		btnNewButton.setForeground(new Color(204, 0, 51));
		btnNewButton.setBackground(new Color(255, 255, 153));
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 18));
		getContentPane().add(btnNewButton);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		NotEnoughInven nof = new NotEnoughInven();
	}
}