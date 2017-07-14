package com.beeva.banco.bancoApp.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.banco.bancoApp.dao.ClienteDao;
import com.beeva.banco.bancoApp.model.Cliente;

@Repository
public class ClienteImpl extends ClienteDao {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public void saveCliente(Cliente c) {
		entityManager.persist(c);
	}

	@Override
	@Transactional
	public List<Cliente> listClientes() {
		Query query = entityManager.createQuery("SELECT c FROM Cliente c");
		return (List<Cliente>) query.getResultList();
	}

	@Override
	@Transactional
	public Cliente getCliente(int id) {
		return entityManager.find(Cliente.class, id);
	}

}
