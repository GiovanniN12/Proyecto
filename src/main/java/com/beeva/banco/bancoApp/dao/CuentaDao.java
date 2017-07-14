package com.beeva.banco.bancoApp.dao;


import com.beeva.banco.bancoApp.model.Cuenta;

public abstract class CuentaDao {
	
	public abstract Cuenta saveCuenta(Cuenta cuenta);
	public abstract void updateCuenta(Cuenta cuenta);

}
