package com.beeva.banco.bancoApp.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.banco.bancoApp.dao.CuentaDao;
import com.beeva.banco.bancoApp.model.Cuenta;

@Repository
public class CuentaImpl extends CuentaDao{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public Cuenta saveCuenta(Cuenta cuenta) {
		entityManager.persist(cuenta);
		return cuenta;
	}

	@Override
	@Transactional
	public void updateCuenta(Cuenta cuenta) {
		entityManager.merge(cuenta);
	}

}
