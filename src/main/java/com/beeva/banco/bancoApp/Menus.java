package com.beeva.banco.bancoApp;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.banco.bancoApp.dao.BancoClienteDao;
import com.beeva.banco.bancoApp.dao.BancoDao;
import com.beeva.banco.bancoApp.dao.ClienteDao;
import com.beeva.banco.bancoApp.dao.CuentaDao;
import com.beeva.banco.bancoApp.dao.TipoCuentaDao;
import com.beeva.banco.bancoApp.factory.CuentaFactory;
import com.beeva.banco.bancoApp.factory.CuentaInterface;
import com.beeva.banco.bancoApp.impl.BancoClienteImpl;
import com.beeva.banco.bancoApp.impl.BancoImpl;
import com.beeva.banco.bancoApp.impl.ClienteImpl;
import com.beeva.banco.bancoApp.impl.CuentaImpl;
import com.beeva.banco.bancoApp.impl.TipoCuentaImpl;
import com.beeva.banco.bancoApp.model.Banco;
import com.beeva.banco.bancoApp.model.Bancoscliente;
import com.beeva.banco.bancoApp.model.Cliente;
import com.beeva.banco.bancoApp.model.Cuenta;
import com.beeva.banco.bancoApp.model.Tipocuenta;

public class Menus {

	ApplicationContext contexto = new ClassPathXmlApplicationContext("SpringBeans.xml");
	BancoDao bancoDao = (BancoDao) contexto.getBean(BancoImpl.class);
	ClienteDao clienteDao = (ClienteDao) contexto.getBean(ClienteImpl.class);
	BancoClienteDao bancoClienteDao = (BancoClienteDao) contexto.getBean(BancoClienteImpl.class);
	TipoCuentaDao tipoCuentaDao = (TipoCuentaDao) contexto.getBean(TipoCuentaImpl.class);
	CuentaDao cuentaDao = (CuentaDao) contexto.getBean(CuentaImpl.class);
	
	/**KieServices ks = KieServices.Factory.get();
    KieContainer kContainer = ks.getKieClasspathContainer();
    KieSession kSession = kContainer.newKieSession("ksession-rule");*/
    
	Scanner scanner = new Scanner(System.in);
	

	public void agregar(){
		int opcion = -1;
		Banco banco = new Banco();
		Cliente cliente = new Cliente();
		Bancoscliente bancoCliente = new Bancoscliente();
		Tipocuenta tipo = new Tipocuenta();
		Cuenta cuenta = new Cuenta();
		while(opcion !=0){
			System.out.println("1.- Crear Cliente");
			//System.out.println("2.- Mostrar Clientes");
			System.out.println("0.- Salir");
			opcion = scanner.nextInt();
			switch (opcion) {
			case 0:
				System.exit(0);
				break;
			case 1:
				System.out.println("Introduce tu nombre: ");
				String nombre = scanner.next();
				System.out.println("Introduce tu apellido: ");
				String apellido = scanner.next();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				clienteDao.saveCliente(cliente);
				System.out.println("*******************************************************");
				System.out.println("Cliente "+cliente.getNombre()+" "+cliente.getApellido()+" \nGuardado Correctamente");
				System.out.println("*******************************************************");
				bancoCuenta(cliente);
			/*case 2:
				List<Cliente> listaClientes = clienteDao.listClientes();
				System.out.println("Clientes ");
				System.out.println("**********************************************");
				Iterator c = listaClientes.iterator();
				Cliente cli;
				while(c.hasNext()){
					cli = (Cliente) c.next();
					System.out.println("Id: "+cli.getIdcliente()+" - Nombre: "+cli.getNombre());
				}
				System.out.println("**********************************************");
				System.out.println("Escribe el Id del cliente en el cual");
				System.out.println("Quieres realizar operaciones");
				int id = scanner.nextInt();
				System.out.println("1.- Agregar una nueva cuenta al cliente: ");
				System.out.println("2.- Realizar operaciones: ");
				int x = scanner.nextInt();
				cliente = clienteDao.getCliente(id);
				//List<Cuenta> listCuenta = cuentaDao.getListCuenta(id);
				//System.out.println(listCuenta);
				operaciones(banco, cliente, bancoCliente, tipo, cuenta);
				break;*/
			}
		}
	}

	public void operaciones(Banco banco, Cliente cliente, Bancoscliente bancoCliente, Tipocuenta tipo, Cuenta cuenta){
		int opcion = -1;
		while (opcion !=0) {
			System.out.println("1.- Depositar");
			System.out.println("2.- Retirar");
			System.out.println("3.- Salir");
			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Cuanto quieres depositar: ");
				double cantidad = scanner.nextDouble();
				
				/**AppDrools drools = new AppDrools();
				BigDecimal b = cuenta.getBalance();
				double ba = b.doubleValue();
				String s = cuenta.getTipocuenta().getNombre();
				drools.setBalance(ba);
				drools.setDeposito(cantidad);
				drools.setTipoCuenta(s);
				
				FactHandle factl;
		        
		        factl = kSession.insert(drools);
		        kSession.fireAllRules();*/
		        
				CuentaFactory factory = new CuentaFactory();
				CuentaInterface cuentaInter = factory.getImplements(cuenta);
				cuenta = cuentaInter.deposito(cuenta, cantidad);
				cuentaDao.updateCuenta(cuenta);
				MongoOp mongo = new MongoOp();
				String op = "Deposito";
				mongo.guardarCuentaMongo(cuenta,op);
				System.out.println("**********************************************");
				System.out.println("Deposito de "+cantidad+" en cuenta de "+cuenta.getTipocuenta().getNombre()+" Correcto");
				System.out.println("Banco: "+banco.getNombre());
				System.out.println("Balance Total "+cuenta.getBalance());
				System.out.println("**********************************************");
				break;

			case 2:
				System.out.println("Cuanto quieres Retirar: ");
				double retiro = scanner.nextDouble();
				CuentaFactory factoryRetiro = new CuentaFactory();
				CuentaInterface cuentaInterR = factoryRetiro.getImplements(cuenta);
				boolean c = cuentaInterR.retirar(cuenta, retiro);
				cuentaDao.updateCuenta(cuenta);
				MongoOp mongoR = new MongoOp();
				String x ="Retiro";
				mongoR.guardarCuentaMongo(cuenta,x);
				if(cuenta.getTipocuenta().getNombre().equals("ahorro")){
					if(c==true){
						System.out.println("**********************************************");
						System.out.println("Retiro en cuenta de ahorro de "+retiro+" Correcto");
						System.out.println("Banco: "+banco.getNombre());
						System.out.println("Balance Total = "+cuenta.getBalance());
						System.out.println("**********************************************");
					}else{
						System.out.println("**********************************************");
						System.out.println("Necesitas tener $5000 para poder retirar");
						System.out.println("**********************************************");
					}
				}else if(cuenta.getTipocuenta().getNombre().equals("cheque")){
					if(c==true){
						System.out.println("**********************************************");
						System.out.println("Retiro en cuenta de cheque de "+retiro+" Correcto");
						System.out.println("Banco: "+banco.getNombre());
						System.out.println("Balance Total = "+cuenta.getBalance());
						System.out.println("**********************************************");
					}else{
						String dia = diaSemana();
						System.out.println("**********************************************");
						System.out.println("No puedes realizar retiros los dias "+dia);
						System.out.println("**********************************************");
					}
				}else{
					System.out.println("Operacion Incorrecta");
				}
				break;
			case 3:
				agregar();
				break;
			}
		}


	}

	public void bancoCuenta(Cliente cliente){
		
		Banco banco = new Banco();
		Bancoscliente bancoCliente = new Bancoscliente();
		Tipocuenta tipo = new Tipocuenta();
		Cuenta cuenta = new Cuenta();
		
		List<Banco> listaBancos = bancoDao.listBanco();
		System.out.println("Elige un banco: ");
		System.out.println("*******************************************************");
		Iterator i = listaBancos.iterator();
		String g;
        while (i.hasNext()) {
            g = (String) i.next();
            System.out.println(g);
        }
        System.out.println("*******************************************************");
		System.out.println("Escribe el nombre del banco que quieres: ");
		String nombreBanco = scanner.next();
		banco = bancoDao.getBanco(nombreBanco);
		
    	bancoCliente.setBanco(banco);
    	bancoCliente.setCliente(cliente);
    	bancoClienteDao.saveBancoCliente(bancoCliente);
		System.out.println("**********************************************");
		System.out.println("El banco seleccionado fue: "+banco.getNombre());
		System.out.println("**********************************************");
		
		List<Tipocuenta> listaTiposCuentas = tipoCuentaDao.listTipoCuenta();
		System.out.println("Elige un tipo de cuenta: ");
		System.out.println("**********************************************");
		Iterator t = listaTiposCuentas.iterator();
		String ti;
        while (t.hasNext()) {
            ti = (String) t.next();
            System.out.println(ti);
        }
        System.out.println("**********************************************");
		System.out.println("Escribe el tipo de cuenta: ");
		String tipoCuenta = scanner.next();
		tipo = tipoCuentaDao.getTipoCuenta(tipoCuenta);
    	
		cuenta.setTipocuenta(tipo);
		cuenta.setCliente(cliente);
		BigDecimal bd = new BigDecimal(0);
		cuenta.setBalance(bd);
		cuenta = cuentaDao.saveCuenta(cuenta);
		System.out.println("**********************************************");
		System.out.println("Cliente : "+cliente.getNombre()+" "+cliente.getApellido());
		System.out.println("Cuenta de : "+cuenta.getTipocuenta().getNombre());
		System.out.println("Balance : "+cuenta.getBalance());
		System.out.println("Banco : "+banco.getNombre());
		System.out.println("Agregado Correctamente");
		System.out.println("**********************************************");
		MongoOp mongo = new MongoOp();
		String s = "Se agrego cliente";
		mongo.guardarClienteMongo(cliente, s);
		operaciones(banco, cliente, bancoCliente, tipo, cuenta);
	}

	public String diaSemana(){
		Calendar calendar = Calendar.getInstance();

		String[] dias = new String[]{"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};

		String dia = dias[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		return dia;
	}
	
	public boolean validar(double cantidad) {
		String cadena = String.valueOf(cantidad);
		if (cadena.matches("[0-9]*")) {
			return true;
			} else {
				return false;
				}
		}

}
