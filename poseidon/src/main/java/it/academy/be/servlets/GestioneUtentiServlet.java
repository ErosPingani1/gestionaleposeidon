package it.academy.be.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.academy.be.dto.Cliente;
import it.academy.be.exception.DBException;
import it.academy.be.service.UtentiService;

@WebServlet("/gestioneUtenti/*")
public class GestioneUtentiServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		
		try {
		
			String requestURI = req.getRequestURI();
			System.out.println("RequestURI [" + requestURI + "]");
			
			String requestPath = requestURI.substring(requestURI.lastIndexOf('/') + 1);
			System.out.println("Request Path [" + requestPath + "]");
			
			if ("ricercaPagina".equalsIgnoreCase(requestPath)) {
				
				List<Cliente> list = findPaged(req);
				
				ObjectMapper objmapper = new ObjectMapper();
				String jsonContent = objmapper.writeValueAsString(list);
				resp.getWriter().print(jsonContent);
				
			} else if ("ricercaNome".equalsIgnoreCase(requestPath)) {
				
				Cliente utente = findByName(req);
				
				if (utente == null) {
					resp.getWriter().print("Nessun utente trovato con il nome ricercato");
				} else {
					ObjectMapper objmapper = new ObjectMapper();
					String jsonContent = objmapper.writeValueAsString(utente);
					resp.getWriter().print(jsonContent);
				}

			} else if ("ricercaCompleta".equalsIgnoreCase(requestPath)) {
				
				List<Cliente> list = findAll(req);
				
				ObjectMapper objmapper = new ObjectMapper();
				String jsonContent = objmapper.writeValueAsString(list);
				resp.getWriter().print(jsonContent);
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			resp.getWriter().print("Si è verificato un errore inaspettato");
		}
	}
	
	private List<Cliente> findAll(HttpServletRequest req) throws DBException {
		
		UtentiService service = new UtentiService();
		return service.findUtenti();
		
	}

	private Cliente findByName(HttpServletRequest req) throws DBException {
		
		String query = req.getParameter("q");
		
		UtentiService service = new UtentiService();
		return service.findByName(query);
	}

	private List<Cliente> findPaged(HttpServletRequest req) throws DBException {

		int pagina = Integer.parseInt(req.getParameter("p"));
		int itemPerPagina = Integer.parseInt(req.getParameter("i"));

		UtentiService service = new UtentiService();
		return service.findUtenti(pagina, itemPerPagina);
	}
	
}
