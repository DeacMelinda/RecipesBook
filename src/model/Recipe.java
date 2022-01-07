package model;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Comparable<Recipe> {

	private int recipe_id;
	private String name;
	private double calories_per_serving;
	private int servings;
	private double serving_size_in_grams;
	private double difficulty;
	
	private List<Ingredients> ingredients;
	private List<Steps> steps;
	
	public Recipe() {
		this.ingredients = new ArrayList<>();
		this.steps = new ArrayList<>();
	}
		
	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCalories_per_serving() {
		return calories_per_serving;
	}
	public void setCalories_per_serving(double calories_per_serving) {
		this.calories_per_serving = calories_per_serving;
	}
	public int getServings() {
		return servings;
	}
	public void setServings(int servings) {
		this.servings = servings;
	}
	public double getServing_size_in_grams() {
		return serving_size_in_grams;
	}
	public void setServing_size_in_grams(double serving_size_in_grams) {
		this.serving_size_in_grams = serving_size_in_grams;
	}
	public double getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(double difficulty) {
		double dif = difficulty*100;
		dif = Math.round(dif);
		dif = dif/100;
		this.difficulty = dif;
	}
	public List<Ingredients> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}
	public List<Steps> getSteps() {
		return steps;
	}
	public void setSteps(List<Steps> steps) {
		this.steps = steps;
	}
	
	public void addSteps(int nr, String step, int recipeID) {
	
		this.steps.add(new Steps(nr, step, recipeID));
	}
	
	public void addSteps(Steps s) {
		this.steps.add(s);
	}

	public void addIngredients(String ingredientName, double d, String ingredientMeasuringUnit, int recipeID){

		this.ingredients.add(new Ingredients(ingredientName, d, ingredientMeasuringUnit, recipeID));
	}
	
	public void addIngredients(Ingredients i) {
		this.ingredients.add(i);
	}
	
	@Override
	public int compareTo(Recipe o) {
		if(calories_per_serving == o.calories_per_serving)
			return 0;
		else if(calories_per_serving > o.calories_per_serving)
			return 1;
		else
			return -1;
	}
}
