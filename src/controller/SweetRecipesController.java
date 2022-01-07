package controller;

import model.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SweetRecipesController {
	@FXML
	private AnchorPane mainPane;
	@FXML
	private VBox vBox;
	@FXML
	private Button sweetsRecipesGoBack;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private AnchorPane vBoxAnchor;

	static DatabaseHelper db = new DatabaseHelper();
	
	// Event Listener on Button[#sweetsRecipesGoBack].onAction
	@FXML
	public void sweetsRecipesGoBackBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) mainPane.getScene().getWindow();
		UsefulController uc = new UsefulController();
		try {
			uc.changeScene("/view/SweetsScene.fxml", mainWindow);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start(Stage paneul) {
		scrollPane = new ScrollPane();
		vBox = new VBox();
		vBoxAnchor = new AnchorPane();
		mainPane = new AnchorPane();
		ArrayList<Recipe> recipes = db.getSweetRecipes();
		for(Recipe r : recipes) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RecipePane.fxml"));
			Pane p = null;
			try {
				p  =  loader.load();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			RecipePaneController controller = loader.getController();
			controller.initData(r.getSteps(), r.getIngredients(), r.getName(),r.getCalories_per_serving()+"",r.getDifficulty()+"%",r.getServings()+"",r.getServing_size_in_grams()+" g");
			controller.setBackground("#ffb6c1");
			
			Separator separator =  new Separator(Orientation.HORIZONTAL);
			separator.setPrefWidth(790);
			vBox.getChildren().add(p);
			vBox.getChildren().add(separator);
			vBox.setStyle("-fx-background-color: " + "#ffb6c1");
			//vBox.setPrefHeight(557);
			vBox.setPrefWidth(800);
		}
		scrollPane.setContent(vBox);
		scrollPane.setPrefHeight(507);
		scrollPane.setPrefWidth(801);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setStyle("-fx-background-color: " + "#ffb6c1");
		vBoxAnchor.setPrefHeight(scrollPane.getHeight());
		vBoxAnchor.getChildren().add(scrollPane);
		vBoxAnchor.setLayoutY(50);
		vBoxAnchor.setPrefWidth(scrollPane.getWidth());
		vBoxAnchor.setStyle("-fx-background-color: " + "#ffb6c1");
		
		
		//setting the go back button
		FileInputStream input = null;
		try {
			input = new FileInputStream("C:\\Users\\Melinda\\eclipse-workspace\\projInFX\\goBack.png");
		} catch (FileNotFoundException e) {
			System.out.println("Nu gaseste poza");
			e.printStackTrace();
		}
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setPreserveRatio(true);
        
		Button b = new Button("",imageView);
        b.setLayoutX(10);
        b.setLayoutY(10);
        b.setPrefSize(30, 30);
        b.setOnAction(event -> {sweetsRecipesGoBackBtnClicked(event);});
        
        
        Separator separator =  new Separator(Orientation.HORIZONTAL);
		separator.setPrefWidth(801);
		separator.setLayoutY(49);
        
        mainPane.getChildren().add(vBoxAnchor);
		mainPane.getChildren().add(b);
		mainPane.getChildren().add(separator);
		mainPane.setPrefWidth(801);
		mainPane.setPrefHeight(557);

		mainPane.setMaxWidth(801);
		mainPane.setMaxHeight(557);

		
		mainPane.setStyle("-fx-background-color: " + "#ffb6c1");
		
		paneul.setHeight(mainPane.getHeight());
		paneul.setScene(new Scene(mainPane));
	}	
}
