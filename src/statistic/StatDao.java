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
	private StatDto2 dto2;
	private int asum = 0;
	private int bsum = 0;

	public StatDto count() {
		// 1.드라이버 설정
		dto = new StatDto();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);

			// 3.SQL문 설정(객체화)
			String sql = "select count(*) from paybill";
			ps = con.prepareStatement(sql);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리

			while (rs.next()) {
				int count2 = rs.getInt(1);
				dto.setCount(count2);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public ArrayList list() {
		ArrayList list = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill order by pin desc";
			ps = con.prepareStatement(sql);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
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
			} // if close

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return list;
	}

	public StatDto espresso() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'espresso' and extract(month from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setEspressoSum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setEspressoTotal(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto americano() {
		ArrayList sum = new ArrayList();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'Americano' and extract(month from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setAmericanoSum(asum);

			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setAmericanoTotal(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto caffelatte() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'caffelatte' and extract(month from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setCaffelatteSum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setCaffelatteTotal(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto viennacoffee() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'viennacoffee' and extract(month from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setViennacoffeeSum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setViennacoffeeTotal(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto chocofrappuccino() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'chocofrappuccino' and extract(month from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setChocofrappuccinoSum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setChocofrappuccinoTotal(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto espressosale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'espresso' and extract(month from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = sum.size() * 2500;

			dto.setEspressosale(asum);

			bsum = sum.size();
			dto.setEspressosalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto americanosale() {
		ArrayList sum = new ArrayList();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'Americano' and extract(month from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = sum.size() * 3000;

			dto.setAmericanosale(asum);

			bsum = sum.size();
			dto.setAmericanosalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto caffelattesale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'caffelatte' and extract(month from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = sum.size() * 3500;

			dto.setCaffelattesale(asum);

			bsum = sum.size();
			dto.setCaffelattesalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto viennacoffeesale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'viennacoffee' and extract(month from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = sum.size() * 3500;

			dto.setViennacoffeesale(asum);

			bsum = sum.size();
			dto.setViennacoffeesalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto chocofrappuccinosale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'chocofrappuccino' and extract(month from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month1);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close

			asum = sum.size() * 4000;

			dto.setChocofrappuccinosale(asum);

			bsum = sum.size();
			dto.setChocofrappuccinosalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;

	}

	public StatDto espressomale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'espresso' and extract(month from buydate) = ? and gender = 'male'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setEspressoMalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setEspressoMalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto americanomale() {
		ArrayList sum = new ArrayList();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'Americano' and extract(month from buydate) = ? and gender = 'male'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setAmericanoMalesum(asum);

			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setAmericanoMalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto caffelattemale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'caffelatte' and extract(month from buydate) = ? and gender = 'male'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setCaffelatteMalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setCaffelatteMalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto viennacoffeemale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'viennacoffee' and extract(month from buydate) = ? and gender = 'male'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setViennacoffeeMalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setViennacoffeeMalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto chocofrappuccinomale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'chocofrappuccino' and extract(month from buydate) = ? and gender = 'male'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setChocofrappuccinoMalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setChocofrappuccinoMalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;

	}

	public StatDto espressofemale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'espresso' and extract(month from buydate) = ? and gender = 'female'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setEspressoFemalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setEspressoFemalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto americanofemale() {
		ArrayList sum = new ArrayList();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'Americano' and extract(month from buydate) = ? and gender = 'female'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setAmericanoFemalesum(asum);

			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setAmericanoFemalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto caffelattefemale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'caffelatte' and extract(month from buydate) = ? and gender = 'female'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setCaffelatteFemalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setCaffelatteFemalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto viennacoffeefemale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'viennacoffee' and extract(month from buydate) = ? and gender = 'female'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setViennacoffeeFemalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setViennacoffeeFemalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto chocofrappuccinofemale() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'chocofrappuccino' and extract(month from buydate) = ? and gender = 'female'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month3);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setChocofrappuccinoFemalesum(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setChocofrappuccinoFemalequan(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;

	}

	public ArrayList selectpin(String pin) {
		ArrayList pin2 = new ArrayList();
		dto = new StatDto();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from paybill where pin = ? order by pin desc";

			ps = con.prepareStatement(sql);
			ps.setString(1, Statistic.pin);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new StatDto();
				int pin1 = rs.getInt(1);
				String menu = rs.getString(2);
				int price = rs.getInt(3);
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				String coupon = rs.getString(6);
				String gender = rs.getString(7);
				String buydate = rs.getString(8);

				dto.setPin(pin1);
				dto.setMenu(menu);
				dto.setPrice(price);
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				dto.setCoupon(coupon);
				dto.setGender(gender);
				dto.setBuydate(buydate);

				pin2.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return pin2;
	}

	public StatDto pincount() {
		dto = new StatDto();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select count(*) from paybill where pin = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, Statistic.pin);
			rs = ps.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);
				dto.setPincount(count);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public ArrayList selectmenu(String menu) {
		ArrayList menu2 = new ArrayList();
		dto = new StatDto();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from paybill where menu = ? order by pin desc";

			ps = con.prepareStatement(sql);
			ps.setString(1, Statistic.menu);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new StatDto();
				int pin = rs.getInt(1);
				String menu1 = rs.getString(2);
				int price = rs.getInt(3);
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				String coupon = rs.getString(6);
				String gender = rs.getString(7);
				String buydate = rs.getString(8);

				dto.setPin(pin);
				dto.setMenu(menu1);
				dto.setPrice(price);
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				dto.setCoupon(coupon);
				dto.setGender(gender);
				dto.setBuydate(buydate);

				menu2.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return menu2;
	}

	public StatDto menucount() {
		dto = new StatDto();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select count(*) from paybill where menu = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, Statistic.menu);
			rs = ps.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);
				dto.setMenucount(count);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public ArrayList selectcoupon(String coupon) {
		ArrayList coupon2 = new ArrayList();
		dto = new StatDto();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from paybill where coupon = ? order by pin desc";

			ps = con.prepareStatement(sql);
			ps.setString(1, Statistic.coupon);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new StatDto();
				int pin = rs.getInt(1);
				String menu = rs.getString(2);
				int price = rs.getInt(3);
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);
				String coupon1 = rs.getString(6);
				String gender = rs.getString(7);
				String buydate = rs.getString(8);

				dto.setPin(pin);
				dto.setMenu(menu);
				dto.setPrice(price);
				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);
				dto.setCoupon(coupon1);
				dto.setGender(gender);
				dto.setBuydate(buydate);

				coupon2.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return coupon2;
	}

	public StatDto couponcount() {
		dto = new StatDto();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select count(*) from paybill where coupon = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, Statistic.coupon);
			rs = ps.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);
				dto.setCouponcount(count);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}
	
	public StatDto espressoday() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'espresso' and extract(month from buydate) = ? and extract(day from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month5);
			ps.setInt(2, Statistic.day);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setEspressoSumd(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setEspressoTotald(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto americanoday() {
		ArrayList sum = new ArrayList();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'Americano' and extract(month from buydate) = ? and extract(day from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month5);
			ps.setInt(2, Statistic.day);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setAmericanoSumd(asum);

			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setAmericanoTotald(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto caffelatteday() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'caffelatte' and extract(month from buydate) = ? and extract(day from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month5);
			ps.setInt(2, Statistic.day);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setCaffelatteSumd(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setCaffelatteTotald(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto viennacoffeeday() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'viennacoffee' and extract(month from buydate) = ? and extract(day from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month5);
			ps.setInt(2, Statistic.day);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setViennacoffeeSumd(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setViennacoffeeTotald(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;
	}

	public StatDto chocofrappuccinoday() {
		ArrayList sum = new ArrayList();
		dto = new StatDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'chocofrappuccino' and extract(month from buydate) = ? and extract(day from buydate) = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month5);
			ps.setInt(2, Statistic.day);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto = new StatDto();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto.setQuantity(quantity);
				dto.setTot_price(tot_price);

				sum.add(dto);
			} // while close
			asum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				asum += dto.getTot_price();

			}
			dto.setChocofrappuccinoSumd(asum);
			bsum = 0;
			for (int i = 0; i < sum.size(); i++) {
				dto = new StatDto();
				dto = (StatDto) sum.get(i);
				bsum += dto.getQuantity();

			}
			dto.setChocofrappuccinoTotald(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto;

	}
	
	
	public StatDto2 espressosaled() {
		ArrayList sum = new ArrayList();
		dto2 = new StatDto2();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'espresso' and extract(month from buydate) = ? and extract(day from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month5);
			ps.setInt(2, Statistic.day);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto2 = new StatDto2();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto2.setQuantity(quantity);
				dto2.setTot_price(tot_price);

				sum.add(dto2);
			} // while close
			asum = sum.size() * 2500;

			dto2.setEspressosaled(asum);

			bsum = sum.size();
			dto2.setEspressosalequand(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto2;
	}

	public StatDto2 americanosaled() {
		ArrayList sum = new ArrayList();
		dto2 = new StatDto2();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'Americano' and extract(month from buydate) = ? and extract(day from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month5);
			ps.setInt(2, Statistic.day);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto2 = new StatDto2();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto2.setQuantity(quantity);
				dto2.setTot_price(tot_price);

				sum.add(dto2);
			} // while close
			asum = sum.size() * 3000;

			dto2.setAmericanosaled(asum);

			bsum = sum.size();
			dto2.setAmericanosalequand(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto2;
	}

	public StatDto2 caffelattesaled() {
		ArrayList sum = new ArrayList();
		dto2 = new StatDto2();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'caffelatte' and extract(month from buydate) = ? and extract(day from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month5);
			ps.setInt(2, Statistic.day);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto2 = new StatDto2();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto2.setQuantity(quantity);
				dto2.setTot_price(tot_price);

				sum.add(dto2);
			} // while close
			asum = sum.size() * 3500;

			dto2.setCaffelattesaled(asum);

			bsum = sum.size();
			dto2.setCaffelattesalequand(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto2;
	}

	public StatDto2 viennacoffeesaled() {
		ArrayList sum = new ArrayList();
		dto2 = new StatDto2();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'viennacoffee' and extract(month from buydate) = ? and extract(day from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month5);
			ps.setInt(2, Statistic.day);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto2 = new StatDto2();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto2.setQuantity(quantity);
				dto2.setTot_price(tot_price);

				sum.add(dto2);
			} // while close
			asum = sum.size() * 3500;

			dto2.setViennacoffeesaled(asum);

			bsum = sum.size();
			dto2.setViennacoffeesalequand(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto2;
	}

	public StatDto2 chocofrappuccinosaled() {
		ArrayList sum = new ArrayList();
		dto2 = new StatDto2();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from paybill where menu = 'chocofrappuccino' and extract(month from buydate) = ? and extract(day from buydate) = ? and coupon = 'o'";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Statistic.month5);
			ps.setInt(2, Statistic.day);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
				dto2 = new StatDto2();
				int quantity = rs.getInt(4);
				int tot_price = rs.getInt(5);

				dto2.setQuantity(quantity);
				dto2.setTot_price(tot_price);

				sum.add(dto2);
			} // while close

			asum = sum.size() * 4000;

			dto2.setChocofrappuccinosaled(asum);

			bsum = sum.size();
			dto2.setChocofrappuccinosalequand(bsum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return dto2;

	}

	
}