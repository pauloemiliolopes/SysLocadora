package br.com.sistemalocadora.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.sistemalocadora.JDBC.Conexao;
import br.com.sistemalocadora.Model.ItensLocacao;
import br.com.sistemalocadora.Model.Locacao;

public class ItensLocacaoDAO {

	private Connection con = Conexao.getInstance().getConexao();

	FilmeDAO filmedao = new FilmeDAO();

	public void adiciona(ItensLocacao itenslocacao) {

		String sql = "insert into itenslocacoes (fk_locacao,fk_filme,dataprevdev,qtd) values (?,?,?,?)";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, itenslocacao.getLocacao());
			stmt.setInt(2, itenslocacao.getFilme().getId());
			stmt.setTimestamp(3, new Timestamp(itenslocacao.getDataprevdevolucao().getTimeInMillis()));
			stmt.setInt(4, itenslocacao.getQtd());

			stmt.execute();
			stmt.close();

			System.out.println("Item Cadastrado!");

		} catch (SQLException e) {

			System.out.println("Erro ao cadastrar Item " + e.getMessage());

		}
	}

	public void alterar(ItensLocacao itenslocacao) {

		String sql = "update itenslocacoes set fk_locacao=?,fk_filme=?,data_devolucao=?,dataprevdev=?,qtd=? where pk_itenslocacao=?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, itenslocacao.getLocacao());
			stmt.setInt(2, itenslocacao.getFilme().getId());
			stmt.setTimestamp(3, new Timestamp(itenslocacao.getDatadevolucao()
					.getTimeInMillis()));
			stmt.setTimestamp(4, new Timestamp(itenslocacao
					.getDataprevdevolucao().getTimeInMillis()));
			stmt.setInt(5, itenslocacao.getQtd());

			stmt.setInt(6, itenslocacao.getId());

			stmt.execute();
			stmt.close();

			System.out.println("Item Atualizado!");

		} catch (SQLException e) {

			System.out.println("Erro ao Alterar Item " + e.getMessage());

		}
	}

	public void excluir(int id) {

		String sql = "delete from itenslocacoes where pk_itenslocacao=? ";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

			System.out.println("Item Excluido!");

		} catch (SQLException e) {

			System.out.println("Erro ao Excluir Item " + e.getMessage());

		}
	}

	public ItensLocacao BuscarPorId(int id) {

		String sql = "select * from itenslocacoes where pk_itenslocacao=?";

		ItensLocacao itenslocacao = null;

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				itenslocacao = new ItensLocacao();

				itenslocacao.setId(rs.getInt("pk_itenslocacao"));
				itenslocacao.setLocacao(rs.getInt("fk_locacao"));
				itenslocacao.setFilme(filmedao.BuscarPorId(rs
						.getInt("fk_filme")));
				

				Calendar c = Calendar.getInstance();
				c.setTimeInMillis((rs.getTimestamp("dataprevdev").getTime()));
				itenslocacao.setDataprevdevolucao(c);
				
				Calendar ca = Calendar.getInstance();
				ca.setTimeInMillis((rs.getTimestamp("datadev").getTime()));
				itenslocacao.setDatadevolucao(ca);
				
				itenslocacao.setQtd(rs.getInt("qtd"));

			}

		} catch (SQLException e) {

			System.out.println("Erro ao Buscarporid Item " + e.getMessage());

		}

		return itenslocacao;
	}

	public List<ItensLocacao> buscarTodos() {

		String sql = "select * from itenslocacoes";

		List<ItensLocacao> lista = new ArrayList<ItensLocacao>();

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ItensLocacao itenslocacao = new ItensLocacao();

				itenslocacao.setId(rs.getInt("pk_itenslocacao"));
				itenslocacao.setLocacao(rs.getInt("fk_locacao"));
				itenslocacao.setFilme(filmedao.BuscarPorId(rs
						.getInt("fk_filme")));
				// Calendar ca = Calendar.getInstance();
				// ca.setTimeInMillis((rs.getTimestamp("data_devolucao").getTime()));
				// itenslocacao.setDatadevolucao(ca);

				Calendar c = Calendar.getInstance();
				c.setTimeInMillis((rs.getTimestamp("dataprevdev").getTime()));
				itenslocacao.setDataprevdevolucao(c);
				
				itenslocacao.setQtd(rs.getInt("qtd"));

				lista.add(itenslocacao);

			}

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;

	}

	public List<ItensLocacao> buscarporLocacao(int id) {

		String sql = "select * from itenslocacoes where fk_locacao=?";

		List<ItensLocacao> lista = new ArrayList<ItensLocacao>();

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ItensLocacao itenslocacao = new ItensLocacao();

				itenslocacao.setId(rs.getInt("pk_itenslocacao"));
				itenslocacao.setLocacao(rs.getInt("fk_locacao"));
				itenslocacao.setFilme(filmedao.BuscarPorId(rs
						.getInt("fk_filme")));
				

				Calendar c = Calendar.getInstance();
				c.setTimeInMillis((rs.getTimestamp("dataprevdev").getTime()));
				itenslocacao.setDataprevdevolucao(c);
			
			
				
				Calendar ca = Calendar.getInstance();
				ca.setTimeInMillis((rs.getTimestamp("datadev").getTime()));
				itenslocacao.setDatadevolucao(ca);
			   
				
				
				itenslocacao.setQtd(rs.getInt("qtd"));

				lista.add(itenslocacao);

			}

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;

	}
	
	

	public void Salvar(ItensLocacao itenslocacao) {

		if ((itenslocacao.getId() != null) && (itenslocacao.getId() != 0)) {

			alterar(itenslocacao);

		} else {

			adiciona(itenslocacao);

		}

	}
	
	public  void GravaDataDevolucao(Timestamp datadevolucao, int id) {

		String sql = "update itenslocacoes set datadev= ? where pk_itenslocacao= ?";


		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setTimestamp(1, new Timestamp(datadevolucao.getTime()));
			
			stmt.setInt(2, id);

			stmt.execute();

			stmt.close();

	        System.out.println("Data de Devoluçao Atribuida");

		} catch (SQLException e) {

			System.out.println("Erro ao Buscarporid Item " + e.getMessage());

		}

		
	}
	
	

}
