package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {

	List<Recipe> recipes = new ArrayList<>();
	List<Recipe> ordered_by_calories = new ArrayList<>();
	
	
	public void orderByCalories() {
		for(Recipe r : this.recipes) {
			this.ordered_by_calories.add(r);
		}
		Collections.sort(this.ordered_by_calories);
	}
	
}
