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
			System.out.println("드라이버 설정 성공...");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			System.out.println("데이터베이스 연결 성공...");
			
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from admin";
			ps = con.prepareStatement(sql);
			
			System.out.println("sql문 설정 성공...");
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				dto = new LoginDto();
				String id = rs.getString(1);
				String pw = rs.getString(2);
				
				dto.setId(id);
				dto.setPw(pw);
			}
			System.out.println("sql문 전송 완료...");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return dto;
	
	}

}