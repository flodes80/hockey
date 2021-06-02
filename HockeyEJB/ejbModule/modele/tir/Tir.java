package modele.tir;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import modele.gardien.Gardien;
import modele.match.Match;
import modele.zonearret.ZoneArret;
import modele.zonetir.ZoneTir;

@Entity
public class Tir implements Serializable{

	private static final long serialVersionUID = 5350440668851401141L;
	
	@Id
	@ManyToOne
	private Gardien gardien;
	
	@Id
	@ManyToOne
	private Match match;
	
	@Id
	@ManyToOne
	private ZoneTir zoneTir;
	
	@Id
	@ManyToOne
	private ZoneArret zoneArret;
	
	private int nbTir;
	
	private int nbBut;


	public Gardien getGardien() {
		return gardien;
	}

	public void setGardien(Gardien gardien) {
		this.gardien = gardien;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public ZoneTir getZoneTir() {
		return zoneTir;
	}

	public void setZoneTir(ZoneTir zoneTir) {
		this.zoneTir = zoneTir;
	}

	public ZoneArret getZoneArret() {
		return zoneArret;
	}

	public void setZoneArret(ZoneArret zoneArret) {
		this.zoneArret = zoneArret;
	}

	public int getNbTir() {
		return nbTir;
	}

	public void setNbTir(int nbTir) {
		this.nbTir = nbTir;
	}

	public int getNbBut() {
		return nbBut;
	}

	public void setNbBut(int nbBut) {
		this.nbBut = nbBut;
	}
	
}
