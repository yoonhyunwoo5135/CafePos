package statistic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StatDao {
	private String url;
	private String user;
	private String password;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private StatDto dto;
	private int asum;
	private int bsum;
	
	public StatDto count() {
			//1.드라이버 설정
			dto = new StatDto();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
	
			//3.SQL문 설정(객체화)
			String sql = "select count(*) from paybill";
			ps = con.prepareStatement(sql);
			//4.SQL문 전송
			rs = ps.executeQuery();
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
		dto = new StatDto();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			//3.SQL문 설정(객체화)
			String sql = "select * from paybill";
			ps = con.prepareStatement(sql);
			//4.SQL문 전송
			rs = ps.executeQuery();
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int pin = rs.getInt(1);
				String menu = rs.getString(2);
				int price = rs.getInt(3);
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				String coupon = rs.getString(6);
				String gender = rs.getString(7);
				String buydate = rs.getString(8);
				dto.setPin(pin);
				dto.setMenu(menu);
				dto.setPrice(price);
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				dto.setCoupon(coupon);
				dto.setGender(gender);
				dto.setBuydate(buydate);
				list.add(dto);
			}//if close
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	return list;
	}
	public StatDto espresso() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
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
			String sql = "select * from paybill where menu = 'espresso' and extract(month from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			System.out.println("3. SQL문 객체화 OK...");
			//4.SQL문 전송
			rs = ps.executeQuery();
			System.out.println("4. SQL문 전송 OK...");
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				
				
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				
				sum.add(dto);
			}//while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				asum += dto.getTot_price();
				
			}
			dto.setEspressoSum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setEspressoTotal(bsum);
		} catch (Exception e) {
			System.out.println("DB처리 중 에러발생...");
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto americano() {
		ArrayList sum = new ArrayList();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			//3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'Americano' and extract(month from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			//4.SQL문 전송
			rs = ps.executeQuery();
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				
				
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				
				sum.add(dto);
			}//while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				asum += dto.getTot_price();
				
			}
			dto.setAmericanoSum(asum);
			
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setAmericanoTotal(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto caffelatte() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			//3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'caffelatte' and extract(month from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			//4.SQL문 전송
			rs = ps.executeQuery();
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				
				
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				
				sum.add(dto);
			}//while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				asum += dto.getTot_price();
				
			}
			dto.setCaffelatteSum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setCaffelatteTotal(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	public StatDto vienacoffee() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			//3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'vienacoffee' and extract(month from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			//4.SQL문 전송
			rs = ps.executeQuery();
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				
				
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				
				sum.add(dto);
			}//while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				asum += dto.getTot_price();
				
			}
			dto.setVienacoffeeSum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setVienacoffeeTotal(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	public StatDto chocofrapuccino() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			//3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'chocofrapuccino' and extract(month from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			//4.SQL문 전송
			rs = ps.executeQuery();
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				
				
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				
				sum.add(dto);
			}//while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				asum += dto.getTot_price();
				
			}
			dto.setChocofrapuccinoSum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setChocofrapuccinoTotal(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto espressosale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			//3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'espresso' and extract(month from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			//4.SQL문 전송
			rs = ps.executeQuery();
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				
				
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				
				sum.add(dto);
			}//while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				asum += dto.getTot_price();
				
			}
			dto.setEspressosale(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setEspressosalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto americanosale() {
		ArrayList sum = new ArrayList();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			//3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'Americano' and extract(month from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			//4.SQL문 전송
			rs = ps.executeQuery();
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				
				
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				
				sum.add(dto);
			}//while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				asum += dto.getTot_price();
				
			}
			dto.setAmericanosale(asum);
			
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setAmericanosalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto caffelattesale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			//3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'caffelatte' and extract(month from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			//4.SQL문 전송
			rs = ps.executeQuery();
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				
				
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				
				sum.add(dto);
			}//while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				asum += dto.getTot_price();
				
			}
			dto.setCaffelattesale(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setCaffelattesalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	public StatDto vienacoffeesale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			//3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'vienacoffee' and extract(month from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			//4.SQL문 전송
			rs = ps.executeQuery();
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				
				
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				
				sum.add(dto);
			}//while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				asum += dto.getTot_price();
				
			}
			dto.setVienacoffeesale(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setVienacoffeesalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	public StatDto chocofrapuccinosale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			//1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			//2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			//3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'chocofrapuccino' and extract(month from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			//4.SQL문 전송
			rs = ps.executeQuery();
			//SQL문의 결과가 있으면, 받아서 처리
			while(rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				
				
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				
				sum.add(dto);
			}//while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				asum += dto.getTot_price();
				
			}
			dto.setChocofrapuccinosale(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setChocofrapuccinosalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
}