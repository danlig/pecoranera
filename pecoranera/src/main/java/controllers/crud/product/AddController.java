package controllers.crud.product;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import dao.ProductTypeDao;
import model.Product;

public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**TODO:: Implementare il controller per l'aggiunta del prodotto con i seguenti avvertimenti:
		 * - Controllare se il nome e la descrizione non siano vuoti, se no inviare un messaggio di errore
		 * - Controllare se il prezzo sia formattato bene
		 * - Controllare se l'id del product type si esistente, se no inviare una BAD REQUEST
		 */
	}

}
