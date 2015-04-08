package br.com.sistemalocadora.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sistemalocadora.DAO.UsuarioDAO;
import br.com.sistemalocadora.Model.Usuario;


@WebServlet("/autcontroller.do")
public class AutenticadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public AutenticadorController() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessao = request.getSession(false);
		
		if(sessao!=null){
			
			sessao.invalidate();
			
			
		}
		
		response.sendRedirect("frmlogin.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("user");
		String senha = request.getParameter("senha");
		
		Usuario usu = new Usuario();
		System.out.println(user +" "+senha);
		
		usu.setUser(user);
		usu.setSenha(senha);
		
		UsuarioDAO usudao = new UsuarioDAO();
		Usuario usuretorno = usudao.Autenticar(usu);
		
		if( usuretorno != null){
			
			HttpSession sessao = request.getSession();
			sessao.setMaxInactiveInterval(3000);
			sessao.setAttribute("usuLogado", usuretorno);
			response.setContentType("text/html;charset=UTF-8");  
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}else{
			
		    String msg = "Usuário ou Senha Invalidos!";
			request.setAttribute("msg", msg);
			
			RequestDispatcher saida = request.getRequestDispatcher("frmlogin.jsp");
			saida.forward(request, response);
			
			
		}
		
		
	}

}
