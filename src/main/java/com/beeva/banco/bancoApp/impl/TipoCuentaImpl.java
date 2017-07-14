package com.beeva.banco.bancoApp.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.banco.bancoApp.dao.TipoCuentaDao;
import com.beeva.banco.bancoApp.model.Tipocuenta;

@Repository
public class TipoCuentaImpl extends TipoCuentaDao{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public void saveTipoCuenta(Tipocuenta tipo) {
		entityManager.persist(tipo);
		//entityManager.flush();
		//return tipo.getIdtipocuenta();
	}

	@Override
	@Transactional
	public List<Tipocuenta> listTipoCuenta() {
		Query query = entityManager.createQuery("SELECT t.nombre FROM Tipocuenta t");
		return (List<Tipocuenta>) query.getResultList();
	}

	@Override
	@Transactional
	public Tipocuenta getTipoCuenta(String tipoCuenta) {
		Tipocuenta tipo = (Tipocuenta) entityManager.createQuery("SELECT t FROM Tipocuenta t WHERE t.nombre=:nombre").setParameter("nombre", tipoCuenta).getSingleResult();
		return tipo;
	}

}
