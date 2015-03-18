package br.com.sistemalocadora.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemalocadora.JDBC.Conexao;
import br.com.sistemalocadora.Model.Cliente;


public class ClienteDAO {
	
	
	private Connection con = Conexao.getConexao();


	public void adiciona(Cliente cliente){
		
	   String sql = "insert into clientes (nome_cliente,cpf,email,telefone,endereco,status) values (?,?,?,?,?,?)";

	   try {
	     
	       PreparedStatement stmt = con.prepareStatement(sql);

	       stmt.setString(1,cliente.getNome());
	       stmt.setString(2,cliente.getCpf());
	       stmt.setString(3,cliente.getEmail());
	       stmt.setString(4,cliente.getTelefone());
	       stmt.setString(5,cliente.getEndereco()); 
	       stmt.setString(6,cliente.getStatus());
	       
	       stmt.execute();
	       stmt.close();
	       
	       System.out.println("Cliente Cadastrado!");
	       
	   } catch (SQLException e) {
		   
	       System.out.println("Erro ao cadastrar Cliente "+ e.getMessage());
	       
	   }
	}


	public void alterar(Cliente cliente) {
		
		   String sql = "update clientes set nome_cliente=?,cpf=?,email=?,telefone=?,endereco=?,status=? where pk_cliente=?";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setString(1,cliente.getNome());
		       stmt.setString(2,cliente.getCpf());
		       stmt.setString(3,cliente.getEmail());
		       stmt.setString(4,cliente.getTelefone());
		       stmt.setString(5,cliente.getEndereco()); 
		       stmt.setString(6,cliente.getStatus());
		       stmt.setInt(7,cliente.getId());
		       
		       stmt.execute();
		       stmt.close();
		       
		       System.out.println("Cliente Atualizado!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Cliente "+ e.getMessage());
		       
		   }
		}

	public void excluir(Cliente cliente) {
		
		   String sql = "delete from clientes where pk_cliente=? ";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, cliente.getId());
		      
		       stmt.execute();
		       stmt.close();
		       
	       System.out.println("Cliente Excluido!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Genero "+ e.getMessage());
		       
		   }
		}

	   public Cliente BuscarPorId(int id){
		   
		   String sql = "select * from clientes where pk_cliente=?";
		   
		   Cliente cliente =  null;
		   
		   try {
			     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, id);
		      
		       stmt.execute();
		       
		       ResultSet rs = stmt.executeQuery();
		       
		       if (rs.next()){ 
		       
		       cliente = new Cliente();
		       
		       cliente.setId(rs.getInt("pk_cliente"));
		       cliente.setNome(rs.getString("nome_cliente"));
		       cliente.setCpf(rs.getString("cpf"));
		       cliente.setEmail(rs.getString("email"));
		       cliente.setTelefone(rs.getString("telefone"));
		       cliente.setEndereco(rs.getString("endereco"));
		       cliente.setStatus(rs.getString("Status"));
		       
		       }
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Cliente "+ e.getMessage());
		       
		   }
	   
		  return cliente;
	   }

	   
 public List<Cliente> BuscarPorNome(String nome){
		   
		   String sql = "select * from clientes where nome_cliente Like ?";
		   
		   List<Cliente> lista = new ArrayList<Cliente>();
		   
		   try {
			     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setString(1, "%"+nome+"%");
		      
		       stmt.execute();
		       
		       ResultSet rs = stmt.executeQuery();
		       
		       while(rs.next()){
		       
		       Cliente cliente = new Cliente();
		       
		       cliente.setId(rs.getInt("pk_cliente"));
		       cliente.setNome(rs.getString("nome_cliente"));
		       cliente.setCpf(rs.getString("cpf"));
		       cliente.setEmail(rs.getString("email"));
		       cliente.setTelefone(rs.getString("telefone"));
		       cliente.setEndereco(rs.getString("endereco"));
		       cliente.setStatus(rs.getString("Status"));
		       
		       lista.add(cliente);
		       
		       }
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Cliente "+ e.getMessage());
		       
		   }
	   
		  return lista;
	   }


	   public List<Cliente> buscarTodos(){
		
		String sql = "select * from clientes";
		
		List<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			 ResultSet rs = stmt.executeQuery();
			 
			 while(rs.next()){
				 
			Cliente cliente = new Cliente();

			 cliente.setId(rs.getInt("pk_cliente"));
		       cliente.setNome(rs.getString("nome_cliente"));
		       cliente.setCpf(rs.getString("cpf"));
		       cliente.setEmail(rs.getString("email"));
		       cliente.setTelefone(rs.getString("telefone"));
		       cliente.setEndereco(rs.getString("endereco"));
		       cliente.setStatus(rs.getString("Status"));
		     
		     lista.add(cliente);
		
			 }
			 
			 stmt.close();
			 
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return lista;
		
	}
	   
	   public void Salvar(Cliente cliente){
		  
		   
		   if(cliente.getId()!= null && cliente.getId()!= 0){
			   
			  alterar(cliente);
			   
		   }else {
			   
			  adiciona(cliente); 
			   
		   }
	 
	   }


	

}
