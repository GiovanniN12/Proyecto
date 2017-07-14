package com.beeva.banco.bancoApp.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the cuenta database table.
 * 
 */
@Entity
@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c")
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcuenta;

	private BigDecimal balance;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Tipocuenta
	@ManyToOne
	@JoinColumn(name="idtipocuenta")
	private Tipocuenta tipocuenta;

	public Cuenta() {
	}

	public int getIdcuenta() {
		return this.idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tipocuenta getTipocuenta() {
		return this.tipocuenta;
	}

	public void setTipocuenta(Tipocuenta tipocuenta) {
		this.tipocuenta = tipocuenta;
	}

}