package inven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * 재고 체워넣는 Dao
 */

public class AddDao {
	private String url;
	private String user;
	private String password;
	private Connection con;
	private PreparedStatement ps;
	private InvenDto dto;
	
	
	public InvenDto Addbean() {
		dto = new InvenDto();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";

			con = DriverManager.getConnection(url, user, password);
			String sql = "update inven set bean = bean + ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(Inven.t1.getText()));

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;

	}

	public InvenDto Addmilk() {
		dto = new InvenDto();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";

			con = DriverManager.getConnection(url, user, password);
			String sql = "update inven set milk = milk + ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(Inven.t1.getText()));

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public InvenDto Addchoco() {
		dto = new InvenDto();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";

			con = DriverManager.getConnection(url, user, password);
			String sql = "update inven set choco = choco + ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(Inven.t1.getText()));

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public InvenDto Addcream() {
		dto = new InvenDto();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";

			con = DriverManager.getConnection(url, user, password);
			String sql = "update inven set cream = cream + ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(Inven.t1.getText()));

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public InvenDto Addcup() {
		dto = new InvenDto();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";

			con = DriverManager.getConnection(url, user, password);
			String sql = "update inven set cup = cup + ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(Inven.t1.getText()));

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public InvenDto Addstraw() {
		dto = new InvenDto();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";

			con = DriverManager.getConnection(url, user, password);
			String sql = "update inven set straw = straw + ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(Inven.t1.getText()));

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public static void main(String[] args) {
		AddDao add = new AddDao();
	}

}