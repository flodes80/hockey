package modele.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class UserManager
 */
@Stateless(mappedName = "UserManager")
@LocalBean
public class UserManager implements UserManagerRemote {

	@PersistenceContext
	EntityManager em;
	
	private static List<User> connectedUsers = new ArrayList<User>();

	@Override
	public User login(String username, String password) throws Exception {
		User user = null;
		
		Query query = em.createQuery("SELECT u FROM User u where u.name = :name AND u.password = :pwd");
		query.setParameter("name", username);
		query.setParameter("pwd", password);
		List<User> usersResult = query.getResultList();
		if(usersResult.size() == 1) {
			user = usersResult.get(0);
			// On attribue un token random au user pour le reconnaitre par la suite
			user.setUidToken(UUID.randomUUID());
			// Clear du password pour éviter lecture mémoire côté client
			user.setPassword("");
			
			// Clear au cas où déjà présent dans les connectés
			connectedUsers.removeIf(u -> u.getName().equalsIgnoreCase(username));
			connectedUsers.add(user);
		}
		
		return user;
	}
	
	/**
	 * Fonction statique permettant de déterminer si un utilisateur donné est authentifié ou non
	 * @param user Utilisateur à vérifier
	 * @return Vrai si authentifié ou faux si non authentifié
	 */
	public static boolean isUserAuthenticated(User user){
		if(user == null || user.getName().isBlank() || user.getUidToken() == null)
			return false;
		
		for(User connectedUser : connectedUsers) {
			if(connectedUser.getName().equalsIgnoreCase(user.getName()) && connectedUser.getUidToken().equals(user.getUidToken()))
				return true;
		}
		
		return false;
	}

}
