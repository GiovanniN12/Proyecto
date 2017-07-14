package com.beeva.banco.bancoApp.dao;

import java.util.List;

import com.beeva.banco.bancoApp.model.Tipocuenta;

public abstract class TipoCuentaDao {
	
	public abstract void saveTipoCuenta(Tipocuenta tipo);
	public abstract List<Tipocuenta> listTipoCuenta();
	public abstract Tipocuenta getTipoCuenta(String tipoCuenta);

}
