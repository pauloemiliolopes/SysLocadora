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
import br.com.sistemalocadora.DAO.UsuarioDAO;
import br.com.sistemalocadora.Model.Genero;
import br.com.sistemalocadora.Model.Usuario;


@WebServlet("/usuariocontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UsuarioController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Metodo Get");

		String acao = request.getParameter("acao");

		UsuarioDAO dao = new UsuarioDAO();

		if (acao != null && acao.equals("exc")) {

			String id = request.getParameter("id");

			Usuario usuario = new Usuario();
			usuario.setId(Integer.parseInt(id));
			dao.excluir(usuario);
			
			response.sendRedirect("usuariocontroller.do?acao=lis");

		}

		if (acao != null && acao.equals("alt")) {

			String id = request.getParameter("id");

			Usuario usuario = dao.BuscarPorId(Integer.parseInt(id));

			request.setAttribute("usuario", usuario);
			RequestDispatcher saida = request
					.getRequestDispatcher("Usuario/frmusuario.jsp");
			saida.forward(request, response);

		}

		if (acao != null && acao.equals("cad")) {

			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setUser("");
			usuario.setSenha("");

			request.setAttribute("usuario", usuario);

			RequestDispatcher saida = request
					.getRequestDispatcher("Usuario/frmusuario.jsp");
			saida.forward(request, response);

		}

		if (acao != null && acao.equals("lis")) {

			List<Usuario> lista = dao.buscarTodos();

			request.setAttribute("lista", lista);
			RequestDispatcher saida = request
					.getRequestDispatcher("Usuario/listausuario.jsp");
			saida.forward(request, response);
		}
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Metodo Post");

		UsuarioDAO dao = new UsuarioDAO();

		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String user = request.getParameter("user");
		String senha = request.getParameter("senha");

		Usuario usuario = new Usuario();
       
		usuario.setId(Integer.parseInt(id));
		usuario.setNome(nome);
		usuario.setUser(user);
		usuario.setSenha(senha);

		dao.Salvar(usuario);

		PrintWriter saida = response.getWriter();
		saida.println("Salvo com sucesso!");
		
		response.sendRedirect("usuariocontroller.do?acao=lis");

	}
		
		
		
	}


