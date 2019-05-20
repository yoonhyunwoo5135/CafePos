package statistic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatDao {
	private String url;
	private String user;
	private String password;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private StatDto dto;
	private int asum = 0;
	private int bsum = 0;
	
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
			asum = sum.size() * 2500;
			
			dto.setEspressosale(asum);
			
			bsum = sum.size();
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
			asum = sum.size() * 3000;
			
			dto.setAmericanosale(asum);
			
			bsum = sum.size();
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
			asum = sum.size() * 3500;
			
			dto.setCaffelattesale(asum);
			
			bsum = sum.size();
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
			asum = sum.size() * 3500;
			
			dto.setVienacoffeesale(asum);
			
			bsum = sum.size();
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
			
			asum = sum.size() * 4000;
			
			dto.setChocofrapuccinosale(asum);
			
			bsum = sum.size();
			dto.setChocofrapuccinosalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
		
	}
	
	
	
	
	public StatDto espressomale() {
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
			String sql = "select * from paybill where menu = 'espresso' and extract(month from buydate) = ? and gender = 'male'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
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
			dto.setEspressoMalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setEspressoMalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto americanomale() {
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
			String sql = "select * from paybill where menu = 'Americano' and extract(month from buydate) = ? and gender = 'male'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
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
			dto.setAmericanoMalesum(asum);
			
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setAmericanoMalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto caffelattemale() {
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
			String sql = "select * from paybill where menu = 'caffelatte' and extract(month from buydate) = ? and gender = 'male'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
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
			dto.setCaffelatteMalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setCaffelatteMalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	public StatDto vienacoffeemale() {
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
			String sql = "select * from paybill where menu = 'vienacoffee' and extract(month from buydate) = ? and gender = 'male'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
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
			dto.setVienacoffeeMalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setVienacoffeeMalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto chocofrapuccinomale() {
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
			String sql = "select * from paybill where menu = 'chocofrapuccino' and extract(month from buydate) = ? and gender = 'male'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
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
			dto.setChocofrapuccinoMalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setChocofrapuccinoMalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
		
	}
	
	
	public StatDto espressofemale() {
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
			String sql = "select * from paybill where menu = 'espresso' and extract(month from buydate) = ? and gender = 'female'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
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
			dto.setEspressoFemalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setEspressoFemalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto americanofemale() {
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
			String sql = "select * from paybill where menu = 'Americano' and extract(month from buydate) = ? and gender = 'female'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
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
			dto.setAmericanoFemalesum(asum);
			
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setAmericanoFemalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto caffelattefemale() {
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
			String sql = "select * from paybill where menu = 'caffelatte' and extract(month from buydate) = ? and gender = 'female'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
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
			dto.setCaffelatteFemalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setCaffelatteFemalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	public StatDto vienacoffeefemale() {
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
			String sql = "select * from paybill where menu = 'vienacoffee' and extract(month from buydate) = ? and gender = 'female'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
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
			dto.setVienacoffeeFemalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setVienacoffeeFemalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;		
	}
	
	public StatDto chocofrapuccinofemale() {
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
			String sql = "select * from paybill where menu = 'chocofrapuccino' and extract(month from buydate) = ? and gender = 'female'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
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
			dto.setChocofrapuccinoFemalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto)sum.get(i);
				bsum += dto.getQuantity();
				
			}
			dto.setChocofrapuccinoFemalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
		
	}
	public StatDto selectPin(String pin) {
		StatDto dto = new StatDto();
		ArrayList pin1 = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from paybill where pin = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, pin);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setPin(rs.getInt(1));
				dto.setMenu(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				dto.setQuantity(rs.getInt(4));
				dto.setTot_price(rs.getInt(5));
				dto.setCoupon(rs.getString(6));
				dto.setGender(rs.getString(7));
				dto.setBuydate(rs.getString(8));
				
				pin1.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
}