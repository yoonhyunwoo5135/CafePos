package statistic;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class Statistic {
	static DefaultTableModel tmodel;
	private StatDto dto;
	private JTable table;
	
	public Statistic() {
		
		ArrayList list = new ArrayList();
		
		StatDao dao = new StatDao();
		dto = dao.count();
		list = dao.list();
		
		JFrame f = new JFrame();
		f.setSize(1080, 800);
		f.setBounds(400, 200, 1080, 800);
		f.getContentPane().setLayout(null);
		
		String [] header = {"결제번호", "메뉴명", "가격", "쿠폰여부", "성별", "날짜"};
		Object[][] contents = new Object[dto.getCount()][6];
		for (int i = 0; i < contents.length; i++) {				
				StatDto dto = (StatDto)list.get(i);
				contents[i][0] = dto.getPin();
				contents[i][1] = dto.getMenu();
				contents[i][2] = dto.getPrice();
				contents[i][3] = dto.getCuppon();
				contents[i][4] = dto.getJender();
				contents[i][5] = dto.getDate();
		}
		tmodel = new DefaultTableModel(header, 0);
		for (int j = 0; j < contents.length; j++) {
			tmodel.addRow(contents[j]);			
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 109, 679, 483);
		f.getContentPane().add(scrollPane);
		table = new JTable(tmodel);
		scrollPane.setViewportView(table);
		
		
		
		
		
		f.setVisible(true);
		
		
	}
	
	
	public static void main(String[] args) {
		Statistic cal = new Statistic();
	}
}