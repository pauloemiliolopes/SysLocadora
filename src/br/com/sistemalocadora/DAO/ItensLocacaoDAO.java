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
import br.com.sistemalocadora.Model.ItensLocacao;


public class ItensLocacaoDAO {
	
	private Connection con = Conexao.getConexao();


	public void adiciona(ItensLocacao itenslocacao){
		
	   String sql = "insert into itenslocacoes (pk_itenslocacao,fk_locacao,fk_filme,data_devolucao,dataprevdev) values (?,?,?,?,?)";

	   try {
	     
	       PreparedStatement stmt = con.prepareStatement(sql);

	       stmt.setInt(1,itenslocacao.getId());
	       stmt.setInt(2,itenslocacao.getLocacao());
	       stmt.setInt(3,itenslocacao.getFilme());
	       stmt.setTimestamp(4, new Timestamp (itenslocacao.getDatadevolucao().getTimeInMillis()));
	       stmt.setTimestamp(5, new Timestamp (itenslocacao.getDataprevdevolucao().getTimeInMillis()));
	       
	       stmt.execute();
	       stmt.close();
	       
	       System.out.println("Item Cadastrado!");
	       
	   } catch (SQLException e) {
		   
	       System.out.println("Erro ao cadastrar Item "+ e.getMessage());
	       
	   }
	}


	public void alterar(ItensLocacao itenslocacao) {
		
		   String sql = "update itenslocacoes set fk_locacao=?,fk_filme=?,data_devolucao=?,dataprevdev=? where pk_itenslocacao=?";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       
		       stmt.setInt(1,itenslocacao.getLocacao());
		       stmt.setInt(2,itenslocacao.getFilme());
		       stmt.setTimestamp(3, new Timestamp (itenslocacao.getDatadevolucao().getTimeInMillis()));
		       stmt.setTimestamp(4, new Timestamp (itenslocacao.getDataprevdevolucao().getTimeInMillis()));
		       
		       stmt.setInt(5,itenslocacao.getId());
		       
		       stmt.execute();
		       stmt.close();
		       
		       System.out.println("Item Atualizado!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Item "+ e.getMessage());
		       
		   }
		}

	public void excluir(ItensLocacao itenslocacao) {
		
		   String sql = "delete from locacoes where pk_locacao=? ";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, itenslocacao.getId());
		      
		       stmt.execute();
		       stmt.close();
		       
	       System.out.println("Item Excluido!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Item "+ e.getMessage());
		       
		   }
		}

	   public ItensLocacao BuscarPorId(int id){
		   
		   String sql = "select * from itenslocacoes where pk_itenslocacao=?";
		   
		   ItensLocacao itenslocacao =  null;
		   
		   try {
			     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, id);
		      
		       stmt.execute();
		       
		       ResultSet rs = stmt.executeQuery();
		       
		       if (rs.next()){ 
		       
		       itenslocacao = new ItensLocacao();
		       
		       
		       itenslocacao.setId(rs.getInt("pk_itenslocacao"));
		       itenslocacao.setId(rs.getInt("fk_locacao"));
		       itenslocacao.setId(rs.getInt("fk_filme"));
		       Calendar ca = Calendar.getInstance();
		       ca.setTimeInMillis((rs.getTimestamp("data_devolucao").getTime()));
		       itenslocacao.setDatadevolucao(ca);
		       
		       Calendar c = Calendar.getInstance();
		       c.setTimeInMillis((rs.getTimestamp("dataprevdev").getTime()));
		       itenslocacao.setDatadevolucao(c);
		       
		    
		       
		       }
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Item "+ e.getMessage());
		       
		   }
	   
		  return itenslocacao;
	   }


	   public List<ItensLocacao> buscarTodos(){
		
		String sql = "select * from itenslocacoes";
		
		List<ItensLocacao> lista = new ArrayList<ItensLocacao>();
		
		try {
			
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			 ResultSet rs = stmt.executeQuery();
			 
			 while(rs.next()){
				 
			ItensLocacao itenslocacao = new ItensLocacao();

			itenslocacao.setId(rs.getInt("pk_itenslocacao"));
		       itenslocacao.setId(rs.getInt("fk_locacao"));
		       itenslocacao.setId(rs.getInt("fk_filme"));
		       Calendar ca = Calendar.getInstance();
		       ca.setTimeInMillis((rs.getTimestamp("data_devolucao").getTime()));
		       itenslocacao.setDatadevolucao(ca);
		       
		       Calendar c = Calendar.getInstance();
		       c.setTimeInMillis((rs.getTimestamp("dataprevdev").getTime()));
		       itenslocacao.setDatadevolucao(c);
		     
		     lista.add(itenslocacao);
		
			 }
			 
			 stmt.close();
			 
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return lista;
		
	}
	   
	   public void Salvar(ItensLocacao itenslocacao){
			  
		   
		   if((itenslocacao.getId() != null) && (itenslocacao.getId()!= 0)){
			   
			  alterar(itenslocacao);
			   
		   }else {
			   
			  adiciona(itenslocacao); 
			   
		   }
	 
	   }
	   
	  

}
