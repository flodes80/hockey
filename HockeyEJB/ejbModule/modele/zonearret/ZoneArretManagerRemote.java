package modele.zonearret;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface ZoneArretManagerRemote {
	
	public Collection<ZoneArret> listerZonesArret();
	
	public ZoneArret getZoneArretByLibelle(String libelle);
}
