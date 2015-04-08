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
import java.util.Date;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;

import br.com.sistemalocadora.JDBC.Conexao;
import br.com.sistemalocadora.Model.ItensLocacao;
import br.com.sistemalocadora.Model.Locacao;

public class ItensLocacaoDAO {

	private Connection con = Conexao.getInstance().getConexao();

	FilmeDAO filmedao = new FilmeDAO();

	public ItensLocacao adiciona(ItensLocacao itenslocacao) {

		String sql = "insert into itenslocacoes (fk_locacao,fk_filme,dataprevdev,qtd,valoritem,status) values (?,?,?,?,?,?)";

		try {
			

			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			
			

			stmt.setInt(1, itenslocacao.getLocacao());
			stmt.setInt(2, itenslocacao.getFilme().getId());
			stmt.setTimestamp(3, new Timestamp(itenslocacao.getDataprevdevolucao().getTimeInMillis()));
			stmt.setInt(4, itenslocacao.getQtd());
			stmt.setBigDecimal(5, itenslocacao.getValoritem());
			stmt.setString(6, itenslocacao.getStatus());

			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();

			rs.next();

			itenslocacao.setId(rs.getInt("pk_itenslocacao"));
			itenslocacao.setId(rs.getInt("fk_locacao"));
			FilmeDAO dao = new FilmeDAO();
			Integer fkfil = rs.getInt("fk_filme");
			itenslocacao.setFilme((dao.BuscarPorId(fkfil)));
			Calendar ca = Calendar.getInstance();
			ca.setTimeInMillis((rs.getTimestamp("dataprevdev").getTime()));
			itenslocacao.setQtd(rs.getInt("qtd"));
			itenslocacao.setValoritem(rs.getBigDecimal("valoritem"));
			itenslocacao.setStatus(rs.getString("status"));

			stmt.close();

			System.out.println("Locação Cadastrado!" + itenslocacao);

			

		} catch (SQLException e) {

			System.out.println("Erro ao cadastrar Item " + e.getMessage());

		}
		return itenslocacao;
	}

	public void alterar(ItensLocacao itenslocacao) {

		String sql = "update itenslocacoes set fk_locacao=?,fk_filme=?,data_devolucao=?,dataprevdev=?,qtd=?,valoritem=? where pk_itenslocacao=?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, itenslocacao.getLocacao());
			stmt.setInt(2, itenslocacao.getFilme().getId());
			stmt.setTimestamp(3, new Timestamp(itenslocacao.getDatadevolucao()
					.getTimeInMillis()));
			stmt.setTimestamp(4, new Timestamp(itenslocacao
					.getDataprevdevolucao().getTimeInMillis()));
			stmt.setInt(5, itenslocacao.getQtd());
			stmt.setBigDecimal(6, itenslocacao.getValoritem());

			stmt.setInt(7, itenslocacao.getId());

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
				
				
				 Timestamp data = rs.getTimestamp("datadev");
	        
					
					if(data != null){
						
						Calendar ca = Calendar.getInstance();
						ca.setTimeInMillis((rs.getTimestamp("datadev").getTime()));
						itenslocacao.setDatadevolucao(ca);
						
					}
				
				itenslocacao.setQtd(rs.getInt("qtd"));
				
				itenslocacao.setValoritem(rs.getBigDecimal("valoritem"));
				itenslocacao.setStatus(rs.getString("status"));

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
				
				

				Calendar c = Calendar.getInstance();
				c.setTimeInMillis((rs.getTimestamp("dataprevdev").getTime()));
				itenslocacao.setDataprevdevolucao(c);
				
				
                Timestamp data = rs.getTimestamp("datadev");
                
             
				
				if(data != null){
					
					Calendar ca = Calendar.getInstance();
					c.setTimeInMillis((rs.getTimestamp("datadev").getTime()));
					itenslocacao.setDataprevdevolucao(ca);
					
				}
				
				
				itenslocacao.setQtd(rs.getInt("qtd"));
				
				itenslocacao.setValoritem(rs.getBigDecimal("valoritem"));
				itenslocacao.setStatus(rs.getString("status"));

				lista.add(itenslocacao);

			}

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;

	}
	
	public List<ItensLocacao> BuscaEntreDatas(Calendar datainicial, Calendar datafinal, String fpgto) {

		String sql = "select * from itenslocacoes  where datadev >= ? and datadev <= ? and formapgto=?";

		List<ItensLocacao> lista = new ArrayList<ItensLocacao>();

		ItensLocacao itenslocacao;
		
		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setTimestamp(1, new Timestamp(datainicial.getTimeInMillis()));
			stmt.setTimestamp(2, new Timestamp(datafinal.getTimeInMillis()));
			stmt.setString(3, fpgto);
			
			System.out.println(stmt);

			stmt.execute();

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {

				itenslocacao = new ItensLocacao();

				itenslocacao.setId(rs.getInt("pk_itenslocacao"));
				itenslocacao.setLocacao(rs.getInt("fk_locacao"));
				itenslocacao.setFilme(filmedao.BuscarPorId(rs
						.getInt("fk_filme")));
				
				

				Calendar c = Calendar.getInstance();
				c.setTimeInMillis((rs.getTimestamp("dataprevdev").getTime()));
				itenslocacao.setDataprevdevolucao(c);
				
				
                Timestamp data = rs.getTimestamp("datadev");
                
             
				
				if(data != null){
					
					Calendar ca = Calendar.getInstance();
					ca.setTimeInMillis((rs.getTimestamp("datadev").getTime()));
					itenslocacao.setDatadevolucao(ca);
					
				}
				
				itenslocacao.setQtd(rs.getInt("qtd"));
				
				itenslocacao.setValoritem(rs.getBigDecimal("valoritem"));
				itenslocacao.setStatus(rs.getString("status"));

				lista.add(itenslocacao);

			}

			
			stmt.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return lista;
     
	}


	public List<ItensLocacao> buscarporLocacao(int id) {

		String sql = "select * from itenslocacoes where fk_locacao=? and status ='A'";

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
			
			
				
				
				if(rs.getTimestamp("datadev") != null){
				
					Calendar ca = Calendar.getInstance();
					ca.setTimeInMillis((rs.getTimestamp("datadev").getTime()));
					itenslocacao.setDatadevolucao(ca);
					System.out.println(ca);
					
				}
				 
			
				
				
				itenslocacao.setQtd(rs.getInt("qtd"));
				
				itenslocacao.setValoritem(rs.getBigDecimal("valoritem"));
				itenslocacao.setStatus(rs.getString("status"));

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
	
	public void FinalizarLocacao(String Status, int id) {

		String sql = "update itenslocacoes set Status=? where pk_itenslocacao=?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, Status);
			stmt.setInt(2, id);

			stmt.execute();
			stmt.close();

			System.out.println("Item Finalizado");

		} catch (SQLException e) {

			System.out.println("Erro ao Atualizar Locacao " + e.getMessage());

		}

	}

	
	public void FinalizarFinaceiro(String fpgto, int id) {

		String sql = "update itenslocacoes set formapgto=? where pk_itenslocacao=?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, fpgto);
			stmt.setInt(2, id);

			stmt.execute();
			stmt.close();

			System.out.println("Financeiro Atualizado");

		} catch (SQLException e) {

			System.out.println("Erro ao Atualizar Locacao " + e.getMessage());

		}

	}

	public void AlteraValorItem(BigDecimal valoritem, int id) {

		String sql = "update itenslocacoes set valoritem=? where pk_itenslocacao=?";

		try {

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setBigDecimal(1, valoritem);
			stmt.setInt(2, id);

			stmt.execute();
			stmt.close();

			System.out.println("Valor Item Atualizado");

		} catch (SQLException e) {

			System.out.println("Erro ao Atualizar Locacao " + e.getMessage());

		}

	}

	

}
