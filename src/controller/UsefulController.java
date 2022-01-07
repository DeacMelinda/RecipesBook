package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UsefulController {

	public void showAlert() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Incorrect password");
		errorAlert.setContentText("Incorrect password!");
		errorAlert.showAndWait();
	}
	
	public String getSweetsPassword() {
		String sweetsPass = null;
		try {
		      File myObj = new File("sweetsPassword.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        sweetsPass = myReader.nextLine();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return sweetsPass;
	}
	
	public void setSweetsPassword(String hashed) {
		try {
		      FileWriter myWriter = new FileWriter("sweetsPassword.txt");
		      myWriter.write(hashed);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public String getBreakfastsPassword() {
		String breakfastsPass = null;
		try {
		      File myObj = new File("breakfastsPassword.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        breakfastsPass = myReader.nextLine();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return breakfastsPass;
	}
	
	public void setBreakfastsPassword(String hashed) {
		try {
		      FileWriter myWriter = new FileWriter("breakfastsPassword.txt");
		      myWriter.write(hashed);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public String getChristmasPassword() {
		String christmasPass = null;
		try {
		      File myObj = new File("christmasPassword.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        christmasPass = myReader.nextLine();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return christmasPass;
	}
	
	public void setChristmasPassword(String hashed) {
		try {
		      FileWriter myWriter = new FileWriter("christmasPassword.txt");
		      myWriter.write(hashed);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public String getCocktailsPassword() {
		String cocktailsPass = null;
		try {
		      File myObj = new File("cocktailsPassword.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        cocktailsPass = myReader.nextLine();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return cocktailsPass;
	}
	
	public void setCocktailsPassword(String hashed) {
		try {
		      FileWriter myWriter = new FileWriter("cocktailsPassword.txt");
		      myWriter.write(hashed);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public String getDinnersPassword() {
		String dinnersPass = null;
		try {
		      File myObj = new File("dinnersPassword.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        dinnersPass = myReader.nextLine();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return dinnersPass;
	}
	
	public void setDinnersPassword(String hashed) {
		try {
		      FileWriter myWriter = new FileWriter("dinnersPassword.txt");
		      myWriter.write(hashed);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public String getSoupsPassword() {
		String soupsPass = null;
		try {
		      File myObj = new File("soupsPassword.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        soupsPass = myReader.nextLine();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return soupsPass;
	}
	
	public void setSoupsPassword(String hashed) {
		try {
		      FileWriter myWriter = new FileWriter("soupsPassword.txt");
		      myWriter.write(hashed);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void changeScene(String fxml, Stage primaryStage) throws IOException{
	    Parent pane = null;
		try {
			pane = FXMLLoader.load(
			       getClass().getResource(fxml));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   primaryStage.getScene().setRoot(pane);
	   if("/view/BreakfastRecipes.fxml".equals(fxml)) {
		   BreakfastRecipesController breakfastRecipesController = new BreakfastRecipesController();
		   breakfastRecipesController.start(primaryStage);
	   }
	   
	   if("/view/SweetRecipes.fxml".equals(fxml)) {
		   SweetRecipesController sweetRecipesController = new SweetRecipesController();
		   sweetRecipesController.start(primaryStage);
	   }
	   
	   if("/view/ChristmasRecipes.fxml".equals(fxml)) {
		   ChristmasRecipesController christmasRecipesController = new ChristmasRecipesController();
		   christmasRecipesController.start(primaryStage);
	   }
	   
	   if("/view/CocktailRecipes.fxml".equals(fxml)) {
		   CocktailRecipesController cocktailRecipesController = new CocktailRecipesController();
		   cocktailRecipesController.start(primaryStage);
	   }
	   
	   if("/view/DinnerRecipes.fxml".equals(fxml)) {
		   DinnerRecipesController dinnerRecipesController = new DinnerRecipesController();
		   dinnerRecipesController.start(primaryStage);
	   }
	   
	   if("/view/SoupRecipes.fxml".equals(fxml)) {
		   SoupRecipesController soupRecipesController = new SoupRecipesController();
		   soupRecipesController.start(primaryStage);
	   }
	}
	
	
}
