package com.beeva.banco.bancoApp.factory;

import com.beeva.banco.bancoApp.model.Cuenta;

public class CuentaFactory {
	
	public CuentaInterface getImplements(Cuenta c){;
		if(c.getTipocuenta().getNombre().equals("ahorro")){
			CuentaInterface cuentaDao = new CuentaDeAhorro();
			return cuentaDao;
		}else if(c.getTipocuenta().getNombre().equals("cheque")){
			CuentaInterface cuentaDao = new CuentaDeCheque();
			return cuentaDao;
		}else
			return null;
	}

}
