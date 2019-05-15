package pos;

public class AdminEditDto {
	
	private String id;
	private String pw;
	private String name;
	private int age;
	private String tel;
	
	public AdminEditDto() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public AdminEditDto(String id, String pw, String name, int age, String tel) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.tel = tel;
	}
	
	
	
}