package br.com.sistemalocadora.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistemalocadora.DAO.ClienteDAO;
import br.com.sistemalocadora.DAO.FilmeDAO;
import br.com.sistemalocadora.DAO.GeneroDAO;
import br.com.sistemalocadora.DAO.ItensLocacaoDAO;
import br.com.sistemalocadora.DAO.LocacaoDAO;
import br.com.sistemalocadora.Model.Cliente;
import br.com.sistemalocadora.Model.Filme;
import br.com.sistemalocadora.Model.Genero;
import br.com.sistemalocadora.Model.ItensLocacao;
import br.com.sistemalocadora.Model.Locacao;

@WebServlet("/locacaocontroller.do")
public class LocacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LocacaoController() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Metodo Get");

		String acao = request.getParameter("acao");

		LocacaoDAO dao = new LocacaoDAO();

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

		if (acao != null && acao.equals("busca")) {
		
			String nome = request.getParameter("buscar");
			
			System.out.println(nome);

			List<Cliente> listaCliente = daocliente.BuscarPorNome(nome);

			request.setAttribute("listaCliente", listaCliente);

			RequestDispatcher saida = request
					.getRequestDispatcher("Locacao/frmlocacao.jsp");
			saida.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
