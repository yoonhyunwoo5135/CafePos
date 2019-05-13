package pos;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import inven.Inven;
import membership.MemberDao;
import membership.MemberDto;
import membership.Membership;
import statistic.Statistic;


public class Main {
	static MemberDao mdao = new MemberDao();
	static MemberDto mdto = new MemberDto();
	private static JTable table;
	static DefaultTableModel tmodel;
	static DefaultTableCellRenderer dcr;
	static boolean finish = false;
	static int tableRow = 0; // �� �� ���� ����
	static int finPrice = 1; // ���� ���� �ݾ�
	private static JTextField textFindMember;
	private static JTextField textShowName;
	private static JTextField textShowTel;
	private static JTextField textShowStamp;
	private static JTextField textTakemoney;
	static JRadioButton radioTel;
	static JRadioButton radioTakeMoney;
	
	public Main() {
		CoffeeInfo espre = new CoffeeInfo("����������", 2500);
		CoffeeInfo ameri = new CoffeeInfo("�Ƹ޸�ī��", 3000);
		CoffeeInfo latte = new CoffeeInfo("ī���", 3500);
		CoffeeInfo viena = new CoffeeInfo("�񿣳�Ŀ��", 3500);
		CoffeeInfo choco = new CoffeeInfo("��������Ǫġ��", 4000);

		JFrame f1 = new JFrame("ī�� ���� �ý���");
		f1.getContentPane().setFont(new Font("����", Font.PLAIN, 19));
		f1.setSize(1080, 800);
		f1.getContentPane().setLayout(null);

		String[] col = { "��ȣ", "�޴�", "�ܰ�", "����", "����", "�������" }; // �� ���

		tmodel = new DefaultTableModel(col, 0);
		dcr = new DefaultTableCellRenderer(); // �� �ٷ�� ��ü (üũ�ڽ� ����, ��� ����)
		dcr.setHorizontalAlignment(SwingConstants.CENTER); //
		table = new JTable(tmodel);

		TableColumnModel tcm = table.getColumnModel(); // ���̺� ��� ����
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dcr); // table���� �÷��� �ҷ��� �� ���� �Ӽ��� ����
		}
		table.setRowHeight(40); // �� ���� ����
		table.getColumnModel().getColumn(0).setPreferredWidth(5); // 1��° �� ���� ����
		// üũ�ڽ��� ���� ��뿩�� Ȯ��
		table.getColumn("�������").setCellRenderer(dcr); // "�������"���� üũ�ڽ� �ֱ�

		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("�������").setCellEditor(new DefaultCellEditor(box));
		// �� �������� table.getValueAt(��, ��)

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
			}// �����
		});
		buttonMembership.setFont(new Font("����", Font.BOLD, 18));
		buttonMembership.setBounds(340, 28, 95, 60);
		f1.getContentPane().add(buttonMembership);

		JButton buttonInven = new JButton("\uC7AC\uACE0");
		buttonInven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inven inven = new Inven();
			}// ���
		});
		buttonInven.setFont(new Font("����", Font.BOLD, 18));
		buttonInven.setBounds(435, 28, 95, 60);
		f1.getContentPane().add(buttonInven);
		
		JButton buttonstatistic = new JButton("\uD1B5\uACC4");
		buttonstatistic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statistic stat = new Statistic();
			}//���
		});
		buttonstatistic.setFont(new Font("����", Font.BOLD, 18));
		buttonstatistic.setBounds(530, 28, 95, 60);
		f1.getContentPane().add(buttonstatistic);
		
		JButton buttonEspre = new JButton("����������");
		buttonEspre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (espre.num == 0) {
					espre.row = tableRow++;
					Object[] obj = new Object[6];
					obj[0] = tableRow;
					obj[1] = espre.name;
					obj[2] = espre.price;
					obj[3] = espre.price;
					obj[4] = ++espre.num;
					obj[5] = false;
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj); // ���߰�
				} else {
					tmodel.setValueAt(++espre.num, espre.row, 4);
					tmodel.setValueAt(espre.price * espre.num, espre.row, 3);
				}
			}
		});
		buttonEspre.setFont(new Font("����", Font.BOLD, 15));
		buttonEspre.setBounds(642, 98, 130, 60);
		f1.getContentPane().add(buttonEspre);

		JButton buttonAmeri = new JButton("�Ƹ޸�ī��");
		buttonAmeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ameri.num == 0) {
					ameri.row = tableRow++;
					Object[] obj = new Object[6];
					obj[0] = tableRow;
					obj[1] = ameri.name;
					obj[2] = ameri.price;
					obj[3] = ameri.price;
					obj[4] = ++ameri.num;
					obj[5] = false;
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj); // ���߰�
				} else {
					tmodel.setValueAt(++ameri.num, ameri.row, 4);
					tmodel.setValueAt(ameri.price * ameri.num, ameri.row, 3);
				}
			}
		});
		buttonAmeri.setFont(new Font("����", Font.BOLD, 15));
		buttonAmeri.setBounds(771, 98, 130, 60);
		f1.getContentPane().add(buttonAmeri);

		JButton buttonLatte = new JButton("\uCE74\uD398\uB77C\uB5BC");
		buttonLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (latte.num == 0) {
					latte.row = tableRow++;
					Object[] obj = new Object[6];
					obj[0] = tableRow;
					obj[1] = latte.name;
					obj[2] = latte.price;
					obj[3] = latte.price;
					obj[4] = ++latte.num;
					obj[5] = false;
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj); // ���߰�
				} else {
					tmodel.setValueAt(++latte.num, latte.row, 4);
					tmodel.setValueAt(latte.price * latte.num, latte.row, 3);
				}
			}
		});
		buttonLatte.setFont(new Font("����", Font.BOLD, 15));
		buttonLatte.setBounds(900, 98, 130, 60);
		f1.getContentPane().add(buttonLatte);

		JButton buttonViena = new JButton("\uBE44\uC5D4\uB098\uCEE4\uD53C");
		buttonViena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (viena.num == 0) {
					viena.row = tableRow++;
					Object[] obj = new Object[6];
					obj[0] = tableRow;
					obj[1] = viena.name;
					obj[2] = viena.price;
					obj[3] = viena.price;
					obj[4] = ++viena.num;
					obj[5] = false;
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj); // ���߰�
				} else {
					tmodel.setValueAt(++viena.num, viena.row, 4);
					tmodel.setValueAt(viena.price * viena.num, viena.row, 3);
				}
			}
		});
		buttonViena.setFont(new Font("����", Font.BOLD, 15));
		buttonViena.setBounds(642, 158, 130, 60);
		f1.getContentPane().add(buttonViena);

		JButton buttonChoco = new JButton("\uCD08\uCF54\uD504\uB77C\uD478\uCE58\uB178");
		buttonChoco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (choco.num == 0) {
					choco.row = tableRow++;
					Object[] obj = new Object[6];
					obj[0] = tableRow;
					obj[1] = choco.name;
					obj[2] = choco.price;
					obj[3] = choco.price;
					obj[4] = ++choco.num;
					obj[5] = false;
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj); // ���߰�
				} else {
					tmodel.setValueAt(++choco.num, choco.row, 4);
					tmodel.setValueAt(choco.price * choco.num, choco.row, 3);
				}
			}
		});
		buttonChoco.setFont(new Font("����", Font.BOLD, 12));
		buttonChoco.setBounds(771, 158, 130, 60);
		f1.getContentPane().add(buttonChoco);

		// �ӽ÷� ������뿩�� �� �������� ��ư
		JButton buttonImsi = new JButton();
//		buttonImsi.setForeground(new Color(34, 139, 34));
//		buttonImsi.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(table.getValueAt(0, 5));
//			}
//		});
		buttonImsi.setFont(new Font("����", Font.BOLD, 14));
		buttonImsi.setBounds(900, 158, 130, 60);
		f1.getContentPane().add(buttonImsi);

		JButton button_6 = new JButton("");
		button_6.setFont(new Font("����", Font.BOLD, 26));
		button_6.setBounds(771, 218, 130, 60);
		f1.getContentPane().add(button_6);

		JButton buttonBack = new JButton("\uCC98\uC74C\uC73C\uB85C");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				f1.setVisible(false);
				
			}//�α��� ȭ������
		});
		buttonBack.setFont(new Font("����", Font.BOLD, 15));
		buttonBack.setBounds(900, 218, 130, 60);
		f1.getContentPane().add(buttonBack);

		// ���θ޴� ����� �г�
		//

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 205));
		panel.setBounds(27, 385, 294, 339);
		f1.getContentPane().add(panel);
		panel.setLayout(null);

		textFindMember = new JTextField();
		textFindMember.setFont(new Font("����", Font.PLAIN, 18));
		textFindMember.setBounds(90, 15, 109, 27);
		panel.add(textFindMember);
		textFindMember.setColumns(10);

		JLabel label = new JLabel("��ȭ��ȣ");
		label.setFont(new Font("�������", Font.BOLD, 16));
		label.setBounds(22, 9, 73, 41);
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(22, 60, 249, 256);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel label_1 = new JLabel("ȸ����");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("����", Font.BOLD, 17));
		label_1.setBounds(12, 44, 81, 35);
		panel_1.add(label_1);

		textShowName = new JTextField();
		textShowName.setBackground(new Color(255, 255, 255));
		textShowName.setEditable(false);
		textShowName.setFont(new Font("����", Font.PLAIN, 16));
		textShowName.setBounds(105, 44, 132, 35);
		panel_1.add(textShowName);
		textShowName.setColumns(10);

		textShowTel = new JTextField();
		textShowTel.setBackground(new Color(255, 255, 255));
		textShowTel.setEditable(false);
		textShowTel.setFont(new Font("����", Font.PLAIN, 16));
		textShowTel.setColumns(10);
		textShowTel.setBounds(105, 95, 132, 35);
		panel_1.add(textShowTel);

		textShowStamp = new JTextField();
		textShowStamp.setBackground(new Color(255, 255, 255));
		textShowStamp.setEditable(false);
		textShowStamp.setFont(new Font("����", Font.PLAIN, 16));
		textShowStamp.setColumns(10);
		textShowStamp.setBounds(105, 142, 132, 35);
		panel_1.add(textShowStamp);

		JLabel label_2 = new JLabel("������ ��");
		label_2.setForeground(new Color(255, 0, 255));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("����", Font.BOLD, 17));
		label_2.setBounds(12, 142, 81, 35);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("��ȭ��ȣ");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("����", Font.BOLD, 17));
		label_3.setBounds(12, 95, 81, 35);
		panel_1.add(label_3);

		JLabel labelMemberAlert = new JLabel("* �մԿ��� �׻� ģ���ϰ� *");
		labelMemberAlert.setHorizontalAlignment(SwingConstants.CENTER);
		labelMemberAlert.setFont(new Font("����", Font.BOLD | Font.ITALIC, 16));
		labelMemberAlert.setBounds(0, 180, 249, 35);
		panel_1.add(labelMemberAlert);

		JButton buttonReset = new JButton("�ʱ�ȭ");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFindMember.setText("");
				textShowName.setText("");
				textShowStamp.setText("");
				textShowTel.setText("");
				labelMemberAlert.setText("* �մԿ��� �׻� ģ���ϰ� *");
			}
		});
		buttonReset.setForeground(new Color(255, 255, 255));
		buttonReset.setBackground(new Color(105, 105, 105));
		buttonReset.setBounds(72, 216, 97, 30);
		panel_1.add(buttonReset);

		JLabel lblNewLabel_1 = new JLabel("-- ȸ�� ���� --");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(SystemColor.controlDkShadow);
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_1.setBounds(0, 0, 249, 39);
		panel_1.add(lblNewLabel_1);

		JButton buttonShowMember = new JButton("�˻�");
		buttonShowMember.setFont(new Font("����", Font.BOLD, 11));
		buttonShowMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tel = textFindMember.getText();
				mdto = mdao.selectMember(tel);

				textShowName.setText(mdto.getName());
				textShowTel.setText(mdto.getTel());
				textShowStamp.setText(String.valueOf(mdto.getStamp()));

				if (mdto.getTel() == "" || mdto.getTel() == null) {
					labelMemberAlert.setText("��ȸ���� �ʴ� ȸ���Դϴ�.");
				} else {
					labelMemberAlert.setText("�׻� ������ " + mdto.getName() + " ȸ����");
				}
			}
		});
		buttonShowMember.setBounds(211, 15, 60, 27);
		panel.add(buttonShowMember);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 222, 173));
		panel_2.setBounds(343, 385, 282, 339);
		f1.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 300, 90);
		panel_3.setBackground(new Color(255, 192, 203));
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		JLabel label_4 = new JLabel("�� ����");
		label_4.setFont(new Font("����", Font.BOLD, 20));
		label_4.setBounds(12, 10, 125, 70);
		panel_3.add(label_4);

		JLabel labelShowNum = new JLabel("");
		labelShowNum.setHorizontalAlignment(SwingConstants.RIGHT);
		labelShowNum.setForeground(new Color(105, 105, 105));
		labelShowNum.setFont(new Font("����", Font.BOLD | Font.ITALIC, 21));
		labelShowNum.setBounds(126, 10, 142, 70);
		panel_3.add(labelShowNum);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 177, 300, 90);
		panel_5.setBackground(new Color(255, 192, 203));
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel label_5 = new JLabel("���� �ݾ�");
		label_5.setFont(new Font("����", Font.BOLD, 20));
		label_5.setBounds(12, 15, 102, 60);
		panel_5.add(label_5);

		JLabel labelShowDiscount = new JLabel("");
		labelShowDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
		labelShowDiscount.setForeground(new Color(153, 0, 0));
		labelShowDiscount.setFont(new Font("����", Font.BOLD | Font.ITALIC, 21));
		labelShowDiscount.setBounds(126, 15, 142, 60);
		panel_5.add(labelShowDiscount);

		JLabel lblNewLabel = new JLabel("�� �ݾ�");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 100, 87, 67);
		panel_2.add(lblNewLabel);

		JLabel label_6 = new JLabel("���� �ݾ�");
		label_6.setFont(new Font("����", Font.BOLD, 20));
		label_6.setBounds(12, 268, 100, 71);
		panel_2.add(label_6);

		JLabel labelShowTotalPrice = new JLabel("");
		labelShowTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		labelShowTotalPrice.setForeground(SystemColor.controlDkShadow);
		labelShowTotalPrice.setFont(new Font("����", Font.BOLD | Font.ITALIC, 21));
		labelShowTotalPrice.setBounds(128, 107, 142, 60);
		panel_2.add(labelShowTotalPrice);

		textTakemoney = new JTextField();
		textTakemoney.setFont(new Font("����", Font.PLAIN, 13));
		textTakemoney.setHorizontalAlignment(SwingConstants.CENTER);
		textTakemoney.setBounds(114, 288, 92, 34);
		panel_2.add(textTakemoney);
		textTakemoney.setColumns(10);

		JLabel labelFinPrice = new JLabel("");
		labelFinPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFinPrice.setFont(new Font("����", Font.BOLD | Font.ITALIC, 35));
		labelFinPrice.setForeground(new Color(147, 112, 219));
		labelFinPrice.setBounds(129, 10, 232, 83);

		JButton buttonSelectFinish = new JButton("���ÿϷ�");
		buttonSelectFinish.setForeground(new Color(255, 255, 255));
		buttonSelectFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// �� ���� ���
				int totalNum = 0;
				for (int i = 0; i < table.getRowCount(); i++)
					totalNum += (int) table.getValueAt(i, 4);
				labelShowNum.setText(String.valueOf(totalNum) + " ��");

				// �� �ݾ� ���
				int totalPrice = 0;
				for (int i = 0; i < table.getRowCount(); i++)
					totalPrice += (int) table.getValueAt(i, 3);
				labelShowTotalPrice.setText(String.valueOf(totalPrice) + " ��");

				// ���� ���� ���αݾ� ���� �� ���
				int discountPrice = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					if ((boolean) table.getValueAt(i, 5) == true)
						discountPrice += (int) table.getValueAt(i, 2);
				}
				labelShowDiscount.setText(String.valueOf(discountPrice) + " ��");

				// �� �ݾ� - ���αݾ��� ������ ���� �ݾ� ���
				finPrice = totalPrice - discountPrice;
				labelFinPrice.setText(String.valueOf(finPrice) + " ��");
			}
		});
		buttonSelectFinish.setBackground(new Color(0, 102, 204));
		buttonSelectFinish.setFont(new Font("����", Font.BOLD, 21));
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

		JLabel label_8 = new JLabel("�Ž��� ��");
		label_8.setFont(new Font("����", Font.BOLD, 13));
		label_8.setBounds(23, 0, 102, 60);
		panel_6.add(label_8);

		JLabel labelShowChange = new JLabel("");
		labelShowChange.setHorizontalAlignment(SwingConstants.RIGHT);
		labelShowChange.setBounds(84, 17, 66, 27);
		panel_6.add(labelShowChange);

		JButton buttonShowChange = new JButton("�Է�");
		buttonShowChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int takeMoney = Integer.parseInt(textTakemoney.getText());
				int change = takeMoney - finPrice;
				labelShowChange.setText(String.valueOf(change) + " ��");
			}
		});
		buttonShowChange.setFont(new Font("����", Font.BOLD, 11));
		buttonShowChange.setBounds(212, 289, 58, 34);
		panel_2.add(buttonShowChange);

		JLabel label_7 = new JLabel("���� �ݾ�");
		label_7.setBounds(24, 10, 131, 83);
		panel_4.add(label_7);
		label_7.setFont(new Font("�������", Font.BOLD, 23));

		panel_4.add(labelFinPrice);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		panel_7.setBounds(771, 288, 257, 262);
		f1.getContentPane().add(panel_7);
		panel_7.setLayout(null);

		/*
		 * ȭ��Ű�е尡 �Էµ� �� ���� â
		 */
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		panel_8.setBounds(642, 387, 130, 163);
		f1.getContentPane().add(panel_8);
		panel_8.setLayout(null);

		radioTel = new JRadioButton("��ȭ��ȣ", true);
		radioTel.setFont(new Font("����", Font.BOLD, 20));
		radioTel.setBackground(new Color(255, 255, 204));
		radioTel.setBounds(14, 15, 177, 29);
		panel_8.add(radioTel);

		radioTakeMoney = new JRadioButton("���� �ݾ�");
		radioTakeMoney.setFont(new Font("����", Font.BOLD, 20));
		radioTakeMoney.setBackground(new Color(255, 204, 153));
		radioTakeMoney.setBounds(14, 50, 177, 29);
		panel_8.add(radioTakeMoney);

		ButtonGroup bg = new ButtonGroup(); // radio�� �� �׷����� ���� �� �� �ϳ��� ����
		bg.add(radioTel);
		bg.add(radioTakeMoney);

		/*
		 * ȭ��Ű�е�
		 */
		JButton b0 = new JButton("0");
		b0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
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
				if (radioTel.isSelected()) {
					str = textFindMember.getText();
					if (str.trim().equals("") || str == null) // str�� ���� ������ �׳� �޼ҵ� ����
						return;
					textFindMember.setText(str.substring(0, str.length() - 1));
				} else {
					str = textTakemoney.getText();
					if (str.trim().equals("") || str == null) // str�� ���� ������ �׳� �޼ҵ� ����
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

		JButton buttonEnter = new JButton("Enter");
		buttonEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioTel.isSelected()) {
					buttonShowMember.doClick();
				} else {
					buttonShowChange.doClick();
				}
			}
		});
		buttonEnter.setForeground(new Color(255, 255, 255));
		buttonEnter.setBackground(new Color(105, 105, 105));
		buttonEnter.setFont(new Font("����", Font.BOLD, 23));
		buttonEnter.setBounds(14, 92, 116, 56);
		panel_8.add(buttonEnter);
		// ����� �г� ��

		/*
		 * ��ü���� ��ư
		 */
		JButton buttonAllDelete = new JButton("\uC804\uCCB4\uC0AD\uC81C");
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
		buttonAllDelete.setFont(new Font("����", Font.BOLD, 17));
		buttonAllDelete.setBounds(642, 223, 125, 55);
		f1.getContentPane().add(buttonAllDelete);

		/*
		 * ���� ��ư
		 */

		JButton buttonCredit = new JButton("�����ϱ� \\");
		buttonCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (labelShowNum.getText().trim().equals("0 ��")) // '0 ��'�� �����Ϸ��� �� ��
					return;

				if (labelFinPrice.getText() == null || labelFinPrice.getText().equals("")) { // ���ÿϷ� ��ư�� ������ �ʰ� ���� ���� ��
					NoFinPrice nof = new NoFinPrice();
					return;
				}
				// ����� ��� DB ����
				String tel = textShowTel.getText();
				if (tel != null || !(tel.trim().equals(""))) { // ���ο��� ����� ��ȸ�� �� ��츸 ����
					mdto = mdao.selectMember(tel);
					int couponCnt = 0;
					for (int i = 0; i < table.getRowCount(); i++) {
						if ((boolean) table.getValueAt(i, 5) == true)
							++couponCnt;
					}
					if (couponCnt != 0) { // �������
						if (tel == null || tel.trim().equals("")) { // ���αݾ��� �����ߴµ� ���� ����� ��ȸ�� ���Ѱ��
							NoMembership no = new NoMembership();
							return;
						} else if (mdto.getStamp() < couponCnt * 10) { // ���� ����ϱ⿡ ������ �� ������ ��� �޽���
							NotEnoughStamp not = new NotEnoughStamp();
							return;
						} else {
							mdao.UseStamp(mdto.getTel(), couponCnt);
						}
					} else { // ���� ��� ���� �� �� ������ŭ ������ ����
						int cnt = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							cnt += (int) table.getValueAt(i, 4);
						}
						mdao.plusStamp(mdto.getTel(), cnt);
					}

				} // ����� ��ȸ�� ��� ��

				SelectGender sg = new SelectGender();

				// �޴�â �ʱ�ȭ
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
				textFindMember.setText("");
				textTakemoney.setText("");
				textShowName.setText("");
				textShowStamp.setText("");
				textShowTel.setText("");
				labelMemberAlert.setText("* �մԿ��� �׻� ģ���ϰ� *");
				radioTel.setSelected(true); // ���� ���� ���� ��ȭ��ȣ �Է� �⺻ ����
			}
		});
		buttonCredit.setFont(new Font("����", Font.BOLD, 26));
		buttonCredit.setBackground(new Color(153, 0, 0));
		buttonCredit.setForeground(new Color(255, 255, 255));
		buttonCredit.setBounds(190, 0, 196, 60);
		panel_6.add(buttonCredit);

		f1.setVisible(true);
	}

	
	public static void main(String[] args) throws Exception{
		Main main = new Main();
		
	}//main end
}//class end