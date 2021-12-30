package application;

public class Steps {

	private int recipe_id;
	private int nr;
	private String description;
	
	public int getNr() {
		return nr;
	}
	public void setNr(int nr) {
		this.nr = nr;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	
	public Steps(int nr, String description, int recipe_id) {
		this.nr = nr;
		this.description = description;
		this.recipe_id = recipe_id;
	}
	
	public Steps() {
		
	}

}
