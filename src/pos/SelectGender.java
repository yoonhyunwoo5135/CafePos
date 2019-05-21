package pos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import event.Event;
import paybill.PaybillDAO;
import paybill.PaybillDTO;

public class SelectGender {

	public SelectGender(ArrayList list) {
		PaybillDAO pdao = new PaybillDAO();
		JFrame f = new JFrame("결제 손님 성별정보 저장");
		f.setSize(500, 300);
		f.getContentPane().setLayout(null);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // x로 안꺼짐

		JButton buttonMan = new JButton("남자");
		buttonMan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < list.size(); i++) {	// 음료 종류 개수만큼 같은 pin번호로 paybill에 입력
					PaybillDTO pdto = (PaybillDTO) list.get(i);
					pdto.setGender("male");
					pdao.insertPaybill(pdto);
				}
				if (Main.eventDoit == 1) {
					if (Main.labelShowEvent.getText().equals("이벤트 모드 On") && Main.telForEvent != null) {
						Event eve = new Event();
					} else {
						Main.labelShowEvent.setText("이벤트 모드 On");
						Main.labelShowEvent.setForeground(new Color(0, 128, 0));
					}
				}
				f.setVisible(false);
			}
		});
		buttonMan.setBackground(new Color(30, 144, 255));
		buttonMan.setFont(new Font("굴림", Font.BOLD, 60));
		buttonMan.setBounds(0, 0, 235, 244);
		f.getContentPane().add(buttonMan);

		JButton buttonWoman = new JButton("여자");
		buttonWoman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < list.size(); i++) {
					PaybillDTO pdto = (PaybillDTO) list.get(i);
					pdto.setGender("female");
					pdao.insertPaybill(pdto);
				}
				if (Main.eventDoit == 1) {
					if (Main.labelShowEvent.getText().equals("이벤트 모드 On") && Main.telForEvent != null) {
						Event eve = new Event();
					} else {
						Main.labelShowEvent.setText("이벤트 모드 On");
						Main.labelShowEvent.setForeground(new Color(0, 128, 0));
					}
				}
				f.setVisible(false);
			}
		});
		buttonWoman.setBackground(new Color(255, 182, 193));
		buttonWoman.setFont(new Font("굴림", Font.BOLD, 60));
		buttonWoman.setBounds(243, 0, 235, 244);
		f.getContentPane().add(buttonWoman);

		f.setVisible(true);
	}

	public static void main(String[] args) {
	}
}