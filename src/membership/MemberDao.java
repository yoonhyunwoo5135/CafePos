package membership;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MemberDao {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String url;
	String user;
	String password;
	int res = 0;

	public MemberDto selectMemberTel(String tel) {
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
				dto.setEvent(rs.getInt("event"));
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
	
	public ArrayList selectMemberName(String name) {
		MemberDto dto;
		ArrayList list = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from membership where name = ? order by tel";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new MemberDto();
				dto.setTel(rs.getString("tel"));
				dto.setName(rs.getString("name"));
				dto.setStamp(rs.getInt("stamp"));
				list.add(dto);
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
		return list;
	}
	
	public ArrayList selectAll() {
		MemberDto dto;
		ArrayList list = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "select * from membership order by name";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new MemberDto();
				dto.setTel(rs.getString("tel"));
				dto.setName(rs.getString("name"));
				dto.setStamp(rs.getInt("stamp"));
				list.add(dto);
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
		return list;
	}
	
	
	public int insertMember(String name, String tel) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String sql = "insert into membership(tel, name) values(?, ?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, tel);
			ps.setString(2, name);
			res = ps.executeUpdate();
		} catch (Exception e) {
			AlreadyMember am = new AlreadyMember();
			res = 0;	// insert가 수행되지 않음을 표시
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
		
		public int deleteMember(String tel) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				url = "jdbc:mysql://localhost:3306/cafe";
				user = "root";
				password = "1234";
				con = DriverManager.getConnection(url, user, password);
				String sql = "delete from membership where tel = ?";
				
				ps = con.prepareStatement(sql);
				ps.setString(1, tel);
				res = ps.executeUpdate();
			} catch (Exception e) {
				DeleteError de = new DeleteError();
				res = 0;	// delete가 수행되지 않음을 표시
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
			return res;
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
		
		public void joinEvent(String tel) {	// 이벤트에 참여하였을 경우 그 이후 하루동안 참여 못하게 막는 용도
			try {
				Class.forName("com.mysql.jdbc.Driver");
				url = "jdbc:mysql://localhost:3306/cafe";
				user = "root";
				password = "1234";
				con = DriverManager.getConnection(url, user, password);
				String sql = "update membership set event = 1 where tel = ?";
				
				ps = con.prepareStatement(sql);
				ps.setString(1, tel);
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
		
		public void resetEvent() {	// 모든 회원이 이벤트에 참여할 수 있도록 초기화
			try {
				Class.forName("com.mysql.jdbc.Driver");
				url = "jdbc:mysql://localhost:3306/cafe";
				user = "root";
				password = "1234";
				con = DriverManager.getConnection(url, user, password);
				String sql = "update membership set event = 0";
				
				ps = con.prepareStatement(sql);
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