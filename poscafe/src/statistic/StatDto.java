package statistic;

public class StatDto {
	private int pin;
	private String menu;
	private int price;
	private String cuppon;
	private String jender;
	private int date;
	private int count;
	
	public StatDto() {
		
	}
	
	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCuppon() {
		return cuppon;
	}

	public void setCuppon(String cuppon) {
		this.cuppon = cuppon;
	}

	public String getJender() {
		return jender;
	}

	public void setJender(String jender) {
		this.jender = jender;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(Object count) {
		this.count = (int)count;
	}

	
}
