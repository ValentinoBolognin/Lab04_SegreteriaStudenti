package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

public class Studente {
	
	private int matricola;
	private String cognome;
	private String nome;
	private String CDS;
	
	private List<Corso> corsi;
	
	public Studente(int matricola, String cognome, String nome) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		
		this.CDS = null;
		this.corsi = new LinkedList<Corso>();
	}

	/**
	 * @return the matricola
	 */
	public int getMatricola() {
		return matricola;
	}

	/**
	 * @param matricola the matricola to set
	 */
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return the CDS
	 */
	public String getCDS() {
		return CDS;
	}

	/**
	 * @param cDS the CDS to set
	 */
	public void setCDS(String CDS) {
		this.CDS = CDS;
	}
	
	/**
	 * @return the corsi
	 */
	public List<Corso> getCorsi() {
		return corsi;
	}

	/**
	 * @param corsi the corsi to set
	 */
	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}
	
	public void add(Corso corso) {
		corsi.add(corso);
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return cognome+" "+nome;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}

}
