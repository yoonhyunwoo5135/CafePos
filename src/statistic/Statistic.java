package statistic;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Statistic {
	static DefaultTableModel tmodel;
	static DefaultTableModel tmodel2;
	private StatDto dto;
	private StatDto dto2;
	private JTable table;
	private JTable table1;

	public Statistic() {

		ArrayList list = new ArrayList();

		StatDao dao = new StatDao();
		dto = dao.count();
		list = dao.list();
		dto2 = dao.americano();

		JFrame f = new JFrame();
		f.setSize(1080, 800);
		f.setBounds(400, 200, 1080, 800);
		f.getContentPane().setLayout(null);

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
				{"Espresso", dto2.getAmericanoSum(), dto2.getAmericanoTotal()},
		//		{"Americano", dto2.getAmericanoSum(), dto2.getAmericanoTotal()},
		//		{"Cafelatte", dto2.getAmericanoSum(), dto2.getAmericanoTotal()},
		//		{"Vienacoffee", dto2.getAmericanoSum(), dto2.getAmericanoTotal()},
		//		{"Chocofrapuccino", dto2.getAmericanoSum(), dto2.getAmericanoTotal()}
		};
		tmodel2 = new DefaultTableModel(header1, 0);
		for (int j = 0; j < contents1.length; j++) {
			tmodel2.addRow(contents1[j]);
		}
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(715, 109, 337, 288);
		f.getContentPane().add(scrollPane1);
		
		table1 = new JTable();
		scrollPane1.setViewportView(table1);
		

		f.setVisible(true);
		
	}

	public static void main(String[] args) {
		Statistic stat = new Statistic();
	}
}