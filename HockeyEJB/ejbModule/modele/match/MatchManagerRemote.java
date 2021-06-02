package modele.match;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface MatchManagerRemote {
	
	public Collection<Match> listerMatchs();
}
