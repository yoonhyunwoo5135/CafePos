package inven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvenDao {
	String url;
	String user;
	String password;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public void update(InvenDto dto) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		url = "jdbc:mysql://localhost:3306/cafe";
		user = "root";
		password = "1234";

		con = DriverManager.getConnection(url, user, password);
		String sql = "update inven set bean = ?, milk = ?, choco = ?, cream = ?, cup = ?, straw = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, dto.getBean());
		ps.setInt(2, dto.getMilk());
		ps.setInt(3, dto.getChoco());
		ps.setInt(4, dto.getCream());
		ps.setInt(5, dto.getCup());
		ps.setInt(6, dto.getStraw());

		ps.executeUpdate();
	}// 추가 수량 메소드

	public void use(InvenDto dto) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";

			con = DriverManager.getConnection(url, user, password);
			String sql = "update inven set bean = bean - ?, milk = milk - ?, choco = choco - ?, cream = cream - ?, cup = cup - ?, straw = straw - ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getBean());
			ps.setInt(2, dto.getMilk());
			ps.setInt(3, dto.getChoco());
			ps.setInt(4, dto.getCream());
			ps.setInt(5, dto.getCup());
			ps.setInt(6, dto.getStraw());

			ps.executeUpdate();
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
		} // 메뉴 선택시 재고 빠져나가는 메소드
	}

	public InvenDto list() {

		InvenDto dto = new InvenDto();
		try {
			// 1.드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			// 2.DB연결
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";
			con = DriverManager.getConnection(url, user, password);
			// 3.SQL문 설정(객체화)
			String sql = "select * from inven";
			ps = con.prepareStatement(sql);
			// 4.SQL문 전송
			rs = ps.executeQuery();
			// SQL문의 결과가 있으면, 받아서 처리
			while (rs.next()) {
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
			} // while close

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}//재고 목록 불러오는 메소드

}