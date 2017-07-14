package com.beeva.banco.bancoApp;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.banco.bancoApp.dao.BancoDao;
import com.beeva.banco.bancoApp.dao.TipoCuentaDao;
import com.beeva.banco.bancoApp.impl.BancoImpl;
import com.beeva.banco.bancoApp.impl.TipoCuentaImpl;
import com.beeva.banco.bancoApp.model.Banco;
import com.beeva.banco.bancoApp.model.Tipocuenta;


public class Prueba {
	
	public static void main(String[] args) {
		
		ApplicationContext contexto = new ClassPathXmlApplicationContext("SpringBeans.xml");
    	BancoDao bancoDao = (BancoDao) contexto.getBean(BancoImpl.class);
    	TipoCuentaDao tipoCuentaDao = (TipoCuentaDao) contexto.getBean(TipoCuentaImpl.class);
		
    	Banco banco = new Banco();
    	banco.setNombre("bancomer");
    	bancoDao.saveBanco(banco);
    	
    	Banco banco2 = new Banco();
    	banco2.setNombre("banamex");
    	bancoDao.saveBanco(banco2);
    	
    	Banco banco3 = new Banco();
    	banco3.setNombre("banorte");
    	bancoDao.saveBanco(banco3);
    	
    	Banco banco4 = new Banco();
    	banco4.setNombre("hsbc");
    	bancoDao.saveBanco(banco4);
    	
    	Banco banco5 = new Banco();
    	banco5.setNombre("santander");
    	bancoDao.saveBanco(banco5);
    	
    	Banco banco6 = new Banco();
    	banco6.setNombre("scotiabank");
    	bancoDao.saveBanco(banco6);
    	
    	Tipocuenta tipo = new Tipocuenta();
    	tipo.setNombre("ahorro");
    	tipoCuentaDao.saveTipoCuenta(tipo);
    	
    	Tipocuenta tipo2 = new Tipocuenta();
    	tipo2.setNombre("cheque");
    	tipoCuentaDao.saveTipoCuenta(tipo2);
    	

		Menus menu = new Menus();
		menu.agregar();
		
	}

}
