package statistic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Statistic {
	static DefaultTableModel tmodel;
	static DefaultTableModel tmodel2;
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
	private StatDto dto11;
	private StatDto dto12;
	private StatDto dto13;
	private StatDto dto14;
	private StatDto dto15;
	private StatDto dto16;
	private StatDto dto17;
	private StatDto dto18;
	private StatDto dto19;
	private StatDto dto20;
	private StatDto dto21;
	private JTable table;
	private JTable table1;
	private JTable table2;
	static int month1;
	static int month3;
	static String pin;
	static int paytableRow;
	static JTextField t1;
	JLabel labelInsertAlert;
	JLabel labelDeleteAlert;
	ArrayList list = new ArrayList();
	ArrayList pin1 = new ArrayList();

	public Statistic() {


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
		dto11 = dao.espressomale();
		dto12 = dao.americanomale();
		dto13 = dao.caffelattemale();
		dto14 = dao.vienacoffeemale();
		dto15 = dao.chocofrapuccinomale();
		dto16 = dao.espressofemale();
		dto17 = dao.americanofemale();
		dto18 = dao.caffelattefemale();
		dto19 = dao.vienacoffeefemale();
		dto20 = dao.chocofrapuccinofemale();
		dto21 = dao.selectPin(pin);

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
		scrollPane.setBounds(24, 146, 679, 446);
		f.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(30);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);// 행 길이
		

		String[] search = { "전체", "결제번호", "메뉴명", "쿠폰사용여부" };
		
		
		JComboBox comboBoxSearch = new JComboBox(search);
		comboBoxSearch.setBounds(24, 109, 94, 27);
		f.getContentPane().add(comboBoxSearch);
		
		t1 = new JTextField();
		t1.setBounds(130, 109, 196, 27);
		f.getContentPane().add(t1);
		t1.setColumns(10);
		
		JButton buttonSearch3 = new JButton("검색");
		buttonSearch3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String selected = comboBoxSearch.getSelectedItem().toString();
				if (selected == "전체") {
					for (int i = paytableRow - 1; i >= 0; i--) {
						tmodel.removeRow(--paytableRow);
					}
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
					
				}
				else if(selected == "결제번호") {
					paytableRow = 0;
					for (int i = paytableRow - 1; i >= 0; i--) {
						tmodel.removeRow(--paytableRow);
					}
						dto21 = dao.selectPin(t1.getText());
						Object[] obj = new Object[7];
						for (int j = 0; j < search.length; j++) {
						obj[j][0] = dto.getPin();
						obj[j][1] = dto.getMenu();
						obj[j][2] = dto.getPrice();
						obj[j][4] = dto.getQuantity();
						obj[j][5] = dto.getTot_price();
						obj[j][6] = dto.getCoupon();
						obj[j][7] = dto.getGender();
						obj[j][8] = dto.getBuydate();
						}			
						tmodel = (DefaultTableModel) table.getModel();
						tmodel.addRow(contents[j]);
					
				}
				
			}// pin검색
		});
		buttonSearch3.setBounds(338, 109, 63, 27);
		f.getContentPane().add(buttonSearch3);
		

		String[] header1 = { "메뉴명", "판매량", "총매출" };
		Object[][] contents1 = { { "Espresso", dto1.getEspressoTotal(), dto1.getEspressoSum() },
				{ "Americano", dto2.getAmericanoTotal(), dto2.getAmericanoSum() },
				{ "Caffelatte", dto3.getCaffelatteTotal(), dto3.getCaffelatteSum() },
				{ "Vienacoffee", dto4.getVienacoffeeTotal(), dto4.getVienacoffeeSum() },
				{ "Chocofrapuccino", dto5.getChocofrapuccinoTotal(), dto5.getChocofrapuccinoSum() } };
		tmodel = new DefaultTableModel(header1, 0);
		tmodel.addRow(contents1[0]);// 테이블 내용 최신화를 위한 기본 세팅
		tmodel.addRow(contents1[1]);
		tmodel.addRow(contents1[2]);
		tmodel.addRow(contents1[3]);
		tmodel.addRow(contents1[4]);
		table1 = new JTable(tmodel);
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(715, 146, 337, 167);
		f.getContentPane().add(scrollPane1);
		scrollPane1.setViewportView(table1);

		String[] month = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

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
				contents1[4][2] = dto5.getChocofrapuccinoSum() - dto10.getChocofrapuccinosale();// 할인 적용 및 통계 수량
				tmodel.addRow(contents1[0]);
				tmodel.addRow(contents1[1]);
				tmodel.addRow(contents1[2]);
				tmodel.addRow(contents1[3]);
				tmodel.addRow(contents1[4]);
			}// 검색 버튼
		});
		buttonSearch.setBounds(790, 109, 63, 27);
		f.getContentPane().add(buttonSearch);

		String[] header2 = { "메뉴명", "판매량", "총매출" };
		Object[][] contents2 = { { "Espresso", dto11.getEspressoMalequan(), dto11.getEspressoMalesum() },
				{ "Americano", dto12.getAmericanoMalequan(), dto12.getAmericanoMalesum() },
				{ "Caffelatte", dto13.getCaffelatteMalequan(), dto13.getCaffelatteMalesum() },
				{ "Vienacoffee", dto14.getVienacoffeeMalequan(), dto14.getVienacoffeeMalesum() },
				{ "Chocofrapuccino", dto15.getChocofrapuccinoMalequan(), dto15.getChocofrapuccinoMalesum() } };
		tmodel2 = new DefaultTableModel(header2, 0);
		tmodel2.addRow(contents2[0]);// 테이블 내용 최신화를 위한 기본 세팅
		tmodel2.addRow(contents2[1]);
		tmodel2.addRow(contents2[2]);
		tmodel2.addRow(contents2[3]);
		tmodel2.addRow(contents2[4]);
		table2 = new JTable(tmodel2);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(715, 360, 337, 232);
		f.getContentPane().add(scrollPane2);
		scrollPane2.setViewportView(table2);

		String[] month2 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

		JComboBox comboBoxMonth2 = new JComboBox(month2);
		comboBoxMonth2.setBounds(715, 323, 63, 27);
		f.getContentPane().add(comboBoxMonth2);

		Object[] gender = { "male", "female" };

		JComboBox comboBoxgender = new JComboBox(gender);
		comboBoxgender.setBounds(790, 323, 63, 27);
		f.getContentPane().add(comboBoxgender);

		JButton buttonSearch2 = new JButton("검색");
		buttonSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = comboBoxgender.getSelectedItem().toString();
				if (selected == "male") {
					month3 = Integer.parseInt(comboBoxMonth2.getSelectedItem().toString());
					for (int i = 4; i >= 0; i--) {
						tmodel2.removeRow(i);
					}
					dto11 = dao.espressomale();
					dto12 = dao.americanomale();
					dto13 = dao.caffelattemale();
					dto14 = dao.vienacoffeemale();
					dto15 = dao.chocofrapuccinomale();

					contents2[0][1] = dto11.getEspressoMalequan() - dto6.getEspressosalequan();
					contents2[0][2] = dto11.getEspressoMalesum() - dto6.getEspressosale();
					contents2[1][1] = dto12.getAmericanoMalequan() - dto7.getAmericanosalequan();
					contents2[1][2] = dto12.getAmericanoMalesum() - dto7.getAmericanosale();
					contents2[2][1] = dto13.getCaffelatteMalequan() - dto8.getCaffelattesalequan();
					contents2[2][2] = dto13.getCaffelatteMalesum() - dto8.getCaffelattesale();
					contents2[3][1] = dto14.getVienacoffeeMalequan() - dto9.getVienacoffeesalequan();
					contents2[3][2] = dto14.getVienacoffeeMalesum() - dto9.getVienacoffeesale();
					contents2[4][1] = dto15.getChocofrapuccinoMalequan() - dto10.getChocofrapuccinosalequan();
					contents2[4][2] = dto15.getChocofrapuccinoMalesum() - dto10.getChocofrapuccinosale();// 할인 적용 및 통계
																											// 수량

					tmodel2.addRow(contents2[0]);
					tmodel2.addRow(contents2[1]);
					tmodel2.addRow(contents2[2]);
					tmodel2.addRow(contents2[3]);
					tmodel2.addRow(contents2[4]);

				}

				else if (selected == "female") {
					month3 = Integer.parseInt(comboBoxMonth2.getSelectedItem().toString());
					for (int i = 4; i >= 0; i--) {
						tmodel2.removeRow(i);
					}
					dto16 = dao.espressofemale();
					dto17 = dao.americanofemale();
					dto18 = dao.caffelattefemale();
					dto19 = dao.vienacoffeefemale();
					dto20 = dao.chocofrapuccinofemale();

					contents2[0][1] = dto16.getEspressoFemalequan() - dto6.getEspressosalequan();
					contents2[0][2] = dto16.getEspressoFemalesum() - dto6.getEspressosale();
					contents2[1][1] = dto17.getAmericanoFemalequan() - dto7.getAmericanosalequan();
					contents2[1][2] = dto17.getAmericanoFemalesum() - dto7.getAmericanosale();
					contents2[2][1] = dto18.getCaffelatteFemalequan() - dto8.getCaffelattesalequan();
					contents2[2][2] = dto18.getCaffelatteFemalesum() - dto8.getCaffelattesale();
					contents2[3][1] = dto19.getVienacoffeeFemalequan() - dto9.getVienacoffeesalequan();
					contents2[3][2] = dto19.getVienacoffeeFemalesum() - dto9.getVienacoffeesale();
					contents2[4][1] = dto20.getChocofrapuccinoFemalequan() - dto10.getChocofrapuccinosalequan();
					contents2[4][2] = dto20.getChocofrapuccinoFemalesum() - dto10.getChocofrapuccinosale();// 할인 적용 및 통계
																											// 수량

					tmodel2.addRow(contents2[0]);
					tmodel2.addRow(contents2[1]);
					tmodel2.addRow(contents2[2]);
					tmodel2.addRow(contents2[3]);
					tmodel2.addRow(contents2[4]);

				}
			}// 검색 버튼
		});
		buttonSearch2.setBounds(865, 323, 63, 27);
		f.getContentPane().add(buttonSearch2);

		f.setVisible(true);
	}

	public static void main(String[] args) {
		Statistic stat = new Statistic();
	}
}