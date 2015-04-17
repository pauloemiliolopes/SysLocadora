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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import br.com.sistemalocadora.DAO.CaixaDAO;
import br.com.sistemalocadora.DAO.ClienteDAO;
import br.com.sistemalocadora.DAO.ItensLocacaoDAO;
import br.com.sistemalocadora.Model.Caixa;
import br.com.sistemalocadora.Model.Cliente;
import br.com.sistemalocadora.Model.Genero;
import br.com.sistemalocadora.Model.ItensLocacao;




@WebServlet("/caixacontroller.do")
public class CaixaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CaixaDAO cxdao = new CaixaDAO();
    
    public CaixaController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
	
		
		
		 if (acao != null && acao.equals("buscarmov")) {
				
			 String dataini = request.getParameter("dataini");
			 
			 String datafin = request.getParameter("datafin");
			 
			 //converter data inicial
			 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			 Date date = null;
			try {
				date = formatter.parse(dataini);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar dtini = Calendar.getInstance();
			dtini.setTimeInMillis(date.getTime());
			
			
			 
			 //converter data final
			
			 DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
			 Date date1 = null;
			try {
				date1 = formatter1.parse(datafin);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Calendar dtfin = Calendar.getInstance();
		     dtfin.setTimeInMillis(date1.getTime());	
			

			 
				List<Caixa> lista = cxdao.BuscaEntreDatas(dtini, dtfin);
						
				

				request.setAttribute("listacaixa", lista);
				
			
				 RequestDispatcher saida1 = request.getRequestDispatcher("Financeiro/listamovimentacao.jsp");
					
				    saida1.forward(request, response);
				
				
				
				
			}
		 
		 if (acao != null && acao.equals("abrir")) {
		 
			RequestDispatcher saida = request.getRequestDispatcher("Relatorios/caixa.jsp") ;
			
			saida.forward(request, response);
			 
			 
			 
		 }
		 
		 if (acao != null && acao.equals("listmov")) {
			 
			    List<Caixa> listacaixa = cxdao.buscarMovimentacoes();
			    
			    
			    request.setAttribute("listacaixa", listacaixa);
			    
			 
				RequestDispatcher saida = request.getRequestDispatcher("Financeiro/listamovimentacao.jsp") ;
				
				saida.forward(request, response);
				 
				 
				 
			 }
		 
		 if (acao != null && acao.equals("listcaixa")) {
			 
            String dataini = request.getParameter("dataini");
			 
			 String datafin = request.getParameter("datafin");
			 
			 //converter data inicial
			 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			 Date date = null;
			try {
				date = formatter.parse(dataini);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar dtini = Calendar.getInstance();
			dtini.setTimeInMillis(date.getTime());
			
			
			 
			 //converter data final
			
			 DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
			 Date date1 = null;
			try {
				date1 = formatter1.parse(datafin);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Calendar dtfin = Calendar.getInstance();
		     dtfin.setTimeInMillis(date1.getTime());	
			

			 
				List<Caixa> listadinheiro = cxdao.MovimentoCaixa(dtini, dtfin,"Dinheiro");
				
				List<Caixa> listacartao = cxdao.MovimentoCaixa(dtini, dtfin,"Cartao");
						
				

				request.setAttribute("listacaixa", listadinheiro);
				
				request.setAttribute("listacartao", listacartao);
				
			
				 RequestDispatcher saida1 = request.getRequestDispatcher("Relatorios/caixa.jsp");
					
				  saida1.forward(request, response);
				
				
				
				 
				 
				 
			 }
		 
		 
		 if (acao != null && acao.equals("alt")) {

				String id = request.getParameter("id");

				Caixa caixa = cxdao.BuscarPorId(Integer.parseInt(id));

				request.setAttribute("caixa", caixa);
				RequestDispatcher saida = request
						.getRequestDispatcher("Financeiro/frmmovimentacao.jsp");
				saida.forward(request, response);

			}

		 if (acao != null && acao.equals("exc")) {

				String id = request.getParameter("id");
				
				Caixa caixa = cxdao.BuscarPorId(Integer.parseInt(id));

				cxdao.excluir(caixa);
				
				List<Caixa> cx = cxdao.buscarMovimentacoes();

				request.setAttribute("listacaixa", cx);
				
				RequestDispatcher saida = request.getRequestDispatcher("Financeiro/listamovimentacao.jsp");
				
				saida.forward(request, response);

			}
		
		 
		 
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CaixaDAO caixadao = new CaixaDAO();

		
		String descricao = request.getParameter("descricao");
		String valor = request.getParameter("valor");
		String tipo = request.getParameter("tipo");
		
		Caixa caixa = new Caixa();
       
	    
		caixa.setDescricao(descricao);
		caixa.setValorpagamento(new BigDecimal(valor));
		caixa.setTipo(tipo);
		
		Date dtatual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dtatual);
		
		caixa.setFormapgto("Dinheiro");
		
		caixa.setDatapagamento(calendar);

		caixadao.adicionaMovimentacao(caixa);
		
		List<Caixa> listacaixa = cxdao.buscarMovimentacoes();
		
		request.setAttribute("listacaixa", listacaixa);

		RequestDispatcher saida = request.getRequestDispatcher("Financeiro/listamovimentacao.jsp") ;
		
		saida.forward(request, response);
		
	}

}
