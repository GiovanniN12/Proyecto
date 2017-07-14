package com.beeva.banco.bancoApp.dao;

import java.util.List;

import com.beeva.banco.bancoApp.model.Cliente;

public abstract class ClienteDao {
	
	public abstract void saveCliente(Cliente c);
	public abstract Cliente getCliente(int id);
	public abstract List<Cliente> listClientes();

}
