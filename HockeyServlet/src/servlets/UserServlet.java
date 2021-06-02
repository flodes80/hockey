package servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleur.EjbLocator;
import modele.user.User;
import modele.user.UserManager;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserManager userManager = EjbLocator.getLocator().getUserManager();
			// R�cup�ration du flux d'entr�e envoy� par l'applet
			ObjectInputStream entree=new ObjectInputStream(request.getInputStream());
			// R�cup�ration de l'objet envoy� par le client
			String[] authInfos = (String[]) entree.readObject();
			if(authInfos != null && authInfos.length == 2) {
				// Utilisation du usermanager pour tentative de login
				User user = userManager.login(authInfos[0], authInfos[1]);
				// Pr�paration du flux de sortie
				ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
				// Envoi du r�sultat au client
				sortie.writeObject(user);
			}
		} catch (Exception ex) {
			System.out.println("Erreur d'ex�cution de la requ�te : "+ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
