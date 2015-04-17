package br.com.sistemalocadora.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sistemalocadora.DAO.CaixaDAO;
import br.com.sistemalocadora.DAO.FinanceiroDAO;
import br.com.sistemalocadora.DAO.LocacaoDAO;
import br.com.sistemalocadora.Model.Caixa;
import br.com.sistemalocadora.Model.Financeiro;
import br.com.sistemalocadora.Model.ItensLocacao;
import br.com.sistemalocadora.Model.Locacao;


@WebServlet("/financeirocontroller.do")
public class FinanceiroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	FinanceiroDAO findao = new FinanceiroDAO();
	Financeiro fin = new Financeiro();
	
	CaixaDAO caixadao = new CaixaDAO();
	
   
    public FinanceiroController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String acao = request.getParameter("acao");
		
		String buscarcliente = request.getParameter("buscarcliente");
		
		 if (acao != null && acao.equals("cad")) {
			 
		 RequestDispatcher saida = request.getRequestDispatcher("Financeiro/frmmovimentacao.jsp") ;
				
		saida.forward(request, response);
			 
			 
		 }
		
		
		 if (acao != null && acao.equals("list")) {
			 
			 
			List<Financeiro> lista = findao.buscarTodos();
			
			request.setAttribute("listafinanceiro", lista);
		
			RequestDispatcher saida = request.getRequestDispatcher("Financeiro/frmbaixa.jsp") ;
				
			saida.forward(request, response);
	 
				 
			 }
		
		 if (buscarcliente != null ) {
	    	  
	    

		  List<Financeiro> lista = findao.buscarporNomeCli(buscarcliente);

			
		  request.setAttribute("listafinanceiro", lista);

				
				 
		  RequestDispatcher saida1 = request.getRequestDispatcher("Financeiro/frmbaixa.jsp");
					
		  saida1.forward(request, response);
				
				
				
				
			}
		 
		 if (acao != null && acao.equals("bt")) {
			 
		    String id = request.getParameter("id");

				
			Financeiro financeiro = findao.BuscarPorId(Integer.parseInt(id)); 
			
			
			if(financeiro.getValorbaixa().doubleValue() != 0.00){
			
			String msg = "Não e possivel realizar a baixa total, O financeiro já possue baixa.";
			
			String msg1 = "A baixa deve ser Parcial.";
			
			request.setAttribute("msg", msg);
			
			request.setAttribute("msg1", msg1);
			
			
			RequestDispatcher saida = request.getRequestDispatcher("Financeiro/frmbaixa.jsp") ;
			
			saida.forward(request, response);
			
			}else{
			
			request.setAttribute("financeiro", financeiro);
			
			RequestDispatcher saida = request.getRequestDispatcher("Financeiro/frmbaixatotal.jsp") ;
			
			saida.forward(request, response);
			
			}
			 
			 
		 }
		 
           if (acao != null && acao.equals("bp")) {
			 
			String id = request.getParameter("id");

				
			Financeiro financeiro = findao.BuscarPorId(Integer.parseInt(id)); 
			
			request.setAttribute("financeiro", financeiro);
			
			RequestDispatcher saida = request.getRequestDispatcher("Financeiro/frmbaixaparcial.jsp") ;
			
			saida.forward(request, response);
			 
			 
		 }
		 
		 
		 if (acao != null && acao.equals("baixartotal")) {
			 
				String id = request.getParameter("id");
				
				String fpgto = request.getParameter("fpgto");

				Financeiro financeiro = findao.BuscarPorId(Integer.parseInt(id));
		
				Financeiro finan = new Financeiro();
				
				finan.setId(Integer.parseInt(id));
			    finan.setLocacao(financeiro.getLocacao());
			    finan.setDataemissao(financeiro.getDataemissao());
			    finan.setDatavencimento(financeiro.getDatavencimento());
			    finan.setValor(financeiro.getValor());
			    
			    finan.setValorbaixa(financeiro.getValor());
			    finan.setSaldo(financeiro.getSaldo().subtract(financeiro.getValor()));
			    
			    finan.setTipo(financeiro.getTipo());
			    
				findao.alterar(finan);
				
				Caixa caixa = new Caixa();
				
				caixa.setFinanceiro(findao.BuscarPorId(Integer.parseInt(id)));
				
				caixa.setDescricao("Doc Nº :"+financeiro.getId()+" , "+financeiro.getLocacao().getCliente().getNome());
				
				Date dataatual = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(dataatual);
				
				
				caixa.setDatapagamento(c);
				
				caixa.setValorpagamento(financeiro.getValor());
				
				caixa.setTipo(financeiro.getTipo());
				
				caixa.setFormapgto(fpgto);
				
				caixadao.adiciona(caixa);
				
				List<Financeiro> lista = findao.buscarTodos();
				
				request.setAttribute("listafinanceiro", lista);
			
				RequestDispatcher saida = request.getRequestDispatcher("Financeiro/frmbaixa.jsp") ;
					
				saida.forward(request, response);
			
					 
				 }
		 
		 if (acao != null && acao.equals("baixarparcial")) {
			 
				String id = request.getParameter("id");
				
				String valor = request.getParameter("valor");
				
				String fpgto = request.getParameter("fpgto");

				BigDecimal valorbig = new BigDecimal(valor);
				
				Financeiro financeiro = findao.BuscarPorId(Integer.parseInt(id));
				
				if(valorbig.doubleValue() <= financeiro.getSaldo().doubleValue()){
				
				Date dataatual = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(dataatual);
				
				Financeiro finan = new Financeiro();
				
				finan.setId(Integer.parseInt(id));
				finan.setLocacao(financeiro.getLocacao());
			    finan.setDataemissao(financeiro.getDataemissao());
			    finan.setDatavencimento(financeiro.getDatavencimento());
			    finan.setValor(financeiro.getValor());
			    System.out.println(financeiro.getValorbaixa());
			    System.out.println(valorbig);
			    finan.setValorbaixa(financeiro.getValorbaixa().add(valorbig));
			    finan.setSaldo(financeiro.getSaldo().subtract(valorbig));
			    finan.setTipo(financeiro.getTipo());
			    
				findao.alterar(finan);
				
                Caixa caixa = new Caixa();
				
				caixa.setFinanceiro(findao.BuscarPorId(Integer.parseInt(id)));
				
				caixa.setDescricao("Doc Nº :"+financeiro.getId()+" , "+financeiro.getLocacao().getCliente().getNome());
				
				Date dataatual1 = new Date();
				Calendar ca = Calendar.getInstance();
				ca.setTime(dataatual1);
				
				
				caixa.setDatapagamento(ca);
				
				caixa.setValorpagamento(valorbig);
				
				caixa.setTipo(financeiro.getTipo());
				
				caixa.setFormapgto(fpgto);
				
				caixadao.adiciona(caixa);
				
				List<Financeiro> lista = findao.buscarTodos();
				
				request.setAttribute("listafinanceiro", lista);
			
				RequestDispatcher saida = request.getRequestDispatcher("Financeiro/frmbaixa.jsp") ;
					
				saida.forward(request, response);
		 
				}else{
					
			        String msg = "Valor tem que ser menor ou igual que o documento.";
			        
			        request.setAttribute("msg", msg);
			        
					request.setAttribute("financeiro", financeiro);
					
					RequestDispatcher saida = request.getRequestDispatcher("Financeiro/frmbaixaparcial.jsp") ;
					
					saida.forward(request, response);	
			
					
				}	 
				 
		 
		 
		 }
		 
		 
		 
	}
	
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
	}

}
