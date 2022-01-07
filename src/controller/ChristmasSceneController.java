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

public class ChristmasSceneController {
	@FXML
	private TextField christmasTitle;
	@FXML
	private TextField christmasNumberOfServings;
	@FXML
	private TextField christmasCaloriesPerServing;
	@FXML
	private TextField christmasServingSizeInGrams;
	@FXML
	private Slider christmasDifficulty;
	@FXML
	private Button christmasAddIngredientBtn;
	@FXML
	private TextField christmasIngredientName;
	@FXML
	private TextField christmasIngredientQuantity;
	@FXML
	private TextField christmasIngredientMeasuringUnit;
	@FXML
	private TextArea christmasStep;
	@FXML
	private Button christmasAddStepBtn;
	@FXML
	private Button christmasAddRecipeBtn;
	@FXML
	private Button christmasViewRecipes;
	@FXML
	private Button christmasGoBack;
	
	int nr = 1;
	Recipe r = new Recipe();
	
	DatabaseHelper db = new DatabaseHelper();
	int recipeID = db.setRows();
	
	// Event Listener on Button[#christmasGoBack].onAction
	@FXML
	public void christmasGoBackBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) christmasGoBack.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("/view/MainScene.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void christmasAddIngredientBtnClicked(ActionEvent event) {
		System.out.println(recipeID);
		String ingredientName = christmasIngredientName.getText();
		String ingredientQuantity = christmasIngredientQuantity.getText();
		String ingredientMeasuringUnit = christmasIngredientMeasuringUnit.getText();
		
		r.addIngredients(ingredientName, Double.parseDouble(ingredientQuantity), ingredientMeasuringUnit, recipeID);
	}

	@FXML
	public void christmasAddStepBtnClicked(ActionEvent event) {
		String step = christmasStep.getText();

		r.addSteps(nr, step, recipeID);
		nr++;
	}
	
	@FXML
	public void christmasAddRecipeBtnClicked(ActionEvent event) {
		String title = christmasTitle.getText();
		String nrServings = christmasNumberOfServings.getText();
		String calories = christmasCaloriesPerServing.getText();
		String size = christmasServingSizeInGrams.getText();
		double difficulty = (double) christmasDifficulty.getValue();
		
		r.setRecipe_id(recipeID);
		r.setName(title);
		r.setServings(Integer.parseInt(nrServings));
		r.setCalories_per_serving(Double.parseDouble(calories));
		r.setServing_size_in_grams(Double.parseDouble(size));
		r.setDifficulty(difficulty);
		
		db.insertRecipeChristmas(r);
		db.insertIngredients(r.getIngredients());
		db.insertSteps(r.getSteps());
		
		recipeID = db.setRows();
		nr = 1;
	}
	
	@FXML
	public void christmasViewRecipesBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) christmasViewRecipes.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("/view/ChristmasRecipes.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
