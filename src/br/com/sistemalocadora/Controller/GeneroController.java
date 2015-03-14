package br.com.sistemalocadora.Controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistemalocadora.DAO.GeneroDAO;
import br.com.sistemalocadora.JDBC.*;
import br.com.sistemalocadora.Model.Genero;

@WebServlet("/generocontroller.do")
public class GeneroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GeneroController() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Metodo Get");

		String acao = request.getParameter("acao");

		GeneroDAO dao = new GeneroDAO();

		if (acao != null && acao.equals("exc")) {

			String id = request.getParameter("id");

			Genero genero = new Genero();
			genero.setId(Integer.parseInt(id));
			dao.excluir(genero);
			
			response.sendRedirect("generocontroller.do?acao=lis");

		}

		if (acao != null && acao.equals("alt")) {

			String id = request.getParameter("id");

			Genero genero = dao.BuscarPorId(Integer.parseInt(id));

			request.setAttribute("genero", genero);
			RequestDispatcher saida = request
					.getRequestDispatcher("Genero/frmgenero.jsp");
			saida.forward(request, response);

		}

		if (acao != null && acao.equals("cad")) {

			Genero genero = new Genero();
			genero.setId(0);
			genero.setNome("");
			genero.setStatus("");

			request.setAttribute("genero", genero);

			RequestDispatcher saida = request
					.getRequestDispatcher("Genero/frmgenero.jsp");
			saida.forward(request, response);

		}

		if (acao != null && acao.equals("lis")) {

			List<Genero> lista = dao.buscarTodos();

			request.setAttribute("lista", lista);
			RequestDispatcher saida = request
					.getRequestDispatcher("Genero/listagenero.jsp");
			saida.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Metodo Post");

		GeneroDAO dao = new GeneroDAO();

		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String status = request.getParameter("status");

		Genero genero = new Genero();
       
		genero.setId(Integer.parseInt(id));
		genero.setNome(nome);
		genero.setStatus(status);

		dao.Salvar(genero);

		PrintWriter saida = response.getWriter();
		saida.println("Salvo com sucesso!");
		
		response.sendRedirect("generocontroller.do?acao=lis");

	}
}
