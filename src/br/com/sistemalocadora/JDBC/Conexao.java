package br.com.sistemalocadora.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

	private static final Conexao instace = new Conexao();
	private Connection con = null;
	public Statement stm;
	public ResultSet rs;
	private int c = 0;
	private Conexao() {
	}

	public static Conexao getInstance() {
		return instace;
	}

	public Connection getConexao() {
		try {
			if (con == null || con.isClosed()) {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/Locadora",
						"postgres", "123");
				System.out.println("Conectado! "+c++);

			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void close() {
		try {
			if (!con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("desconectado!");
	}

	public void executaSQL(String sql) {
		try {
			stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,
					rs.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
		} catch (SQLException ex) {
			// JOptionPane.showMessageDialog(null,"Erro de ExecutaSQL!\n Erro"+ex.getMessage());
		}
	}

}
