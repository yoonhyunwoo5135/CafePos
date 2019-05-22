package inven;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Inven {
	
	private JTable table;
	
	static JTextField t1;
	static DefaultTableModel tmodel;
	static InvenDao dao;
	static InvenDto dto;
	
	public Inven() {
		JFrame f = new JFrame("카페 포스 시스템");
		f.setTitle("재고 관리 창");
		f.setBounds(600, 200, 700, 500);
		f.setResizable(false);
		f.setSize(700 , 500);
		f.setResizable(false);
		
		dao = new InvenDao();
		dto = dao.list();
				
		ImageIcon icon = new ImageIcon("Mainpont.png");
		JLabel la1 = new JLabel(icon);
		la1.setBounds(25, 10, 300, 80);
		f.getContentPane().add(la1);
		
		String [] header = {"원두", "우유", "초콜릿", "크림", "컵", "빨대"};
		Object[][] contents = new Object[1][6];
		
		contents[0][0] = dto.getBean();
		contents[0][1] = dto.getMilk();
		contents[0][2] = dto.getChoco();
		contents[0][3] = dto.getCream();
		contents[0][4] = dto.getCup();
		contents[0][5] = dto.getStraw();//초기 테이블 Row 생성
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 98, 645, 143);
		f.getContentPane().add(scrollPane);
		
		tmodel = new DefaultTableModel(header, 0);//Row 타이틀
		table = new JTable(tmodel);
		table.setFont(new Font("굴림", Font.PLAIN, 18));
		tmodel.addRow(contents[0]);
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer(); // 셀 다루는 객체 (체크박스 생성, 가운데 정렬)
		dcr.setHorizontalAlignment(SwingConstants.CENTER); //
		TableColumnModel tcm = table.getColumnModel(); // 테이블 가운데 정렬
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dcr); // table에서 컬럼을 불러온 뒤 셀의 속성을 설정
		}
		table.setRowHeight(30); // 행 높이 조절
		
		JButton addbean = new JButton("\uC6D0\uB450\uC8FC\uBB38");
		addbean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addbean();//dto값에 이미 dao.list가 들어가 있어서 dao2로 생성
				if(t1.getText().trim().equals("")) {
					return;
				}
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getBean();
					tmodel.removeRow(0);//기존 행 삭제
					dto = dao.list();
					contents[0][0] = dto.getBean();
					tmodel.addRow(contents[0]);//새로운 행 추가
					t1.setText("");	// 텍스트 필드 초기화
				};
				
				
			}//bean 추가 버튼
		});
		addbean.setFont(new Font("굴림", Font.BOLD, 20));
		addbean.setBounds(25, 276, 142, 47);
		f.getContentPane().add(addbean);
		
		JButton addmilk = new JButton("\uC6B0\uC720\uC8FC\uBB38");
		addmilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addmilk();//dto값에 이미 dao.list가 들어가 있어서 dao2로 생성
				if(t1.getText().trim().equals("")) {
					return;
				}
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getMilk();
					tmodel.removeRow(0);//기존 행 삭제
					dto = dao.list();
					contents[0][1] = dto.getMilk();
					tmodel.addRow(contents[0]);//새로운 행 추가
					t1.setText("");//텍스트 필드 초기화
				};
			}//milk추가 버튼
		});
		addmilk.setFont(new Font("굴림", Font.BOLD, 20));
		addmilk.setBounds(195, 276, 142, 47);
		f.getContentPane().add(addmilk);
		
		JButton addchoco = new JButton("\uCD08\uCF5C\uB9BF\uC8FC\uBB38");
		addchoco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addchoco();//dto값에 이미 dao.list가 들어가 있어서 dao2로 생성
				if(t1.getText().trim().equals("")) {
					return;
				}
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getChoco();
					tmodel.removeRow(0);//기존 행 삭제
					dto = dao.list();
					contents[0][2] = dto.getChoco();
					tmodel.addRow(contents[0]);//새로운 행 추가
					t1.setText("");//텍스트 필드 초기화
				};
				
			}//choco추가 버튼
		});
		addchoco.setFont(new Font("굴림", Font.BOLD, 20));
		addchoco.setBounds(365, 276, 142, 47);
		f.getContentPane().add(addchoco);
		
		JButton addcream = new JButton("\uD06C\uB9BC\uC8FC\uBB38");
		addcream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addcream();//dto값에 이미 dao.list가 들어가 있어서 dao2로 생성
				if(t1.getText().trim().equals("")) {
					return;
				}
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getCream();
					tmodel.removeRow(0);//기존 행 삭제
					dto = dao.list();
					contents[0][3] = dto.getCream();
					tmodel.addRow(contents[0]);//새로운 행 추가
					t1.setText("");//텍스트 필드 초기화
				};
			}//cream추가 버튼
		});
		addcream.setFont(new Font("굴림", Font.BOLD, 20));
		addcream.setBounds(25, 350, 142, 47);
		f.getContentPane().add(addcream);
		
		JButton addcup = new JButton("\uCEF5\uC8FC\uBB38");
		addcup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addcup();//dto값에 이미 dao.list가 들어가 있어서 dao2로 생성
				if(t1.getText().trim().equals("")) {
					return;
				}
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getCup();
					tmodel.removeRow(0);//기존행 삭제
					dto = dao.list();
					contents[0][4] = dto.getCup();
					tmodel.addRow(contents[0]);//새로운 행 추가
					t1.setText("");//텍스트 필드 초기화
				};
			}//cup추가 버튼
		});
		addcup.setFont(new Font("굴림", Font.BOLD, 20));
		addcup.setBounds(195, 350, 142, 47);
		f.getContentPane().add(addcup);
		
		JButton addstraw = new JButton("\uBE68\uB300\uC8FC\uBB38");
		addstraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addstraw();//dto값에 이미 dao.list가 들어가 있어서 dao2로 생성
				if(t1.getText().trim().equals("")) {
					return;
				}
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getStraw();
					tmodel.removeRow(0);//기존행 삭제
					dto = dao.list();
					contents[0][5] = dto.getStraw();
					tmodel.addRow(contents[0]);//새로운 행 추가
					t1.setText("");//텍스트 필드 초기화
				};
			}//straw추가 버튼
		});
		addstraw.setFont(new Font("굴림", Font.BOLD, 20));
		addstraw.setBounds(365, 350, 142, 47);
		f.getContentPane().add(addstraw);
		
		
		t1 = new JTextField();
		t1.setFont(new Font("굴림", Font.PLAIN, 22));
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t1.setBounds(528, 312, 142, 42);
		f.getContentPane().add(t1);
		t1.setColumns(10);//추가할 수량 적는 텍스트 필드
		
		JLabel la2 = new JLabel("\uC8FC\uBB38 \uC218\uB7C9");
		la2.setHorizontalAlignment(SwingConstants.CENTER);
		la2.setFont(new Font("굴림", Font.BOLD, 20));
		la2.setBounds(524, 276, 146, 31);
		f.getContentPane().add(la2);
		f.getContentPane().setLayout(null);//주문수량 라벨
		
		JButton back = new JButton("종료하기");
		back.setForeground(new Color(255, 255, 255));
		back.setBackground(new Color(255, 0, 51));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.setVisible(false);
			}//돌아가기
		});
		back.setFont(new Font("굴림", Font.BOLD, 14));
		back.setBounds(548, 366, 105, 31);
		f.getContentPane().add(back);
		
		
		f.setVisible(true);
		
		
	}
	
	
	
	public static void main(String[] args) {
		Inven inven = new Inven();
	
	}
}