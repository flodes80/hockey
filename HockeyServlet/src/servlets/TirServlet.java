package servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleur.EjbLocator;
import modele.tir.Tir;
import modele.tir.TirManager;
import modele.user.User;
import modele.user.UserManager;
import modele.zonearret.ZoneArret;
import modele.zonearret.ZoneArretManager;
import modele.zonetir.ZoneTir;
import modele.zonetir.ZoneTirManager;

/**
 * Servlet implementation class TirServlet
 */
@WebServlet("/TirServlet")
public class TirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			TirManager tirManager = EjbLocator.getLocator().getTirManager();
			
			// Récupération du flux d'entrée envoyé par l'applet
			ObjectInputStream entree=new ObjectInputStream(request.getInputStream());
			
			// Récupération de l'objet envoyé par le client
			Object[] objects = (Object[]) entree.readObject();
			Tir tir = (Tir) objects[0];
			User user = (User) objects[1];
			
			if(UserManager.isUserAuthenticated(user)) {
				// Récupération des entités de zone d'arrêt car non récupéré par le client
				ZoneArretManager zoneArretManager = EjbLocator.getLocator().getZoneArretManager();
				ZoneArret zoneArret = zoneArretManager.getZoneArretByLibelle(tir.getZoneArret().getLibelle());
				tir.setZoneArret(zoneArret);
				
				// Récupération des entités de zone de tir car non récupéré par le client
				ZoneTirManager zoneTirManager = EjbLocator.getLocator().getZoneTirManager();
				ZoneTir zoneTir = zoneTirManager.getZoneTirByLibelle(tir.getZoneTir().getLibelle());
				tir.setZoneTir(zoneTir);
				
				// Ajout du tir
				tirManager.ajouterTir(tir);
			}
					
		} catch (Exception ex) {
			System.out.println("Erreur d'exécution de la requête : "+ex);
		}
		doGet(request, response);
	}

}
