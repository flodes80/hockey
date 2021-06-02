package modele.gardien;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class GardienManager
 */
@Stateless(mappedName = "GardienManager")
@LocalBean
public class GardienManager implements GardienManagerRemote {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Collection<Gardien> listerGardiens() {
		return em.createQuery("SELECT g FROM Gardien g").getResultList();
	}

}
