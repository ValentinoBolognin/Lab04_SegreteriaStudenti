/**
 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
 */

package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboCorso"
    private ComboBox<Corso> comboCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaNome"
    private Button btnCercaNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnCerca"
    private Button btnCerca; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader
    
    public void setModel(Model model) {

		this.model = model;
		
		comboCorso.getItems().add(new Corso(""));
		comboCorso.getItems().addAll(model.getTuttiICorsi());
   
	}
    
    @FXML
    void doCerca(ActionEvent event) {
    	
    	txtResult.clear();
    	txtNome.clear();
		txtCognome.clear();
		
		try{
		
			Corso corso = comboCorso.getValue();
			
			if(corso == null || corso.getCodins().equals("")){
				txtResult.setText("Selezionare un corso");
				return;
			}
			
			int matricola = Integer.parseInt(txtMatricola.getText());
			Studente studente = model.getStudente(matricola);
			
			if(studente == null){
				txtResult.setText("Matricola "+matricola+" inesistente");
				return;
			}
			
			if(model.isStudenteIscrittoAlCorso(studente, corso)){
				txtResult.setText("Lo studente "+studente.toString()+" è già iscritto al corso "+corso.toString());
			}
			else{
				txtResult.setText("Lo studente "+studente.toString()+" non è ancora iscritto al corso "+corso.toString());
			}		
						
			} catch (NumberFormatException e) {
				txtResult.setText("Inserire una matricola nel formato corretto.");
			} catch (RuntimeException e) {
				txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	txtResult.clear();
    	txtNome.clear();
		txtCognome.clear();
		
		try{
		
			int matricola = Integer.parseInt(txtMatricola.getText());
			Studente studente = model.getStudente(matricola);

			if(studente == null){
				txtResult.setText("Matricola "+matricola+" inesistente");
				return;
			}
			
			List<Corso> corsiPerStudente = model.getCorsiPerStudente(studente);
			
			StringBuilder sb = new StringBuilder();

			for (Corso corso : corsiPerStudente) {
				sb.append(String.format("%-8s ", corso.getCodins()));
				sb.append(String.format("%-4s ", corso.getCrediti()));
				sb.append(String.format("%-45s ", corso.getNome()));
				sb.append(String.format("%-4s ", corso.getPd()));
				sb.append("\n");
			}
			txtResult.appendText(sb.toString());
			
			} catch (NumberFormatException e) {
				txtResult.setText("Inserire una matricola nel formato corretto.");
			} catch (RuntimeException e) {
				txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	txtResult.clear();
    	txtNome.clear();
		txtCognome.clear();
		
		try {

			Corso corso = comboCorso.getValue();
			if(corso == null || corso.getCodins().equals("")){
				txtResult.setText("Selezionare un corso");
				return;
			}
			
			List<Studente> studentiIscrittiAlCorso = model.getStudentiIscrittiAlCorso(corso);
			
			StringBuilder sb = new StringBuilder();

			for (Studente studente : studentiIscrittiAlCorso) {

				sb.append(String.format("%-10s ", studente.getMatricola()));
				sb.append(String.format("%-20s ", studente.getCognome()));
				sb.append(String.format("%-20s ", studente.getNome()));
				sb.append(String.format("%-10s ", studente.getCDS()));
				sb.append("\n");
			}

			txtResult.appendText(sb.toString());

		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
		
    }

    @FXML
    void doCercaNome(ActionEvent event) {
    	
    	txtResult.clear();
		txtNome.clear();
		txtCognome.clear();

		try {

			int matricola = Integer.parseInt(txtMatricola.getText());
			Studente studente = model.getStudente(matricola);

			if (studente == null) {
				txtResult.appendText("Matricola "+matricola+" inesistente");
				return;
			}

			txtNome.setText(studente.getNome());
			txtCognome.setText(studente.getCognome());

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
		
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	txtResult.clear();
    	txtNome.clear();
		txtCognome.clear();
		
		try{
		
			Corso corso = comboCorso.getValue();
			
			if(corso == null || corso.getCodins().equals("")){
				txtResult.setText("Selezionare un corso");
				return;
			}
			
			int matricola = Integer.parseInt(txtMatricola.getText());
			Studente studente = model.getStudente(matricola);
			
			if(studente == null){
				txtResult.setText("Matricola "+matricola+" inesistente");
				return;
			}
			
			if(model.inscriviStudenteACorso(studente, corso)){
				txtResult.setText("Lo studente "+studente.toString()+" è stato iscritto al corso "+corso.toString());
			}
			else{
				txtResult.setText("Lo studente "+studente.toString()+" non è stato iscritto al corso "+corso.toString());
			}		
						
			} catch (NumberFormatException e) {
				txtResult.setText("Inserire una matricola nel formato corretto.");
			} catch (RuntimeException e) {
				txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    }

    @FXML
    void doReset(ActionEvent event) {

		comboCorso.getSelectionModel().clearSelection();
    	txtMatricola.clear();
		txtNome.clear();
		txtCognome.clear();
		txtResult.clear();
		
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

        txtResult.setStyle("-fx-font-family: monospace");
    }
}
