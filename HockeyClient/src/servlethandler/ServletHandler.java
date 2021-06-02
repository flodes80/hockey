package servlethandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;

import modele.gardien.Gardien;
import modele.match.Match;
import modele.tir.Tir;
import modele.user.User;

public class ServletHandler {
	
	private final String baseUrl = "http://localhost:8080/HockeyServlet/";
	
	/**
	 * Demande authentification � la servlet
	 * @param username Nom d'utilisateur
	 * @param password Mot de passe
	 * @return Un user compl�t� si ok
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public User callLogin(String username, String password) throws IOException, ClassNotFoundException {
		User user = null;
		URL url = new URL(baseUrl+"UserServlet");
		URLConnection connexion=url.openConnection();
		connexion.setDoOutput(true);
		// R�cup�ration du flux de sortie
		ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
		// Envoi de la demande d'auth
		String[] authInfos = {username, password};
		fluxsortie.writeObject(authInfos);
		// R�cup�ration du flux d�entr�e
		ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
		user = (User) fluxentree.readObject();	
		
		return user;
	}
	
	/**
	 * Appel au servlet pour obtenir la liste des gardiens
	 * @param user Utilisateur actuellement authentif�
	 * @return Collection de gardiens existants
	 */
	public Collection<Gardien> getListeGardien(User user){
		Collection<Gardien> gardiens = null;
		try {
			URL url = new URL(baseUrl+"GardienServlet");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true);
			// R�cup�ration du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			// Envoi du user authentifi�
			fluxsortie.writeObject(user);
			// R�cup�ration du flux d�entr�e
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			gardiens = (Collection<Gardien>) fluxentree.readObject();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gardiens;
	}
	
	/**
	 * Appel au servlet pour obtenir la liste des matchs
	 * @param user Utilisateur actuellement authentif�
	 * @return Collection de matchs existants
	 */
	public Collection<Match> getListeMatch(User user){
		Collection<Match> matchs = null;
		try {
			URL url = new URL(baseUrl+"MatchServlet");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true);
			// R�cup�ration du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			// Envoi du user authentifi�
			fluxsortie.writeObject(user);
			// R�cup�ration du flux d�entr�e
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			matchs = (Collection<Match>) fluxentree.readObject();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchs;
	}
	
	public void envoiDonneesTir(Tir tir, User user) {
		try {
			URL url = new URL(baseUrl+"TirServlet");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true);
			// R�cup�ration du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			Object[] objects = {tir, user};
			// Envoi des donn�es tu tir et user authentifi�
			fluxsortie.writeObject(objects);
			// Envoi de la requ�te
			connexion.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
