package application;

import javafx.fxml.FXML;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;


public class MainSceneController {
	@FXML
	private PasswordField sweetsPassword;
	@FXML
	private PasswordField soupsPassword;
	@FXML
	private PasswordField dinnersPassword;
	@FXML
	private PasswordField breakfastsPassword;
	@FXML
	private PasswordField christmasPassword;
	@FXML
	private PasswordField cocktailsPassword;
	
	private String sweetsPass = null;
	private String soupsPass = null;
	private String dinnersPass = null;
	private String breakfastsPass = null;
	private String christmasPass = null;
	private String cocktailsPass = null;
	
	// Event Listener on Button.onAction
	@FXML
	public void sweetsBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) sweetsPassword.getScene().getWindow();
		String pass = sweetsPassword.getText();
		
		UsefulController uc = new UsefulController();
		sweetsPass = uc.getSweetsPassword();
		
		if(sweetsPass == null) {
			String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
			sweetsPass = hashed;
			
			uc.setSweetsPassword(sweetsPass);
			
			try {
				uc.changeScene("SweetsScene.fxml", mainWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else {
			if (BCrypt.checkpw(pass, sweetsPass)) {
				
				try {
					uc.changeScene("SweetsScene.fxml", mainWindow);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				uc.showAlert();
			}
		}
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void soupsBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) soupsPassword.getScene().getWindow();
		String pass = soupsPassword.getText();
		
		UsefulController uc = new UsefulController();
		soupsPass = uc.getSoupsPassword();
		
		if(soupsPass == null) {
			String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
			soupsPass = hashed;
			
			uc.setSoupsPassword(soupsPass);
			
			try {
				uc.changeScene("SoupsScene.fxml", mainWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else {
			if (BCrypt.checkpw(pass, soupsPass)) {
				
				try {
					uc.changeScene("SoupsScene.fxml", mainWindow);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				uc.showAlert();
			}
		}
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void dinnersBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) dinnersPassword.getScene().getWindow();
		String pass = dinnersPassword.getText();
		
		UsefulController uc = new UsefulController();
		dinnersPass = uc.getDinnersPassword();
		
		if(dinnersPass == null) {
			String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
			dinnersPass = hashed;
			
			uc.setDinnersPassword(dinnersPass);
			
			try {
				uc.changeScene("DinnersScene.fxml", mainWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else {
			if (BCrypt.checkpw(pass, dinnersPass)) {
				
				try {
					uc.changeScene("DinnersScene.fxml", mainWindow);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				uc.showAlert();
			}
		}
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void breakfastsBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) breakfastsPassword.getScene().getWindow();
		String pass = breakfastsPassword.getText();
		
		UsefulController uc = new UsefulController();
		breakfastsPass = uc.getBreakfastsPassword();
		
		if(breakfastsPass == null) {
			String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
			breakfastsPass = hashed;
			
			uc.setBreakfastsPassword(breakfastsPass);
			
			try {
				uc.changeScene("BreakfastsScene.fxml", mainWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else {
			if (BCrypt.checkpw(pass, breakfastsPass)) {
				
				try {
					uc.changeScene("BreakfastsScene.fxml", mainWindow);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				uc.showAlert();
			}
		}
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void christmasBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) christmasPassword.getScene().getWindow();
		String pass = christmasPassword.getText();
		
		UsefulController uc = new UsefulController();
		christmasPass = uc.getChristmasPassword();
		
		if(christmasPass == null) {
			String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
			christmasPass = hashed;
			
			uc.setChristmasPassword(christmasPass);
			
			try {
				uc.changeScene("ChristmasScene.fxml", mainWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else {
			if (BCrypt.checkpw(pass, christmasPass)) {
				
				try {
					uc.changeScene("ChristmasScene.fxml", mainWindow);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				uc.showAlert();
			}
		}
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void cocktailsBtnClicked(ActionEvent event) {
		Stage mainWindow = (Stage) cocktailsPassword.getScene().getWindow();
		String pass = cocktailsPassword.getText();
		
		UsefulController uc = new UsefulController();
		cocktailsPass = uc.getCocktailsPassword();
		
		if(cocktailsPass == null) {
			String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
			cocktailsPass = hashed;
			
			uc.setCocktailsPassword(cocktailsPass);
			
			try {
				uc.changeScene("CocktailsScene.fxml", mainWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else {
			if (BCrypt.checkpw(pass, cocktailsPass)) {
				
				try {
					uc.changeScene("CocktailsScene.fxml", mainWindow);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				uc.showAlert();
			}
		}
	}
	
}
