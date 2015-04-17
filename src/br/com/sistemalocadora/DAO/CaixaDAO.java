package br.com.sistemalocadora.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.sistemalocadora.JDBC.Conexao;
import br.com.sistemalocadora.Model.Caixa;
import br.com.sistemalocadora.Model.ItensLocacao;


public class CaixaDAO {
	
	
	private Connection con = Conexao.getInstance().getConexao();
	
	FinanceiroDAO findao = new FinanceiroDAO();


	public void adiciona(Caixa caixa){
		
	   String sql = "insert into caixa (fk_financeiro,descricao,datapagamento,valorpagamento,tipo,formapgto) values (?,?,?,?,?,?)";

	   try {
	     
	       PreparedStatement stmt = con.prepareStatement(sql);

	       stmt.setInt(1,caixa.getFinanceiro().getId());
	       stmt.setString(2,caixa.getDescricao());
	       stmt.setTimestamp(3, new Timestamp(caixa.getDatapagamento().getTimeInMillis()));
	       stmt.setBigDecimal(4,caixa.getValorpagamento());
	       stmt.setString(5,caixa.getTipo()); 
	       stmt.setString(6, caixa.getFormapgto());
	       
	       stmt.execute();
	       stmt.close();
	       
	       System.out.println("Movimento de Caixa Cadastrado!");
	       
	   } catch (SQLException e) {
		   
	       System.out.println("Erro ao cadastrar Movimento de Caixa "+ e.getMessage());
	       
	   }
	}

	
	public void adicionaMovimentacao(Caixa caixa){
		
		   String sql = "insert into caixa (descricao,datapagamento,valorpagamento,tipo,formapgto) values (?,?,?,?,?)";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);



		       stmt.setString(1,caixa.getDescricao());
		       stmt.setTimestamp(2, new Timestamp(caixa.getDatapagamento().getTimeInMillis()));
		       stmt.setBigDecimal(3,caixa.getValorpagamento());
		       stmt.setString(4,caixa.getTipo()); 
		       stmt.setString(5, caixa.getFormapgto());
		       
		       stmt.execute();
		       stmt.close();
		       
		       System.out.println("Movimento de Caixa Cadastrado!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Movimento de Caixa "+ e.getMessage());
		       
		   }
		}


	public void alterar(Caixa caixa) {
		
		   String sql = "update caixa set fk_financeiro=?,descricao=?,datapagamento=?,valorpagamento=?,tipo=?,formapgto=? where pk_caixa=?";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1,caixa.getFinanceiro().getId());
		       stmt.setString(2,caixa.getDescricao());
		       stmt.setTimestamp(3, new Timestamp(caixa.getDatapagamento().getTimeInMillis()));
		       stmt.setBigDecimal(4,caixa.getValorpagamento());
		       stmt.setString(5,caixa.getTipo());
		       stmt.setString(5,caixa.getFormapgto());
		       stmt.setInt(6,caixa.getId()); 
		       
		       stmt.execute();
		       stmt.close();
		       
		       System.out.println(" Movimento de Caixa Atualizado!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar  Movimento de Caixa "+ e.getMessage());
		       
		   }
		}

	public void excluir(Caixa caixa) {
		
		   String sql = "delete from caixa where pk_caixa=? ";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, caixa.getId());
		      
		       stmt.execute();
		       stmt.close();
		       
	       System.out.println(" Movimento de Caixa Excluido!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar  Movimento de Caixa "+ e.getMessage());
		       
		   }
		}

	   public Caixa BuscarPorId(int id){
		   
		   String sql = "select * from caixa where pk_caixa=?";
		   
		   Caixa caixa =  null;
		   
		   try {
			     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, id);
		      
		       stmt.execute();
		       
		       ResultSet rs = stmt.executeQuery();
		       
		       if (rs.next()){ 
		       
		       caixa = new Caixa();
		       
		       caixa.setId(rs.getInt("pk_caixa"));
		       caixa.setFinanceiro(findao.BuscarPorId(rs.getInt("fk_financeiro")));
		       caixa.setDescricao(rs.getString("descricao"));
		       
		       Calendar ca = Calendar.getInstance();
		       ca.setTimeInMillis(rs.getTimestamp("datapagamento").getTime());
		       caixa.setDatapagamento(ca);
		       
		       caixa.setValorpagamento(rs.getBigDecimal("valorpagamento"));
		       
		       caixa.setTipo(rs.getString("tipo"));
		       
		       caixa.setFormapgto(rs.getString("formapgto"));

		       
		       }
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Cliente "+ e.getMessage());
		       
		   }
	   
		  return caixa;
	   }

	   


	   public List<Caixa> buscarTodos(){
		
		String sql = "select * from caixa";
		
		List<Caixa> lista = new ArrayList<Caixa>();
		
		try {
			
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			 ResultSet rs = stmt.executeQuery();
			 
			 while(rs.next()){
				 
			Caixa caixa = new Caixa();

			caixa.setId(rs.getInt("pk_caixa"));
		       caixa.setFinanceiro(findao.BuscarPorId(rs.getInt("fk_financeiro")));
		       caixa.setDescricao(rs.getString("descricao"));
		       
		       if(rs.getTimestamp("datapagamento") != null){
		    	   
		       Calendar ca = Calendar.getInstance();
		       ca.setTimeInMillis(rs.getTimestamp("datapagamento").getTime());
		       caixa.setDatapagamento(ca);
		       
		       }
		       caixa.setValorpagamento(rs.getBigDecimal("valorpagamento"));
		       
		       caixa.setTipo(rs.getString("tipo"));
		       
		       caixa.setFormapgto(rs.getString("formapgto"));
		     
		     lista.add(caixa);
		
			 }
			 
			 stmt.close();
			 
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return lista;
		
	}
	   
	   public List<Caixa> buscarMovimentacoes(){
			
			String sql = "select * from caixa where fk_financeiro is null";
			
			List<Caixa> lista = new ArrayList<Caixa>();
			
			try {
				
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				 ResultSet rs = stmt.executeQuery();
				 
				 while(rs.next()){
					 
				Caixa caixa = new Caixa();

				caixa.setId(rs.getInt("pk_caixa"));
			       caixa.setFinanceiro(findao.BuscarPorId(rs.getInt("fk_financeiro")));
			       caixa.setDescricao(rs.getString("descricao"));
			       
			       if(rs.getTimestamp("datapagamento") != null){
			    	   
			       Calendar ca = Calendar.getInstance();
			       ca.setTimeInMillis(rs.getTimestamp("datapagamento").getTime());
			       caixa.setDatapagamento(ca);
			       
			       }
			       caixa.setValorpagamento(rs.getBigDecimal("valorpagamento"));
			       
			       caixa.setTipo(rs.getString("tipo"));
			       
			       caixa.setFormapgto(rs.getString("formapgto"));
			     
			     lista.add(caixa);
			
				 }
				 
				 stmt.close();
				 
				 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			return lista;
			
		}
	   
	   public  List<Caixa> BuscaEntreDatas(Calendar datainicial, Calendar datafinal) {

		   String sql = "select * from caixa where fk_financeiro is null and datapagamento >= ? and datapagamento <= ?";
			
			List<Caixa> lista = new ArrayList<Caixa>();
			
			try {
				
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setTimestamp(1, new Timestamp(datainicial.getTimeInMillis()));
				
				stmt.setTimestamp(2, new Timestamp(datafinal.getTimeInMillis()));
						
				
				 ResultSet rs = stmt.executeQuery();
				 
				 while(rs.next()){
					 
				Caixa caixa = new Caixa();

				   caixa.setId(rs.getInt("pk_caixa"));
			       caixa.setFinanceiro(findao.BuscarPorId(rs.getInt("fk_financeiro")));
			       caixa.setDescricao(rs.getString("descricao"));
			       
			       if(rs.getTimestamp("datapagamento") != null){
			    	   
			       Calendar ca = Calendar.getInstance();
			       ca.setTimeInMillis(rs.getTimestamp("datapagamento").getTime());
			       caixa.setDatapagamento(ca);
			       
			       }
			       caixa.setValorpagamento(rs.getBigDecimal("valorpagamento"));
			       
			       caixa.setTipo(rs.getString("tipo"));
			       
			       caixa.setFormapgto(rs.getString("formapgto"));
			     
			     lista.add(caixa);
			
				 }
				 
				 stmt.close();

			} catch (SQLException e) {
				
				e.printStackTrace();
			}

			return lista;
	     
		}

		   
	   public  List<Caixa> MovimentoCaixa(Calendar datainicial, Calendar datafinal, String fpgto) {

		   String sql = "select * from caixa where datapagamento >= ? and datapagamento <= ? and formapgto=?";
			
			List<Caixa> lista = new ArrayList<Caixa>();
			
			try {
				
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setTimestamp(1, new Timestamp(datainicial.getTimeInMillis()));
				
				stmt.setTimestamp(2, new Timestamp(datafinal.getTimeInMillis()));
				
				stmt.setString(3, fpgto);
						
				
				 ResultSet rs = stmt.executeQuery();
				 
				 while(rs.next()){
					 
				Caixa caixa = new Caixa();

				   caixa.setId(rs.getInt("pk_caixa"));
			       caixa.setFinanceiro(findao.BuscarPorId(rs.getInt("fk_financeiro")));
			       caixa.setDescricao(rs.getString("descricao"));
			       
			       if(rs.getTimestamp("datapagamento") != null){
			    	   
			       Calendar ca = Calendar.getInstance();
			       ca.setTimeInMillis(rs.getTimestamp("datapagamento").getTime());
			       caixa.setDatapagamento(ca);
			       
			       }
			       caixa.setValorpagamento(rs.getBigDecimal("valorpagamento"));
			       
			       caixa.setTipo(rs.getString("tipo"));
			       
			       caixa.setFormapgto(rs.getString("formapgto"));
			     
			     lista.add(caixa);
			
				 }
				 
				 stmt.close();

			} catch (SQLException e) {
				
				e.printStackTrace();
			}

			return lista;
	     
		}

		   
	   

}
