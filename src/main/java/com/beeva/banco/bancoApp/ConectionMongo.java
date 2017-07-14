package com.beeva.banco.bancoApp;

import java.net.UnknownHostException;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class ConectionMongo {
	
	private String servidor;
	private String puerto;
	

	public Mongo conexion() {
		int puertoD = Integer.parseInt(puerto);
		try {
			MongoClient mongo = new MongoClient(servidor, puertoD);
			return mongo;
		} catch (UnknownHostException e) {
			return null;
		}
	}


	public String getServidor() {
		return servidor;
	}


	public void setServidor(String servidor) {
		this.servidor = servidor;
	}


	public String getPuerto() {
		return puerto;
	}


	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

}
