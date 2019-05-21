package paybill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaybillDAO {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String url;
	String user;
	String password;
	int res = 0;

	public int selectLastPin() { // 마지막 pin번호 불러오기
		int lastpin = 100; // 첫 주문일 경우 pin 번호 100으로 설정 (default)
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select pin from paybill order by pin desc limit 1;";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				lastpin = rs.getInt("pin");
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
		return lastpin;
	}

	public int insertPaybill(PaybillDTO dto) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "insert into paybill (pin, menu, price, quantity, tot_price , coupon, gender) values (?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getPin());
			ps.setString(2, dto.getMenu());
			ps.setInt(3, dto.getPrice());
			ps.setInt(4, dto.getQuantity());
			ps.setInt(5, dto.getTot_price());
			ps.setString(6, dto.getCuppon());
			ps.setString(7, dto.getGender());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

}