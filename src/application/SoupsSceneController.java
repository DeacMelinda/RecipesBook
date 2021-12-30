package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Slider;

import javafx.scene.control.TextArea;

public class SoupsSceneController {
	@FXML
	private TextField soupsTitle;
	@FXML
	private TextField soupsNumberOfServings;
	@FXML
	private TextField soupsCaloriesPerServing;
	@FXML
	private TextField soupsServingSizeInGrams;
	@FXML
	private Slider soupsDifficulty;
	@FXML
	private Button soupsAddIngredientBtn;
	@FXML
	private TextField soupsIngredientName;
	@FXML
	private TextField soupsIngredientQuantity;
	@FXML
	private TextField soupsIngredientMeasuringUnit;
	@FXML
	private TextArea soupsStep;
	@FXML
	private Button soupsAddStepBtn;
	@FXML
	private Button soupsAddRecipeBtn;
	@FXML
	private Button soupsViewRecipes;
	@FXML
	private Button soupsGoBack;
	
	int nr = 1;
	Recipe r = new Recipe();
	
	DatabaseHelper db = new DatabaseHelper();
	int recipeID = db.setRows();
	
	// Event Listener on Button[#soupsGoBack].onAction
	@FXML
	public void soupsGoBackBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) soupsGoBack.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("MainScene.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void soupsAddIngredientBtnClicked(ActionEvent event) {
		System.out.println(recipeID);
		String ingredientName = soupsIngredientName.getText();
		String ingredientQuantity = soupsIngredientQuantity.getText();
		String ingredientMeasuringUnit = soupsIngredientMeasuringUnit.getText();
		
		r.addIngredients(ingredientName, Double.parseDouble(ingredientQuantity), ingredientMeasuringUnit, recipeID);
	}

	@FXML
	public void soupsAddStepBtnClicked(ActionEvent event) {
		String step = soupsStep.getText();

		r.addSteps(nr, step, recipeID);
		nr++;
	}
	
	@FXML
	public void soupsAddRecipeBtnClicked(ActionEvent event) {
		String title = soupsTitle.getText();
		String nrServings = soupsNumberOfServings.getText();
		String calories = soupsCaloriesPerServing.getText();
		String size = soupsServingSizeInGrams.getText();
		double difficulty = (double) soupsDifficulty.getValue();
		
		r.setRecipe_id(recipeID);
		r.setName(title);
		r.setServings(Integer.parseInt(nrServings));
		r.setCalories_per_serving(Double.parseDouble(calories));
		r.setServing_size_in_grams(Double.parseDouble(size));
		r.setDifficulty(difficulty);
		
		db.insertRecipeSoups(r);
		db.insertIngredients(r.getIngredients());
		db.insertSteps(r.getSteps());
		
		recipeID = db.setRows();
		nr = 1;
	}
	
	@FXML
	public void soupsViewRecipesBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) soupsViewRecipes.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("SoupRecipes.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
