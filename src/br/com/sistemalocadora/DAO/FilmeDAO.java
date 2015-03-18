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
import br.com.sistemalocadora.Model.Filme;
import br.com.sistemalocadora.Model.Genero;

public class FilmeDAO {
	
	private Connection con = Conexao.getConexao();
	
	private GeneroDAO daogenero = new GeneroDAO();


	public void adiciona(Filme filme){
		
	   String sql = "insert into filmes (nome_filme,lancamento,sinopse,tempolocacao,qtd,preco,fk_genero,status) values (?,?,?,?,?,?,?,?)";

	   try {
	     
	       PreparedStatement stmt = con.prepareStatement(sql);

	       stmt.setString(1,filme.getNome());
	       stmt.setTimestamp(2, new Timestamp(filme.getDatalanc().getTimeInMillis()));
	       stmt.setString(3,filme.getSinopse());
	       stmt.setInt(4,filme.getTempoloc());
	       stmt.setInt(5,filme.getQtd()); 
	       stmt.setBigDecimal(6, filme.getPreco());
	       stmt.setInt(7, filme.getGenero().getId());
	       stmt.setString(8,filme.getStatus());
	       
	       stmt.execute();
	       stmt.close();
	       
	       System.out.println("Filme Cadastrado!");
	       
	   } catch (SQLException e) {
		   
	       System.out.println("Erro ao cadastrar Filme "+ e.getMessage());
	       
	   }
	}


	public void alterar(Filme filme) {
		
		   String sql = "update filmes set nome_filme=?,lancamento=?,sinopse=?,tempolocacao=?,qtd=?,preco=?,fk_genero=?,status=? where pk_filme=?";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setString(1,filme.getNome());
		       stmt.setTimestamp(2, new Timestamp(filme.getDatalanc().getTimeInMillis()));
		       stmt.setString(3,filme.getSinopse());
		       stmt.setInt(4,filme.getTempoloc());
		       stmt.setInt(5,filme.getQtd()); 
		       stmt.setBigDecimal(6,filme.getPreco());
		       stmt.setInt(7, filme.getGenero().getId());
		       stmt.setString(8,filme.getStatus());
		       stmt.setInt(9, filme.getId());
		       
		       stmt.execute();
		       stmt.close();
		       
		       System.out.println("Filme Atualizado!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Filme "+ e.getMessage());
		       
		   }
		}

	public void excluir(Filme filme) {
		
		   String sql = "delete from filmes where pk_filme=? ";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, filme.getId());
		      
		       stmt.execute();
		       stmt.close();
		       
	       System.out.println("Filme Excluido!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Filme "+ e.getMessage());
		       
		   }
		}

	   public Filme BuscarPorId(int id){
		   
		   String sql = "select * from filmes where pk_filme=?";
		   
		   Filme filme =  null;
		   
		   try {
			     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, id);
		      
		       stmt.execute();
		       
		       ResultSet rs = stmt.executeQuery();
		       
		       if (rs.next()){ 
		       
		       filme = new Filme();
		       
		       
		       filme.setId(rs.getInt("pk_filme"));
		       filme.setNome(rs.getString("nome_filme"));
		       
		       Calendar ca = Calendar.getInstance();
		       ca.setTimeInMillis((rs.getTimestamp("lancamento").getTime()));
		       filme.setDatalanc(ca);
		       filme.setSinopse(rs.getString("sinopse"));
		       filme.setTempoloc(rs.getInt("tempolocacao"));
		       filme.setPreco(rs.getBigDecimal("preco"));
		       filme.setQtd(rs.getInt("qtd"));
		       filme.setGenero(daogenero.BuscarPorId(rs.getInt("fk_genero")));
		       filme.setStatus(rs.getString("Status"));
		       
		       }
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Filme "+ e.getMessage());
		       
		   }
	   
		  return filme;
	   }


	   public List<Filme> buscarTodos(){
		
		String sql = "select * from filmes";
		
		List<Filme> lista = new ArrayList<Filme>();
		
		try {
			
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			 ResultSet rs = stmt.executeQuery();
			 
			 while(rs.next()){
				 
			Filme filme = new Filme();

			 filme.setId(rs.getInt("pk_filme"));
		       filme.setNome(rs.getString("nome_filme"));
		       Calendar ca = Calendar.getInstance();
		       System.out.println(rs.getTimestamp("lancamento"));
		       ca.setTimeInMillis((rs.getTimestamp("lancamento").getTime()));
		       
		       filme.setDatalanc(ca);
		       filme.setSinopse(rs.getString("sinopse"));
		       filme.setTempoloc(rs.getInt("tempolocacao"));
		       filme.setQtd(rs.getInt("qtd"));
		       filme.setPreco(rs.getBigDecimal("preco"));
		       filme.setGenero(daogenero.BuscarPorId(rs.getInt("fk_genero")));
		      
		       filme.setStatus(rs.getString("Status"));
		     
		     lista.add(filme);
		
			 }
			 
			 stmt.close();
			 
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return lista;
		
	}
	   
	   public void Salvar(Filme filme){
		  
		   
		   if(filme.getId()!= null && filme.getId()!= 0){
			   
			  alterar(filme);
			   
		   }else {
			   
			  adiciona(filme); 
			   
		   }
	 
	   }

	 
}
	   

	   
	   

	 


