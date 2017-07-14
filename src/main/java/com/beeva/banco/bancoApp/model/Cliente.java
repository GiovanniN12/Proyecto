package com.beeva.banco.bancoApp.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcliente;

	private String apellido;

	private String nombre;

	//bi-directional many-to-one association to Bancoscliente
	@OneToMany(mappedBy="cliente")
	private List<Bancoscliente> bancosclientes;

	//bi-directional many-to-one association to Cuenta
	@OneToMany(mappedBy="cliente")
	private List<Cuenta> cuentas;

	public Cliente() {
	}

	public int getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Bancoscliente> getBancosclientes() {
		return this.bancosclientes;
	}

	public void setBancosclientes(List<Bancoscliente> bancosclientes) {
		this.bancosclientes = bancosclientes;
	}

	public Bancoscliente addBancoscliente(Bancoscliente bancoscliente) {
		getBancosclientes().add(bancoscliente);
		bancoscliente.setCliente(this);

		return bancoscliente;
	}

	public Bancoscliente removeBancoscliente(Bancoscliente bancoscliente) {
		getBancosclientes().remove(bancoscliente);
		bancoscliente.setCliente(null);

		return bancoscliente;
	}

	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cuenta addCuenta(Cuenta cuenta) {
		getCuentas().add(cuenta);
		cuenta.setCliente(this);

		return cuenta;
	}

	public Cuenta removeCuenta(Cuenta cuenta) {
		getCuentas().remove(cuenta);
		cuenta.setCliente(null);

		return cuenta;
	}

}