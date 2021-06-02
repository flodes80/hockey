package modele.user;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 4588110976310120390L;

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false,
			unique=true)
	private String name;
	
	@Column(nullable=false)
	private String password;
	
	@Transient
	private UUID uidToken;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UUID getUidToken() {
		return uidToken;
	}
	
	public void setUidToken(UUID uidToken) {
		this.uidToken = uidToken;
	}
	
}
