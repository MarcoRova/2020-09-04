/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.imdb;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.imdb.model.Model;
import it.polito.tdp.imdb.model.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnGrandoMax"
    private Button btnGrandoMax; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="txtRank"
    private TextField txtRank; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMovie"
    private ComboBox<Movie> cmbMovie; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	Movie m = this.cmbMovie.getValue();
    	
    	if(m == null) {
    		this.txtResult.appendText("Devi selezionare un film per continuare.");
    		return;
    	}
    	
    	List<Movie> cammino = this.model.calcolaCammino(m);
    	
    	
    	for(Movie mo : cammino) {
    		this.txtResult.appendText("\n"+mo);
    	}
    	
    	

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	this.txtResult.clear();
    	    	
    	Double rank = 0.0;
    	
    	try {
    		rank = Double.parseDouble(this.txtRank.getText());
    		
    		if(rank< 0.0 || rank > 10.0){
    			this.txtResult.setText("Inserire un rank tra 0 e 10.");
    			return;
    		}
    	}catch(NumberFormatException e) {
    		txtResult.setText("Formato non corretto per il rank.");
    		return;
    	}
    	
    	
    	this.model.creaGrafo(rank);
    	
    	this.txtResult.appendText(this.model.infoGrafo());
    	
    	this.btnGrandoMax.setDisable(false);
    	this.btnCammino.setDisable(false);
    	
    	this.cmbMovie.getItems().addAll(this.model.getVertici());
    	
    }

    @FXML
    void doGradoMax(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	if(this.model.gradoMassimo() == null) {
    		this.txtResult.appendText("Grado massimo non disponibie per questi parametri.");
    		return;
    	}
    	
    	this.txtResult.appendText(this.model.gradoMassimo().getM()+" ("+this.model.gradoMassimo().getSomma()+")");
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGrandoMax != null : "fx:id=\"btnGrandoMax\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRank != null : "fx:id=\"txtRank\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMovie != null : "fx:id=\"cmbMovie\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.btnGrandoMax.setDisable(true);
    	this.btnCammino.setDisable(true);
    }
}
