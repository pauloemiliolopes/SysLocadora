package br.com.sistemalocadora.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import br.com.sistemalocadora.DAO.ClienteDAO;
import br.com.sistemalocadora.DAO.FilmeDAO;
import br.com.sistemalocadora.DAO.ItensLocacaoDAO;
import br.com.sistemalocadora.DAO.LocacaoDAO;
import br.com.sistemalocadora.Model.Cliente;
import br.com.sistemalocadora.Model.Filme;
import br.com.sistemalocadora.Model.Genero;
import br.com.sistemalocadora.Model.ItensLocacao;
import br.com.sistemalocadora.Model.Locacao;

@WebServlet("/locacaocontroller.do")
public class LocacaoController extends HttpServlet implements
		ChagePositionGenero<Cliente> {
	private static final long serialVersionUID = 1L;

	public LocacaoController() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		 LocacaoDAO locdao = new LocacaoDAO();
		 
		 ItensLocacaoDAO itenslocdao = new ItensLocacaoDAO();

		System.out.println("Metodo Get");

		String acao = request.getParameter("acao");

		String buscarcli = request.getParameter("buscarcli");

		if (buscarcli != null) {

			ClienteDAO clidao = new ClienteDAO();

			List<Cliente> listaCliente = clidao.BuscarPorNome(buscarcli);

			Date data = new Date();

			data.getTime();

			System.out.println(data);

			request.setAttribute("data", data);

			request.setAttribute("listaCliente", listaCliente);

			RequestDispatcher saida1 = request
					.getRequestDispatcher("Locacao/frmlocacao.jsp");
			saida1.forward(request, response);

		}

		if (acao != null && acao.equals("cad")) {

			Locacao loc = new Locacao();

			
			ClienteDAO clidao = new ClienteDAO();

			FilmeDAO filmedao = new FilmeDAO();

			Date data = new Date();

			data.getTime();

			System.out.println(data);

			request.setAttribute("data", data);

			List<Cliente> listaCliente = clidao.buscarTodos();

			request.setAttribute("listaCliente", listaCliente);

			List<Filme> listaFilme = filmedao.buscarTodos();

			request.setAttribute("listaFilme", listaFilme);

			RequestDispatcher saida1 = request
					.getRequestDispatcher("Locacao/frmlocacao.jsp");
			saida1.forward(request, response);

		}

		 if(acao != null && acao.equals("fimdev")){
        	 
			 String id = request.getParameter("id");
			 
			 List<ItensLocacao> lista =  itenslocdao.buscarporLocacao(Integer.parseInt(id));
			 
			 System.out.println(lista.toString());
			 
	         if(lista.isEmpty()) {
	        	 
	        	 locdao.FinalizarLocacao("F", Integer.parseInt(id));
		          
		          Locacao loc = locdao.BuscarPorId(Integer.parseInt(id));
	        
	        	 
	        	 request.setAttribute("locacao", loc);
	        	 
	        	 
	        	 RequestDispatcher saida1 = request.getRequestDispatcher("index.jsp");
					
	 			 saida1.forward(request, response);
	        	 
	         }else{
	        	 
	        	 
	        	 String msg = "Por Favor devolva todos os filmes antes de finalizar a Devolução";
	        	 
	        	 
	        	 request.setAttribute("msg", msg);
	        	
	        	 
	        	        	  
	        	  Locacao loc = locdao.BuscarPorId(Integer.parseInt(id));
	        	  
	        	  List<ItensLocacao> listaitens = itenslocdao.buscarporLocacao(Integer.parseInt(id));
	        	          	  
	        	  request.setAttribute("listaItens", listaitens);
	        	  
	        	  request.setAttribute("locacao", loc);
	  
				 RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/frmdevolucao.jsp");
					
				  saida1.forward(request, response);
	        	 
	        	 
	         }
	          
	         
        	 
        	 
        	
			
				
				
				
			}
		 
		 
		
			
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		Locacao loc = new Locacao();

		LocacaoDAO locdao = new LocacaoDAO();

		ClienteDAO clidao = new ClienteDAO();

		FilmeDAO filmedao = new FilmeDAO();

		ItensLocacao itensloc = new ItensLocacao();

		ItensLocacaoDAO itenslocdao = new ItensLocacaoDAO();

		loc.setValor(null);

		if (acao != null && acao.equals("cadvenda")) {

			PrintWriter saida = response.getWriter();

			String idcliente = request.getParameter("clientes");

			String dataloc = request.getParameter("datalocacao");

			// String valor = request.getParameter("valor");

			loc.setCliente(clidao.BuscarPorId(Integer.parseInt(idcliente)));

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
			try {
				if (dataloc
						.matches("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$")) {
					cal.setTime(formate.parse(dataloc));
				} else {
					saida.println("Data fora do Padra (dd/MM/YYYY)!");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			loc.setDataloc(cal);
			loc.setValor(new BigDecimal("0"));
			
			loc.setStatus("A");
			
			

			locdao.adiciona(loc);

			List<Cliente> listaCliente = clidao.buscarTodos();

			for (Cliente g : listaCliente) {
				if (g.getNome().equals(loc.getCliente().getNome())) {
					listaCliente = changePosition(listaCliente, g);
				}
			}

			request.setAttribute("listaCliente", listaCliente);
			
			HttpSession secaolocacao = request.getSession();
			
			secaolocacao.setAttribute("locacao", loc);

			List<Filme> listaFilme = filmedao.buscarTodos();

			request.setAttribute("listaFilme", listaFilme);

			RequestDispatcher saida1 = request
					.getRequestDispatcher("Locacao/ItensLocacao.jsp");
			saida1.forward(request, response);

		}

	}

	public List<Cliente> changePosition(List<Cliente> list, Cliente obj) {
		int indexOf = list.indexOf(obj);
		Cliente primeiro = list.get(0);
		list.set(0, obj);
		list.set(indexOf, primeiro);
		return list;
	}

	public List<Filme> changePosition(List<Filme> list, Filme obj) {
		int indexOf = list.indexOf(obj);
		Filme primeiro = list.get(0);
		list.set(0, obj);
		list.set(indexOf, primeiro);
		return list;
	}

}
