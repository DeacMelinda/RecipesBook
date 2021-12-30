package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Slider;

import javafx.scene.control.TextArea;

public class CocktailsSceneController {
	@FXML
	private TextField cocktailsTitle;
	@FXML
	private TextField cocktailsNumberOfServings;
	@FXML
	private TextField cocktailsCaloriesPerServing;
	@FXML
	private TextField cocktailsServingSizeInGrams;
	@FXML
	private Slider cocktailsDifficulty;
	@FXML
	private Button cocktailsAddIngredientBtn;
	@FXML
	private TextField cocktailsIngredientName;
	@FXML
	private TextField cocktailsIngredientQuantity;
	@FXML
	private TextField cocktailsIngredientMeasuringUnit;
	@FXML
	private TextArea cocktailsStep;
	@FXML
	private Button cocktailsAddStepBtn;
	@FXML
	private Button cocktailsAddRecipeBtn;
	@FXML
	private Button cocktailsViewRecipes;
	@FXML
	private Button cocktailsGoBack;
	
	int nr = 1;
	Recipe r = new Recipe();
	
	DatabaseHelper db = new DatabaseHelper();
	int recipeID = db.setRows();
	
	
	// Event Listener on Button[#cocktailsGoBack].onAction
	@FXML
	public void cocktailsGoBackBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) cocktailsGoBack.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("MainScene.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void cocktailsAddIngredientBtnClicked(ActionEvent event) {
		System.out.println(recipeID);
		String ingredientName = cocktailsIngredientName.getText();
		String ingredientQuantity = cocktailsIngredientQuantity.getText();
		String ingredientMeasuringUnit = cocktailsIngredientMeasuringUnit.getText();
		
		r.addIngredients(ingredientName, Double.parseDouble(ingredientQuantity), ingredientMeasuringUnit, recipeID);
	}

	@FXML
	public void cocktailsAddStepBtnClicked(ActionEvent event) {
		String step = cocktailsStep.getText();

		r.addSteps(nr, step, recipeID);
		nr++;
	}
	
	@FXML
	public void cocktailsAddRecipeBtnClicked(ActionEvent event) {
		String title = cocktailsTitle.getText();
		String nrServings = cocktailsNumberOfServings.getText();
		String calories = cocktailsCaloriesPerServing.getText();
		String size = cocktailsServingSizeInGrams.getText();
		double difficulty = (double) cocktailsDifficulty.getValue();
		
		r.setRecipe_id(recipeID);
		r.setName(title);
		r.setServings(Integer.parseInt(nrServings));
		r.setCalories_per_serving(Double.parseDouble(calories));
		r.setServing_size_in_grams(Double.parseDouble(size));
		r.setDifficulty(difficulty);
		
		db.insertRecipeCocktails(r);
		db.insertIngredients(r.getIngredients());
		db.insertSteps(r.getSteps());
		
		recipeID = db.setRows();
		nr = 1;
	}
	
	@FXML
	public void cocktailsViewRecipesBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) cocktailsViewRecipes.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("CocktailRecipes.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
