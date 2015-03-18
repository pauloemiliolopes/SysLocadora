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
import br.com.sistemalocadora.Model.Locacao;

public class LocacaoDAO {
	
	
	private Connection con = Conexao.getConexao();
	
	private ClienteDAO daocliente = new ClienteDAO();


	public void adiciona(Locacao locacao){
		
	   String sql = "insert into locacoes (pk_locacao,fk_cliente,datalocacao,valor) values (?,?,?,?)";

	   try {
	     
	       PreparedStatement stmt = con.prepareStatement(sql);

	       stmt.setInt(1,locacao.getId());
	       stmt.setInt(2,locacao.getCliente().getId());
	       stmt.setTimestamp(3, new Timestamp (locacao.getDataloc().getTimeInMillis()));
	       stmt.setBigDecimal(4,locacao.getValor());
	       
	       stmt.execute();
	       stmt.close();
	       
	       System.out.println("Locação Cadastrado!");
	       
	   } catch (SQLException e) {
		   
	       System.out.println("Erro ao cadastrar Locaçao "+ e.getMessage());
	       
	   }
	}


	public void alterar(Locacao locacao) {
		
		   String sql = "update locacoes set fk_cliente=?,data_locacao=?,valor=? where pk_locacao=?";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       
		       stmt.setInt(1,locacao.getCliente().getId());
		       stmt.setTimestamp(2, new Timestamp (locacao.getDataloc().getTimeInMillis()));
		       stmt.setBigDecimal(3,locacao.getValor());
		       
		       stmt.setInt(4,locacao.getId());
		       
		       stmt.execute();
		       stmt.close();
		       
		       System.out.println("Locação Atualizada!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Locacao "+ e.getMessage());
		       
		   }
		}

	public void excluir(Locacao locacao) {
		
		   String sql = "delete from locacoes where pk_locacao=? ";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, locacao.getId());
		      
		       stmt.execute();
		       stmt.close();
		       
	       System.out.println("Locaçoes Excluido!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Locaçoes "+ e.getMessage());
		       
		   }
		}

	   public Locacao BuscarPorId(int id){
		   
		   String sql = "select * from locacoes where pk_locacao=?";
		   
		   Locacao locacao =  null;
		   
		   try {
			     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, id);
		      
		       stmt.execute();
		       
		       ResultSet rs = stmt.executeQuery();
		       
		       if (rs.next()){ 
		       
		       locacao = new Locacao();
		       
		       
		       locacao.setId(rs.getInt("pk_locacao"));
		       locacao.setCliente(daocliente.BuscarPorId(rs.getInt("fk_cliente")));
		       
		       Calendar ca = Calendar.getInstance();
		       ca.setTimeInMillis((rs.getTimestamp("data_locacao").getTime()));
		       locacao.setDataloc(ca);
		       locacao.setValor(rs.getBigDecimal("valor"));
		    
		       
		       }
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Locação "+ e.getMessage());
		       
		   }
	   
		  return locacao;
	   }


	   public List<Locacao> buscarTodos(){
		
		String sql = "select * from locacoes";
		
		List<Locacao> lista = new ArrayList<Locacao>();
		
		try {
			
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			 ResultSet rs = stmt.executeQuery();
			 
			 while(rs.next()){
				 
			Locacao locacao = new Locacao();

			   locacao.setId(rs.getInt("pk_locacao"));
		       locacao.setCliente(daocliente.BuscarPorId(rs.getInt("fk_cliente")));
		       Calendar ca = Calendar.getInstance();
		       ca.setTimeInMillis((rs.getTimestamp("data_locacao").getTime()));
		       locacao.setDataloc(ca);
		       locacao.setValor(rs.getBigDecimal("valor"));
		     
		     lista.add(locacao);
		
			 }
			 
			 stmt.close();
			 
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return lista;
		
	}
	   
	   public void Salvar(Locacao locacao){
			  
		   
		   if((locacao.getId() != null) && (locacao.getId()!= 0)){
			   
			  alterar(locacao);
			   
		   }else {
			   
			  adiciona(locacao); 
			   
		   }
	 
	   }
	   
	  
	

}
