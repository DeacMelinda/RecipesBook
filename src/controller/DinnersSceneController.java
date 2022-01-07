package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Recipe;
import javafx.scene.control.Slider;

import javafx.scene.control.TextArea;

public class DinnersSceneController {
	@FXML
	private TextField dinnersTitle;
	@FXML
	private TextField dinnersNumberOfServings;
	@FXML
	private TextField dinnersCaloriesPerServing;
	@FXML
	private TextField dinnersServingSizeInGrams;
	@FXML
	private Slider dinnersDifficulty;
	@FXML
	private Button dinnersAddIngredientBtn;
	@FXML
	private TextField dinnersIngredientName;
	@FXML
	private TextField dinnersIngredientQuantity;
	@FXML
	private TextField dinnersIngredientMeasuringUnit;
	@FXML
	private TextArea dinnersStep;
	@FXML
	private Button dinnersAddStepBtn;
	@FXML
	private Button dinnersAddRecipeBtn;
	@FXML
	private Button dinnersViewRecipes;
	@FXML
	private Button dinnersGoBack;

	int nr = 1;
	Recipe r = new Recipe();
	
	DatabaseHelper db = new DatabaseHelper();
	int recipeID = db.setRows();
	
	// Event Listener on Button[#dinnersGoBack].onAction
	@FXML
	public void dinnersGoBackBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) dinnersGoBack.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("/view/MainScene.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void dinnersAddIngredientBtnClicked(ActionEvent event) {
		System.out.println(recipeID);
		String ingredientName = dinnersIngredientName.getText();
		String ingredientQuantity = dinnersIngredientQuantity.getText();
		String ingredientMeasuringUnit = dinnersIngredientMeasuringUnit.getText();
		
		r.addIngredients(ingredientName, Double.parseDouble(ingredientQuantity), ingredientMeasuringUnit, recipeID);
	}

	@FXML
	public void dinnersAddStepBtnClicked(ActionEvent event) {
		String step = dinnersStep.getText();

		r.addSteps(nr, step, recipeID);
		nr++;
	}
	
	@FXML
	public void dinnersAddRecipeBtnClicked(ActionEvent event) {
		String title = dinnersTitle.getText();
		String nrServings = dinnersNumberOfServings.getText();
		String calories = dinnersCaloriesPerServing.getText();
		String size = dinnersServingSizeInGrams.getText();
		double difficulty = (double) dinnersDifficulty.getValue();
		
		r.setRecipe_id(recipeID);
		r.setName(title);
		r.setServings(Integer.parseInt(nrServings));
		r.setCalories_per_serving(Double.parseDouble(calories));
		r.setServing_size_in_grams(Double.parseDouble(size));
		r.setDifficulty(difficulty);
		
		db.insertRecipeDinners(r);
		db.insertIngredients(r.getIngredients());
		db.insertSteps(r.getSteps());
		
		recipeID = db.setRows();
		nr = 1;
	}
	
	@FXML
	public void dinnersViewRecipesBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) dinnersViewRecipes.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("/view/DinnerRecipes.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
