package br.com.sistemalocadora.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.sistemalocadora.JDBC.Conexao;
import br.com.sistemalocadora.Model.Locacao;

public class LocacaoDAO {

	private Connection con = Conexao.getInstance().getConexao();

	private ClienteDAO daocliente = new ClienteDAO();

	public Locacao adiciona(Locacao locacao) {

		String sql = "insert into locacoes (fk_cliente,data_locacao,valor,status) values (?,?,?,?)";

		try {

			PreparedStatement stmt = con.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, locacao.getCliente().getId());
			stmt.setTimestamp(2, new Timestamp(locacao.getDataloc()
					.getTimeInMillis()));
			stmt.setBigDecimal(3, locacao.getValor());

			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();

			rs.next();

			locacao.setId(rs.getInt("pk_locacao"));
			ClienteDAO dao = new ClienteDAO();
			Integer fkcli = rs.getInt("fk_cliente");
			locacao.setCliente((dao.BuscarPorId(fkcli)));
			Calendar ca = Calendar.getInstance();
			ca.setTimeInMillis((rs.getTimestamp("data_locacao").getTime()));
			locacao.setDataloc(ca);
			locacao.setValor(rs.getBigDecimal("valor"));
			locacao.setStatus(rs.getString("status"));

			stmt.close();

			System.out.println("Locação Cadastrado!" + locacao);

			return locacao;

		} catch (SQLException e) {

			System.out.println("Erro ao cadastrar Locaçao " + e.getMessage());

		}

		return null;
	}

	public void alterar(Locacao locacao) {

		String sql = "update locacoes set fk_cliente=?,data_locacao=?,valor=? where pk_locacao=?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, locacao.getCliente().getId());
			stmt.setTimestamp(2, new Timestamp(locacao.getDataloc()
					.getTimeInMillis()));
			stmt.setBigDecimal(3, locacao.getValor());

			stmt.setInt(4, locacao.getId());

			stmt.execute();
			stmt.close();

			System.out.println("Locação Atualizada!");

		} catch (SQLException e) {

			System.out.println("Erro ao cadastrar Locacao " + e.getMessage());

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

			System.out.println("Erro ao cadastrar Locaçoes " + e.getMessage());

		}
	}

	public Locacao BuscarPorId(int id) {

		String sql = "select * from locacoes where pk_locacao=?";

		Locacao locacao = null;

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				locacao = new Locacao();

				locacao.setId(rs.getInt("pk_locacao"));
				locacao.setCliente(daocliente.BuscarPorId(rs
						.getInt("fk_cliente")));

				Calendar ca = Calendar.getInstance();
				ca.setTimeInMillis((rs.getTimestamp("data_locacao").getTime()));
				locacao.setDataloc(ca);
				locacao.setValor(rs.getBigDecimal("valor"));

			}

		} catch (SQLException e) {

			System.out.println("Erro ao cadastrar Locação " + e.getMessage());

		}

		return locacao;
	}
	
	
	public Locacao BuscarPorCli(int id) {

		String sql = "select * from locacoes where pk_cliente=? ";

		Locacao locacao = null;

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				locacao = new Locacao();

				locacao.setId(rs.getInt("pk_locacao"));
				locacao.setCliente(daocliente.BuscarPorId(rs
						.getInt("fk_cliente")));

				Calendar ca = Calendar.getInstance();
				ca.setTimeInMillis((rs.getTimestamp("data_locacao").getTime()));
				locacao.setDataloc(ca);
				locacao.setValor(rs.getBigDecimal("valor"));

			}

		} catch (SQLException e) {

			System.out.println("Erro ao cadastrar Locação " + e.getMessage());

		}

		return locacao;
	}


	public List<Locacao> buscarTodos() {

		String sql = "select * from locacoes";

		List<Locacao> lista = new ArrayList<Locacao>();

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Locacao locacao = new Locacao();

				locacao.setId(rs.getInt("pk_locacao"));
				locacao.setCliente(daocliente.BuscarPorId(rs
						.getInt("fk_cliente")));
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
	
	
	public List<Locacao> buscarporNomeCli(String nome) {

		String sql = "select * from locacoes join clientes "
				+ "on pk_cliente=fk_cliente where nome_cliente like ?";

		List<Locacao> lista = new ArrayList<Locacao>();

		try {

			PreparedStatement stmt = con.prepareStatement(sql);
			
			 stmt.setString(1, "%"+nome+"%");
			 
			 
			 
			stmt.execute();

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				System.out.println(rs.getInt("pk_locacao"));

				Locacao locacao = new Locacao();

				locacao.setId(rs.getInt("pk_locacao"));
				locacao.setCliente(daocliente.BuscarPorId(rs
						.getInt("fk_cliente")));
				Calendar ca = Calendar.getInstance();
				ca.setTimeInMillis((rs.getTimestamp("data_locacao").getTime()));
				locacao.setDataloc(ca);
				locacao.setValor(rs.getBigDecimal("valor"));

				lista.add(locacao);

			}

			stmt.close();

		} catch (SQLException e) {
			System.out.println("Nao foi possivel Buscar por nome"+ e);
			e.printStackTrace();
		}

		return lista;

	}


	public void Salvar(Locacao locacao) {

		if ((locacao.getId() != null) && (locacao.getId() != 0)) {

			alterar(locacao);

		} else {

			adiciona(locacao);

		}

	}

	public int pegarultimoId() throws SQLException {
		String sql = "SELECT MAX(pk_locacao) as id FROM locacoes";
		PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int lastId = rs.getInt("id");

		rs.close();
		stmt.close();

		return lastId;
	}

	public void alterarValor(BigDecimal valor, int id) {

		String sql = "update locacoes set valor=? where pk_locacao=?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setBigDecimal(1, valor);
			stmt.setInt(2, id);

			stmt.execute();
			stmt.close();

			System.out.println("Valor Locação Atualizada!");

		} catch (SQLException e) {

			System.out.println("Erro ao Atualizar Locacao " + e.getMessage());

		}

	}

	public void FinalizarLocacao(String Status, int id) {

		String sql = "update locacoes set Status=? where pk_locacao=?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, Status);
			stmt.setInt(2, id);

			stmt.execute();
			stmt.close();

			System.out.println("Locação Finalizada");

		} catch (SQLException e) {

			System.out.println("Erro ao Atualizar Locacao " + e.getMessage());

		}

	}

	
	
	
}
