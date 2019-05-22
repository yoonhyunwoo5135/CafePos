package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AdminEditDao {
	private String url;
	private String user;
	private String password;
	private Connection con;
	private PreparedStatement ps;
	private AdminEditDto dto;
	
	public AdminEditDto update() {
		dto = new AdminEditDto();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/cafe";
			user = "root";
			password = "1234";

			con = DriverManager.getConnection(url, user, password);
			String sql = "update admin set id = ?, pw = ?, name = ?, age = ?, tel = ? where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, AdminEdit.tId.getText());
			ps.setString(2, AdminEdit.pF.getText());
			ps.setString(3, AdminEdit.tName.getText());
			ps.setInt(4, Integer.parseInt(AdminEdit.tAge.getText()));
			ps.setString(5, AdminEdit.tTel.getText());
			ps.setString(6, InfoUpdate.tIdKey.getText());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
}