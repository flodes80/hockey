package modele.zonetir;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface ZoneTirManagerRemote {
	
	public Collection<ZoneTir> listerZonesTir();

	public ZoneTir getZoneTirByLibelle(String libelle);
}
