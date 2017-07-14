package com.beeva.banco.bancoApp.factory;

import com.beeva.banco.bancoApp.model.Cuenta;

public interface CuentaInterface {
	
	public Cuenta deposito(Cuenta cuenta, double cantidad);
	public boolean retirar(Cuenta cuenta, double cantidad);

}
