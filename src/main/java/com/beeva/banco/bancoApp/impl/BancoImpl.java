package com.beeva.banco.bancoApp.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.banco.bancoApp.dao.BancoDao;
import com.beeva.banco.bancoApp.model.Banco;

@Repository
public class BancoImpl extends BancoDao{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public void saveBanco(Banco banco) {
		entityManager.persist(banco);
		//return banco;
	}

	@Override
	@Transactional
	public List<Banco> listBanco() {
		Query query = entityManager.createQuery("SELECT b.nombre FROM Banco b");
		return (List<Banco>) query.getResultList();
	}

	@Override
	@Transactional
	public Banco getBanco(String nombreBanco) {
		Banco banco = (Banco) entityManager.createQuery("SELECT b FROM Banco b WHERE b.nombre=:nombre").setParameter("nombre", nombreBanco).getSingleResult();
		return banco;
	}

}