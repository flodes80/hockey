package modele.zonearret;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ZoneArretManager
 */
@Stateless(mappedName = "ZoneArretManager")
@LocalBean
public class ZoneArretManager implements ZoneArretManagerRemote {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Collection<ZoneArret> listerZonesArret() {
		return em.createQuery("SELECT z FROM ZoneArret z").getResultList();
	}

	@Override
	public ZoneArret getZoneArretByLibelle(String libelle) {
		Query query = em.createQuery("SELECT z FROM ZoneArret z where z.libelle = :libelle");
		query.setParameter("libelle", libelle);
		return (ZoneArret) query.getSingleResult();
	}

}
