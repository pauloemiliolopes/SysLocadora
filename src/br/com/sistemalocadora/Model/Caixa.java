package br.com.sistemalocadora.Model;

import java.math.BigDecimal;
import java.util.Calendar;

public class Caixa {
	
	private Integer id;
	private Financeiro financeiro;
	private String descricao;
	private Calendar datapagamento;
	private String formapgto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Financeiro getFinanceiro() {
		return financeiro;
	}
	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getDatapagamento() {
		return datapagamento;
	}
	public void setDatapagamento(Calendar datapagamento) {
		this.datapagamento = datapagamento;
	}
	public BigDecimal getValorpagamento() {
		return valorpagamento;
	}
	public void setValorpagamento(BigDecimal valorpagamento) {
		this.valorpagamento = valorpagamento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFormapgto() {
		return formapgto;
	}
	public void setFormapgto(String formapgto) {
		this.formapgto = formapgto;
	}
	private BigDecimal valorpagamento;
	private String tipo;
	
	
	
	

}
