package membership;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import javax.swing.JToggleButton;
import pos.Main;

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
	JLabel labelInsertAlert; // 멤버쉽 생성 시 뜨는 메시지
	JLabel labelDeleteAlert; // 멤버쉽 삭제 시 뜨는 메시지
	private JTextField textdeleteTel;
	JToggleButton toggleEvent; // 이벤트 실행 토글

	public Membership() {
		setTitle("멤버쉽 관리 창 (종료하기 버튼으로 종료)");
		setSize(985, 674);
		setBounds(200, 100, 770, 545);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // x로 안꺼짐
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 41, 508, 442);
		getContentPane().add(scrollPane);

		String[] col = { "번호", "이름", "전화번호", "스탬프 수" };
		tmodel = new DefaultTableModel(col, 0);
		dcr = new DefaultTableCellRenderer(); // 셀 다루는 객체 (체크박스 생성, 가운데 정렬)
		dcr.setHorizontalAlignment(SwingConstants.CENTER); //
		table = new JTable(tmodel);
		table.setEnabled(false);
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
		comboBox.setBounds(12, 10, 80, 21);
		getContentPane().add(comboBox);

		textFindmember = new JTextField();
		textFindmember.setBounds(99, 11, 132, 21);
		getContentPane().add(textFindmember);
		textFindmember.setColumns(10);

		JButton buttonSearch = new JButton("검색");
		buttonSearch.setBounds(234, 11, 67, 22);
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
		getContentPane().add(buttonSearch);

		// 종료버튼
		JButton buttonExit = new JButton("종료하기");
		buttonExit.setBounds(532, 406, 210, 77);
		buttonExit.setForeground(new Color(255, 255, 240));
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mtableRow = 0;
				setVisible(false);
			}
		});
		buttonExit.setBackground(new Color(220, 20, 60));
		buttonExit.setFont(new Font("굴림", Font.BOLD, 34));
		getContentPane().add(buttonExit);

		JButton buttonShowAll = new JButton("전체목록");
		buttonShowAll.setBounds(375, 10, 145, 23);
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
		getContentPane().add(buttonShowAll);

		JPanel panel = new JPanel();
		panel.setBounds(532, 41, 210, 142);
		panel.setBackground(new Color(255, 228, 181));
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
		lblNewLabel.setBounds(0, 38, 82, 15);
		panel.add(lblNewLabel);

		textInputName = new JTextField();
		textInputName.setBounds(81, 34, 116, 21);
		panel.add(textInputName);
		textInputName.setColumns(10);

		JLabel label_1 = new JLabel("전화번호");
		label_1.setFont(new Font("굴림", Font.BOLD, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 67, 82, 15);
		panel.add(label_1);

		textInputTel = new JTextField();
		textInputTel.setColumns(10);
		textInputTel.setBounds(81, 64, 116, 21);
		panel.add(textInputTel);

		labelInsertAlert = new JLabel("");
		labelInsertAlert.setBounds(532, 190, 210, 15);
		labelInsertAlert.setBackground(new Color(220, 20, 60));
		labelInsertAlert.setFont(new Font("굴림", Font.PLAIN, 14));
		labelInsertAlert.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelInsertAlert);

		JButton buttonInsertMem = new JButton("가입하기");
		buttonInsertMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelInsertAlert.setText("");
				labelDeleteAlert.setText("");
				String name = textInputName.getText();
				String tel = textInputTel.getText();
				int res = mdao.insertMember(name, tel);
				if (res != 0) { // 0일 경우 insert 수행 안된 것
					labelInsertAlert.setText(name + "님 가입이 완료되었습니다.");
					textInputName.setText("");
					textInputTel.setText("");
				}
			}
		});
		buttonInsertMem.setBounds(53, 95, 109, 35);
		panel.add(buttonInsertMem);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(532, 234, 210, 114);
		panel_1.setBackground(new Color(205, 92, 92));
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
		labelDeleteAlert.setBounds(532, 358, 210, 15);
		labelDeleteAlert.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeleteAlert.setFont(new Font("굴림", Font.PLAIN, 14));
		getContentPane().add(labelDeleteAlert);

		JButton buttonDeleteMem = new JButton("삭제하기");
		buttonDeleteMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelInsertAlert.setText("");
				labelDeleteAlert.setText("");
				String tel = textdeleteTel.getText();
				int confirm = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
				if (confirm == 0) {
					int res = mdao.deleteMember(tel);
					if (res != 0) { // 0일 경우 delete 수행 안된 것
						labelDeleteAlert.setText("삭제가 완료되었습니다.");
						textdeleteTel.setText("");
					}
				} else {
					textdeleteTel.setText("");
					return;
				}
			}

		});
		buttonDeleteMem.setBounds(57, 69, 109, 35);
		panel_1.add(buttonDeleteMem);
		/*
		 * 이벤트 토글
		 */
		if (Main.eventDoit == 0) {
			JToggleButton toggleEvent = new JToggleButton("이벤트 모드 실행하기", false);
			toggleEvent.setFont(new Font("굴림", Font.BOLD, 13));
			toggleEvent.setForeground(new Color(0, 128, 0));
			toggleEvent.setBackground(new Color(245, 255, 250));
			toggleEvent.setBounds(551, 10, 180, 21);
			getContentPane().add(toggleEvent);
			toggleEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (toggleEvent.isSelected()) {
						toggleEvent.setText("이벤트 모드 해제하기");
						toggleEvent.setForeground(Color.red);
						mdao.resetEvent(); // 이벤트 모드 실행하면 회원들의 이벤트 참여 내역 초기화
						Main.eventDoit = 1;
						Main.labelShowEvent.setText("이벤트 모드 On");
						Main.labelShowEvent.setForeground(new Color(0, 128, 0));
					} else {
						toggleEvent.setText("이벤트 모드 실행하기");
						toggleEvent.setForeground(new Color(0, 128, 0));
						toggleEvent.setBackground(new Color(245, 255, 250));
						Main.eventDoit = 0;
						Main.labelShowEvent.setText("");
					}
				}
			});
		} else {
			JToggleButton toggleEvent = new JToggleButton("이벤트 모드 해제하기", true); // 토글 선택 된 상태
			toggleEvent.setFont(new Font("굴림", Font.BOLD, 13));
			toggleEvent.setForeground(Color.red);
			toggleEvent.setBackground(new Color(245, 255, 250));
			toggleEvent.setBounds(551, 10, 180, 21);
			getContentPane().add(toggleEvent);
			toggleEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (toggleEvent.isSelected()) {
						toggleEvent.setText("이벤트 모드 해제하기");
						toggleEvent.setForeground(Color.red);
						mdao.resetEvent(); // 이벤트 모드 실행하면 회원들의 이벤트 참여 내역 초기화
						Main.eventDoit = 1;
						Main.labelShowEvent.setText("이벤트 모드 On");
						Main.labelShowEvent.setForeground(new Color(0, 128, 0));
					} else {
						toggleEvent.setText("이벤트 모드 실행하기");
						toggleEvent.setForeground(new Color(0, 128, 0));
						toggleEvent.setBackground(new Color(245, 255, 250));
						Main.eventDoit = 0;
						Main.labelShowEvent.setText("");
					}
				}
			});
		}

		setVisible(true);

	}

	public static void main(String[] args) {
		Membership m = new Membership();
	}
}
