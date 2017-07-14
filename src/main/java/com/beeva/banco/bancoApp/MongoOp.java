package com.beeva.banco.bancoApp;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.banco.bancoApp.model.Cliente;
import com.beeva.banco.bancoApp.model.Cuenta;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class MongoOp {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
	ConectionMongo m = (ConectionMongo) context.getBean("mongoConection");
	
	public void guardarClienteMongo(Cliente cliente, String s){
		
		DB db = m.conexion().getDB("bancodb");
	    DBCollection table = db.getCollection("clientes");
	
	    BasicDBObject document = new BasicDBObject();
	    Date date = new Date();
	    DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	    DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
	    String fecha = formatoFecha.format(date);
	    String hora = formatoHora.format(date);
	    
	    String nombreCliente = cliente.getNombre();
	    String apellidoCliente = cliente.getApellido();
	    int idCliente = cliente.getIdcliente();
	    document.put("Operacion", s);
	    document.put("idCliente", idCliente);
	    document.put("Nombre", nombreCliente);
	    document.put("Apellido",apellidoCliente);
	    document.put("Fecha", fecha);
	    document.put("Hora", hora);
	    table.insert(document);   
	}
	
	public void guardarCuentaMongo(Cuenta cuenta, String s){
		
		DB db = m.conexion().getDB("bancodb");
	    DBCollection table = db.getCollection("cuentas");
	
	    BasicDBObject document = new BasicDBObject();
	    Date date = new Date();
	    DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	    DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
	    String fecha = formatoFecha.format(date);
	    String hora = formatoHora.format(date);
	    int idCuenta = cuenta.getIdcuenta();
	    
	    BigDecimal b = cuenta.getBalance();
		double bal = b.doubleValue();
		document.put("Operacion: ", s);
		document.put("idCuenta", idCuenta);
		document.put("Balance", bal);
		document.put("Tipo", cuenta.getTipocuenta().getNombre());
	    document.put("Fecha", fecha);
	    document.put("Hora", hora);
	    table.insert(document);
	}

}
