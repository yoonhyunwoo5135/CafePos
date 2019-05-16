package membership;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Membership extends JFrame {
	static DefaultTableModel tmodel;
	static int mtableRow = 0; // 멤버쉽 테이블 목록 창
	static DefaultTableCellRenderer dcr;
	private JTable table;
	MemberDto mdto;
	MemberDao mdao = new MemberDao();
	ArrayList memberList = new ArrayList();
	private JTextField textFindmember;
	private JTextField textInputName;
	private JTextField textInputTel;
	JLabel labelInsertAlert;
	JLabel labelDeleteAlert;
	private JTextField textdeleteTel;

	public Membership() {
		setTitle("멤버쉽 관리 창 (종료하기 버튼으로 종료)");
		setSize(800, 600);
		setBounds(200, 100, 800, 600);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // x로 안꺼짐
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 95, 508, 442);
		getContentPane().add(scrollPane);

		String[] col = { "번호", "이름", "전화번호", "스탬프 수" };
		tmodel = new DefaultTableModel(col, 0);
		dcr = new DefaultTableCellRenderer(); // 셀 다루는 객체 (체크박스 생성, 가운데 정렬)
		dcr.setHorizontalAlignment(SwingConstants.CENTER); //
		table = new JTable(tmodel);
		scrollPane.setViewportView(table);

		TableColumnModel tcm = table.getColumnModel(); // 테이블 가운데 정렬
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dcr); // table에서 컬럼을 불러온 뒤 셀의 속성을 설정
		}

		table.setRowHeight(30); // 행 높이 조절
		table.getColumnModel().getColumn(0).setPreferredWidth(1); // 1번째 열 넓이 조절

		String[] cbList = new String[] { "전화번호", "이름" };

		// 멤버쉽 창 실행시 전체 멤버쉽 목록 출력
		memberList = mdao.selectAll();
		for (int i = 0; i < memberList.size(); i++) {
			mdto = (MemberDto) memberList.get(i);
			Object[] obj = new Object[4];
			obj[0] = ++mtableRow;
			obj[1] = mdto.getName();
			obj[2] = mdto.getTel();
			obj[3] = mdto.getStamp();
			tmodel = (DefaultTableModel) table.getModel();
			tmodel.addRow(obj);
		}

		JComboBox comboBox = new JComboBox(cbList);
		comboBox.setBounds(42, 71, 80, 21);
		getContentPane().add(comboBox);

		textFindmember = new JTextField();
		textFindmember.setBounds(123, 71, 132, 21);
		getContentPane().add(textFindmember);
		textFindmember.setColumns(10);

		JButton buttonSearch = new JButton("검색");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelInsertAlert.setText("");
				labelDeleteAlert.setText("");
				String selected = comboBox.getSelectedItem().toString();
				// 메뉴창 초기화
				for (int i = mtableRow - 1; i >= 0; i--) {
					tmodel.removeRow(--mtableRow);
				}
				if (selected == "전화번호") {
					mtableRow = 0;
					mdto = mdao.selectMemberTel(textFindmember.getText());
					Object[] obj = new Object[4];
					obj[0] = ++mtableRow;
					obj[1] = mdto.getName();
					obj[2] = mdto.getTel();
					obj[3] = mdto.getStamp();
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj);
				} else {
					memberList = mdao.selectMemberName(textFindmember.getText());
					for (int i = 0; i < memberList.size(); i++) {
						mdto = (MemberDto) memberList.get(i);
						Object[] obj = new Object[4];
						obj[0] = ++mtableRow;
						obj[1] = mdto.getName();
						obj[2] = mdto.getTel();
						obj[3] = mdto.getStamp();
						tmodel = (DefaultTableModel) table.getModel();
						tmodel.addRow(obj);
					}

				}
			}
		});
		buttonSearch.setBounds(264, 70, 67, 22);
		getContentPane().add(buttonSearch);

		// 종료버튼
		JButton buttonExit = new JButton("종료하기");
		buttonExit.setForeground(new Color(255, 255, 240));
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mtableRow = 0;
				setVisible(false);
			}
		});
		buttonExit.setBackground(new Color(220, 20, 60));
		buttonExit.setFont(new Font("굴림", Font.BOLD, 34));
		buttonExit.setBounds(562, 460, 210, 77);
		getContentPane().add(buttonExit);

		JButton buttonShowAll = new JButton("전체목록");
		buttonShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelInsertAlert.setText("");
				labelDeleteAlert.setText("");
				for (int i = mtableRow - 1; i >= 0; i--) {
					tmodel.removeRow(--mtableRow);
				}
				mtableRow = 0;
				memberList = mdao.selectAll();
				for (int i = 0; i < memberList.size(); i++) {
					mdto = (MemberDto) memberList.get(i);
					Object[] obj = new Object[4];
					obj[0] = ++mtableRow;
					obj[1] = mdto.getName();
					obj[2] = mdto.getTel();
					obj[3] = mdto.getStamp();
					tmodel = (DefaultTableModel) table.getModel();
					tmodel.addRow(obj);
				}
			}
		});
		buttonShowAll.setBounds(405, 70, 145, 23);
		getContentPane().add(buttonShowAll);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 181));
		panel.setBounds(562, 95, 210, 158);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("-- 멤버쉽 가입 --");
		label.setForeground(new Color(119, 136, 153));
		label.setFont(new Font("굴림", Font.BOLD, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 10, 210, 15);
		panel.add(label);

		JLabel lblNewLabel = new JLabel("이 름");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 50, 82, 15);
		panel.add(lblNewLabel);

		textInputName = new JTextField();
		textInputName.setBounds(81, 47, 116, 21);
		panel.add(textInputName);
		textInputName.setColumns(10);

		JLabel label_1 = new JLabel("전화번호");
		label_1.setFont(new Font("굴림", Font.BOLD, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 85, 82, 15);
		panel.add(label_1);

		textInputTel = new JTextField();
		textInputTel.setColumns(10);
		textInputTel.setBounds(81, 82, 116, 21);
		panel.add(textInputTel);

		labelInsertAlert = new JLabel("");
		labelInsertAlert.setBackground(new Color(220, 20, 60));
		labelInsertAlert.setFont(new Font("굴림", Font.PLAIN, 14));
		labelInsertAlert.setHorizontalAlignment(SwingConstants.CENTER);
		labelInsertAlert.setBounds(562, 263, 210, 15);
		getContentPane().add(labelInsertAlert);

		JButton buttonInsertMem = new JButton("가입하기");
		buttonInsertMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelInsertAlert.setText("");
				labelDeleteAlert.setText("");
				String name = textInputName.getText();
				String tel = textInputTel.getText();
				int res = mdao.insertMember(name, tel);	
				if (res != 0) {	// 0일 경우 insert 수행 안된 것 
					labelInsertAlert.setText(name + "님 가입이 완료되었습니다.");
					textInputName.setText("");
					textInputTel.setText("");
				}
			}
		});
		buttonInsertMem.setBounds(53, 113, 109, 35);
		panel.add(buttonInsertMem);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(205, 92, 92));
		panel_1.setBounds(562, 300, 210, 114);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel label_2 = new JLabel("-- 멤버쉽 삭제 --");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setFont(new Font("굴림", Font.BOLD, 16));
		label_2.setBounds(0, 10, 210, 15);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("전화번호");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("굴림", Font.BOLD, 14));
		label_3.setBounds(0, 38, 82, 15);
		panel_1.add(label_3);

		textdeleteTel = new JTextField();
		textdeleteTel.setColumns(10);
		textdeleteTel.setBounds(81, 35, 116, 21);
		panel_1.add(textdeleteTel);

		labelDeleteAlert = new JLabel("");
		labelDeleteAlert.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeleteAlert.setFont(new Font("굴림", Font.PLAIN, 14));
		labelDeleteAlert.setBounds(562, 424, 210, 15);
		getContentPane().add(labelDeleteAlert);

		JButton buttonDeleteMem = new JButton("삭제하기");
		buttonDeleteMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelInsertAlert.setText("");
				labelDeleteAlert.setText("");
				String tel = textdeleteTel.getText();
				int res = mdao.deleteMember(tel);
				if (res != 0) { // 0일 경우 delete 수행 안된 것 
					labelDeleteAlert.setText("삭제가 완료되었습니다.");
					textdeleteTel.setText("");
				}
			}

		});
		buttonDeleteMem.setBounds(57, 69, 109, 35);
		panel_1.add(buttonDeleteMem);

		setVisible(true);

	}
}
