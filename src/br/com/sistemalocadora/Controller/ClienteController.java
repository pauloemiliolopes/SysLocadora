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

import br.com.sistemalocadora.DAO.ClienteDAO;
import br.com.sistemalocadora.DAO.GeneroDAO;
import br.com.sistemalocadora.Model.Cliente;
import br.com.sistemalocadora.Model.Genero;


@WebServlet("/clientecontroller.do")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ClienteController() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Metodo Get");

		String acao = request.getParameter("acao");
		
		String buscarcli = request.getParameter("buscarcli");

		ClienteDAO dao = new ClienteDAO();

		if (acao != null && acao.equals("exc")) {

			String id = request.getParameter("id");

			Cliente cliente = new Cliente();
			cliente.setId(Integer.parseInt(id));
			dao.excluir(cliente);
			
			response.sendRedirect("clientecontroller.do?acao=lis");

		}

		if (acao != null && acao.equals("alt")) {

			String id = request.getParameter("id");

			Cliente cliente = dao.BuscarPorId(Integer.parseInt(id));

			request.setAttribute("cliente", cliente);
			RequestDispatcher saida = request
					.getRequestDispatcher("Cliente/frmcliente.jsp");
			saida.forward(request, response);

		}

		if (acao != null && acao.equals("cad")) {

			Cliente cliente = new Cliente();
			cliente.setId(0);
			cliente.setNome("");
			

			request.setAttribute("cliente", cliente);

			RequestDispatcher saida = request
					.getRequestDispatcher("Cliente/frmcliente.jsp");
			saida.forward(request, response);

		}

		if (acao != null && acao.equals("lis")) {

			List<Cliente> lista = dao.buscarTodos();

			request.setAttribute("lista", lista);
			RequestDispatcher saida = request
					.getRequestDispatcher("Cliente/listacliente.jsp");
			saida.forward(request, response);
		}
		
		
		if (buscarcli != null) {

			List<Cliente> lista = dao.BuscarPorNome(buscarcli);

			request.setAttribute("lista", lista);

			RequestDispatcher saida = request
					.getRequestDispatcher("Cliente/listacliente.jsp");
			saida.forward(request, response);

		}

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Metodo Post");

		ClienteDAO dao = new ClienteDAO();

		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		String endereco = request.getParameter("endereco");
		String status = request.getParameter("status");

		Cliente cliente = new Cliente();
       
		cliente.setId(Integer.parseInt(id));
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setEmail(email);
		cliente.setTelefone(telefone);
		cliente.setEndereco(endereco);
		cliente.setStatus(status);

		dao.Salvar(cliente);

		PrintWriter saida = response.getWriter();
		saida.println("Salvo com sucesso!");
		
		response.sendRedirect("clientecontroller.do?acao=lis");

	}
		
	}


