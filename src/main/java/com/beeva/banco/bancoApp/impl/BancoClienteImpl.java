package com.beeva.banco.bancoApp.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.banco.bancoApp.dao.BancoClienteDao;
import com.beeva.banco.bancoApp.model.Bancoscliente;

@Repository
public class BancoClienteImpl extends BancoClienteDao{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public void saveBancoCliente(Bancoscliente bancoCliente) {
		entityManager.persist(bancoCliente);
		
	}

	/*@Override
	@Transactional
	public Bancoscliente listBancosClientes(int id) {
		Query query = entityManager.createQuery("SELECT b FROM Bancoscliente b WHERE b.idcliente=:id").setParameter("id", id);
		return (Bancoscliente) query.getResultList();
	}*/

}
