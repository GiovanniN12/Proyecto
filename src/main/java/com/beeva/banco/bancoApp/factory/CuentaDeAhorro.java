package com.beeva.banco.bancoApp.factory;

import java.math.BigDecimal;
import java.math.MathContext;

import com.beeva.banco.bancoApp.model.Cuenta;

public class CuentaDeAhorro implements CuentaInterface{

	public Cuenta deposito(Cuenta cuenta, double cantidad) {
		if(cantidad > 0){
		  BigDecimal b = cuenta.getBalance();
		  double balance = b.doubleValue();
		  double r = balance+cantidad;
		  BigDecimal resultado = new BigDecimal(r, MathContext.DECIMAL64);
		  cuenta.setBalance(resultado);
		  return cuenta;
		}else
			return cuenta;
	}

	public boolean retirar(Cuenta cuenta, double cantidad) {
		BigDecimal b = cuenta.getBalance();
		double balance = b.doubleValue();
		if( balance >= 5000){
			double r = balance - cantidad;
			BigDecimal resultado = new BigDecimal(r, MathContext.DECIMAL64);
			cuenta.setBalance(resultado);
		 return true;
		}else
			return false;
	}

}
