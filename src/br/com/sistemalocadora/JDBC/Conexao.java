package br.com.sistemalocadora.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	
	 public Connection conn;
	 public Statement stm;
	 public ResultSet rs;

	public static Connection getConexao(){
		
		Connection con = null;
		
				try {
					try {
						Class.forName("org.postgresql.Driver");
					} catch (ClassNotFoundException e) {
						System.out.println("Nao foi possivle encontrar o Driver" + e.getMessage());
					} 
					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Locadora", "postgres","123");
					System.out.println("Conectado!");
				} catch (SQLException e) {
				   System.err.println("Nao foi possivel realizar a conexao"+ e.getMessage());
				}
				
				
				return con;
	}
	
	
	
	 public void executaSQL(String sql){
	        try{
	        stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
	        rs = stm.executeQuery(sql);
	        }   catch (SQLException ex) {
	            //JOptionPane.showMessageDialog(null,"Erro de ExecutaSQL!\n Erro"+ex.getMessage());
	        }
	    }

}
