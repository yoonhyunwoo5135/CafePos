package membership;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String url;
	String user;
	String password;
	int res = 0;

	public MemberDto selectMember(String tel) {
		MemberDto dto = new MemberDto();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from membership where tel = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, tel);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setTel(rs.getString("tel"));
				dto.setName(rs.getString("name"));
				dto.setStamp(rs.getInt("stamp"));
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
	
	
	public void insertMember(MemberDto dto) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "insert into membership(tel, name) values(?, ?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTel());
			ps.setString(2, dto.getName());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (res != 0)
					ps.close();
				if (res != 0)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

		public void plusStamp(String tel, int cnt) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				url = "jdbc:mysql://localhost:3306/cafe";
				user = "root";
				password = "1234";
				con = DriverManager.getConnection(url, user, password);
				String sql = "update membership set stamp = stamp + ? where tel = ?";
				
				ps = con.prepareStatement(sql);
				ps.setInt(1, cnt);
				ps.setString(2, tel);
				res = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (res != 0)
						ps.close();
					if (res != 0)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
		
		public void deleteMember(MemberDto dto) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				url = "jdbc:mysql://localhost:3306/cafe";
				user = "root";
				password = "1234";
				con = DriverManager.getConnection(url, user, password);
				String sql = "delete from membership where tel = ?";
				
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getTel());
				res = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (res != 0)
						ps.close();
					if (res != 0)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void UseStamp(String tel, int couponCnt) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				url = "jdbc:mysql://localhost:3306/cafe";
				user = "root";
				password = "1234";
				con = DriverManager.getConnection(url, user, password);
				String sql = "update membership set stamp = stamp - (10*?) where tel = ?";
				
				ps = con.prepareStatement(sql);
				ps.setInt(1, couponCnt);
				ps.setString(2, tel);
				res = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (res != 0)
						ps.close();
					if (res != 0)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
}