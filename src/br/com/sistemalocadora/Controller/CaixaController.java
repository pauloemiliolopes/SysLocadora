package br.com.sistemalocadora.Controller;

import java.io.IOException;
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

import br.com.sistemalocadora.DAO.ItensLocacaoDAO;
import br.com.sistemalocadora.Model.ItensLocacao;




@WebServlet("/caixacontroller.do")
public class CaixaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CaixaController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		ItensLocacaoDAO itensdao = new ItensLocacaoDAO();
		
		 if (acao != null && acao.equals("buscarforma")) {
				
			 String dataini = request.getParameter("dataini");
			 
			 String datafin = request.getParameter("datafin");
			 
			 String fpgto = request.getParameter("fpgto");
			 
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
			

			
				
			 
				List<ItensLocacao> lista = itensdao.BuscaEntreDatas(dtini, dtfin, fpgto);
				
				for (ItensLocacao itensLocacao : lista) {
					
					System.out.println(itensLocacao);
					
				}

				request.setAttribute("listaitens", lista);
				
				

				 
				 RequestDispatcher saida1 = request.getRequestDispatcher("Relatorios/caixa.jsp");
					
				    saida1.forward(request, response);
				
				
				
				
			}
		 
		 if (acao != null && acao.equals("abrir")) {
		 
			RequestDispatcher saida = request.getRequestDispatcher("Relatorios/caixa.jsp") ;
			
			saida.forward(request, response);
			 
			 
			 
		 }
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
