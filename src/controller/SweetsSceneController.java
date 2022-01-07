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

public class SweetsSceneController {
	@FXML
	private TextField sweetsTitle;
	@FXML
	private TextField sweetsNumberOfServings;
	@FXML
	private TextField sweetsCaloriesPerServing;
	@FXML
	private TextField sweetsServingSizeInGrams;
	@FXML
	private Slider sweetsDifficulty;
	@FXML
	private Button sweetsAddIngredientBtn;
	@FXML
	private TextField sweetsIngredientName;
	@FXML
	private TextField sweetsIngredientQuantity;
	@FXML
	private TextField sweetsIngredientMeasuringUnit;
	@FXML
	private TextArea sweetsStep;
	@FXML
	private Button sweetsAddStepBtn;
	@FXML
	private Button sweetsAddRecipeBtn;
	@FXML
	private Button sweetsViewRecipes;
	@FXML
	private Button sweetsGoBack;

	int nr = 1;
	Recipe r = new Recipe();
	
	DatabaseHelper db = new DatabaseHelper();
	int recipeID = db.setRows();
	
	// Event Listener on Button[#sweetsGoBack].onAction
	@FXML
	public void sweetsGoBackBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) sweetsGoBack.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("/view/MainScene.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void sweetsAddIngredientBtnClicked(ActionEvent event) {
		System.out.println(recipeID);
		String ingredientName = sweetsIngredientName.getText();
		String ingredientQuantity = sweetsIngredientQuantity.getText();
		String ingredientMeasuringUnit = sweetsIngredientMeasuringUnit.getText();
		
		r.addIngredients(ingredientName, Double.parseDouble(ingredientQuantity), ingredientMeasuringUnit, recipeID);
	}
	
	@FXML
	public void sweetsAddStepBtnClicked(ActionEvent event) {
		String step = sweetsStep.getText();

		r.addSteps(nr, step, recipeID);
		nr++;
	}
	
	@FXML
	public void sweetsAddRecipeBtnClicked(ActionEvent event) {
		String title = sweetsTitle.getText();
		String nrServings = sweetsNumberOfServings.getText();
		String calories = sweetsCaloriesPerServing.getText();
		String size = sweetsServingSizeInGrams.getText();
		double difficulty = (double) sweetsDifficulty.getValue();
		
		r.setRecipe_id(recipeID);
		r.setName(title);
		r.setServings(Integer.parseInt(nrServings));
		r.setCalories_per_serving(Double.parseDouble(calories));
		r.setServing_size_in_grams(Double.parseDouble(size));
		r.setDifficulty(difficulty);
		
		db.insertRecipeSweets(r);
		db.insertIngredients(r.getIngredients());
		db.insertSteps(r.getSteps());
		
		recipeID = db.setRows();
		nr = 1;
	}
	
	@FXML
	public void sweetsViewRecipesBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) sweetsViewRecipes.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("/view/SweetRecipes.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
