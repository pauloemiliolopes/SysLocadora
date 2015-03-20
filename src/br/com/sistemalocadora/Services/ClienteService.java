package br.com.sistemalocadora.Services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistemalocadora.DAO.ClienteDAO;
import br.com.sistemalocadora.Model.Cliente;

@WebServlet("/api/clientes")
public class ClienteService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDAO clienteDAO;
	private PrintWriter saida;
	private List<Cliente> clientes;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		saida = response.getWriter();

		String nome = request.getParameter("nome");

		clienteDAO = new ClienteDAO();

		if (nome != null && nome.matches("^[A-Z][a-zA-Z '&-]*[A-Za-z]$")) {
			clientes = clienteDAO.BuscarPorNome(nome);
			saida.println(GsonResponse.JsonBilder(clientes));
			saida.flush();
		} else {
			clientes = clienteDAO.buscarTodos();
			saida.println(GsonResponse.JsonBilder(clientes));
			saida.flush();
		}

	}

}
