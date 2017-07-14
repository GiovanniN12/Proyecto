package com.beeva.banco.bancoApp;

public class AppDrools {
	
	private double balance;
	private double deposito;
	private String tipoCuenta;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getDeposito() {
		return deposito;
	}
	public void setDeposito(double deposito) {
		this.deposito = deposito;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	public void mensaje(String g){
		System.out.println(g);
	}
}
