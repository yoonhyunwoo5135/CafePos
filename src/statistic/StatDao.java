package statistic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import inven.InvenDto;

public class StatDao {
	private String url;
	private String user;
	private String password;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private StatDto dto;
	
	public StatDto count() {
			//1.드라이버 설정
			dto = new StatDto();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1. 드라이버 설정 ok...");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			System.out.println("2. DB연결 OK...");
			//3.SQL문 설정(객체화)
			String sql = "select count(*) from paybill";
			ps = con.prepareStatement(sql);
			System.out.println("3. SQL문 객체화 OK...");
			//4.SQL문 전송
			rs = ps.executeQuery();
			System.out.println("4. SQL문 전송 OK...");
			//SQL문의 결과가 있으면, 받아서 처리
			
			while(rs.next()) {
				int count2 = rs.getInt(1);
				dto.setCount(count2);
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return dto;
	}
	public ArrayList list() {
		ArrayList list = new ArrayList();
		StatDto dto = new StatDto();
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
			String sql = "select * from paybill";
			ps = con.prepareStatement(sql);
			System.out.println("3. SQL문 객체화 OK...");
			//4.SQL문 전송
			rs = ps.executeQuery();
			System.out.println("4. SQL문 전송 OK...");
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int pin = rs.getInt(1);
				String menu = rs.getString(2);
				int price = rs.getInt(3);
				String cuppon = rs.getString(4);
				String jender = rs.getString(5);
				int date = rs.getInt(6);
				dto.setPin(pin);
				dto.setMenu(menu);
				dto.setPrice(price);
				dto.setCuppon(cuppon);
				dto.setJender(jender);
				dto.setDate(date);
				list.add(dto);
			}//if close
			
		} catch (Exception e) {
			System.out.println("DB처리 중 에러발생...");
			e.printStackTrace();
		}
	return list;
	}
}