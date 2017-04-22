package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = 
						"SELECT codins, crediti, nome, pd " +
						"FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo Corso alla lista
				Corso corso = new Corso(
						rs.getString("codins"),
						rs.getInt("crediti"),
						rs.getString("nome"),
						rs.getInt("pd")
						) ;
				corsi.add(corso);
			}
			
			conn.close();
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {

		final String sql = 
				"SELECT * " +
				"FROM iscrizione, studente " +
				"WHERE iscrizione.matricola=studente.matricola AND codins=?";

		List<Studente> studentiIscrittiAlCorso = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
	
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

			Studente studenteIscrittoAlCorso = new Studente(
					rs.getInt("matricola"),
					rs.getString("cognome"),
					rs.getString("nome")
					) ;
			studenteIscrittoAlCorso.setCDS(rs.getString("CDS"));
			studentiIscrittiAlCorso.add(studenteIscrittoAlCorso);
			corso.setStudentiIscritti(studentiIscrittiAlCorso);
		}
		conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}	
		
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
			
		String sql = "INSERT INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES (?, ?);" ;
		
		boolean iscritto = false;
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);

			
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());			
			
			int result = st.executeUpdate();
			
			if(result == 1) {
				corso.add(studente);
				studente.add(corso);
				iscritto = true ;
			}
			
			conn.close();
			return iscritto;
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false ;
			
	}
}
