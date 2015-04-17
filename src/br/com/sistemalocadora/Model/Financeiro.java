package br.com.sistemalocadora.Model;

import java.math.BigDecimal;
import java.util.Calendar;

public class Financeiro {
	
	private Integer id;
	private Locacao locacao;
	private Calendar dataemissao;
	private Calendar datavencimento;
	private BigDecimal valor;
	private BigDecimal valorbaixa;
	private BigDecimal saldo;
	private String tipo;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Locacao getLocacao() {
		return locacao;
	}
	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}
	public Calendar getDataemissao() {
		return dataemissao;
	}
	public void setDataemissao(Calendar dataemissao) {
		this.dataemissao = dataemissao;
	}
	public Calendar getDatavencimento() {
		return datavencimento;
	}
	public void setDatavencimento(Calendar datavencimento) {
		this.datavencimento = datavencimento;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public BigDecimal getValorbaixa() {
		return valorbaixa;
	}
	public void setValorbaixa(BigDecimal valorbaixa) {
		this.valorbaixa = valorbaixa;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	
	

}
