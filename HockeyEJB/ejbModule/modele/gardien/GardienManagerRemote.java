package modele.gardien;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface GardienManagerRemote {
	
	public Collection<Gardien> listerGardiens();
}
