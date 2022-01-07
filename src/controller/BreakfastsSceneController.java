package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Recipe;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Slider;

import javafx.scene.control.TextArea;

public class BreakfastsSceneController {
	@FXML
	private TextField breakfastsTitle;
	@FXML
	private TextField breakfastsNumberOfServings;
	@FXML
	private TextField breakfastsCaloriesPerServing;
	@FXML
	private TextField breakfastsServingSizeInGrams;
	@FXML
	private Slider breakfastsDifficulty;
	@FXML
	private Button breakfastsAddIngredientBtn;
	@FXML
	private TextField breakfastsIngredientName;
	@FXML
	private TextField breakfastsIngredientQuantity;
	@FXML
	private TextField breakfastsIngredientMeasuringUnit;
	@FXML
	private TextArea breakfastsStep;
	@FXML
	private Button breakfastsAddStepBtn;
	@FXML
	private Button breakfastsAddRecipeBtn;
	@FXML
	private Button breakfastsViewRecipes;
	@FXML
	private Button breakfastsGoBack;
	
	int nr = 1;
	Recipe r = new Recipe();
	
	DatabaseHelper db = new DatabaseHelper();
	int recipeID = db.setRows();
	// Event Listener on Button[#breakfastsGoBack].onAction
	@FXML
	public void breakfastsGoBackBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) breakfastsGoBack.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("/view/MainScene.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void breakfastsAddIngredientBtnClicked(ActionEvent event) {
		System.out.println(recipeID);
		String ingredientName = breakfastsIngredientName.getText();
		String ingredientQuantity = breakfastsIngredientQuantity.getText();
		String ingredientMeasuringUnit = breakfastsIngredientMeasuringUnit.getText();
		
		r.addIngredients(ingredientName, Double.parseDouble(ingredientQuantity), ingredientMeasuringUnit, recipeID);
	}

	@FXML
	public void breakfastsAddStepBtnClicked(ActionEvent event) {
		String step = breakfastsStep.getText();

		r.addSteps(nr, step, recipeID);
		nr++;
	}
	
	@FXML
	public void breakfastsAddRecipeBtnClicked(ActionEvent event) {
		String title = breakfastsTitle.getText();
		String nrServings = breakfastsNumberOfServings.getText();
		String calories = breakfastsCaloriesPerServing.getText();
		String size = breakfastsServingSizeInGrams.getText();
		double difficulty = (double) breakfastsDifficulty.getValue();
		
		r.setRecipe_id(recipeID);
		r.setName(title);
		r.setServings(Integer.parseInt(nrServings));
		r.setCalories_per_serving(Double.parseDouble(calories));
		r.setServing_size_in_grams(Double.parseDouble(size));
		r.setDifficulty(difficulty);
		
		db.insertRecipeBreakfast(r);
		db.insertIngredients(r.getIngredients());
		db.insertSteps(r.getSteps());
		
		recipeID = db.setRows();
		nr = 1;
	}
	
	@FXML
	public void breakfastViewRecipesBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) breakfastsViewRecipes.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("/view/BreakfastRecipes.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
