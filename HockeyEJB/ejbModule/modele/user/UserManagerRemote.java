package modele.user;

import javax.ejb.Remote;

@Remote
public interface UserManagerRemote {
	
	public User login(String username, String password) throws Exception;
	
}
