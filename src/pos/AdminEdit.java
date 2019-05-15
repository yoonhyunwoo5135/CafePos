package pos;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdminEdit {
	private JTextField tId;
	private JPasswordField pF;
	private JPasswordField pFCheck;
	private JTextField tName;
	private JTextField tAge;
	private JTextField tTel;
	
	public AdminEdit() {
		JFrame f = new JFrame();
		f.setSize(400, 500);
		f.setBounds(400, 300, 400, 500);
		f.getContentPane().setLayout(null);
		
		JLabel labelTitle = new JLabel("\uD68C\uC6D0\uC815\uBCF4\uC218\uC815");
		labelTitle.setFont(new Font("굴림", Font.BOLD, 20));
		labelTitle.setBounds(129, 10, 126, 32);
		f.getContentPane().add(labelTitle);
		
		JLabel labelId = new JLabel("\uC544\uC774\uB514");
		labelId.setFont(new Font("굴림", Font.BOLD, 14));
		labelId.setBounds(12, 55, 96, 32);
		f.getContentPane().add(labelId);
		
		JLabel labelPw = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		labelPw.setFont(new Font("굴림", Font.BOLD, 14));
		labelPw.setBounds(12, 98, 96, 32);
		f.getContentPane().add(labelPw);
		
		JLabel labelPwCheck = new JLabel("\uD328\uC2A4\uC6CC\uB4DC\uD655\uC778");
		labelPwCheck.setFont(new Font("굴림", Font.BOLD, 14));
		labelPwCheck.setBounds(12, 140, 96, 32);
		f.getContentPane().add(labelPwCheck);
		
		JLabel labelName = new JLabel("\uC774\uB984");
		labelName.setFont(new Font("굴림", Font.BOLD, 14));
		labelName.setBounds(12, 251, 96, 32);
		f.getContentPane().add(labelName);
		
		JLabel labelAge = new JLabel("\uB098\uC774");
		labelAge.setFont(new Font("굴림", Font.BOLD, 14));
		labelAge.setBounds(12, 293, 96, 32);
		f.getContentPane().add(labelAge);
		
		JLabel labelTel = new JLabel("\uC804\uD654\uBC88\uD638");
		labelTel.setFont(new Font("굴림", Font.BOLD, 14));
		labelTel.setBounds(12, 335, 96, 32);
		f.getContentPane().add(labelTel);
		
		tId = new JTextField();
		tId.setBounds(120, 60, 151, 25);
		f.getContentPane().add(tId);
		tId.setColumns(10);
		
		pF = new JPasswordField();
		pF.setBounds(120, 105, 151, 25);
		f.getContentPane().add(pF);
		
		pFCheck = new JPasswordField();
		pFCheck.setBounds(120, 147, 151, 25);
		f.getContentPane().add(pFCheck);

		
		tName = new JTextField();
		tName.setColumns(10);
		tName.setBounds(120, 251, 151, 25);
		f.getContentPane().add(tName);
		
		tAge = new JTextField();
		tAge.setColumns(10);
		tAge.setBounds(120, 300, 151, 25);
		f.getContentPane().add(tAge);
		
		tTel = new JTextField();
		tTel.setColumns(10);
		tTel.setBounds(120, 342, 151, 25);
		f.getContentPane().add(tTel);
		
		JButton buttonConfirm = new JButton("\uD655\uC778");
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}//수정
		});
		buttonConfirm.setFont(new Font("굴림", Font.PLAIN, 14));
		buttonConfirm.setBounds(92, 390, 96, 32);
		f.getContentPane().add(buttonConfirm);
		
		JButton buttonCancel = new JButton("\uCDE8\uC18C");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}//취소
		});
		buttonCancel.setFont(new Font("굴림", Font.PLAIN, 14));
		buttonCancel.setBounds(200, 390, 96, 32);
		f.getContentPane().add(buttonCancel);
		
		JButton buttonCheck = new JButton("\uC911\uBCF5\uD655\uC778");
		buttonCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginDao dao = new LoginDao();
				LoginDto dto = dao.info();
				
				if(tId.getText().equals(dto.getId())) {
					JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
				}
				else {
					JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다.");					
				}
			}//중복확인
		});
		buttonCheck.setBounds(275, 61, 97, 23);
		f.getContentPane().add(buttonCheck);
		
		JButton buttonPwCheck = new JButton("PW\uD655\uC778");
		buttonPwCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pF.getText().equals(pFCheck.getText())) {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치합니다.");
				}
				else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				}
			}//PW확인
		});
		buttonPwCheck.setBounds(275, 148, 97, 23);
		f.getContentPane().add(buttonPwCheck);
		
		
		f.setVisible(true);
		
		
	}
	public static void main(String[] args) {
		AdminEdit aE = new AdminEdit();
	}
}