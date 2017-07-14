package com.beeva.banco.bancoApp.dao;

import java.util.List;

import com.beeva.banco.bancoApp.model.Banco;

public abstract class BancoDao {
	
	public abstract void saveBanco(Banco banco);
	public abstract List<Banco> listBanco();
	public abstract Banco getBanco(String nombreBanco);

}
