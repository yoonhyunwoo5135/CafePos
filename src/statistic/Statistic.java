package statistic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import statistic.StatDto;

public class Statistic {
	static DefaultTableModel tmodel;
	private StatDto dto;
	private StatDto dto1;
	private StatDto dto2;
	private StatDto dto3;
	private StatDto dto4;
	private StatDto dto5;
	private StatDto dto6;
	private StatDto dto7;
	private StatDto dto8;
	private StatDto dto9;
	private StatDto dto10;
	private JTable table;
	private JTable table1;
	private JTable table2;
	static int month1;
	
	public Statistic() {

		ArrayList list = new ArrayList();

		StatDao dao = new StatDao();
		dto = dao.count();
		list = dao.list();
		dto1 = dao.espresso();
		dto2 = dao.americano();
		dto3 = dao.caffelatte();
		dto4 = dao.vienacoffee();
		dto5 = dao.chocofrapuccino();
		dto6 = dao.espressosale();
		dto7 = dao.americanosale();
		dto8 = dao.caffelattesale();
		dto9 = dao.vienacoffeesale();
		dto10 = dao.chocofrapuccinosale();
		

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
				{"Caffelatte", dto1.getCaffelatteTotal(), dto1.getCaffelatteSum()},
				{"Vienacoffee", dto1.getVienacoffeeTotal(), dto1.getVienacoffeeSum()},
				{"Chocofrapuccino", dto1.getChocofrapuccinoTotal(), dto1.getChocofrapuccinoTotal()}
		};
		tmodel = new DefaultTableModel(header1, 0);		
		tmodel.addRow(contents1[0]);
		tmodel.addRow(contents1[1]);
		tmodel.addRow(contents1[2]);
		tmodel.addRow(contents1[3]);
		tmodel.addRow(contents1[4]);
		table1 = new JTable(tmodel);
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(715, 146, 337, 167);
		f.getContentPane().add(scrollPane1);
		scrollPane1.setViewportView(table1);
		
		String [] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		
		JComboBox comboBoxMonth = new JComboBox(month);
		comboBoxMonth.setBounds(715, 109, 63, 27);
		f.getContentPane().add(comboBoxMonth);
		
		JButton buttonSearch = new JButton("검색");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				month1 = Integer.parseInt(comboBoxMonth.getSelectedItem().toString());
					for (int i = 4; i >= 0; i--) {
						tmodel.removeRow(i);
					}
					dto1 = dao.espresso();
					dto2 = dao.americano();
					dto3 = dao.caffelatte();
					dto4 = dao.vienacoffee();
					dto5 = dao.chocofrapuccino();
					dto6 = dao.espressosale();
					dto7 = dao.americanosale();
					dto8 = dao.caffelattesale();
					dto9 = dao.vienacoffeesale();
					dto10 = dao.chocofrapuccinosale();
					contents1[0][1] = dto1.getEspressoTotal() - dto6.getEspressosalequan();
					contents1[0][2] = dto1.getEspressoSum() - dto6.getEspressosale();
					contents1[1][1] = dto2.getAmericanoTotal() - dto7.getAmericanosalequan();
					contents1[1][2] = dto2.getAmericanoSum() - dto7.getAmericanosale();
					contents1[2][1] = dto3.getCaffelatteTotal() - dto8.getCaffelattesalequan();
					contents1[2][2] = dto3.getCaffelatteSum() - dto8.getCaffelattesale();
					contents1[3][1] = dto4.getVienacoffeeTotal() - dto9.getVienacoffeesalequan();
					contents1[3][2] = dto4.getVienacoffeeSum() - dto9.getVienacoffeesale();
					contents1[4][1] = dto5.getChocofrapuccinoTotal() - dto10.getChocofrapuccinosalequan();
					contents1[4][2] = dto5.getChocofrapuccinoSum() - dto10.getChocofrapuccinosale();
					tmodel.addRow(contents1[0]);
					tmodel.addRow(contents1[1]);
					tmodel.addRow(contents1[2]);
					tmodel.addRow(contents1[3]);
					tmodel.addRow(contents1[4]);
				}//검색 버튼
		});
		buttonSearch.setBounds(790, 109, 63, 27);
		f.getContentPane().add(buttonSearch);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(715, 360, 337, 232);
		f.getContentPane().add(scrollPane2);
		
		Object [] gender = {"male", "female"};
		
		JComboBox comboBox = new JComboBox(gender);
		comboBox.setBounds(715, 323, 63, 27);
		f.getContentPane().add(comboBox);
		
		JButton buttonSearch2 = new JButton("검색");
		buttonSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}//검색 버튼
		});
		buttonSearch2.setBounds(790, 323, 63, 27);
		f.getContentPane().add(buttonSearch2);
		
		table2 = new JTable();
		scrollPane2.setViewportView(table2);
		
		
		f.setVisible(true);
		}

		public static void main(String[] args) {
			Statistic stat = new Statistic();
		}
}