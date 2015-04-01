package br.com.sistemalocadora.Model;

import java.math.BigDecimal;
import java.util.Calendar;

public class Locacao {
	
	private Integer id;
	private Cliente cliente;
	private Calendar dataloc;
	private BigDecimal valor;
	private String status;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Calendar getDataloc() {
		return dataloc;
	}
	public void setDataloc(Calendar dataloc) {
		this.dataloc = dataloc;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
