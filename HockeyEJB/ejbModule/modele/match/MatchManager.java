package modele.match;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class MatchManager
 */
@Stateless(mappedName = "MatchManager")
@LocalBean
public class MatchManager implements MatchManagerRemote {

	@PersistenceContext
	EntityManager em;

	@Override
	public Collection<Match> listerMatchs() {
		return em.createQuery("SELECT m FROM Match m").getResultList();
	}

}
