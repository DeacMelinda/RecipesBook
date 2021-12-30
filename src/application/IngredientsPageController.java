package application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class IngredientsPageController {

	ArrayList<CheckBox> cBox;
	
	public void setVBox(ArrayList<CheckBox> vBox) {
		this.cBox = vBox;
	}
	
	@FXML
	public void sendToShoppingListBtnClicked(ActionEvent event) {
		try {
		      FileWriter myWriter = new FileWriter("shoppingList.txt");
		      for(CheckBox c : cBox) {
		    	  if(c.isSelected()) {
		    		  myWriter.write(c.getText());
		    		  myWriter.write("\n");
		    	  }
		      }
		      myWriter.close();
		   } catch (IOException e) {
		     System.out.println("An error occurred.");
		     e.printStackTrace();
		    }
			
			
	}
}
