package controller;

import model.*;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class RecipePaneController {
	@FXML
	private Label Calories;
	@FXML
	private Label title;
	@FXML
	private Label caloriesLabel;
	@FXML
	private Label difficultyLabel;
	@FXML
	private Label Servings;
	@FXML
	private Label Difficulty;
	@FXML
	private Label servingsLabel;
	@FXML
	private Label Serving_size_in_grams;
	@FXML
	private Label serving_size_in_gramsLabel;
	@FXML
	private Button viewIngredientsBtn;
	@FXML
	private Button viewStepsBtn;
	@FXML
	private AnchorPane mainPane;
	@FXML
	private VBox vBox;
	
	private List<Ingredients> ingredients;
	private List<Steps> steps;

	public void initData(List<Steps> steps, List<Ingredients> ingredients, String titlee, String caloriess, String difficultyy, String servingss, String size) {
		this.title.setText(titlee);
		this.caloriesLabel.setText(caloriess);
		this.difficultyLabel.setText(difficultyy);
		this.servingsLabel.setText(servingss);
		this.serving_size_in_gramsLabel.setText(size);
		this.ingredients = ingredients;
		this.steps = steps;
	}
	
	
	public void setBackground(String color) {
		this.mainPane.setStyle("-fx-background-color: " + color);
	}
	
	// Event Listener on Button[#viewIngredientsBtn].onAction
	@FXML
	public void viewIngredientsBtnClicked(ActionEvent event) {
		AnchorPane mPane = new AnchorPane();
		VBox vBox = new VBox();
		vBox.setLayoutX(40);
		vBox.setLayoutY(40);
		ArrayList<CheckBox> cBox = new ArrayList<>();
		
		IngredientsPageController ipg = new IngredientsPageController();
		
		for(Ingredients i : this.ingredients) {
			
			CheckBox cb = new CheckBox(i.getName()+" "+i.getQuantity()+" "+i.getMeasuring_unit());
			cBox.add(cb);
			vBox.getChildren().add(cb);
		}
		
		Button sendSL = new Button("Send to shopping list");
		sendSL.setLayoutX(241);
		sendSL.setLayoutY(448);
		sendSL.setOnAction(event1 -> {ipg.setVBox(cBox); ipg.sendToShoppingListBtnClicked(event1);});
		
		ScrollPane sp = new ScrollPane();
		
		mPane.getChildren().add(vBox);
		mPane.getChildren().add(sendSL);
		
		sp.setContent(mPane);
		
		Stage popupwindow=new Stage();
	      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Ingredients");
		      
		popupwindow.setScene(new Scene(sp));
		      
		popupwindow.showAndWait();
	}
	// Event Listener on Button[#viewStepsBtn].onAction
	@FXML
	public void viewStepsBtnClicked(ActionEvent event) {
		VBox vBox = new VBox();
		vBox.setLayoutX(40);
		vBox.setLayoutY(40);
		vBox.setPrefWidth(480);
		
		for(Steps i : this.steps) {
			
			Label l1 = new Label(i.getNr()+". "+i.getDescription());
			l1.setWrapText(true);
			vBox.getChildren().add(l1);
		}
		
		ScrollPane sp = new ScrollPane();
		
		sp.setContent(vBox);
		
		sp.setPrefWidth(500);
		sp.setPrefHeight(600);
		
		Stage popupwindow=new Stage();
	      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Steps");
		      
		popupwindow.setScene(new Scene(sp));
		      
		popupwindow.showAndWait();
	}
}
