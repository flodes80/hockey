package modele.tir;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modele.gardien.Gardien;
import modele.match.Match;

/**
 * Session Bean implementation class TirManager
 */
@Stateless(mappedName = "TirManager")
@LocalBean
public class TirManager implements TirManagerRemote {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Tir ajouterTir(Tir tir) {
		// On check s'il existe déjà un enregistrement existant
		Query query = em.createQuery("SELECT t FROM Tir t WHERE "
				+ "t.match = :match "
				+ "AND t.gardien = :gardien "
				+ "AND t.zoneTir = :zoneTir "
				+ "AND t.zoneArret = :zoneArret");
		query.setParameter("match", tir.getMatch());
		query.setParameter("gardien", tir.getGardien());
		query.setParameter("zoneTir", tir.getZoneTir());
		query.setParameter("zoneArret", tir.getZoneArret());
		
		// Création enregistrement
		if(query.getResultList().size() == 0) {
			em.persist(tir);
		}
		// Merge de l'enregistrement en ajoutant le tir / but
		else {
			Tir tirToMerge = (Tir) query.getSingleResult();
			tirToMerge.setNbBut(tirToMerge.getNbBut() + tir.getNbBut());
			tirToMerge.setNbTir(tirToMerge.getNbTir() + tir.getNbTir());
			tir = em.merge(tirToMerge);
		}
		
		return tir;
	}

	@Override
	public Collection<Tir> listerTirs() {
		return em.createQuery("SELECT t FROM Tir t").getResultList();
	}

	@Override
	public int getTotalNbLancers(Match match, Gardien gardien) {
		int total = 0;
		
		Query query = em.createQuery("SELECT sum(t.nbTir) + sum(t.nbBut) FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "AND t.match = :match "
				+ "group by gardien, match");
		query.setParameter("gardien", gardien);
		query.setParameter("match", match);
		
		List<Object[]> resultList = query.getResultList();
		
		if(resultList.size() > 0) {
			total = Integer.valueOf(String.valueOf(resultList.get(0)));
		}
		
		return total;
	}

	@Override
	public List<Object[]> getNbLancersByZoneTir(Match match, Gardien gardien) {
		Query query = em.createQuery("SELECT sum(t.nbTir) + sum(t.nbBut), t.zoneTir FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "AND t.match = :match "
				+ "group by t.gardien, t.match, t.zoneTir");
		query.setParameter("gardien", gardien);
		query.setParameter("match", match);		
		
		return query.getResultList();
	}

	@Override
	public List<Object[]> getNbLancersByZoneArret(Match match, Gardien gardien) {
		Query query = em.createQuery("SELECT sum(t.nbTir) + sum(t.nbBut), t.zoneArret FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "AND t.match = :match "
				+ "group by t.gardien, t.match, t.zoneArret");
		query.setParameter("gardien", gardien);
		query.setParameter("match", match);		
		
		return query.getResultList();
	}

	@Override
	public int getTotalNbArrets(Match match, Gardien gardien) {
		int total = 0;
		
		Query query = em.createQuery("SELECT sum(t.nbTir) FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "AND t.match = :match "
				+ "group by t.gardien, t.match");
		query.setParameter("gardien", gardien);
		query.setParameter("match", match);
		
		List<Object[]> resultList = query.getResultList();
		
		if(resultList.size() > 0) {
			total = Integer.valueOf(String.valueOf(resultList.get(0)));
		}
		
		return total;
	}

	@Override
	public List<Object[]> getNbArretsByZoneTir(Match match, Gardien gardien) {
		Query query = em.createQuery("SELECT sum(t.nbTir), t.zoneTir FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "AND t.match = :match "
				+ "group by t.gardien, t.match, t.zoneTir");
		query.setParameter("gardien", gardien);
		query.setParameter("match", match);		
		
		return query.getResultList();
	}

	@Override
	public List<Object[]> getNbArretsByZoneArret(Match match, Gardien gardien) {
		Query query = em.createQuery("SELECT sum(t.nbTir), t.zoneArret FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "AND t.match = :match "
				+ "group by t.gardien, t.match, t.zoneArret");
		query.setParameter("gardien", gardien);
		query.setParameter("match", match);		
		
		return query.getResultList();
	}

	@Override
	public float getPourcentageArret(Match match, Gardien gardien) {
		float pourcentage = 0.0f;
		
		Query query = em.createQuery("SELECT sum(t.nbTir)/(sum(t.nbTir) + sum(t.nbBut)) FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "AND t.match = :match "
				+ "group by t.gardien, t.match");
		query.setParameter("gardien", gardien);
		query.setParameter("match", match);
		
		List<Object[]> resultList = query.getResultList();
		
		if(resultList.size() > 0) {
			pourcentage = Float.valueOf(String.valueOf(resultList.get(0)));
		}
		
		return pourcentage;
	}

	@Override
	public int getTotalNbLancers(Gardien gardien) {
		int total = 0;
		
		Query query = em.createQuery("SELECT sum(t.nbTir) + sum(t.nbBut) FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "group by gardien");
		query.setParameter("gardien", gardien);
		
		List<Object[]> resultList = query.getResultList();
		
		if(resultList.size() > 0) {
			total = Integer.valueOf(String.valueOf(resultList.get(0)));
		}
		
		return total;
	}

	@Override
	public List<Object[]> getNbLancersByZoneTir(Gardien gardien) {
		Query query = em.createQuery("SELECT sum(t.nbTir) + sum(t.nbBut), t.zoneTir FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "group by t.gardien, t.zoneTir");
		query.setParameter("gardien", gardien);	
		
		return query.getResultList();
	}

	@Override
	public List<Object[]> getNbLancersByZoneArret(Gardien gardien) {
		Query query = em.createQuery("SELECT sum(t.nbTir) + sum(t.nbBut), t.zoneArret FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "group by t.gardien, t.zoneArret");
		query.setParameter("gardien", gardien);	
		
		return query.getResultList();
	}

	@Override
	public int getTotalNbArrets(Gardien gardien) {
		int total = 0;
		
		Query query = em.createQuery("SELECT sum(t.nbTir) FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "group by t.gardien");
		query.setParameter("gardien", gardien);
		
		List<Object[]> resultList = query.getResultList();
		
		if(resultList.size() > 0) {
			total = Integer.valueOf(String.valueOf(resultList.get(0)));
		}
		
		return total;
	}

	@Override
	public List<Object[]> getNbArretsByZoneTir(Gardien gardien) {
		Query query = em.createQuery("SELECT sum(t.nbTir), t.zoneTir FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "group by t.gardien, t.zoneTir");
		query.setParameter("gardien", gardien);
		
		return query.getResultList();
	}

	@Override
	public List<Object[]> getNbArretsByZoneArret(Gardien gardien) {
		Query query = em.createQuery("SELECT sum(t.nbTir), t.zoneArret FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "group by t.gardien, t.zoneArret");
		query.setParameter("gardien", gardien);	
		
		return query.getResultList();
	}

	@Override
	public float getPourcentageArret(Gardien gardien) {
		float pourcentage = 0.0f;
		
		Query query = em.createQuery("SELECT sum(t.nbTir)/(sum(t.nbTir) + sum(t.nbBut)) FROM Tir t "
				+ "WHERE t.gardien = :gardien "
				+ "group by t.gardien");
		query.setParameter("gardien", gardien);
		
		List<Object[]> resultList = query.getResultList();
		
		if(resultList.size() > 0) {
			pourcentage = Float.valueOf(String.valueOf(resultList.get(0)));
		}
		
		return pourcentage;
	}

}
