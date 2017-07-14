package com.beeva.banco.bancoApp.factory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;

import com.beeva.banco.bancoApp.model.Cuenta;


public class CuentaDeCheque implements CuentaInterface{

	public Cuenta deposito(Cuenta cuenta, double cantidad) {
		if(cantidad >= 0){
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
       
		Calendar calendar = Calendar.getInstance();
		
		String[] dias = new String[]{"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
		
		String dia = dias[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		if(!dia.equals("Sabado") && !dia.equals("Domingo")){
			BigDecimal b = cuenta.getBalance();
			double balance = b.doubleValue();
			double r = balance - cantidad;
			BigDecimal resultado = new BigDecimal(r, MathContext.DECIMAL64);
			cuenta.setBalance(resultado);
			return true;
		}else
		return false;
	}


}
