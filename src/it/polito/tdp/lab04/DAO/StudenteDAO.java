package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	/*
	 * Ottengo uno studente data la matricola
	 */
	public Studente getStudente(int matricola) {

		final String sql = 
						"SELECT matricola, cognome, nome " + 
						"FROM studente " +
						"WHERE matricola=?" ;

		Studente studente = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				studente = new Studente(
						rs.getInt("matricola"),
						rs.getString("cognome"),
						rs.getString("nome")
						) ;
			}

			conn.close();
			return studente;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public void getCorsiPerStudente(Studente studente) {

		final String sql = 
				"SELECT * " +
				"FROM iscrizione, corso " +
				"WHERE iscrizione.codins=corso.codins AND matricola=?";

		List<Corso> corsiPerStudente = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
	
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

			Corso corsoPerStudente = new Corso(
					rs.getString("codins"),
					rs.getInt("crediti"),
					rs.getString("nome"),
					rs.getInt("pd")
					) ;
			
			corsiPerStudente.add(corsoPerStudente);
			studente.setCorsi(corsiPerStudente);
		}
		conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}	
		
	}
	
	public boolean isStudenteIscrittoAlCorso(Studente studente, Corso corso){
		
		final String sql = 
				"SELECT * " +
				"FROM iscrizione " +
				"WHERE matricola=? AND codins=?";

		boolean trovato = false;
		
		try {
			Connection conn = ConnectDB.getConnection();
	
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				trovato = true;
			}
			
			conn.close();
			return trovato;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
			
	}

}
