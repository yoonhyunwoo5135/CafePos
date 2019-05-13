package inven;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.ScrollPane;

import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;

public class Inven {
	
	private JTable table;
	
	static JTextField t1;
	static DefaultTableModel tmodel;
	static InvenDao dao;
	static InvenDto dto;
	
	public Inven() {
		JFrame f = new JFrame("카페 포스 시스템");
		f.setSize(800 , 600);
		f.setResizable(false);
		
		dao = new InvenDao();
		dto = dao.list();
				
		ImageIcon icon = new ImageIcon("Mainpont.png");
		JLabel la1 = new JLabel(icon);
		la1.setBounds(37, 8, 300, 80);
		f.getContentPane().add(la1);
		
		String [] header = {"원두", "우유", "초콜릿", "크림", "컵", "빨대"};
		Object[][] contents = new Object[1][6];
		
		contents[0][0] = dto.getBean();
		contents[0][1] = dto.getMilk();
		contents[0][2] = dto.getChoco();
		contents[0][3] = dto.getCream();
		contents[0][4] = dto.getCup();
		contents[0][5] = dto.getStraw();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 98, 720, 143);
		f.getContentPane().add(scrollPane);
		
		tmodel = new DefaultTableModel(header, 0);
		table = new JTable(tmodel);
		tmodel.addRow(contents[0]);
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		
		JButton addbean = new JButton("\uC6D0\uB450\uC8FC\uBB38");
		addbean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddDao dao2 = new AddDao();
				dto = dao2.Addbean();
				
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getBean();
					tmodel.removeRow(0);
					dto = dao.list();
					contents[0][0] = dto.getBean();
					tmodel.addRow(contents[0]);
					
				};
				
				
			}//bean
		});
		addbean.setFont(new Font("굴림", Font.BOLD, 20));
		addbean.setBounds(37, 276, 142, 47);
		f.getContentPane().add(addbean);
		
		JButton addmilk = new JButton("\uC6B0\uC720\uC8FC\uBB38");
		addmilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addmilk();
				
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getMilk();
					tmodel.removeRow(0);
					dto = dao.list();
					contents[0][1] = dto.getMilk();
					tmodel.addRow(contents[0]);
				};
			}//milk
		});
		addmilk.setFont(new Font("굴림", Font.BOLD, 20));
		addmilk.setBounds(37, 350, 142, 47);
		f.getContentPane().add(addmilk);
		
		JButton addchoco = new JButton("\uCD08\uCF5C\uB9BF\uC8FC\uBB38");
		addchoco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addchoco();
				
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getChoco();
					tmodel.removeRow(0);
					dto = dao.list();
					contents[0][2] = dto.getChoco();
					tmodel.addRow(contents[0]);
					
				};
				
			}//choco
		});
		addchoco.setFont(new Font("굴림", Font.BOLD, 20));
		addchoco.setBounds(37, 427, 142, 47);
		f.getContentPane().add(addchoco);
		
		JButton addcream = new JButton("\uD06C\uB9BC\uC8FC\uBB38");
		addcream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addcream();
				
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getCream();
					tmodel.removeRow(0);
					dto = dao.list();
					contents[0][3] = dto.getCream();
					tmodel.addRow(contents[0]);
					
				};
			}//cream
		});
		addcream.setFont(new Font("굴림", Font.BOLD, 20));
		addcream.setBounds(206, 276, 142, 47);
		f.getContentPane().add(addcream);
		
		JButton addcup = new JButton("\uCEF5\uC8FC\uBB38");
		addcup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addcup();
				
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getCup();
					tmodel.removeRow(0);
					dto = dao.list();
					contents[0][4] = dto.getCup();
					tmodel.addRow(contents[0]);
					
				};
			}//cup
		});
		addcup.setFont(new Font("굴림", Font.BOLD, 20));
		addcup.setBounds(206, 350, 142, 47);
		f.getContentPane().add(addcup);
		
		JButton addstraw = new JButton("\uBE68\uB300\uC8FC\uBB38");
		addstraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDao dao2 = new AddDao();
				dto = dao2.Addstraw();
				
				int confirm = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				if(confirm == 0) {
					dto.getStraw();
					tmodel.removeRow(0);
					dto = dao.list();
					contents[0][5] = dto.getStraw();
					tmodel.addRow(contents[0]);
					
				};
			}//straw
		});
		addstraw.setFont(new Font("굴림", Font.BOLD, 20));
		addstraw.setBounds(206, 427, 142, 47);
		f.getContentPane().add(addstraw);
		
		
		t1 = new JTextField();
		t1.setBounds(534, 281, 223, 42);
		f.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel la2 = new JLabel("\uC8FC\uBB38 \uC218\uB7C9");
		la2.setFont(new Font("굴림", Font.BOLD, 20));
		la2.setBounds(411, 279, 97, 40);
		f.getContentPane().add(la2);
		f.getContentPane().setLayout(null);
		
		
		f.setVisible(true);
		
		
	}
	
	
	
	public static void main(String[] args) {
		Inven inven = new Inven();
	
	}
}
