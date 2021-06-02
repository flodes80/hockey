package modele.tir;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import modele.gardien.Gardien;
import modele.match.Match;

@Remote
public interface TirManagerRemote {
	
	public Tir ajouterTir(Tir tir);
	
	public Collection<Tir> listerTirs();
	
	public int getTotalNbLancers(Match match, Gardien gardien);
	
	public List<Object[]> getNbLancersByZoneTir(Match match, Gardien gardien);
	
	public List<Object[]> getNbLancersByZoneArret(Match match, Gardien gardien);
	
	public int getTotalNbArrets(Match match, Gardien gardien);
	
	public List<Object[]> getNbArretsByZoneTir(Match match, Gardien gardien);
	
	public List<Object[]> getNbArretsByZoneArret(Match match, Gardien gardien);
	
	public float getPourcentageArret(Match match, Gardien gardien);
	
	public int getTotalNbLancers(Gardien gardien);
	
	public List<Object[]> getNbLancersByZoneTir(Gardien gardien);
	
	public List<Object[]> getNbLancersByZoneArret(Gardien gardien);
	
	public int getTotalNbArrets(Gardien gardien);
	
	public List<Object[]> getNbArretsByZoneTir(Gardien gardien);
	
	public List<Object[]> getNbArretsByZoneArret(Gardien gardien);
	
	public float getPourcentageArret(Gardien gardien);
}
