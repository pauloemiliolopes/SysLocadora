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
import br.com.sistemalocadora.Model.Financeiro;



public class FinanceiroDAO {
	
	private Connection con = Conexao.getInstance().getConexao();
   
	LocacaoDAO locdao = new LocacaoDAO();

	public void adiciona(Financeiro financeiro){
		
	   String sql = "insert into financeiro (fk_locacao,dataemissao,datavencimento,valor,valorbaixa,saldo,tipo) values (?,?,?,?,?,?,?)";

	   try {
	     
	       PreparedStatement stmt = con.prepareStatement(sql);

	       stmt.setInt(1,financeiro.getLocacao().getId());
	       stmt.setTimestamp(2,new Timestamp(financeiro.getDataemissao().getTimeInMillis()));
	       stmt.setTimestamp(3,new Timestamp(financeiro.getDatavencimento().getTimeInMillis())); 
	       stmt.setBigDecimal(4,financeiro.getValor()); 
	       stmt.setBigDecimal(5,financeiro.getValorbaixa()); 
	       stmt.setBigDecimal(6,financeiro.getSaldo()); 
	       stmt.setString(7,financeiro.getTipo());
	       
	       stmt.execute();
	       stmt.close();
	       
	       System.out.println("Financeiro Cadastrado!");
	       
	   } catch (SQLException e) {
		   
	       System.out.println("Erro ao cadastrar Financeiro "+ e.getMessage());
	       
	   }
	}


	public void alterar(Financeiro financeiro) {
		
		   String sql = "update financeiro set fk_locacao=?,dataemissao=?,datavencimento=?,valor=?,valorbaixa=?,saldo=?,tipo=? where pk_financeiro=?";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1,financeiro.getLocacao().getId());
		       stmt.setTimestamp(2,new Timestamp(financeiro.getDataemissao().getTimeInMillis()));
		       stmt.setTimestamp(3,new Timestamp(financeiro.getDatavencimento().getTimeInMillis()));
		       stmt.setBigDecimal(4,financeiro.getValor()); 
		       stmt.setBigDecimal(5,financeiro.getValorbaixa());
		       stmt.setBigDecimal(6,financeiro.getSaldo());
		       stmt.setString(7,financeiro.getTipo());
		       stmt.setInt(8,financeiro.getId());
		       
		       stmt.execute();
		       stmt.close();
		       
		       System.out.println("Financeiro Atualizado!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Financeiro "+ e.getMessage());
		       
		   }
		}

	public void excluir(Financeiro financeiro) {
		
		   String sql = "delete from financeiro where pk_financeiro=? ";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, financeiro.getId());
		      
		       stmt.execute();
		       stmt.close();
		       
	       System.out.println("Financeiro Excluido!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Financeiro "+ e.getMessage());
		       
		   }
		}

	   public Financeiro BuscarPorId(int id){
		   
		   String sql = "select * from financeiro where pk_financeiro=?";
		   
		   Financeiro financeiro =  null;
		   
		   try {
			     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setInt(1, id);
		      
		       stmt.execute();
		       
		       ResultSet rs = stmt.executeQuery();
		       
		       if (rs.next()){ 
		       
		       financeiro = new Financeiro();
		       
		       financeiro.setId(rs.getInt("pk_financeiro"));
		       
		       financeiro.setLocacao(locdao.BuscarPorId(rs.getInt("fk_locacao")));
		       
		       
		       Calendar dtemissao = Calendar.getInstance();
		       dtemissao.setTimeInMillis(rs.getTimestamp("dataemissao").getTime());
		       financeiro.setDataemissao(dtemissao);
		       
		       Calendar dtvencimento = Calendar.getInstance();
		       dtvencimento.setTimeInMillis(rs.getTimestamp("datavencimento").getTime());
		       financeiro.setDatavencimento(dtvencimento);
		       
		    
		       
		       financeiro.setValor(rs.getBigDecimal("valor"));
		       
		       financeiro.setValorbaixa(rs.getBigDecimal("valorbaixa"));
		       
		       financeiro.setSaldo(rs.getBigDecimal("saldo"));
		       
		       financeiro.setTipo(rs.getString("tipo"));
		       
		     
		       }
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao cadastrar Financeiro "+ e.getMessage());
		       
		   }
	   
		  return financeiro;
	   }



	   public List<Financeiro> buscarTodos(){
		
		String sql = "select * from financeiro f join locacoes l "
					+ "on fk_locacao=pk_locacao join clientes c "
					+ "on fk_cliente=pk_cliente where tipo='R' and saldo <> 0 ";
		
		List<Financeiro> lista = new ArrayList<Financeiro>();
	
		
		try {
			
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			 ResultSet rs = stmt.executeQuery();
			 
			 while(rs.next()){
				 
			Financeiro financeiro = new Financeiro();

		       
		       financeiro.setId(rs.getInt("pk_financeiro"));
		       financeiro.setLocacao(locdao.BuscarPorId(rs.getInt("fk_locacao")));
		       
		       
		       Calendar dtemissao = Calendar.getInstance();
		       dtemissao.setTimeInMillis(rs.getTimestamp("dataemissao").getTime());
		       financeiro.setDataemissao(dtemissao);
		       
		       Calendar dtvencimento = Calendar.getInstance();
		       dtvencimento.setTimeInMillis(rs.getTimestamp("datavencimento").getTime());
		       financeiro.setDatavencimento(dtvencimento);
		       
	
		       
		       financeiro.setValor(rs.getBigDecimal("valor"));
		       
               financeiro.setValorbaixa(rs.getBigDecimal("valorbaixa"));
		       
		       financeiro.setSaldo(rs.getBigDecimal("saldo"));
		       
		       financeiro.setTipo(rs.getString("tipo"));
		       
		     
		     lista.add(financeiro);
		
			 }
			 
			 stmt.close();
			 
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return lista;
		
	}
	   
	   public void Salvar(Financeiro financeiro){
		  
		   
		   if(financeiro.getId()!= null && financeiro.getId()!= 0){
			   
			  alterar(financeiro);
			   
		   }else {
			   
			  adiciona(financeiro); 
			   
		   }
	 
	   }
	   
	   public List<Financeiro> buscarporNomeCli(String nome) {

			String sql = "select * from (select * from financeiro f join locacoes l "
					+ "on fk_locacao=pk_locacao join clientes c "
					+ "on fk_cliente=pk_cliente where tipo='R' and saldo <> 0 ) as tudo where nome_cliente like ?";

			List<Financeiro> lista = new ArrayList<Financeiro>();

			try {

				PreparedStatement stmt = con.prepareStatement(sql);
				
				 stmt.setString(1, "%"+nome+"%");
				
				stmt.execute();

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					
					Financeiro financeiro = new Financeiro();
					
					 financeiro.setId(rs.getInt("pk_financeiro"));
				       financeiro.setLocacao(locdao.BuscarPorId(rs.getInt("fk_locacao")));
				       
				       
				       Calendar dtemissao = Calendar.getInstance();
				       dtemissao.setTimeInMillis(rs.getTimestamp("dataemissao").getTime());
				       financeiro.setDataemissao(dtemissao);
				       
				       Calendar dtvencimento = Calendar.getInstance();
				       dtvencimento.setTimeInMillis(rs.getTimestamp("datavencimento").getTime());
				       financeiro.setDatavencimento(dtvencimento);
				       
				       
				      
				       
				       
				       financeiro.setValor(rs.getBigDecimal("valor"));
				       
				       financeiro.setValorbaixa(rs.getBigDecimal("valorbaixa"));
				       
				       financeiro.setSaldo(rs.getBigDecimal("Saldo"));
				       
				       financeiro.setTipo(rs.getString("tipo"));
				       
				     
				     lista.add(financeiro);
				
					 }
					 
				stmt.close();

			} catch (SQLException e) {
				System.out.println("Nao foi possivel Buscar por nome"+ e);
				e.printStackTrace();
			}

			return lista;

		}
	   
	   public void Baixa(String descr, Calendar databaixa, BigDecimal valorbaixa, BigDecimal saldo, String formapgto, int id) {
			
		   String sql = "update financeiro set descricao=?,databaixa=?,valorbaixa=?, saldo=?, formapgto=? where pk_financeiro=?";

		   try {
		     
		       PreparedStatement stmt = con.prepareStatement(sql);

		       stmt.setString(1,descr);
		       stmt.setTimestamp(2,new Timestamp(databaixa.getTimeInMillis()));
		       stmt.setBigDecimal(3,valorbaixa);
		       stmt.setBigDecimal(4,saldo);
		       stmt.setString(5,formapgto);
		       stmt.setInt(6,id);
		       
		       stmt.execute();
		       stmt.close();
		       
		       System.out.println("Financeiro Baixado!");
		       
		   } catch (SQLException e) {
			   
		       System.out.println("Erro ao baixar Financeiro "+ e.getMessage());
		       
		   }
		}


	

}
