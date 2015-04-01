package br.com.sistemalocadora.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
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


@WebServlet("/itenscontroller.do")
public class ItensController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ItensController() {
        super();
       
    }
    
   

    
    Locacao loc = new Locacao();
	
	LocacaoDAO locdao = new LocacaoDAO();
	
	ClienteDAO clidao = new ClienteDAO();
	
	Filme filme = new Filme();
	
	FilmeDAO filmedao = new FilmeDAO();
	
    ItensLocacao itensloc = new ItensLocacao();
	
	ItensLocacaoDAO itenslocdao = new ItensLocacaoDAO();
	
	
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String acao = request.getParameter("acao");
		
		String buscarfil = request.getParameter("buscarfil");
		
		String buscarcliente = request.getParameter("buscarcliente");
		
		
		if (buscarfil != null) {
			
			
			 try {
		        	
				   itensloc.setLocacao(locdao.pegarultimoId());
				   
				   Locacao locaimpri= locdao.BuscarPorId(itensloc.getLocacao());
				   
				   request.setAttribute("locacao",locaimpri);
				   
				   List<ItensLocacao> lista =  itenslocdao.buscarporLocacao(itensloc.getLocacao());

				request.setAttribute("listaItens", lista);
				
				List<Filme> listaFilme = filmedao.BuscarPorNome(buscarfil);	
				
				request.setAttribute("listaFilme", listaFilme);		
					
					for (Filme g : listaFilme) {
						if(g.getNome().equals(itensloc.getFilme().getNome())){
							listaFilme = changePosition(listaFilme, g);
						}					
					}
			
					
				} catch (SQLException e1) {
					
					System.out.println("Não foi possivel encontrar a Venda  " + e1.getMessage());
					
				}
			 
			 RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/ItensLocacao.jsp");
				
			    saida1.forward(request, response);
			
			
			
			
		}

		
          if (acao != null && acao.equals("dev")) {
        	  
        	  String id = request.getParameter("id");
        	  
        	  Locacao loc = locdao.BuscarPorId(Integer.parseInt(id));
        	  
        	  System.out.println(loc.getId());
        	  
        	  List<ItensLocacao> listaitens = itenslocdao.buscarporLocacao(Integer.parseInt(id));
        	  
        	  
        	  request.setAttribute("listaItens", listaitens);
        	  
        	  request.setAttribute("locacao", loc);
  
			 RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/frmdevolucao.jsp");
				
			    saida1.forward(request, response);
			
			
			
			
		 }
          
          
          if (acao != null && acao.equals("devitem")) {
        	  
        	String id = request.getParameter("id");
        	  
        	ItensLocacao itenslocacao = itenslocdao.BuscarPorId(Integer.parseInt(id));
        	
        	 Locacao loc = locdao.BuscarPorId(itenslocacao.getLocacao());
        
        	 List<ItensLocacao> listaitens = itenslocdao.buscarporLocacao(Integer.parseInt(id));
        	 
        	 System.out.println(itenslocacao.getDatadevolucao());
        	 if(itenslocacao.getDatadevolucao().equals(null)){
        		 
        		 Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis()); 
            	 
            	 itenslocdao.GravaDataDevolucao(dataDeHoje, Integer.parseInt(id));
            	 
            	 
            	
        	
        	 
        	 }else{
        		 
        		 
        		 String msg = "Item já devolvido, devolva todos os filmes e depois finalize a devolução!";
       		  request.setAttribute("msg", msg);
       		  
       		  
       	
        		 
        		 
        	 }
        	 
        	 
        	 
        	 request.setAttribute("listaItens", listaitens);
          	  
          	  request.setAttribute("locacao", loc);
    
  			 RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/frmdevolucao.jsp");
  				
  			saida1.forward(request, response);
    
			
		 }


		
		if (acao != null && acao.equals("listloc")) {
			
			
			List<Locacao> listalocacoes = locdao.buscarTodos();

			request.setAttribute("listalocacoes", listalocacoes);

			 
			 RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/listalocacao.jsp");
				
			    saida1.forward(request, response);
			
			
			
			
		}

		
      if (buscarcliente != null ) {
    	  
    	   LocacaoDAO daoloc = new LocacaoDAO();
    	   

			List<Locacao> listaLocacao = daoloc.buscarporNomeCli(buscarcliente);

			

			request.setAttribute("listalocacoes", listaLocacao);

			
			 
	  RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/listalocacao.jsp");
				
	  saida1.forward(request, response);
			
			
			
			
		}

		
		
		
		if(acao != null && acao.equals("sair")){
			
			
			RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/frmsair.jsp");
			
			saida1.forward(request, response);
			
			
			
		}
		
		
		
		if(acao != null && acao.equals("exc")){
			
			String id = request.getParameter("id");
			
			System.out.println("Item a ser excluido"+id);

			Locacao locacao = new Locacao();
			locacao.setId(Integer.parseInt(id));
			locdao.excluir(locacao);
				
			RequestDispatcher saida1 = request.getRequestDispatcher("index.jsp");
			
			saida1.forward(request, response);
			
			
			
		}
		
if(acao != null && acao.equals("excitem")){
			
			String id = request.getParameter("id");
			
		    ItensLocacao itenslocacao = itenslocdao.BuscarPorId(Integer.parseInt(id));
		    
			
			Locacao locacao = locdao.BuscarPorId(itenslocacao.getLocacao());
			
			Integer idloc = locacao.getId();
			
            BigDecimal valor = locacao.getValor();
			
            System.out.println(locacao.getValor());
            
       
      
			Filme filme = filmedao.BuscarPorId(itenslocacao.getFilme().getId());
			
			BigDecimal item = filme.getPreco();
			
			
			BigDecimal qtd = new BigDecimal(itenslocacao.getQtd());
			 
			
			
			item = item.multiply(qtd);
			
		
			valor = valor.subtract(item);
			
			
			System.out.println(valor);
			
			
		    locdao.alterarValor(valor, idloc);
		    
			
		    itenslocdao.excluir(Integer.parseInt(id));
		    
		    
		    request.setAttribute("locacao", locacao);
		    
		    
			System.out.println("Item a ser excluido"+id);
				
			 try {
		        	
				   
				   
			    Locacao locaimpri= locdao.BuscarPorId(locdao.pegarultimoId());
				   
				request.setAttribute("locacao",locaimpri);
				   
				List<ItensLocacao> lista =  itenslocdao.buscarporLocacao(locaimpri.getId());

				request.setAttribute("listaItens", lista);
				
				List<Filme> listaFilme = filmedao.buscarTodos();	
				
				request.setAttribute("listaFilme", listaFilme);		
					
					for (Filme g : listaFilme) {
						if(g.getNome().equals(itensloc.getFilme().getNome())){
							listaFilme = changePosition(listaFilme, g);
						}					
					}
			
					
				} catch (SQLException e1) {
					
					System.out.println("Não foi possivel encontrar a Venda  " + e1.getMessage());
					
				}
			 
			 RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/ItensLocacao.jsp");
				
			 saida1.forward(request, response);
			
			
			
		}
		
		
		
if(acao != null && acao.equals("voltar")){
			
	
			
			RequestDispatcher saida1 = request.getRequestDispatcher("index.jsp");
			
			saida1.forward(request, response);
			
			
			
		}


		
		
		
		if(acao != null && acao.equals("list")){
			
			 Locacao loca = new Locacao();
			   
			   
			   HttpSession session = request.getSession();
			   
			   
			   
			   loca =  (Locacao) session.getAttribute("locacao");
				
			   itensloc.setLocacao(loca.getId());
			   
			   Locacao locaimpri= locdao.BuscarPorId(itensloc.getLocacao());
			   
			   request.setAttribute("locacao",locaimpri);
			 
			   
			   List<ItensLocacao> lista =  itenslocdao.buscarporLocacao(itensloc.getLocacao());

			request.setAttribute("listaItens", lista);
			 
			RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/imprilocacao.jsp");
				
		    saida1.forward(request, response);
	
			
		}
		
		
		
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String acao = request.getParameter("acao");
		
	
		
		if(acao != null && acao.equals("itensvenda")){
			
			   

	           String idfilme = request.getParameter("filmes");
				
	           String qtditem = request.getParameter("qtditem");
			
		      /*  try {
		        	if (loc.getId() == null)
		        	{
				   itensloc.setLocacao(locdao.pegarultimoId());
		        	
		        	}
					
				} catch (SQLException e1) {
					
					System.out.println("Não foi possivel encontrar a Venda " + e1.getMessage());
				}
		        */
	           
	           Locacao loca = new Locacao();
	           
	           
	           HttpSession session = request.getSession();
	           
	           
	           
	           loca =  (Locacao) session.getAttribute("locacao");
	            
	           
				itensloc.setLocacao(loca.getId());
				
				Filme filme = filmedao.BuscarPorId(Integer.parseInt(idfilme));
				
				
				if(filme.getQtd() < (Integer.parseInt(qtditem))){
					
					 String estoque ="A quantidade insuficiente! O estoque atual é : "+filme.getQtd();
					 
				     request.setAttribute("estoque", estoque);
				     
				     Locacao locar = locdao.BuscarPorId(itensloc.getLocacao());
				     
				     request.setAttribute("locacao", locar);
				     
				     
				     List<Filme> listaFilme = filmedao.buscarTodos();	
				     
				     request.setAttribute("listaFilme", listaFilme);	
						
						
					 List<ItensLocacao> listaitens =  itenslocdao.buscarporLocacao(itensloc.getLocacao());

					 request.setAttribute("listaItens", listaitens);
						
				
			
				        RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/ItensLocacao.jsp");
					
						saida1.forward(request, response);
							
			
					
				}else{
				
				
				
				itensloc.setFilme(filme);
				
				int dataprev = filme.getTempoloc();
				
				
				
	        
		
				/*Calendar cal = Calendar.getInstance();
				SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
				try {
					if (dataprev.matches("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$")) {
						cal.setTime(formate.parse(dataprev));
					} else {
						saida.println("Data fora do Padra (dd/MM/YYYY)!");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}*/
				
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE,dataprev);
				
				System.out.println(cal.getTime());
				itensloc.setDataprevdevolucao(cal);
				
				
				System.out.println(itensloc.getDatadevolucao());
				
				
				
				itensloc.setQtd(Integer.parseInt(qtditem));
				
				
				
				
				itenslocdao.adiciona(itensloc);
				
				
	           List<Filme> listaFilme = filmedao.buscarTodos();	
				
				for (Filme g : listaFilme) {
					if(g.getNome().equals(itensloc.getFilme().getNome())){
						listaFilme = changePosition(listaFilme, g);
					}					
				}
				
				
				
				Locacao locacao = locdao.BuscarPorId(itensloc.getLocacao());
				
				Integer idloc = locacao.getId();
				
                BigDecimal valor = locacao.getValor();
				
                System.out.println(locacao.getValor());
                
           
           
				Filme filme1 = filmedao.BuscarPorId(Integer.parseInt(idfilme));
				
				
				/*Realizando baixa no estoque*/
				
				System.out.println("Quant Filme "+filme1.getQtd());
				System.out.println("Quant Item "+qtditem);
				
				int estoque = (filme1.getQtd() - Integer.parseInt(qtditem));
				
				filmedao.alterarEstoque(estoque, filme1.getId());
				
		
				
				/*Realizando os calculos de preço e quantidade*/
				
				BigDecimal item = filme.getPreco();
				
				BigDecimal qtd = new BigDecimal(itensloc.getQtd());
			   
				item = item.multiply(qtd);
				
				System.out.println("valor mult"+item);
				
				valor = valor.add(item);
				
				System.out.println(valor);
				
			    locdao.alterarValor(valor, idloc);
			    
			    
			    request.setAttribute("locacao", locacao);
			    
				
				Locacao loc = locdao.BuscarPorId(idloc);
				
				request.setAttribute("locacao", loc);
			
				request.setAttribute("itenslocacao", itensloc);
				
				request.setAttribute("listaFilme", listaFilme);	
				
				
				List<ItensLocacao> listaitens =  itenslocdao.buscarporLocacao(itensloc.getLocacao());

				request.setAttribute("listaItens", listaitens);
				
		
				RequestDispatcher saida1 = request.getRequestDispatcher("Locacao/ItensLocacao.jsp");
			
				saida1.forward(request, response);
					
				}
		
		}
		}
		

	
	public List<Filme> changePosition(List<Filme> list, Filme obj) {
		int indexOf = list.indexOf(obj);
		Filme primeiro = list.get(0);
		list.set(0, obj);
		list.set(indexOf, primeiro);
		return list;
	}
	
}
