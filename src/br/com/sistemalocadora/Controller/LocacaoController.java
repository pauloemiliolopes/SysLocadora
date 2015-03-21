package br.com.sistemalocadora.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistemalocadora.DAO.ClienteDAO;
import br.com.sistemalocadora.DAO.FilmeDAO;
import br.com.sistemalocadora.DAO.LocacaoDAO;
import br.com.sistemalocadora.Model.Cliente;
import br.com.sistemalocadora.Model.Filme;
import br.com.sistemalocadora.Model.Locacao;

@WebServlet("/locacaocontroller.do")
public class LocacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LocacaoController() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
/*
		System.out.println("Metodo Get");

		String acao = request.getParameter("acao");
		String nomeCliente = request.getParameter("buscar");
		String nomeFilme = request.getParameter("buscarfilme");
		LocacaoDAO dao = new LocacaoDAO();
		FilmeDAO filmeDao = new FilmeDAO();

		ClienteDAO daocliente = new ClienteDAO();

		if (acao != null && acao.equals("exc")) {

			String id = request.getParameter("id");

			Locacao locacao = new Locacao();
			locacao.setId(Integer.parseInt(id));
			dao.excluir(locacao);

			response.sendRedirect("locacaocontroller.do?acao=cad");

		}

		if (acao != null && acao.equals("alt")) {

			String id = request.getParameter("id");

			Locacao locacao = dao.BuscarPorId(Integer.parseInt(id));

			request.setAttribute("locacao", locacao);

			List<Cliente> listaCliente = daocliente.buscarTodos();

			request.setAttribute("listaCliente", listaCliente);

			RequestDispatcher saida = request
					.getRequestDispatcher("Locacao/frmlocacao.jsp");
			saida.forward(request, response);

		}

		if (acao != null && acao.equals("cad")) {

			List<Cliente> listaCliente = daocliente.buscarTodos();

			request.setAttribute("listaCliente", listaCliente);

			List<Locacao> lista = dao.buscarTodos();

			request.setAttribute("lista", lista);

			RequestDispatcher saida = request
					.getRequestDispatcher("Locacao/frmlocacao.jsp");
			saida.forward(request, response);

		}

		if (nomeCliente != null) {

			List<Cliente> listaCliente = daocliente.BuscarPorNome(nomeCliente);

			request.setAttribute("listaCliente", listaCliente);

			RequestDispatcher saida = request
					.getRequestDispatcher("Locacao/frmlocacao.jsp");
			saida.forward(request, response);

		}

		if (nomeFilme != null) {

			List<Filme> listFilme = filmeDao.BuscarPorNome(nomeFilme);

			request.setAttribute("listfilme", listFilme);

			RequestDispatcher saida = request
					.getRequestDispatcher("Locacao/frmlocacao.jsp");
			saida.forward(request, response);
		}*/
		RequestDispatcher saida = request
				.getRequestDispatcher("Locacao/locacao.jsp");
		saida.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
