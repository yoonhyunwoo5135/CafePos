package statistic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.net.ssl.SSLEngineResult.Status;
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
	static DefaultTableModel tmodel1;
	static DefaultTableModel tmodel2;
	StatDto dto;
	StatDto dto1;
	StatDto dto2;
	StatDto dto3;
	StatDto dto4;
	StatDto dto5;
	StatDto dto6;
	StatDto dto7;
	StatDto dto8;
	StatDto dto9;
	StatDto dto10;
	StatDto dto11;
	StatDto dto12;
	StatDto dto13;
	StatDto dto14;
	StatDto dto15;
	StatDto dto16;
	StatDto dto17;
	StatDto dto18;
	StatDto dto19;
	StatDto dto20;
	StatDto dto21;
	private JTable table;
	private JTable table1;
	private JTable table2;
	static int month1;
	static int month3;
	static String pin;
	static String menu;
	static String coupon;
	static int paytableRow;
	static JTextField t1;
	JLabel labelInsertAlert;
	JLabel labelDeleteAlert;
	ArrayList list = new ArrayList();
	ArrayList pin1 = new ArrayList();
	ArrayList menu1 = new ArrayList();
	ArrayList coupon1 = new ArrayList();
	StatDao dao = new StatDao();

	public Statistic() {


		dto = dao.count();
		list = dao.list();
		dto1 = dao.espresso();
		dto2 = dao.americano();
		dto3 = dao.caffelatte();
		dto4 = dao.viennacoffee();
		dto5 = dao.chocofrappuccino();
		dto6 = dao.espressosale();
		dto7 = dao.americanosale();
		dto8 = dao.caffelattesale();
		dto9 = dao.viennacoffeesale();
		dto10 = dao.chocofrappuccinosale();
		dto11 = dao.espressomale();
		dto12 = dao.americanomale();
		dto13 = dao.caffelattemale();
		dto14 = dao.viennacoffeemale();
		dto15 = dao.chocofrappuccinomale();
		dto16 = dao.espressofemale();
		dto17 = dao.americanofemale();
		dto18 = dao.caffelattefemale();
		dto19 = dao.viennacoffeefemale();
		dto20 = dao.chocofrappuccinofemale();
		
		
		JFrame f = new JFrame();
		f.setSize(1080, 800);
		f.setBounds(400, 200, 1080, 600);


		String[] header = { "No", "결제번호", "메뉴명", "가격", "수량", "합계", "쿠폰여부", "성별", "날짜" };
		Object[][] contents = new Object[dto.getCount()][9];
		for (int i = 0; i < contents.length; i++) {
			StatDto dto = (StatDto) list.get(i);			
			contents[i][0] = ++paytableRow;
			contents[i][1] = dto.getPin();
			contents[i][2] = dto.getMenu();
			contents[i][3] = dto.getPrice();
			contents[i][4] = dto.getQuantity();
			contents[i][5] = dto.getTot_price();
			contents[i][6] = dto.getCoupon();
			contents[i][7] = dto.getGender();
			contents[i][8] = dto.getBuydate();
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
		scrollPane.setBounds(24, 79, 706, 446);
		f.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(30);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);// 행 길이
		

		String[] search = { "전체", "결제번호", "메뉴명", "쿠폰사용여부" };
		
		
		JComboBox comboBoxSearch = new JComboBox(search);
		comboBoxSearch.setBounds(24, 42, 94, 27);
		f.getContentPane().add(comboBoxSearch);
		
		t1 = new JTextField();
		t1.setBounds(130, 42, 196, 27);
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
					for (int i = 0; i < dto.getCount(); i++) {
						StatDto dto = (StatDto) list.get(i);						
						contents[i][0] = ++paytableRow;
						contents[i][1] = dto.getPin();
						contents[i][2] = dto.getMenu();
						contents[i][3] = dto.getPrice();
						contents[i][4] = dto.getQuantity();
						contents[i][5] = dto.getTot_price();
						contents[i][6] = dto.getCoupon();
						contents[i][7] = dto.getGender();
						contents[i][8] = dto.getBuydate();
					}
					tmodel = (DefaultTableModel) table.getModel();					
					for (int j = 0; j < dto.getCount(); j++) {
						tmodel.addRow(contents[j]);
					}
					
				}
				else if(selected == "결제번호") {
					pin = t1.getText();
					for (int i = paytableRow - 1; i >= 0; i--) {
						tmodel.removeRow(--paytableRow);
					}
					paytableRow = 0;
					StatDto dto = dao.pincount();		
					Object [] obj = new Object[9];
					pin1 = dao.selectpin(t1.getText());
						for (int i = 0; i < dto.getPincount(); i++) {
							StatDto pdto = (StatDto)pin1.get(i);							
							obj[0] = ++paytableRow;
							obj[1] = pdto.getPin();
							obj[2] = pdto.getMenu();
							obj[3] = pdto.getPrice();
							obj[4] = pdto.getQuantity();
							obj[5] = pdto.getTot_price();
							obj[6] = pdto.getCoupon();
							obj[7] = pdto.getGender();
							obj[8] = pdto.getBuydate();
							System.out.println(dto.getPincount());
							tmodel.addRow(obj);
						}			

				}
				else if(selected == "메뉴명") {
					menu = t1.getText();
					for (int i = paytableRow - 1; i >= 0; i--) {
						tmodel.removeRow(--paytableRow);
					}
					paytableRow = 0;
					StatDto dto = dao.menucount();		
					Object [] obj = new Object[9];
					menu1 = dao.selectmenu(t1.getText());
						for (int i = 0; i < dto.getMenucount(); i++) {
							StatDto mdto = (StatDto)menu1.get(i);							
							obj[0] = ++paytableRow;
							obj[1] = mdto.getPin();
							obj[2] = mdto.getMenu();
							obj[3] = mdto.getPrice();
							obj[4] = mdto.getQuantity();
							obj[5] = mdto.getTot_price();
							obj[6] = mdto.getCoupon();
							obj[7] = mdto.getGender();
							obj[8] = mdto.getBuydate();
							System.out.println(dto.getMenucount());
							tmodel.addRow(obj);
						}			

				}
				else if(selected == "쿠폰사용여부") {
					coupon = t1.getText();
					for (int i = paytableRow - 1; i >= 0; i--) {
						tmodel.removeRow(--paytableRow);
					}
					paytableRow = 0;
					StatDto dto = dao.couponcount();		
					Object [] obj = new Object[9];
					coupon1 = dao.selectcoupon(t1.getText());
						for (int i = 0; i < dto.getCouponcount(); i++) {
							StatDto cdto = (StatDto)coupon1.get(i);							
							obj[0] = ++paytableRow;
							obj[1] = cdto.getPin();
							obj[2] = cdto.getMenu();
							obj[3] = cdto.getPrice();
							obj[4] = cdto.getQuantity();
							obj[5] = cdto.getTot_price();
							obj[6] = cdto.getCoupon();
							obj[7] = cdto.getGender();
							obj[8] = cdto.getBuydate();
							System.out.println(dto.getCouponcount());
							tmodel.addRow(obj);
						}			

				}
			}// pin검색
		});
		buttonSearch3.setBounds(338, 42, 63, 27);
		f.getContentPane().add(buttonSearch3);
		

		String[] header1 = { "메뉴명", "판매량", "총매출" };
		Object[][] contents1 = { { "Espresso", dto1.getEspressoTotal(), dto1.getEspressoSum() },
				{ "Americano", dto2.getAmericanoTotal(), dto2.getAmericanoSum() },
				{ "Caffelatte", dto3.getCaffelatteTotal(), dto3.getCaffelatteSum() },
				{ "viennacoffee", dto4.getviennacoffeeTotal(), dto4.getviennacoffeeSum() },
				{ "Chocofrappuccino", dto5.getChocofrappuccinoTotal(), dto5.getChocofrappuccinoSum() } };
		tmodel1 = new DefaultTableModel(header1, 0);
		tmodel1.addRow(contents1[0]);// 테이블 내용 최신화를 위한 기본 세팅
		tmodel1.addRow(contents1[1]);
		tmodel1.addRow(contents1[2]);
		tmodel1.addRow(contents1[3]);
		tmodel1.addRow(contents1[4]);
		table1 = new JTable(tmodel1);
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(742, 79, 310, 167);
		f.getContentPane().add(scrollPane1);
		scrollPane1.setViewportView(table1);

		String[] month = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

		JComboBox comboBoxMonth = new JComboBox(month);
		comboBoxMonth.setBounds(742, 42, 63, 27);
		f.getContentPane().add(comboBoxMonth);

		JButton buttonSearch = new JButton("검색");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				month1 = Integer.parseInt(comboBoxMonth.getSelectedItem().toString());
				for (int i = 4; i >= 0; i--) {
					tmodel1.removeRow(i);
				}
				dto1 = dao.espresso();
				dto2 = dao.americano();
				dto3 = dao.caffelatte();
				dto4 = dao.viennacoffee();
				dto5 = dao.chocofrappuccino();
				dto6 = dao.espressosale();
				dto7 = dao.americanosale();
				dto8 = dao.caffelattesale();
				dto9 = dao.viennacoffeesale();
				dto10 = dao.chocofrappuccinosale();
				contents1[0][1] = dto1.getEspressoTotal() - dto6.getEspressosalequan();
				contents1[0][2] = dto1.getEspressoSum() - dto6.getEspressosale();
				contents1[1][1] = dto2.getAmericanoTotal() - dto7.getAmericanosalequan();
				contents1[1][2] = dto2.getAmericanoSum() - dto7.getAmericanosale();
				contents1[2][1] = dto3.getCaffelatteTotal() - dto8.getCaffelattesalequan();
				contents1[2][2] = dto3.getCaffelatteSum() - dto8.getCaffelattesale();
				contents1[3][1] = dto4.getviennacoffeeTotal() - dto9.getviennacoffeesalequan();
				contents1[3][2] = dto4.getviennacoffeeSum() - dto9.getviennacoffeesale();
				contents1[4][1] = dto5.getChocofrappuccinoTotal() - dto10.getChocofrappuccinosalequan();
				contents1[4][2] = dto5.getChocofrappuccinoSum() - dto10.getChocofrappuccinosale();// 할인 적용 및 통계 수량
				tmodel1.addRow(contents1[0]);
				tmodel1.addRow(contents1[1]);
				tmodel1.addRow(contents1[2]);
				tmodel1.addRow(contents1[3]);
				tmodel1.addRow(contents1[4]);
			}// 검색 버튼
		});
		buttonSearch.setBounds(817, 42, 63, 27);
		f.getContentPane().add(buttonSearch);

		String[] header2 = { "메뉴명", "판매량", "총매출" };
		Object[][] contents2 = { { "Espresso", dto11.getEspressoMalequan(), dto11.getEspressoMalesum() },
				{ "Americano", dto12.getAmericanoMalequan(), dto12.getAmericanoMalesum() },
				{ "Caffelatte", dto13.getCaffelatteMalequan(), dto13.getCaffelatteMalesum() },
				{ "viennacoffee", dto14.getviennacoffeeMalequan(), dto14.getviennacoffeeMalesum() },
				{ "Chocofrappuccino", dto15.getChocofrappuccinoMalequan(), dto15.getChocofrappuccinoMalesum() } };
		tmodel2 = new DefaultTableModel(header2, 0);
		tmodel2.addRow(contents2[0]);// 테이블 내용 최신화를 위한 기본 세팅
		tmodel2.addRow(contents2[1]);
		tmodel2.addRow(contents2[2]);
		tmodel2.addRow(contents2[3]);
		tmodel2.addRow(contents2[4]);
		table2 = new JTable(tmodel2);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(742, 293, 310, 232);
		f.getContentPane().add(scrollPane2);
		scrollPane2.setViewportView(table2);

		String[] month2 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

		JComboBox comboBoxMonth2 = new JComboBox(month2);
		comboBoxMonth2.setBounds(742, 256, 63, 27);
		f.getContentPane().add(comboBoxMonth2);

		Object[] gender = { "male", "female" };

		JComboBox comboBoxgender = new JComboBox(gender);
		comboBoxgender.setBounds(817, 256, 63, 27);
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
					dto14 = dao.viennacoffeemale();
					dto15 = dao.chocofrappuccinomale();

					contents2[0][1] = dto11.getEspressoMalequan() - dto6.getEspressosalequan();
					contents2[0][2] = dto11.getEspressoMalesum() - dto6.getEspressosale();
					contents2[1][1] = dto12.getAmericanoMalequan() - dto7.getAmericanosalequan();
					contents2[1][2] = dto12.getAmericanoMalesum() - dto7.getAmericanosale();
					contents2[2][1] = dto13.getCaffelatteMalequan() - dto8.getCaffelattesalequan();
					contents2[2][2] = dto13.getCaffelatteMalesum() - dto8.getCaffelattesale();
					contents2[3][1] = dto14.getviennacoffeeMalequan() - dto9.getviennacoffeesalequan();
					contents2[3][2] = dto14.getviennacoffeeMalesum() - dto9.getviennacoffeesale();
					contents2[4][1] = dto15.getChocofrappuccinoMalequan() - dto10.getChocofrappuccinosalequan();
					contents2[4][2] = dto15.getChocofrappuccinoMalesum() - dto10.getChocofrappuccinosale();// 할인 적용 및 통계
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
					dto19 = dao.viennacoffeefemale();
					dto20 = dao.chocofrappuccinofemale();

					contents2[0][1] = dto16.getEspressoFemalequan() - dto6.getEspressosalequan();
					contents2[0][2] = dto16.getEspressoFemalesum() - dto6.getEspressosale();
					contents2[1][1] = dto17.getAmericanoFemalequan() - dto7.getAmericanosalequan();
					contents2[1][2] = dto17.getAmericanoFemalesum() - dto7.getAmericanosale();
					contents2[2][1] = dto18.getCaffelatteFemalequan() - dto8.getCaffelattesalequan();
					contents2[2][2] = dto18.getCaffelatteFemalesum() - dto8.getCaffelattesale();
					contents2[3][1] = dto19.getviennacoffeeFemalequan() - dto9.getviennacoffeesalequan();
					contents2[3][2] = dto19.getviennacoffeeFemalesum() - dto9.getviennacoffeesale();
					contents2[4][1] = dto20.getChocofrappuccinoFemalequan() - dto10.getChocofrappuccinosalequan();
					contents2[4][2] = dto20.getChocofrappuccinoFemalesum() - dto10.getChocofrappuccinosale();// 할인 적용 및 통계
																											// 수량

					tmodel2.addRow(contents2[0]);
					tmodel2.addRow(contents2[1]);
					tmodel2.addRow(contents2[2]);
					tmodel2.addRow(contents2[3]);
					tmodel2.addRow(contents2[4]);

				}
			}// 검색 버튼
		});
		buttonSearch2.setBounds(892, 256, 63, 27);
		f.getContentPane().add(buttonSearch2);

		f.setVisible(true);
	}

	public static void main(String[] args) {
		Statistic stat = new Statistic();
	}
}