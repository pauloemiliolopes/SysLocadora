package br.com.sistemalocadora.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sistemalocadora.JDBC.Conexao;

public class AluguelDAO {
	private Connection con = Conexao.getInstance().getConexao();

	public boolean gravarAluguel(String json) {

		String sql = "INSERT INTO aluguel (data) VALUES(?::jsonb)";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, json);
			stmt.execute();
			stmt.close();
			con.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	/*ainda bugado*/
	public String getall() {

		String sql = "SELECT * FROM aluguel";
		String json = "";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				json += "{ id :"+rs.getLong(1) + " : " + rs.getString(2)+ "}";
			}

			stmt.close();
			con.close();

			return json;
	
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
}
