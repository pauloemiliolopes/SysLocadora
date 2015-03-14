package br.com.sistemalocadora.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistemalocadora.JDBC.Conexao;
import br.com.sistemalocadora.Model.Genero;
import br.com.sistemalocadora.Model.Usuario;

public class UsuarioDAO {

	
	private Connection con = Conexao.getConexao();


	public void adiciona(Usuario usuario) {
		
	   String sql = "insert into Usuarios (nome,login,senha) values (?,?,?)";

	   try {
	     
	       PreparedStatement stmt = con.prepareStatement(sql);

	       stmt.setString(1,usuario.getNome());
	       stmt.setString(2,usuario.getUser());
	       stmt.setString(3,usuario.getSenha());
	       
	       
	       stmt.execute();
	       stmt.close();
	       
	       System.out.println("Usuario Cadastrado!");
	       
	   } catch (SQLException e) {
		   
	       System.out.println("Erro ao cadastrar Genero "+ e.getMessage());
	       
	   }
	}


	public void alterar(Usuario usuario) {
		
		   String sql = "update usuarios set nome=?,login=?,senha=? where pk_usuario=?";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setString(1,usuario.getNome());
		       stmt.setString(2,usuario.getUser());
		       stmt.setString(3, usuario.getSenha());
		       stmt.setInt(4, usuario.getId());
		       
		       stmt.execute();
		       stmt.close();
		       
		       System.out.println("Usuario Atualizado!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Genero "+ e.getMessage());
		       
		   }
		}

	public void excluir(Usuario usuario) {
		
		   String sql = "delete from usuarios where pk_usuario=? ";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, usuario.getId());
		      
		       stmt.execute();
		       stmt.close();
		       
	       System.out.println("Usuario Excluido!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao excluir Usuario "+ e.getMessage());
		       
		   }
		}

	   public Usuario BuscarPorId(int id){
		   
		   String sql = "select * from usuarios where pk_usuario=?";
		   
		   Usuario usuario =  null;
		   
		   try {
			     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, id);
		      
		       stmt.execute();
		       
		       ResultSet rs = stmt.executeQuery();
		       
		       if (rs.next()){ 
		       
		       usuario = new Usuario();
		       
		       usuario.setId(rs.getInt("pk_usuario"));
		       usuario.setNome(rs.getString("nome"));
		       usuario.setUser(rs.getString("login"));
		       usuario.setSenha(rs.getString("senha"));
		       }
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao buscar Usuario "+ e.getMessage());
		       
		   }
	   
		  return usuario;
	   }


   public Usuario Autenticar(Usuario usuario){
		   
		   String sql = "select * from usuarios where login=? and senha=?";
		   
		   Usuario usuarioretorno=  null;
		   
		   try {
			     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setString(1, usuario.getUser());
		       stmt.setString(2, usuario.getSenha());
		      
		       stmt.execute();
		       
		       ResultSet rs = stmt.executeQuery();
		       
		       if (rs.next()){ 
		       
		       usuarioretorno = new Usuario();
		       
		       usuarioretorno.setId(rs.getInt("pk_usuario"));
		       usuarioretorno.setNome(rs.getString("nome"));
		       usuarioretorno.setUser(rs.getString("login"));
		       usuarioretorno.setSenha(rs.getString("senha"));
		       }
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao autenticar usuario"+ e.getMessage());
		       
		   }
	   
		  return usuarioretorno;
	   }

	   
	   
	   public List<Usuario> buscarTodos(){
		
		String sql = "select * from Usuarios";
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			 ResultSet resultado = stmt.executeQuery();
			 
			 while(resultado.next()){
				 
			Usuario usuario = new Usuario();

		     usuario.setId(resultado.getInt("pk_usuario"));
		     usuario.setNome(resultado.getString("nome"));
		     usuario.setUser(resultado.getString("login"));
		     usuario.setSenha(resultado.getString("senha"));
		     lista.add(usuario);
		
			 }
			 
			 stmt.close();
			 
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return lista;
		
	}
	   
	   public void Salvar(Usuario usuario){
		  
		   
		   if(usuario.getId()!= null && usuario.getId()!= 0){
			   
			  alterar(usuario);
			   
		   }else {
			   
			  adiciona(usuario); 
			   
		   }
	 
	   }


	
	
}
