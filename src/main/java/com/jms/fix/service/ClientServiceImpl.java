package com.jms.fix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jms.fix.dao.ClientDao;
import com.jms.fix.entity.Client;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao dao;
	
	public Client findById(int id) {
		return dao.findById(id);
	}

	public void saveClient(Client client) {
		dao.saveClient(client);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateClient(Client client) {
		Client entity = dao.findById(client.getId());
		if(entity!=null){
			entity.setName(client.getName());
			entity.setJoiningDate(client.getJoiningDate());
			entity.setSalary(client.getSalary());
			entity.setSsn(client.getSsn());
		}
	}

	public void deleteClientBySsn(String ssn) {
		dao.deleteClientBySsn(ssn);
	}
	
	public List<Client> findAllClients() {
		return dao.findAllClients();
	}

	public Client findClientBySsn(String ssn) {
		return dao.findClientBySsn(ssn);
	}

	public boolean isClientSsnUnique(Integer id, String ssn) {
		Client client = findClientBySsn(ssn);
		return ( client == null || ((id != null) && (client.getId() == id)));
	}
	
}
