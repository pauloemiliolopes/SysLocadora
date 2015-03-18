package br.com.sistemalocadora.Model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Filme {
	
	private Integer id;
	private String nome;
	private Calendar datalanc;
	private String sinopse;
	private int tempoloc;
	private int qtd;
	private BigDecimal preco;
	private String status;
	private Genero genero;
	
	
	
	
	
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public  Calendar getDatalanc() {
		return datalanc;
	}
	public void setDatalanc(Calendar datalanc) {

		this.datalanc = datalanc;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public int getTempoloc() {
		return tempoloc;
	}
	public void setTempoloc(int tempoloc) {
		this.tempoloc = tempoloc;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Filme [id=" + id + ", nome=" + nome + ", datalanc=" + datalanc
				+ ", sinopse=" + sinopse + ", tempoloc=" + tempoloc + ", qtd="
				+ qtd + ", preco=" + preco + ", status=" + status + ", genero="
				+ genero + "]";
	}
	
	
	

}
