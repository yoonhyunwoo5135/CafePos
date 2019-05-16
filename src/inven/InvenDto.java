package inven;

public class InvenDto {

	private int bean;
	private int milk;
	private int choco;
	private int cream;
	private int cup;
	private int straw;
	
	public InvenDto() { }
	
	public InvenDto(int bean, int milk, int choco, int cream, int cup, int straw) {
		super();
		this.bean = bean;
		this.milk = milk;
		this.choco = choco;
		this.cream = cream;
		this.cup = cup;
		this.straw = straw;
	}
	
	public int getBean() {
		return bean;
	}
	public void setBean(int bean) {
		this.bean = bean;
	}
	public int getMilk() {
		return milk;
	}
	public void setMilk(int milk) {
		this.milk = milk;
	}
	public int getChoco() {
		return choco;
	}
	public void setChoco(int choco) {
		this.choco = choco;
	}
	public int getCream() {
		return cream;
	}
	public void setCream(int cream) {
		this.cream = cream;
	}
	public int getCup() {
		return cup;
	}
	public void setCup(int cup) {
		this.cup = cup;
	}
	public int getStraw() {
		return straw;
	}
	public void setStraw(int straw) {
		this.straw = straw;
	}
	
	

}
