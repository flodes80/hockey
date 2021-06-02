package modele.zonetir;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ZoneTir implements Serializable{

	private static final long serialVersionUID = -1050666475026108677L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false)
	private String libelle;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
