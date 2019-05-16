package statistic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Statistic {
	static DefaultTableModel tmodel;
	private StatDto dto;
	private StatDto dto1;
	private JTable table;
	private JTable table1;
	private JTable table2;
	static String selected;
	
	public Statistic() {

		ArrayList list = new ArrayList();

		StatDao dao = new StatDao();
		dto = dao.count();
		list = dao.list();
		dto1 = dao.americano();

		JFrame f = new JFrame();
		f.setSize(1080, 800);
		f.setBounds(400, 200, 1080, 800);

		String[] header = { "결제번호", "메뉴명", "가격", "수량", "합계", "쿠폰여부", "성별", "날짜" };
		Object[][] contents = new Object[dto.getCount()][8];
		for (int i = 0; i < contents.length; i++) {
			StatDto dto = (StatDto) list.get(i);
			contents[i][0] = dto.getPin();
			contents[i][1] = dto.getMenu();
			contents[i][2] = dto.getPrice();
			contents[i][3] = dto.getQuantity();
			contents[i][4] = dto.getTot_price();
			contents[i][5] = dto.getCoupon();
			contents[i][6] = dto.getGender();
			contents[i][7] = dto.getBuydate();
		}
		tmodel = new DefaultTableModel(header, 0);
		for (int j = 0; j < contents.length; j++) {
			tmodel.addRow(contents[j]);
		}
		table = new JTable(tmodel);
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer();
		dcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dcr); // table에서 컬럼을 불러온 뒤 셀의 속성을 설정
		}
		f.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 109, 679, 483);
		f.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
				
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(30);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		
		
		String[] header1 = {"메뉴명", "판매량", "총매출"};
		Object[][] contents1 = {
				{"Espresso", dto1.getEspressoTotal(), dto1.getEspressoSum()},
				{"Americano", dto1.getAmericanoTotal(), dto1.getAmericanoSum()},
				{"Cafelatte", dto1.getCafelatteTotal(), dto1.getCafelatteSum()},
				{"Vienacoffee", dto1.getVienacoffeeTotal(), dto1.getVienacoffeeSum()},
				{"Chocofrapuccino", dto1.getChocofrapuccinoTotal(), dto1.getChocofrapuccinoTotal()}
								
		};
		tmodel = new DefaultTableModel(header1, 0);		
		for (int j = 0; j < contents1.length; j++) {
			tmodel.addRow(contents1[j]);
		}
		
		table1 = new JTable(tmodel);
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(715, 110, 337, 167);
		f.getContentPane().add(scrollPane1);
		scrollPane1.setViewportView(table1);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(715, 323, 337, 269);
		f.getContentPane().add(scrollPane2);
		
		table2 = new JTable();
		scrollPane2.setViewportView(table2);
		
		String [] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		
		JComboBox comboBox = new JComboBox(month);
		
		selected = comboBox.getSelectedItem().toString();
		System.out.println(selected);
		
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(714, 287, 69, 26);
		f.getContentPane().add(comboBox);

		f.setVisible(true);
	}

	public static void main(String[] args) {
		Statistic stat = new Statistic();
	}
}