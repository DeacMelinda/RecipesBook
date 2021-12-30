package application;

public class Ingredients {

	private String name;
	private double quantity;
	private String measuring_unit;
	private int recipe_id;
	
	public String getMeasuring_unit() {
		return measuring_unit;
	}
	public void setMeasuring_unit(String measuring_unit) {
		this.measuring_unit = measuring_unit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public Ingredients(String name, double quantity, String measuring_unit, int recipe_id) {
		this.name = name;
		this.quantity = quantity;
		this.measuring_unit = measuring_unit;
		this.recipe_id = recipe_id;
	}
	public Ingredients() {
		
	}
	
}
