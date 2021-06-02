package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import controleur.EjbLocator;
import modele.gardien.Gardien;
import modele.gardien.GardienManager;
import modele.match.Match;
import modele.match.MatchManager;
import modele.tir.TirManager;
import modele.zonearret.ZoneArret;
import modele.zonearret.ZoneArretManager;
import modele.zonetir.ZoneTir;
import modele.zonetir.ZoneTirManager;

@Named("applicationBean")
@ApplicationScoped
public class ApplicationBean implements Serializable{
	
	private static final long serialVersionUID = -3010493057332076456L;
	
	private GardienManager gardienManager;
	private MatchManager matchManager;
	
	private Collection<Gardien> collGardiens;
	private Collection<Match> collMatchs;
	
	public ApplicationBean() {
		gardienManager = EjbLocator.getLocator().getGardienManager();
		matchManager = EjbLocator.getLocator().getMatchManager();
		loadCollections();
	}
	
	private void loadCollections() {
		collGardiens = gardienManager.listerGardiens();
		collMatchs = matchManager.listerMatchs();
	}

	public Collection<Gardien> getCollGardiens() {
		return collGardiens;
	}

	public Collection<Match> getCollMatchs() {
		return collMatchs;
	}
	
}
