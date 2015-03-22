package br.com.sistemalocadora.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	/*ainda bugado*/
	public List<?> getall() {

		String sql = "SELECT * FROM aluguel";
		List<String> alugueis =  new ArrayList<>();

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				alugueis.add(rs.getString(2));
			}

			stmt.close();
			
			return alugueis;
	
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
}
