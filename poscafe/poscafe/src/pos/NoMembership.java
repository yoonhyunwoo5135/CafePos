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
		setTitle("��ư ������ ���â �ݱ�");
		setSize(443, 198);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("������ �Һ��� ȸ�� ���� ��ȸ �ʿ�!!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton.setForeground(new Color(204, 0, 51));
		btnNewButton.setFont(new Font("����", Font.BOLD, 22));
		btnNewButton.setBackground(new Color(255, 255, 153));
		btnNewButton.setBounds(0, 0, 421, 142);
		getContentPane().add(btnNewButton);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		NoMembership no = new NoMembership();
	}
}