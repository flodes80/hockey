package servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleur.EjbLocator;
import modele.gardien.GardienManager;
import modele.user.User;
import modele.user.UserManager;

/**
 * Servlet implementation class GardienServlet
 */
@WebServlet("/GardienServlet")
public class GardienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GardienServlet() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			GardienManager gardienManager = EjbLocator.getLocator().getGardienManager();
			
			// R�cup�ration du flux d'entr�e envoy� par l'applet
			ObjectInputStream entree=new ObjectInputStream(request.getInputStream());
			
			// R�cup�ration de l'objet envoy� par le client
			User user = (User) entree.readObject();
			
			if(UserManager.isUserAuthenticated(user)) {
				ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
				// Envoi du r�sultat au client
				sortie.writeObject(gardienManager.listerGardiens());
			}
		} catch (Exception ex) {
			System.out.println("Erreur d'ex�cution de la requ�te : "+ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
