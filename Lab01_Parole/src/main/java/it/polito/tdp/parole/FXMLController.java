package it.polito.tdp.parole;

import it.polito.tdp.parole.model.Parole;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private String parolaInserita;
	Parole elenco ;
	private long tempoImpiegato;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnInserisci;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    @FXML
    private TextArea txtTime;

    
    @FXML
    private Button btnCancel;

    public boolean check (String p) {
		boolean check = true;
		for (int i=0; i<p.length();i++) {
			if (!Character.isLetter(p.charAt(i))){
				check=false;
			}
		}
		return check;
	}
    
    @FXML
    void doInsert(ActionEvent event) {
    	// TODO
    	//ripulisco il txt dei risultati prima di iniziare per evitare che mi ristampi piu volte la stessa cosa
    	txtResult.clear();
    	//prendo la parola inserita dall'utente
    	this.parolaInserita = txtParola.getText();
    	//controllo che l'utente non abbia inserito una parola vuota (ma non pulisco l'elenco in modo che non dimentichi eventuali parole gia inserite)
    	if (parolaInserita.length()==0) {
    		txtResult.clear();
    		txtResult.appendText("Inserimento non valido \n");
        	txtParola.clear();

    		return;
    	}
    	
    	//effettuo il controllo nel caso in cui l'utente avesse inserito dei caratteri non validi
    	if (this.check(parolaInserita)==false) {
    		txtResult.clear();
    		txtResult.appendText("Inserire parola valida \n");
        	txtParola.clear();

    		return;
    	}
    	
    	//aggiungo nell'elenco la parola nuova
    	elenco.addParola(parolaInserita);
    	
    	//stampo la parola nel txt del risultato in ordine alfabetico grazie a getElenco
    	for (String s: elenco.getElenco()) {
    		txtResult.appendText(s+"\n");
    	}	
    	
    	this.doTime(event);
    	    	
    	txtParola.clear();
    }

    @FXML
    public void doTime (ActionEvent event) {
    	long tempo=System.nanoTime();
    	txtTime.appendText(String.valueOf(tempo)+"\n");
    }
    
    @FXML
    void doReset(ActionEvent event) {
    	// TODO
    	this.elenco.reset();
    	txtResult.clear();
    	txtTime.clear();
    }
    
    @FXML
    void doCancel(ActionEvent event) {
    	
    	String parolaDaEliminare="";
    	parolaDaEliminare = txtResult.getSelectedText();
    	
    	if (parolaDaEliminare.compareTo("")!=0) {
    	elenco.cancelWord(parolaDaEliminare);
    	txtResult.clear();
    	
    	for (String s: elenco.getElenco()) {
    		txtResult.appendText(s+"\n");
    	}	
    	
    	this.doTime(event);
    	}
    }

    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnInserisci != null : "fx:id=\"btnInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Scene.fxml'.";
        elenco = new Parole();

    }
}