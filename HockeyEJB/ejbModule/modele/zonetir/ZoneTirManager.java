package modele.zonetir;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modele.zonearret.ZoneArret;

/**
 * Session Bean implementation class ZoneTirManager
 */
@Stateless(mappedName = "ZoneTirManager")
@LocalBean
public class ZoneTirManager implements ZoneTirManagerRemote {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Collection<ZoneTir> listerZonesTir() {
		return em.createQuery("SELECT z FROM ZoneTir z").getResultList();
	}

	@Override
	public ZoneTir getZoneTirByLibelle(String libelle) {
		Query query = em.createQuery("SELECT z FROM ZoneTir z where z.libelle = :libelle");
		query.setParameter("libelle", libelle);
		return (ZoneTir) query.getSingleResult();
	}
}
