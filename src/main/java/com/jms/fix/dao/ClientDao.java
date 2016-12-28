package com.jms.fix.dao;

import java.util.List;

import com.jms.fix.entity.Client;

public interface ClientDao {

	Client findById(int id);

	void saveClient(Client client);
	
	void deleteClientBySsn(String ssn);
	
	List<Client> findAllClients();

	Client findClientBySsn(String ssn);

}
