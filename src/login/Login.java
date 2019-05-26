package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pos.Main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {
	JButton b1;
	
	private static JTextField t1;
	private JPasswordField pF1;
	public Login() {
		JFrame f = new JFrame("카페 포스 시스템");
		f.setBackground(new Color(192, 192, 192));
		f.getContentPane().setBackground(new Color(0, 102, 51));
		f.setBounds(400, 100, 1080, 800);
		f.setResizable(false);
		f.setSize(1080, 800);
		f.getContentPane().setLayout(null);
		
		t1 = new JTextField();
		t1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					b1.doClick();
			}
		});
		t1.setFont(new Font("굴림", Font.PLAIN, 20));
		t1.setBounds(459, 380, 302, 38);
		f.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel la1 = new JLabel("\uC544\uC774\uB514");
		la1.setForeground(new Color(255, 255, 255));
		la1.setFont(new Font("굴림", Font.BOLD, 26));
		la1.setBounds(311, 377, 110, 38);
		f.getContentPane().add(la1);
		
		JLabel la2 = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		la2.setForeground(new Color(255, 255, 255));
		la2.setFont(new Font("굴림", Font.BOLD, 26));
		la2.setBounds(311, 445, 110, 38);
		f.getContentPane().add(la2);
		
		JLabel la3 = new JLabel("\uCE74\uD398 POS \uC2DC\uC2A4\uD15C");
		la3.setForeground(new Color(255, 255, 255));
		la3.setBackground(new Color(102, 205, 170));
		la3.setFont(new Font("굴림", Font.BOLD, 90));
		la3.setBounds(174, 168, 765, 180);
		f.getContentPane().add(la3);
		
		JButton b2 = new JButton("\uC885\uB8CC\uD558\uAE30");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "정말 종료하시겠습니까?");
				if (confirm == 0) {
					System.exit(0);
				}
				
			}//종료
		});
		b2.setFont(new Font("굴림", Font.PLAIN, 24));
		b2.setBounds(450, 519, 153, 37);
		f.getContentPane().add(b2);
		
		b1 = new JButton("\uB85C\uADF8\uC778");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginDao dao = new LoginDao();
				LoginDto dto = dao.info();
				
				String id = dto.getId();
				String pw = dto.getPw();
				
				String id2 = t1.getText();
				String pw2 = pF1.getText();
				if(id.equals(id2) && pw.equals(pw2)) {
					Main main = new Main();
					f.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "다시 입력해주세요");
				}
			}//로그인
		});
		b1.setFont(new Font("굴림", Font.PLAIN, 24));
		b1.setBounds(311, 519, 134, 37);
		f.getContentPane().add(b1);
		
		pF1 = new JPasswordField();
		pF1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					b1.doClick();
			}
		});
		pF1.setBounds(459, 451, 302, 38);
		f.getContentPane().add(pF1);
		
		JButton b3 = new JButton("\uC815\uBCF4\uC218\uC815");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoUpdate info = new InfoUpdate();
			}//정보 수정
		});
		b3.setFont(new Font("굴림", Font.PLAIN, 24));
		b3.setBounds(608, 519, 153, 37);
		f.getContentPane().add(b3);
		
		f.setVisible(true);
		
	}
	
	
	
	public static void main(String[] args) {
		Login log = new Login();
		
	}
}