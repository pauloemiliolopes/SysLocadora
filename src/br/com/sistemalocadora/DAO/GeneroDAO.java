package br.com.sistemalocadora.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import br.com.sistemalocadora.JDBC.Conexao;
import br.com.sistemalocadora.Model.Genero;

public class GeneroDAO {
	
private Connection con = Conexao.getConexao();


public void adiciona(Genero genero) {
	
   String sql = "insert into Generos (nomegenero,status) values (?,?)";

   try {
     
       PreparedStatement stmt = con.prepareStatement(sql);

       stmt.setString(1,genero.getNome());
       stmt.setString(2,genero.getStatus());
       
       stmt.execute();
       stmt.close();
       
       System.out.println("Genero Cadastrado!");
       
   } catch (SQLException e) {
	   
       System.out.println("Erro ao cadastrar Genero "+ e.getMessage());
       
   }
}


public void alterar(Genero genero) {
	
	   String sql = "update generos set nomegenero=?,status=? where pk_genero=?";

	   try {
	     
	       PreparedStatement stmt = con.prepareStatement(sql);

	       stmt.setString(1,genero.getNome());
	       stmt.setString(2,genero.getStatus());
	       stmt.setInt(3,genero.getId());
	       
	       stmt.execute();
	       stmt.close();
	       
	       System.out.println("Genero Atualizado!");
	       
	   } catch (SQLException e) {
		   
	       System.out.println("Erro ao cadastrar Genero "+ e.getMessage());
	       
	   }
	}

public void excluir(Genero genero) {
	
	   String sql = "delete from generos where pk_genero=? ";

	   try {
	     
	       PreparedStatement stmt = con.prepareStatement(sql);

	       stmt.setInt(1, genero.getId());
	      
	       stmt.execute();
	       stmt.close();
	       
       System.out.println("Genero Excluido!");
	       
	   } catch (SQLException e) {
		   
	       System.out.println("Erro ao cadastrar Genero "+ e.getMessage());
	       
	   }
	}

   public Genero BuscarPorId(int id){
	   
	   String sql = "select * from generos where pk_genero=?";
	   
	   Genero genero =  null;
	   
	   try {
		     
	       PreparedStatement stmt = con.prepareStatement(sql);

	       stmt.setInt(1, id);
	      
	       stmt.execute();
	       
	       ResultSet rs = stmt.executeQuery();
	       
	       if (rs.next()){ 
	       
	       genero = new Genero();
	       
	       genero.setId(rs.getInt("pk_genero"));
	       genero.setNome(rs.getString("nomegenero"));
	       genero.setStatus(rs.getString("Status"));
	       
	       }
	       
	   } catch (SQLException e) {
		   
	       System.out.println("Erro ao cadastrar Genero "+ e.getMessage());
	       
	   }
   
	  return genero;
   }


   public List<Genero> buscarTodos(){
	
	String sql = "select * from Generos";
	
	List<Genero> lista = new ArrayList<Genero>();
	
	try {
		
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		 ResultSet resultado = stmt.executeQuery();
		 
		 while(resultado.next()){
			 
		Genero genero = new Genero();

	     genero.setId(resultado.getInt("pk_genero"));
	     genero.setNome(resultado.getString("nomegenero"));
	     genero.setStatus(resultado.getString("status"));
	     
	     lista.add(genero);
	
		 }
		 
		 stmt.close();
		 
		 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	return lista;
	
}
   
   public void Salvar(Genero genero){
	  
	   
	   if(genero.getId()!= null && genero.getId()!= 0){
		   
		  alterar(genero);
		   
	   }else {
		   
		  adiciona(genero); 
		   
	   }
 
   }


}
