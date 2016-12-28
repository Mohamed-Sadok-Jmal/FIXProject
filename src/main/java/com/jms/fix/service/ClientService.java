package com.jms.fix.service;

import java.util.List;

import com.jms.fix.entity.Client;

public interface ClientService {

	Client findById(int id);
	
	void saveClient(Client client);
	
	void updateClient(Client client);
	
	void deleteClientBySsn(String ssn);

	List<Client> findAllClients(); 
	
	Client findClientBySsn(String ssn);

	boolean isClientSsnUnique(Integer id, String ssn);
	
}
