package br.com.sistemalocadora.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import br.com.sistemalocadora.DAO.ClienteDAO;
import br.com.sistemalocadora.DAO.FilmeDAO;
import br.com.sistemalocadora.DAO.GeneroDAO;
import br.com.sistemalocadora.Model.Cliente;
import br.com.sistemalocadora.Model.Filme;
import br.com.sistemalocadora.Model.Genero;


@WebServlet("/filmecontroller.do")
public class FilmeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FilmeController() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Metodo Get");

		String acao = request.getParameter("acao");

		FilmeDAO dao = new FilmeDAO();
		
		GeneroDAO daogenero = new GeneroDAO();

		if (acao != null && acao.equals("exc")) {

			String id = request.getParameter("id");

			Filme filme = new Filme();
			filme.setId(Integer.parseInt(id));
			dao.excluir(filme);
			
			response.sendRedirect("filmecontroller.do?acao=lis");

		}

		if (acao != null && acao.equals("alt")) {

			String id = request.getParameter("id");

			Filme filme = dao.BuscarPorId(Integer.parseInt(id));
			
			request.setAttribute("filme", filme);
				
            List<Genero> listaGenero = daogenero.buscarTodos();
			
			request.setAttribute("listaGenero", listaGenero);

			RequestDispatcher saida = request
					.getRequestDispatcher("Filme/frmfilme.jsp");
			saida.forward(request, response);

		}

		if (acao != null && acao.equals("cad")) {
			
			Filme filme = new Filme();
			
			filme.setId(0);
			
			List<Genero> listaGenero = daogenero.buscarTodos();
			
			request.setAttribute("listaGenero", listaGenero);

			request.setAttribute("filme", filme);

			RequestDispatcher saida = request
					.getRequestDispatcher("Filme/frmfilme.jsp");
			saida.forward(request, response);

		}

		if (acao != null && acao.equals("lis")) {
		
			List<Filme> lista = dao.buscarTodos();
			
			request.setAttribute("lista", lista);
		
			RequestDispatcher saida = request
					.getRequestDispatcher("Filme/listafilme.jsp");
			saida.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Metodo Post");

		PrintWriter saida = response.getWriter();
		
		FilmeDAO dao = new FilmeDAO();

		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String datalanc = request.getParameter("lancamento");
		String sinopse = request.getParameter("sinopse");
		String tempoloc = request.getParameter("tempolocacao");
		String preco = request.getParameter("preco");
		String qtd = request.getParameter("qtd");
		String genero = request.getParameter("generos");
		String status = request.getParameter("status");

		

			
			Filme filme = new Filme();
		       
			filme.setId(Integer.parseInt(id));
			filme.setNome(nome);
		
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
			try {
				if(datalanc.matches("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$")){
				cal.setTime(formate.parse(datalanc));
				}else {
					saida.println("Data fora do Padra (dd/MM/YYYY)!"); 	
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			filme.setDatalanc(cal); 
			filme.setSinopse(sinopse);
			filme.setTempoloc(Integer.parseInt(tempoloc));
			filme.setQtd(Integer.parseInt(qtd));
			filme.setPreco(new BigDecimal(preco));
			filme.setGenero(Integer.parseInt(genero));
			filme.setStatus(status);
			System.out.println(filme);
			dao.Salvar(filme);
	
		
		saida.println("Salvo com sucesso!");
		
		response.sendRedirect("filmecontroller.do?acao=lis");

	}
		

}
