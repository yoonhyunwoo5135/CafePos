package event;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import membership.MemberDao;
import pos.Main;

public class Event extends JFrame {
	static Random rand = new Random();
	MemberDao mdao = new MemberDao();
	static int selected = 0;
	JButton buttonBox1;
	JButton buttonBox2;
	JButton buttonBox3;
	JButton buttonBox4;
	JButton buttonBox5;
	JButton buttonBox6;
	JButton buttonBox7;
	JButton buttonBox8;

	public Event() {
		setTitle("이벤트 창 (종료 버튼으로 종료)");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // x로 안꺼짐
		Random rand = new Random();
		int[] num = new int[8];
		for (int i = 0; i < 8; i++) {
			num[i] = rand.nextInt(8);
			for (int j = 0; j <= i; j++) // 현재 발생시킨 지점까지 검색해서 같은수 비교
			{
				if (num[i] == num[j] && j != i) {
					i = i - 1; // 같은수 있으면 i하나 감소해서 다시 발생
				}
			}
		}

		for (int n : num) {
			System.out.println(n);
		}
		System.out.println(Main.telForEvent);
		setSize(518, 396);
		getContentPane().setBackground(new Color(204, 255, 255));
		getContentPane().setLayout(null);

		JLabel labelTitle = new JLabel("와 ! 랜 덤 박 스 !");
		labelTitle.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 31));
		labelTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTitle.setBounds(0, 0, 381, 65);
		getContentPane().add(labelTitle);

		ImageIcon icon_search = new ImageIcon("search.jpg");
		ImageIcon icon_present = new ImageIcon("present.jpg");
		ImageIcon icon_explosion = new ImageIcon("explosion.png");
		ImageIcon icon_oneFinger = new ImageIcon("oneFinger.jpg");
		ImageIcon icon_twoFingers = new ImageIcon("twoFingers.jpg");
		ImageIcon icon_present_no = new ImageIcon("present_no.jpg");
		ImageIcon icon_explosion_no = new ImageIcon("explosion_no.png");
		ImageIcon icon_oneFinger_no = new ImageIcon("oneFinger_no.jpg");
		ImageIcon icon_twoFingers_no = new ImageIcon("twoFingers_no.jpg");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(17, 67, 462, 31);
		getContentPane().add(panel);

		JLabel labelShowMessage = new JLabel("8개의 버튼 중 하나를 선택해주세요...당신의 행운은?!");
		panel.add(labelShowMessage);
		labelShowMessage.setFont(new Font("굴림", Font.BOLD, 15));
		labelShowMessage.setHorizontalAlignment(SwingConstants.CENTER);

		buttonBox1 = new JButton();
		buttonBox1.setIcon(icon_search);
		buttonBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selected == 0) {
					if (num[0] == 0) {
						buttonBox1.setIcon(icon_present);
						labelShowMessage.setText("*** 음료 쿠폰 1장(스탬프 10개)에 당첨되셨습니다 ! ***");
						mdao.plusStamp(Main.telForEvent, 10);
					} else if (num[0] == 1) {
						buttonBox1.setIcon(icon_oneFinger);
						labelShowMessage.setText("* 스탬프 1개 추가 적립에 당첨되셨습니다 ! *");
						mdao.plusStamp(Main.telForEvent, 1);
					} else if (num[0] == 2) {
						buttonBox1.setIcon(icon_twoFingers);
						labelShowMessage.setText("** 스탬프 2개 추가 적립에 당첨되셨습니다 ! **");
						mdao.plusStamp(Main.telForEvent, 2);
					} else {
						buttonBox1.setIcon(icon_explosion);
						labelShowMessage.setText("꽝...다음 기회에 다시 도전해주세요 !");
					}
					selected = 1;

					buttonBox2.doClick();
					buttonBox3.doClick();
					buttonBox4.doClick();
					buttonBox5.doClick();
					buttonBox6.doClick();
					buttonBox7.doClick();
					buttonBox8.doClick();
				} else {
					if (num[0] == 0) {
						buttonBox1.setIcon(icon_present_no);
					} else if (num[0] == 1) {
						buttonBox1.setIcon(icon_oneFinger_no);
					} else if (num[0] == 2) {
						buttonBox1.setIcon(icon_twoFingers_no);
					} else {
						buttonBox1.setIcon(icon_explosion_no);
					}
				}

			}
		});

		buttonBox1.setBounds(12, 113, 110, 110);
		getContentPane().add(buttonBox1);

		buttonBox2 = new JButton();
		buttonBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selected == 0) {
					if (num[1] == 0) {
						buttonBox2.setIcon(icon_present);
						labelShowMessage.setText("*** 음료 쿠폰 1장(스탬프 10개)에 당첨되셨습니다 ! ***");
						mdao.plusStamp(Main.telForEvent, 10);
					} else if (num[1] == 1) {
						buttonBox2.setIcon(icon_oneFinger);
						labelShowMessage.setText("* 스탬프 1개 추가 적립에 당첨되셨습니다 ! *");
						mdao.plusStamp(Main.telForEvent, 1);
					} else if (num[1] == 2) {
						buttonBox2.setIcon(icon_twoFingers);
						labelShowMessage.setText("** 스탬프 2개 추가 적립에 당첨되셨습니다 ! **");
						mdao.plusStamp(Main.telForEvent, 2);
					} else {
						buttonBox2.setIcon(icon_explosion);
						labelShowMessage.setText("꽝...다음 기회에 다시 도전해주세요 !");
					}
					selected = 1;
					buttonBox1.doClick();
					buttonBox3.doClick();
					buttonBox4.doClick();
					buttonBox5.doClick();
					buttonBox6.doClick();
					buttonBox7.doClick();
					buttonBox8.doClick();

				} else {
					if (num[1] == 0) {
						buttonBox2.setIcon(icon_present_no);
					} else if (num[1] == 1) {
						buttonBox2.setIcon(icon_oneFinger_no);
					} else if (num[1] == 2) {
						buttonBox2.setIcon(icon_twoFingers_no);
					} else {
						buttonBox2.setIcon(icon_explosion_no);
					}
				}

			}
		});
		buttonBox2.setIcon(icon_search);
		buttonBox2.setBounds(134, 113, 110, 110);
		getContentPane().add(buttonBox2);

		buttonBox3 = new JButton();
		buttonBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected == 0) {
					if (num[2] == 0) {
						buttonBox3.setIcon(icon_present);
						labelShowMessage.setText("*** 음료 쿠폰 1장(스탬프 10개)에 당첨되셨습니다 ! ***");
						mdao.plusStamp(Main.telForEvent, 10);
					} else if (num[2] == 1) {
						buttonBox3.setIcon(icon_oneFinger);
						labelShowMessage.setText("* 스탬프 1개 추가 적립에 당첨되셨습니다 ! *");
						mdao.plusStamp(Main.telForEvent, 1);
					} else if (num[2] == 2) {
						buttonBox3.setIcon(icon_twoFingers);
						labelShowMessage.setText("** 스탬프 2개 추가 적립에 당첨되셨습니다 ! **");
						mdao.plusStamp(Main.telForEvent, 2);
					} else {
						buttonBox3.setIcon(icon_explosion);
						labelShowMessage.setText("꽝...다음 기회에 다시 도전해주세요 !");
					}
					selected = 1;

					buttonBox1.doClick();
					buttonBox2.doClick();
					buttonBox4.doClick();
					buttonBox5.doClick();
					buttonBox6.doClick();
					buttonBox7.doClick();
					buttonBox8.doClick();
				} else {
					if (num[2] == 0) {
						buttonBox3.setIcon(icon_present_no);
					} else if (num[2] == 1) {
						buttonBox3.setIcon(icon_oneFinger_no);
					} else if (num[2] == 2) {
						buttonBox3.setIcon(icon_twoFingers_no);
					} else {
						buttonBox3.setIcon(icon_explosion_no);
					}
				}

			}
		});
		buttonBox3.setIcon(icon_search);
		buttonBox3.setBounds(256, 113, 110, 110);
		getContentPane().add(buttonBox3);

		buttonBox4 = new JButton();
		buttonBox4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected == 0) {
					if (num[3] == 0) {
						buttonBox4.setIcon(icon_present);
						labelShowMessage.setText("*** 음료 쿠폰 1장(스탬프 10개)에 당첨되셨습니다 ! ***");
						mdao.plusStamp(Main.telForEvent, 10);
					} else if (num[3] == 1) {
						buttonBox4.setIcon(icon_oneFinger);
						labelShowMessage.setText("* 스탬프 1개 추가 적립에 당첨되셨습니다 ! *");
						mdao.plusStamp(Main.telForEvent, 1);
					} else if (num[3] == 2) {
						buttonBox4.setIcon(icon_twoFingers);
						labelShowMessage.setText("** 스탬프 2개 추가 적립에 당첨되셨습니다 ! **");
						mdao.plusStamp(Main.telForEvent, 2);
					} else {
						buttonBox4.setIcon(icon_explosion);
						labelShowMessage.setText("꽝...다음 기회에 다시 도전해주세요 !");
					}

					selected = 1;
					buttonBox1.doClick();
					buttonBox2.doClick();
					buttonBox3.doClick();
					buttonBox5.doClick();
					buttonBox6.doClick();
					buttonBox7.doClick();
					buttonBox8.doClick();
				} else {
					if (num[3] == 0) {
						buttonBox4.setIcon(icon_present_no);
					} else if (num[3] == 1) {
						buttonBox4.setIcon(icon_oneFinger_no);
					} else if (num[3] == 2) {
						buttonBox4.setIcon(icon_twoFingers_no);
					} else {
						buttonBox4.setIcon(icon_explosion_no);
					}
				}

			}
		});
		buttonBox4.setIcon(icon_search);
		buttonBox4.setBounds(378, 113, 110, 110);
		getContentPane().add(buttonBox4);

		buttonBox5 = new JButton();
		buttonBox5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected == 0) {
					if (num[4] == 0) {
						buttonBox5.setIcon(icon_present);
						labelShowMessage.setText("*** 음료 쿠폰 1장(스탬프 10개)에 당첨되셨습니다 ! ***");
						mdao.plusStamp(Main.telForEvent, 10);
					} else if (num[4] == 1) {
						buttonBox5.setIcon(icon_oneFinger);
						labelShowMessage.setText("* 스탬프 1개 추가 적립에 당첨되셨습니다 ! *");
						mdao.plusStamp(Main.telForEvent, 1);
					} else if (num[4] == 2) {
						buttonBox5.setIcon(icon_twoFingers);
						labelShowMessage.setText("** 스탬프 2개 추가 적립에 당첨되셨습니다 ! **");
						mdao.plusStamp(Main.telForEvent, 2);
					} else {
						buttonBox5.setIcon(icon_explosion);
						labelShowMessage.setText("꽝...다음 기회에 다시 도전해주세요 !");
					}
					selected = 1;
					buttonBox1.doClick();
					buttonBox2.doClick();
					buttonBox3.doClick();
					buttonBox4.doClick();
					buttonBox6.doClick();
					buttonBox7.doClick();
					buttonBox8.doClick();
				} else {
					if (num[4] == 0) {
						buttonBox5.setIcon(icon_present_no);
					} else if (num[4] == 1) {
						buttonBox5.setIcon(icon_oneFinger_no);
					} else if (num[4] == 2) {
						buttonBox5.setIcon(icon_twoFingers_no);
					} else {
						buttonBox5.setIcon(icon_explosion_no);
					}
				}

			}
		});
		buttonBox5.setIcon(icon_search);
		buttonBox5.setBounds(12, 233, 110, 110);
		getContentPane().add(buttonBox5);

		buttonBox6 = new JButton();
		buttonBox6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected == 0) {
					if (num[5] == 0) {
						buttonBox6.setIcon(icon_present);
						labelShowMessage.setText("*** 음료 쿠폰 1장(스탬프 10개)에 당첨되셨습니다 ! ***");
						mdao.plusStamp(Main.telForEvent, 10);
					} else if (num[5] == 1) {
						buttonBox6.setIcon(icon_oneFinger);
						labelShowMessage.setText("* 스탬프 1개 추가 적립에 당첨되셨습니다 ! *");
						mdao.plusStamp(Main.telForEvent, 1);
					} else if (num[5] == 2) {
						buttonBox6.setIcon(icon_twoFingers);
						labelShowMessage.setText("** 스탬프 2개 추가 적립에 당첨되셨습니다 ! **");
						mdao.plusStamp(Main.telForEvent, 2);
					} else {
						buttonBox6.setIcon(icon_explosion);
						labelShowMessage.setText("꽝...다음 기회에 다시 도전해주세요 !");
					}
					selected = 1;
					buttonBox1.doClick();
					buttonBox2.doClick();
					buttonBox3.doClick();
					buttonBox4.doClick();
					buttonBox5.doClick();
					buttonBox7.doClick();
					buttonBox8.doClick();
				} else {
					if (num[5] == 0) {
						buttonBox6.setIcon(icon_present_no);
					} else if (num[5] == 1) {
						buttonBox6.setIcon(icon_oneFinger_no);
					} else if (num[5] == 2) {
						buttonBox6.setIcon(icon_twoFingers_no);
					} else {
						buttonBox6.setIcon(icon_explosion_no);
					}
				}

			}
		});
		buttonBox6.setIcon(icon_search);
		buttonBox6.setBounds(134, 233, 110, 110);
		getContentPane().add(buttonBox6);

		buttonBox7 = new JButton();
		buttonBox7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected == 0) {
					if (num[6] == 0) {
						buttonBox7.setIcon(icon_present);
						labelShowMessage.setText("*** 음료 쿠폰 1장(스탬프 10개)에 당첨되셨습니다 ! ***");
						mdao.plusStamp(Main.telForEvent, 10);
					} else if (num[6] == 1) {
						buttonBox7.setIcon(icon_oneFinger);
						labelShowMessage.setText("* 스탬프 1개 추가 적립에 당첨되셨습니다 ! *");
						mdao.plusStamp(Main.telForEvent, 1);
					} else if (num[6] == 2) {
						buttonBox7.setIcon(icon_twoFingers);
						labelShowMessage.setText("** 스탬프 2개 추가 적립에 당첨되셨습니다 ! **");
						mdao.plusStamp(Main.telForEvent, 2);
					} else {
						buttonBox7.setIcon(icon_explosion);
						labelShowMessage.setText("꽝...다음 기회에 다시 도전해주세요 !");
					}
					selected = 1;
					buttonBox1.doClick();
					buttonBox2.doClick();
					buttonBox3.doClick();
					buttonBox4.doClick();
					buttonBox5.doClick();
					buttonBox6.doClick();
					buttonBox8.doClick();
				} else {
					if (num[6] == 0) {
						buttonBox7.setIcon(icon_present_no);
					} else if (num[6] == 1) {
						buttonBox7.setIcon(icon_oneFinger_no);
					} else if (num[6] == 2) {
						buttonBox7.setIcon(icon_twoFingers_no);
					} else {
						buttonBox7.setIcon(icon_explosion_no);
					}
				}

			}
		});
		buttonBox7.setIcon(icon_search);
		buttonBox7.setBounds(256, 233, 110, 110);
		getContentPane().add(buttonBox7);

		buttonBox8 = new JButton();
		buttonBox8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected == 0) {
					if (num[7] == 0) {
						buttonBox8.setIcon(icon_present);
						labelShowMessage.setText("*** 음료 쿠폰 1장(스탬프 10개)에 당첨되셨습니다 ! ***");
						mdao.plusStamp(Main.telForEvent, 10);
					} else if (num[7] == 1) {
						buttonBox8.setIcon(icon_oneFinger);
						labelShowMessage.setText("* 스탬프 1개 추가 적립에 당첨되셨습니다 ! *");
						mdao.plusStamp(Main.telForEvent, 1);
					} else if (num[7] == 2) {
						buttonBox8.setIcon(icon_twoFingers);
						labelShowMessage.setText("** 스탬프 2개 추가 적립에 당첨되셨습니다 ! **");
						mdao.plusStamp(Main.telForEvent, 2);
					} else {
						buttonBox8.setIcon(icon_explosion);
						labelShowMessage.setText("꽝...다음 기회에 다시 도전해주세요 !");
					}
					selected = 1;
					buttonBox1.doClick();
					buttonBox2.doClick();
					buttonBox3.doClick();
					buttonBox4.doClick();
					buttonBox5.doClick();
					buttonBox6.doClick();
					buttonBox7.doClick();
				} else {
					if (num[7] == 0) {
						buttonBox8.setIcon(icon_present_no);
					} else if (num[7] == 1) {
						buttonBox8.setIcon(icon_oneFinger_no);
					} else if (num[7] == 2) {
						buttonBox8.setIcon(icon_twoFingers_no);
					} else {
						buttonBox8.setIcon(icon_explosion_no);
					}
				}

			}
		});
		buttonBox8.setIcon(icon_search);
		buttonBox8.setBounds(378, 233, 110, 110);
		getContentPane().add(buttonBox8);
		
		JButton buttonExit = new JButton("종료");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected == 1) {	// 버튼을 눌러서 랜덤박스를 열었을 경우
					mdao.joinEvent(Main.telForEvent);// 해당 멤버쉽 db에 이벤트 참여하였음을 입력
				}
				Main.telForEvent = null;
				selected = 0;	// 다음 랜덤박스 실행때를 위해 0으로 초기화
				setVisible(false);
			}
		});
		buttonExit.setFont(new Font("굴림", Font.BOLD, 19));
		buttonExit.setForeground(new Color(255, 255, 255));
		buttonExit.setBackground(new Color(255, 20, 147));
		buttonExit.setBounds(398, 21, 81, 31);
		getContentPane().add(buttonExit);

		setVisible(true);
	}

	public static void main(String[] args) {
	}
}
