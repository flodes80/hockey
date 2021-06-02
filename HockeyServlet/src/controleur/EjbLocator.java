package controleur;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import modele.gardien.GardienManager;
import modele.match.MatchManager;
import modele.tir.TirManager;
import modele.user.UserManager;
import modele.zonearret.ZoneArretManager;
import modele.zonetir.ZoneTirManager;

public class EjbLocator {
	
	private static EjbLocator instance = new EjbLocator();
	
	private EjbLocator() {
	}
	
	public static EjbLocator getLocator() {
		return instance;
	}

	private <T> T getEjb(Class<T> ejbClass, String beanName) {
		try {
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			final String appName = "HockeyEAR";
			final String moduleName = "HockeyEJB";
			return (T) context.lookup("java:global/" + appName + "/" + moduleName + "/" + beanName + "!" + ejbClass.getName());
		} catch (NamingException e) {
			return null;
		}
	}
	
	public UserManager getUserManager() {
		return getEjb(UserManager.class, "UserManager");
	}
	
	public GardienManager getGardienManager() {
		return getEjb(GardienManager.class, "GardienManager");
	}
	
	public MatchManager getMatchManager() {
		return getEjb(MatchManager.class, "MatchManager");
	}
	
	public ZoneTirManager getZoneTirManager() {
		return getEjb(ZoneTirManager.class, "ZoneTirManager");
	}
	
	public ZoneArretManager getZoneArretManager() {
		return getEjb(ZoneArretManager.class, "ZoneArretManager");
	}
	
	public TirManager getTirManager() {
		return getEjb(TirManager.class, "TirManager");
	}
}
