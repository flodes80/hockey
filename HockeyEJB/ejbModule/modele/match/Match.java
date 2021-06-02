package modele.match;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Matchs")
public class Match implements Serializable{

	private static final long serialVersionUID = -464356092076329659L;
	private static SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false)
	private String lieu;
	
	@Column(nullable=false)
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return lieu + ", le " + formater.format(date) + " - " + id;
	}
	
}
