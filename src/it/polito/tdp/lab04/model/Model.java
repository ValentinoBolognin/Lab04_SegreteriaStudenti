package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;

	public Model() {
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
	}

	public List<Corso> getTuttiICorsi() {
		return corsoDAO.getTuttiICorsi();
	}
	
	public Studente getStudente(int matricola) {
		return studenteDAO.getStudente(matricola);
	}

	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		corsoDAO.getStudentiIscrittiAlCorso(corso);
		return corso.getStudentiIscritti();
	}
	
	public List<Corso> getCorsiPerStudente(Studente studente){
		studenteDAO.getCorsiPerStudente(studente);
		return studente.getCorsi();
	}
	
	public boolean isStudenteIscrittoAlCorso(Studente studente, Corso corso){
		return studenteDAO.isStudenteIscrittoAlCorso(studente, corso);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsoDAO.iscriviStudenteACorso(studente, corso);
	}
	
}
