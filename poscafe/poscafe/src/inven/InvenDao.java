package inven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InvenDao {
	String url;
	String user;
	String password;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public void insert(InvenDto dto) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("드라이버 설정 성공...");
		url = "jdbc:mysql://localhost:3306/cafe";
		user = "root";
		password = "1234";
		System.out.println("데이터베이스 연결 성공...");
		
		con = DriverManager.getConnection(url, user, password);
		String sql = "insert into inven values(?,?,?,?,?,?)";
		ps = con.prepareStatement(sql);
		
		ps.setInt(1, dto.getBean());
		ps.setInt(2, dto.getMilk());
		ps.setInt(3, dto.getChoco());
		ps.setInt(4, dto.getCream());
		ps.setInt(5, dto.getCup());
		ps.setInt(6, dto.getStraw());
		System.out.println("sql문 설정 성공...");
		
		ps.executeUpdate();
		System.out.println("sql문 전송 완료...");
	}
	
	public void update(InvenDto dto) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("드라이버 설정 성공...");
		url = "jdbc:mysql://localhost:3306/cafe";
		user = "root";
		password = "1234";
		System.out.println("데이터베이스 연결 성공...");
		
		con = DriverManager.getConnection(url, user, password);
		String sql = "update inven set bean = ?, milk = ?, choco = ?, cream = ?, cup = ?, straw = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, dto.getBean());
		ps.setInt(2, dto.getMilk());
		ps.setInt(3, dto.getChoco());
		ps.setInt(4, dto.getCream());
		ps.setInt(5, dto.getCup());
		ps.setInt(6, dto.getStraw());
		System.out.println("sql문 설정 성공...");
		
		ps.executeUpdate();
		System.out.println("sql문 전송 완료...");
	}
	
	public void use(int bean, int milk, int choco, int cream, int cup, int straw) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("드라이버 설정 성공...");
		url = "jdbc:mysql://localhost:3306/cafe";
		user = "root";
		password = "1234";
		System.out.println("데이터베이스 연결 성공...");
		
		con = DriverManager.getConnection(url, user, password);
		String sql = "update inven set bean = ?, milk = ?, choco = ?, cream = ?, cup = ?, straw = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, bean);
		ps.setInt(2, milk);
		ps.setInt(3, choco);
		ps.setInt(4, cream);
		ps.setInt(5, cup);
		ps.setInt(6, straw);
		System.out.println("sql문 설정 성공...");
		
		ps.executeUpdate();
		System.out.println("sql문 전송 완료...");
		
		
	}
	public InvenDto list() {
		
		InvenDto dto = new InvenDto();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1. 드라이버 설정 ok...");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			System.out.println("2. DB연결 OK...");
			//3.SQL문 설정(객체화)
			String sql = "select * from inven";
			ps = con.prepareStatement(sql);
			System.out.println("3. SQL문 객체화 OK...");
			//4.SQL문 전송
			rs = ps.executeQuery();
			System.out.println("4. SQL문 전송 OK...");
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new InvenDto();
				int bean = rs.getInt(1);
				int milk = rs.getInt(2);
				int choco = rs.getInt(3);
				int cream = rs.getInt(4);
				int cup = rs.getInt(5);
				int straw = rs.getInt(6);
				dto.setBean(bean);
				dto.setMilk(milk);
				dto.setChoco(choco);
				dto.setCream(cream);
				dto.setCup(cup);
				dto.setStraw(straw);
			}//while close
			
		} catch (Exception e) {
			System.out.println("DB처리 중 에러발생...");
			e.printStackTrace();
		}
		
		return dto;
	}

	
}
