package br.com.sistemalocadora.Services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistemalocadora.DAO.FilmeDAO;
import br.com.sistemalocadora.Model.Filme;

@WebServlet("/api/filmes")
public class FilmeService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FilmeDAO filmeDAO;
	private PrintWriter saida;
	private List<Filme> filmes;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");

		saida = response.getWriter();

		String nome = request.getParameter("nome");

		filmeDAO = new FilmeDAO();

		if (nome != null && nome.matches("^[A-Z][a-zA-Z '&-]*[A-Za-z]$")) {
			filmes = filmeDAO.BuscarPorNome(nome);
			saida.println(GsonResponse.JsonBilder(filmes));
			saida.flush();
		} else {
			filmes = filmeDAO.buscarTodos();
			saida.println(GsonResponse.JsonBilder(filmes));
			saida.flush();
		}

	}

}
