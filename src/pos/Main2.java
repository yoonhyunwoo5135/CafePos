package pos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import inven.Inven;
import inven.InvenDao;
import inven.InvenDto;
import login.Login;
import membership.MemberDao;
import membership.MemberDto;
import membership.Membership;
import paybill.PaybillDAO;
import paybill.PaybillDTO;
import statistic.Statistic;

public class Main2 {
	static MemberDao mdao = new MemberDao();
	static MemberDto mdto = new MemberDto();
	static PaybillDTO pdto = new PaybillDTO(); // 결제 내역 관리
	static PaybillDAO pdao = new PaybillDAO();
	static InvenDao idao = new InvenDao(); // 재고 관리
	static InvenDto idto = new InvenDto();
	private static JTable table;
	static DefaultTableModel tmodel;
	static DefaultTableCellRenderer dcr;
	static boolean finish = false;
	static int tableRow = 0; // 행 수 세는 변수
	static int finPrice = 0; // 최종 결제 금액
	static int selectText = 0;
	private static JTextField textFindMember;
	private static JTextField textShowName;
	private static JTextField textShowTel;
	private static JTextField textShowStamp;
	private static JTextField textTakemoney;
	private static JLabel labelDate;
	private static JLabel labelTime;

	public Main2() {
		CoffeeInfo espre = new CoffeeInfo("Espresso", 2500);
		CoffeeInfo ameri = new CoffeeInfo("Americano", 3000);
		CoffeeInfo latte = new CoffeeInfo("CaffeLatte", 3500);
		CoffeeInfo viena = new CoffeeInfo("VienaCoffee", 3500);
		CoffeeInfo choco = new CoffeeInfo("ChocoFrapuccino", 4000);

		JFrame f1 = new JFrame("카페 포스 시스템");
		f1.setTitle("카페POS프로그램 (Ver 1.01)");
		f1.getContentPane().setFont(new Font("굴림", Font.PLAIN, 19));
		f1.setSize(1080, 800);
		f1.getContentPane().setLayout(null);

		String[] col = { "번호", "메뉴", "단가", "수량", "가격", "쿠폰사용" }; // 열 목록

		tmodel = new DefaultTableModel(col, 0);
		dcr = new DefaultTableCellRenderer(); // 셀 다루는 객체 (체크박스 생성, 가운데 정렬)
		dcr.setHorizontalAlignment(SwingConstants.CENTER); //
		table = new JTable(tmodel);

		TableColumnModel tcm = table.getColumnModel(); // 테이블 가운데 정렬
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dcr); // table에서 컬럼을 불러온 뒤 셀의 속성을 설정
		}
		table.setRowHeight(40); // 행 높이 조절
		table.getColumnModel().getColumn(0).setPreferredWidth(5); // 1번째 열 넓이 조절
		// 체크박스로 쿠폰 사용여부 확인
		table.getColumn("쿠폰사용").setCellRenderer(dcr); // "쿠폰사용"열에 체크박스 넣기

		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("쿠폰사용").setCellEditor(new DefaultCellEditor(box));
		// 값 가져오기 table.getValueAt(행, 열)

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(27, 98, 598, 277);
		f1.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		JLabel la1 = new JLabel();
		ImageIcon icon = new ImageIcon("Mainpont.png");
		la1.setIcon(icon);
		la1.setBounds(27, 10, 300, 80);
		f1.getContentPane().add(la1);

		JButton buttonMembership = new JButton("\uBA64\uBC84\uC27D");
		buttonMembership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Membership mem = new Membership();
			}// 멤버쉽
		});
		buttonMembership.setFont(new Font("굴림", Font.BOLD, 18));
		buttonMembership.setBounds(340, 28, 95, 60);
		f1.getContentPane().add(buttonMembership);

		JButton buttonInven = new JButton("\uC7AC\uACE0");
		buttonInven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inven inven = new Inven();
			}// 재고
		});
		buttonInven.setFont(new Font("굴림", Font.BOLD, 18));
		buttonInven.setBounds(435, 28, 95, 60);
		f1.getContentPane().add(buttonInven);

		JButton buttonstatistic = new JButton("\uD1B5\uACC4");
		buttonstatistic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statistic stat = new Statistic();
			}// 통계
		});
		buttonstatistic.setFont(new Font("굴림", Font.BOLD, 18));
		buttonstatistic.setBounds(530, 28, 95, 60);
		f1.getContentPane().add(buttonstatistic);

		JButton buttonEspre = new JButton("에스프레소");
		buttonEspre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idto.setBean(idto.getBean() + 2); // 원두 2소모
				idto.setCup(idto.getCup() + 1); // 컵 1소모
				idto.setStraw(idto.getStraw() + 1); // 빨대 1소모
				if (espre.num == 0) { // 목록에 처음 올라갈 경우
					espre.row = tableRow++;
					Object[] obj = new Object[6];
					obj[0] = tableRow;
					obj[1] = espre.name;
					obj[2] = espre.price;
					obj[3] = ++espre.num;
					obj[4] = espre.price;
					obj[5] = false;
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj); // 행추가
				} else { // 같은 메뉴가 이미 목록에 올라와 있는 경우
					tmodel.setValueAt(++espre.num, espre.row, 3);
					tmodel.setValueAt(espre.price * espre.num, espre.row, 4);
				}
			}
		});
		buttonEspre.setFont(new Font("굴림", Font.BOLD, 15));
		buttonEspre.setBounds(642, 98, 130, 60);
		f1.getContentPane().add(buttonEspre);

		JButton buttonAmeri = new JButton("아메리카노");
		buttonAmeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idto.setBean(idto.getBean() + 2); // 원두 2소모
				idto.setCup(idto.getCup() + 1); // 컵 1소모
				idto.setStraw(idto.getStraw() + 1); // 빨대 1소모
				if (ameri.num == 0) { // 목록에 처음 올라갈 경우
					ameri.row = tableRow++;
					Object[] obj = new Object[6];
					obj[0] = tableRow;
					obj[1] = ameri.name;
					obj[2] = ameri.price;
					obj[3] = ++ameri.num;
					obj[4] = ameri.price;
					obj[5] = false;
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj); // 행추가
				} else {// 같은 메뉴가 이미 목록에 올라와 있는 경우
					tmodel.setValueAt(++ameri.num, ameri.row, 3);
					tmodel.setValueAt(ameri.price * ameri.num, ameri.row, 4);
				}
			}
		});
		buttonAmeri.setFont(new Font("굴림", Font.BOLD, 15));
		buttonAmeri.setBounds(771, 98, 130, 60);
		f1.getContentPane().add(buttonAmeri);

		JButton buttonLatte = new JButton("\uCE74\uD398\uB77C\uB5BC");
		buttonLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idto.setBean(idto.getBean() + 2); // 원두 2소모
				idto.setMilk(idto.getMilk() + 2); // 우유 2소모
				idto.setCup(idto.getCup() + 1); // 컵 1소모
				idto.setStraw(idto.getStraw() + 1); // 빨대 1소모
				if (latte.num == 0) { // 목록에 처음 올라갈 경우
					latte.row = tableRow++;
					Object[] obj = new Object[6];
					obj[0] = tableRow;
					obj[1] = latte.name;
					obj[2] = latte.price;
					obj[3] = ++latte.num;
					obj[4] = latte.price;
					obj[5] = false;
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj); // 행추가
				} else {// 같은 메뉴가 이미 목록에 올라와 있는 경우
					tmodel.setValueAt(++latte.num, latte.row, 3);
					tmodel.setValueAt(latte.price * latte.num, latte.row, 4);
				}
			}
		});
		buttonLatte.setFont(new Font("굴림", Font.BOLD, 15));
		buttonLatte.setBounds(900, 98, 130, 60);
		f1.getContentPane().add(buttonLatte);

		JButton buttonViena = new JButton("\uBE44\uC5D4\uB098\uCEE4\uD53C");
		buttonViena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idto.setBean(idto.getBean() + 2); // 원두 2소모
				idto.setCream(idto.getCream() + 2); // 크림 2소모
				idto.setCup(idto.getCup() + 1); // 컵 1소모
				idto.setStraw(idto.getStraw() + 1); // 빨대 1소모
				if (viena.num == 0) { // 목록에 처음 올라갈 경우
					viena.row = tableRow++;
					Object[] obj = new Object[6];
					obj[0] = tableRow;
					obj[1] = viena.name;
					obj[2] = viena.price;
					obj[3] = ++viena.num;
					obj[4] = viena.price;
					obj[5] = false;
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj); // 행추가
				} else {// 같은 메뉴가 이미 목록에 올라와 있는 경우
					tmodel.setValueAt(++viena.num, viena.row, 3);
					tmodel.setValueAt(viena.price * viena.num, viena.row, 4);
				}
			}
		});
		buttonViena.setFont(new Font("굴림", Font.BOLD, 15));
		buttonViena.setBounds(642, 158, 130, 60);
		f1.getContentPane().add(buttonViena);

		JButton buttonChoco = new JButton("\uCD08\uCF54\uD504\uB77C\uD478\uCE58\uB178");
		buttonChoco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idto.setBean(idto.getBean() + 2); // 원두 2소모
				idto.setMilk(idto.getMilk() + 1); // 우유 1소모
				idto.setChoco(idto.getChoco() + 2); // 초코 2소모
				idto.setCream(idto.getCream() + 1); // 크림 1소모
				idto.setCup(idto.getCup() + 1); // 컵 1소모
				idto.setStraw(idto.getStraw() + 1); // 빨대 1소모
				if (choco.num == 0) { // 목록에 처음 올라갈 경우
					choco.row = tableRow++;
					Object[] obj = new Object[6];
					obj[0] = tableRow;
					obj[1] = choco.name;
					obj[2] = choco.price;
					obj[3] = ++choco.num;
					obj[4] = choco.price;
					obj[5] = false;
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj); // 행추가
				} else {// 같은 메뉴가 이미 목록에 올라와 있는 경우
					tmodel.setValueAt(++choco.num, choco.row, 3);
					tmodel.setValueAt(choco.price * choco.num, choco.row, 4);
				}
			}
		});
		buttonChoco.setFont(new Font("굴림", Font.BOLD, 12));
		buttonChoco.setBounds(771, 158, 130, 60);
		f1.getContentPane().add(buttonChoco);

		// 임시로 쿠폰사용여부 값 가져오는 버튼
		JButton button_1 = new JButton();

		button_1.setFont(new Font("굴림", Font.BOLD, 14));
		button_1.setBounds(900, 158, 130, 60);
		f1.getContentPane().add(button_1);

		JButton button_2 = new JButton("");
		button_2.setFont(new Font("굴림", Font.BOLD, 26));
		button_2.setBounds(771, 218, 130, 60);
		f1.getContentPane().add(button_2);

		JButton buttonBack = new JButton("로그인 화면");
		buttonBack.setForeground(new Color(255, 255, 255));
		buttonBack.setBackground(new Color(0, 100, 0));
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int toLogin = JOptionPane.showConfirmDialog(null, "로그인 화면으로 돌아가겠습니까?");
				if (toLogin == 0) {	// '예' 눌렀을때 로그인 화면 실행
					Login log = new Login();
					f1.setVisible(false);
				}else {	// 그대로
					return;
				}
			}// 로그인 화면으로
		});
		buttonBack.setFont(new Font("굴림", Font.BOLD, 15));
		buttonBack.setBounds(642, 30, 130, 60);
		f1.getContentPane().add(buttonBack);

		// 메인메뉴 멤버쉽 패널
		//

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 205));
		panel.setBounds(27, 385, 294, 339);
		f1.getContentPane().add(panel);
		panel.setLayout(null);

		textFindMember = new JTextField();
		textFindMember.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				selectText = 0;
			}
		});
		textFindMember.setFont(new Font("굴림", Font.PLAIN, 18));
		textFindMember.setBounds(90, 15, 109, 35);
		panel.add(textFindMember);
		textFindMember.setColumns(10);

		JLabel label = new JLabel("전화번호");
		label.setFont(new Font("나눔고딕", Font.BOLD, 16));
		label.setBounds(22, 9, 73, 41);
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(22, 60, 249, 256);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel label_1 = new JLabel("회원명");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("굴림", Font.BOLD, 17));
		label_1.setBounds(12, 44, 81, 35);
		panel_1.add(label_1);

		textShowName = new JTextField();
		textShowName.setHorizontalAlignment(SwingConstants.CENTER);
		textShowName.setBackground(new Color(255, 255, 255));
		textShowName.setEditable(false);
		textShowName.setFont(new Font("굴림", Font.PLAIN, 16));
		textShowName.setBounds(105, 44, 132, 35);
		panel_1.add(textShowName);
		textShowName.setColumns(10);

		textShowTel = new JTextField();
		textShowTel.setHorizontalAlignment(SwingConstants.CENTER);
		textShowTel.setBackground(new Color(255, 255, 255));
		textShowTel.setEditable(false);
		textShowTel.setFont(new Font("굴림", Font.PLAIN, 16));
		textShowTel.setColumns(10);
		textShowTel.setBounds(105, 95, 132, 35);
		panel_1.add(textShowTel);

		textShowStamp = new JTextField();
		textShowStamp.setHorizontalAlignment(SwingConstants.CENTER);
		textShowStamp.setBackground(new Color(255, 255, 255));
		textShowStamp.setEditable(false);
		textShowStamp.setFont(new Font("굴림", Font.BOLD, 16));
		textShowStamp.setColumns(10);
		textShowStamp.setBounds(105, 142, 132, 35);
		panel_1.add(textShowStamp);

		JLabel label_2 = new JLabel("스탬프 수");
		label_2.setForeground(new Color(255, 0, 255));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("굴림", Font.BOLD, 17));
		label_2.setBounds(12, 142, 81, 35);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("전화번호");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("굴림", Font.BOLD, 17));
		label_3.setBounds(12, 95, 81, 35);
		panel_1.add(label_3);

		JLabel labelMemberAlert = new JLabel("* 손님에게 항상 친절하게 *");
		labelMemberAlert.setHorizontalAlignment(SwingConstants.CENTER);
		labelMemberAlert.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 16));
		labelMemberAlert.setBounds(0, 180, 249, 35);
		panel_1.add(labelMemberAlert);

		JButton buttonReset = new JButton("초기화");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFindMember.setText("");
				textShowName.setText("");
				textShowStamp.setText("");
				textShowTel.setText("");
				labelMemberAlert.setText("* 손님에게 항상 친절하게 *");
			}
		});
		buttonReset.setForeground(new Color(255, 255, 255));
		buttonReset.setBackground(new Color(105, 105, 105));
		buttonReset.setBounds(72, 216, 97, 30);
		panel_1.add(buttonReset);

		JLabel lblNewLabel_1 = new JLabel("-- 회원 정보 --");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(SystemColor.controlDkShadow);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1.setBounds(0, 0, 249, 39);
		panel_1.add(lblNewLabel_1);

		JButton buttonShowMember = new JButton("검색");
		buttonShowMember.setFont(new Font("굴림", Font.PLAIN, 13));
		buttonShowMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tel = textFindMember.getText();
				mdto = mdao.selectMemberTel(tel);

				textShowName.setText(mdto.getName());
				textShowTel.setText(mdto.getTel());
				textShowStamp.setText(String.valueOf(mdto.getStamp()));

				if (mdto.getStamp() < 10) {
					textShowStamp.setForeground(Color.red);
				} else {
					textShowStamp.setForeground(Color.black);
				}

				if (mdto.getTel() == "" || mdto.getTel() == null) {
					labelMemberAlert.setText("조회되지 않는 회원입니다.");
				} else {
					labelMemberAlert.setText("항상 감사한 " + mdto.getName() + " 회원님");
				}
			}
		});
		buttonShowMember.setBounds(211, 15, 60, 35);
		panel.add(buttonShowMember);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 222, 173));
		panel_2.setBounds(340, 385, 282, 339);
		f1.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 300, 90);
		panel_3.setBackground(new Color(255, 192, 203));
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		JLabel label_4 = new JLabel("총 수량");
		label_4.setFont(new Font("굴림", Font.BOLD, 20));
		label_4.setBounds(12, 10, 125, 70);
		panel_3.add(label_4);

		JLabel labelShowNum = new JLabel("");
		labelShowNum.setHorizontalAlignment(SwingConstants.RIGHT);
		labelShowNum.setForeground(new Color(105, 105, 105));
		labelShowNum.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 21));
		labelShowNum.setBounds(126, 10, 142, 70);
		panel_3.add(labelShowNum);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 177, 300, 90);
		panel_5.setBackground(new Color(255, 192, 203));
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel label_5 = new JLabel("할인 금액");
		label_5.setFont(new Font("굴림", Font.BOLD, 20));
		label_5.setBounds(12, 15, 102, 60);
		panel_5.add(label_5);

		JLabel labelShowDiscount = new JLabel("");
		labelShowDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
		labelShowDiscount.setForeground(new Color(153, 0, 0));
		labelShowDiscount.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 21));
		labelShowDiscount.setBounds(126, 15, 142, 60);
		panel_5.add(labelShowDiscount);

		JLabel lblNewLabel = new JLabel("총 금액");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 100, 87, 67);
		panel_2.add(lblNewLabel);

		JLabel label_6 = new JLabel("받은 금액");
		label_6.setFont(new Font("굴림", Font.BOLD, 20));
		label_6.setBounds(12, 268, 100, 71);
		panel_2.add(label_6);

		JLabel labelShowTotalPrice = new JLabel("");
		labelShowTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		labelShowTotalPrice.setForeground(SystemColor.controlDkShadow);
		labelShowTotalPrice.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 21));
		labelShowTotalPrice.setBounds(128, 107, 142, 60);
		panel_2.add(labelShowTotalPrice);

		textTakemoney = new JTextField();
		textTakemoney.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				selectText = 1;
			}
		});
		textTakemoney.setFont(new Font("굴림", Font.PLAIN, 13));
		textTakemoney.setHorizontalAlignment(SwingConstants.CENTER);
		textTakemoney.setBounds(114, 288, 92, 35);
		panel_2.add(textTakemoney);
		textTakemoney.setColumns(10);

		JLabel labelFinPrice = new JLabel("");
		labelFinPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFinPrice.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 35));
		labelFinPrice.setForeground(new Color(147, 112, 219));
		labelFinPrice.setBounds(129, 10, 232, 83);

		JButton buttonSelectFinish = new JButton("선택완료");
		buttonSelectFinish.setForeground(new Color(255, 255, 255));
		buttonSelectFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 총 수량 출력
				int totalNum = 0;
				for (int i = 0; i < table.getRowCount(); i++)
					totalNum += (int) table.getValueAt(i, 3);
				labelShowNum.setText(String.valueOf(totalNum) + " 잔");

				// 총 금액 출력
				int totalPrice = 0;
				for (int i = 0; i < table.getRowCount(); i++)
					totalPrice += (int) table.getValueAt(i, 4);
				labelShowTotalPrice.setText(String.valueOf(totalPrice) + " 원");

				// 쿠폰 사용시 할인금액 적용 및 출력
				int discountPrice = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					if ((boolean) table.getValueAt(i, 5) == true)
						discountPrice += (int) table.getValueAt(i, 2);
				}
				labelShowDiscount.setText(String.valueOf(discountPrice) + " 원");

				// 총 금액 - 할인금액을 적용한 받을 금액 출력
				finPrice = totalPrice - discountPrice;
				labelFinPrice.setText(String.valueOf(finPrice) + " 원");
			}
		});
		buttonSelectFinish.setBackground(new Color(0, 102, 204));
		buttonSelectFinish.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 21));
		buttonSelectFinish.setBounds(642, 288, 125, 85);
		f1.getContentPane().add(buttonSelectFinish);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(127, 255, 212));
		panel_4.setBounds(642, 561, 386, 163);
		f1.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 103, 386, 60);
		panel_4.add(panel_6);
		panel_6.setBackground(new Color(255, 248, 220));
		panel_6.setLayout(null);

		JLabel label_8 = new JLabel("거스름 돈");
		label_8.setFont(new Font("굴림", Font.BOLD, 13));
		label_8.setBounds(23, 0, 102, 60);
		panel_6.add(label_8);

		JLabel labelShowChange = new JLabel("");
		labelShowChange.setHorizontalAlignment(SwingConstants.RIGHT);
		labelShowChange.setBounds(84, 17, 66, 27);
		panel_6.add(labelShowChange);

		JButton buttonShowChange = new JButton("입력");
		buttonShowChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int takeMoney = Integer.parseInt(textTakemoney.getText());
				int change = takeMoney - finPrice;
				labelShowChange.setText(String.valueOf(change) + " 원");
			}
		});
		buttonShowChange.setFont(new Font("굴림", Font.PLAIN, 12));
		buttonShowChange.setBounds(212, 288, 58, 36);
		panel_2.add(buttonShowChange);

		JLabel label_7 = new JLabel("받을 금액");
		label_7.setBounds(24, 10, 131, 83);
		panel_4.add(label_7);
		label_7.setFont(new Font("나눔고딕", Font.BOLD, 23));

		panel_4.add(labelFinPrice);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		panel_7.setBounds(771, 288, 257, 262);
		f1.getContentPane().add(panel_7);
		panel_7.setLayout(null);

		/*
		 * 화상키패드가 입력될 곳 선택 창
		 */
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		panel_8.setBounds(642, 387, 130, 163);
		f1.getContentPane().add(panel_8);
		panel_8.setLayout(null);

		ButtonGroup bg = new ButtonGroup();

		/*
		 * 화상키패드
		 */
		JButton b0 = new JButton("0");
		b0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("0"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("0"));
				}
			}
		});
		b0.setBackground(new Color(169, 169, 169));
		b0.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b0.setBounds(12, 191, 72, 56);
		panel_7.add(b0);

		JButton b1 = new JButton("1");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("1"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("1"));
				}
			}
		});
		b1.setBackground(new Color(169, 169, 169));
		b1.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b1.setBounds(12, 130, 72, 56);
		panel_7.add(b1);

		JButton b2 = new JButton("2");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("2"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("2"));
				}
			}
		});
		b2.setBackground(new Color(169, 169, 169));
		b2.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b2.setBounds(89, 130, 72, 56);
		panel_7.add(b2);

		JButton b3 = new JButton("3");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("3"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("3"));
				}
			}
		});
		b3.setBackground(new Color(169, 169, 169));
		b3.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b3.setBounds(167, 130, 72, 56);
		panel_7.add(b3);

		JButton b4 = new JButton("4");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("4"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("4"));
				}
			}
		});
		b4.setBackground(new Color(169, 169, 169));
		b4.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b4.setBounds(12, 70, 72, 56);
		panel_7.add(b4);

		JButton b5 = new JButton("5");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("5"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("5"));
				}
			}
		});
		b5.setBackground(new Color(169, 169, 169));
		b5.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b5.setBounds(89, 70, 72, 56);
		panel_7.add(b5);

		JButton b6 = new JButton("6");
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("6"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("6"));
				}
			}
		});
		b6.setBackground(new Color(169, 169, 169));
		b6.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b6.setBounds(167, 70, 72, 56);
		panel_7.add(b6);

		JButton b7 = new JButton("7");
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("7"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("7"));
				}
			}
		});
		b7.setBackground(new Color(169, 169, 169));
		b7.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b7.setBounds(12, 10, 72, 56);
		panel_7.add(b7);

		JButton b8 = new JButton("8");
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("8"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("8"));
				}
			}
		});
		b8.setBackground(new Color(169, 169, 169));
		b8.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b8.setBounds(89, 10, 72, 56);
		panel_7.add(b8);

		JButton b9 = new JButton("9");
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("9"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("9"));
				}
			}
		});
		b9.setBackground(new Color(169, 169, 169));
		b9.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b9.setBounds(167, 10, 72, 56);
		panel_7.add(b9);

		JButton b10 = new JButton("00");
		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					textFindMember.setText(textFindMember.getText().concat("00"));
				} else {
					textTakemoney.setText(textTakemoney.getText().concat("00"));
				}
			}
		});
		b10.setBackground(new Color(169, 169, 169));
		b10.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b10.setBounds(89, 191, 72, 56);
		panel_7.add(b10);

		JButton b11 = new JButton("<-");
		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str;
				if (selectText == 0) {
					str = textFindMember.getText();
					if (str.trim().equals("") || str == null) // str에 값이 없을때 그냥 메소드 종료
						return;
					textFindMember.setText(str.substring(0, str.length() - 1));
				} else {
					str = textTakemoney.getText();
					if (str.trim().equals("") || str == null) // str에 값이 없을때 그냥 메소드 종료
						return;
					textTakemoney.setText(str.substring(0, str.length() - 1));
				}
			}
		});
		b11.setForeground(new Color(255, 0, 0));
		b11.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 21));
		b11.setBackground(SystemColor.controlHighlight);
		b11.setBounds(167, 191, 72, 56);
		panel_7.add(b11);

		JButton buttonEnter = new JButton("검색 / 입력");
		buttonEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					buttonShowMember.doClick();
				} else {
					buttonShowChange.doClick();
				}
			}
		});
		buttonEnter.setForeground(new Color(255, 255, 255));
		buttonEnter.setBackground(new Color(105, 105, 105));
		buttonEnter.setFont(new Font("굴림", Font.BOLD, 14));
		buttonEnter.setBounds(12, 25, 116, 56);
		panel_8.add(buttonEnter);

		JButton btnAc = new JButton("AC");
		btnAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectText == 0) {
					textFindMember.setText("");
				} else {
					textTakemoney.setText("");
				}
			}
		});
		btnAc.setForeground(Color.RED);
		btnAc.setFont(new Font("굴림", Font.BOLD, 23));
		btnAc.setBackground(new Color(153, 153, 153));
		btnAc.setBounds(12, 91, 116, 56);
		panel_8.add(btnAc);
		// 멤버쉽 패널 끝

		/*
		 * 전체삭제 버튼
		 */
		JButton buttonAllDelete = new JButton("목록삭제");
		buttonAllDelete.setForeground(new Color(220, 20, 60));
		buttonAllDelete.setBackground(new Color(255, 255, 51));
		buttonAllDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = tableRow - 1; i >= 0; i--) {
					tmodel.removeRow(i);
				}
				tableRow = 0;
				espre.num = 0;
				ameri.num = 0;
				latte.num = 0;
				viena.num = 0;
				choco.num = 0;
				labelShowChange.setText("");
				labelShowDiscount.setText("");
				labelShowNum.setText("");
				labelShowTotalPrice.setText("");
				labelFinPrice.setText("");
			}
		});
		buttonAllDelete.setFont(new Font("굴림", Font.BOLD, 19));
		buttonAllDelete.setBounds(642, 223, 125, 55);
		f1.getContentPane().add(buttonAllDelete);

		/*
		 * 결제 버튼
		 */

		JButton buttonCredit = new JButton("결제하기 \\");
		buttonCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (labelShowNum.getText().trim().equals("0 잔")) // '0 잔'을 결제하려고 할 때
					return;

				if (labelFinPrice.getText() == null || labelFinPrice.getText().equals("")) { // 선택완료 버튼을 누르지 않고 결제 진행 시
					NoFinPrice nof = new NoFinPrice();
					return;
				}

				InvenDto idto2 = idao.list();
				if (idto2.getBean() - idto.getBean() < 0 || idto2.getMilk() - idto.getMilk() < 0
						|| idto2.getChoco() - idto.getBean() < 0 || idto2.getCream() - idto.getCream() < 0
						|| idto2.getCup() - idto.getCup() < 0 || idto2.getStraw() - idto.getStraw() < 0) {
					NotEnoughInven nei = new NotEnoughInven();
					return;
				}
				idao.use(idto); // db에 있는 재고 소모시킴
				idto = new InvenDto(0, 0, 0, 0, 0, 0); // 모두 0값으로 초기화

				// 멤버쉽 사용 DB 연동
				String tel = textShowTel.getText();
				if (tel != null || !(tel.trim().equals(""))) { // 메인에서 멤버쉽 조회를 한 경우만 실행
					mdto = mdao.selectMemberTel(tel);
					int couponCnt = 0;
					for (int i = 0; i < table.getRowCount(); i++) {
						if ((boolean) table.getValueAt(i, 5) == true)
							++couponCnt;
					}
					if (couponCnt != 0) { // 쿠폰사용
						if (tel == null || tel.trim().equals("")) { // 할인금액을 적용했는데 정작 멤버쉽 조회는 안한경우
							NoMembership no = new NoMembership();
							return;
						} else if (mdto.getStamp() < couponCnt * 10) { // 쿠폰 사용하기에 스탬프 수 부족할 경우 메시지
							NotEnoughStamp not = new NotEnoughStamp();
							return;
						} else {
							mdao.UseStamp(mdto.getTel(), couponCnt);
						}
					} else { // 쿠폰 사용 안할 시 총 수량만큼 스탬프 적립
						int cnt = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							cnt += (int) table.getValueAt(i, 3);
						}
						mdao.plusStamp(mdto.getTel(), cnt);
					}

				} // 멤버쉽 조회한 경우 끝

				// 메뉴창 초기화 + ArrayList에 paybill들 저장
				int lastpin = pdao.selectLastPin();
				ArrayList payList = new ArrayList();
				for (int i = tableRow - 1; i >= 0; i--) {
					pdto = new PaybillDTO();
					pdto.setPin(lastpin + 1);
					pdto.setMenu((String) table.getValueAt(i, 1));
					pdto.setPrice((int) table.getValueAt(i, 2));
					pdto.setQuantity((int) table.getValueAt(i, 3));
					pdto.setTot_price((int) table.getValueAt(i, 4));
					pdto.setCuppon(((boolean) table.getValueAt(i, 5) ? "o" : "x")); // true면 o 아니면 x 반환
					payList.add(pdto);
					tmodel.removeRow(i);
				}
				/*
				 * 최종 paybill에 insert는 SelectGender에서 이루어진다. 바로위에서 'gender값을 제외하고 생성한 payList'를
				 * SelectGender로 건네고 SelectGender에서 비어있는 gender값을 체운뒤 db에 insert
				 */
				SelectGender sg = new SelectGender(payList);

				tableRow = 0;
				espre.num = 0;
				ameri.num = 0;
				latte.num = 0;
				viena.num = 0;
				choco.num = 0;
				labelShowChange.setText("");
				labelShowDiscount.setText("");
				labelShowNum.setText("");
				labelShowTotalPrice.setText("");
				labelFinPrice.setText("");
				textFindMember.setText("");
				textTakemoney.setText("");
				textShowName.setText("");
				textShowStamp.setText("");
				textShowTel.setText("");
				labelMemberAlert.setText("* 손님에게 항상 친절하게 *");
				selectText = 0; // 전화번호 자동 입력으로 기본 세팅
			}
		});
		buttonCredit.setFont(new Font("굴림", Font.BOLD, 26));
		buttonCredit.setBackground(new Color(153, 0, 0));
		buttonCredit.setForeground(new Color(255, 255, 255));
		buttonCredit.setBounds(190, 0, 196, 60);
		panel_6.add(buttonCredit);

		Timer timer = new Timer();
		timer.schedule(new MakeTime(), 0, 1000);
		// 호출 객체, 지연시간, 호출간격

		labelDate = new JLabel("New label"); // 우측 상단 날짜 라벨
		labelDate.setForeground(new Color(128, 128, 128));
		labelDate.setFont(new Font("굴림", Font.BOLD, 15));
		labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDate.setBounds(756, 72, 273, 18);
		f1.getContentPane().add(labelDate);

		labelTime = new JLabel("New label");
		labelTime.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTime.setForeground(Color.GRAY);
		labelTime.setFont(new Font("굴림", Font.BOLD, 15));
		labelTime.setBounds(756, 51, 273, 18);
		f1.getContentPane().add(labelTime);

		JButton button_3 = new JButton("");
		button_3.setFont(new Font("굴림", Font.BOLD, 26));
		button_3.setBounds(900, 218, 130, 60);
		f1.getContentPane().add(button_3);

		f1.setVisible(true);
	}

	class MakeTime extends TimerTask { // 실시간 시계를 정의하는 클래스

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Calendar date = Calendar.getInstance();
			SimpleDateFormat sdf1_1 = new SimpleDateFormat("yyyy년 M월 d일 E요일");
			SimpleDateFormat sdf1_2 = new SimpleDateFormat("yyyy년 M월 dd일 E요일");
			SimpleDateFormat sdf2_1 = new SimpleDateFormat("yyyy년 MM월 d일 E요일");
			SimpleDateFormat sdf2_2 = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
			SimpleDateFormat sdf3 = new SimpleDateFormat("HH시 mm분 ss초");
			if (date.MONTH >= 10) {
				if (date.MINUTE >= 10)
					labelDate.setText(sdf2_2.format(date.getTime()));
				else
					labelDate.setText(sdf2_1.format(date.getTime()));
			} else {
				if (date.MINUTE >= 10)
					labelDate.setText(sdf1_2.format(date.getTime()));
				else
					labelDate.setText(sdf1_1.format(date.getTime()));
			}
			labelTime.setText(sdf3.format(date.getTime()));
		}

	}

	public static void main(String[] args) throws Exception {
		Main2 main = new Main2();

	}// main end
}// class end
