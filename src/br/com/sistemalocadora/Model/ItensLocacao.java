package br.com.sistemalocadora.Model;

import java.util.Calendar;

public class ItensLocacao {
	
	private Integer id;
	private int locacao;
	private Filme filme;
	private Calendar datadevolucao;
	private Calendar dataprevdevolucao;
	private int qtd;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getLocacao() {
		return locacao;
	}
	public void setLocacao(int locacao) {
		this.locacao = locacao;
	}
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	public Calendar getDatadevolucao() {
		return datadevolucao;
	}
	public void setDatadevolucao(Calendar datadevolucao) {
		this.datadevolucao = datadevolucao;
	}
	public Calendar getDataprevdevolucao() {
		return dataprevdevolucao;
	}
	public void setDataprevdevolucao(Calendar dataprevdevolucao) {
		this.dataprevdevolucao = dataprevdevolucao;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	


}
