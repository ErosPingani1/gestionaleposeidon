package it.academy.be.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.academy.be.dto.Film;
import it.academy.be.exception.DBException;
import it.academy.be.service.FilmService;

@WebServlet("/film/*")
public class GestioneFilmServlet extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
		
		try {
			
			String requestURI = req.getRequestURI();
			System.out.println("RequestURI [" + requestURI + "]");
			
			String requestPath = requestURI.substring(requestURI.lastIndexOf('/') + 1);
			System.out.println("Request Path [" + requestPath + "]");
			
			if("ricercaCompleta".equalsIgnoreCase(requestPath)) {
				
				List<Film> list = findAll(req);
				ObjectMapper objmapper = new ObjectMapper();
				String jsonContent = objmapper.writeValueAsString(list);
				resp.getWriter().print(jsonContent);

			}
			
		}catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().print("Si è verificato un errore inaspettato");
		}
		resp.getWriter().append("Served at: ").append(req.getContextPath());
	}
	
	private List<Film> findAll(HttpServletRequest req) throws DBException {
		
		FilmService service = new FilmService();
		return service.findFilm();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
