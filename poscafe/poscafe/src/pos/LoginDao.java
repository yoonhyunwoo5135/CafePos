package pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginDao {
	
	String url;
	String user;
	String password;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	LoginDto dto = new LoginDto();
	
	public LoginDto info() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� ���� ����...");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			System.out.println("�����ͺ��̽� ���� ����...");
			
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from admin";
			ps = con.prepareStatement(sql);
			
			System.out.println("sql�� ���� ����...");
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				dto = new LoginDto();
				String id = rs.getString(1);
				String pw = rs.getString(2);
				
				dto.setId(id);
				dto.setPw(pw);
			}
			System.out.println("sql�� ���� �Ϸ�...");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return dto;
	
	}

}
