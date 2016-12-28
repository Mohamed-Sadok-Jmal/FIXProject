package com.websystique.springmvc.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jms.fix.dao.ClientDao;
import com.jms.fix.entity.Client;
import com.jms.fix.service.ClientServiceImpl;

public class ClientServiceImplTest {

	@Mock
	ClientDao dao;
	
	@InjectMocks
	ClientServiceImpl clientService;
	
	@Spy
	List<Client> clients = new ArrayList<Client>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		clients = getClientList();
	}

	@Test
	public void findById(){
		Client emp = clients.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		Assert.assertEquals(clientService.findById(emp.getId()),emp);
	}

	@Test
	public void saveClient(){
		doNothing().when(dao).saveClient(any(Client.class));
		clientService.saveClient(any(Client.class));
		verify(dao, atLeastOnce()).saveClient(any(Client.class));
	}
	
	@Test
	public void updateClient(){
		Client emp = clients.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		clientService.updateClient(emp);
		verify(dao, atLeastOnce()).findById(anyInt());
	}

	@Test
	public void deleteClientBySsn(){
		doNothing().when(dao).deleteClientBySsn(anyString());
		clientService.deleteClientBySsn(anyString());
		verify(dao, atLeastOnce()).deleteClientBySsn(anyString());
	}
	
	@Test
	public void findAllClients(){
		when(dao.findAllClients()).thenReturn(clients);
		Assert.assertEquals(clientService.findAllClients(), clients);
	}
	
	@Test
	public void findClientBySsn(){
		Client emp = clients.get(0);
		when(dao.findClientBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(clientService.findClientBySsn(anyString()), emp);
	}

	@Test
	public void isClientSsnUnique(){
		Client emp = clients.get(0);
		when(dao.findClientBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(clientService.isClientSsnUnique(emp.getId(), emp.getSsn()), true);
	}
	
	
	public List<Client> getClientList(){
		Client e1 = new Client();
		e1.setId(1);
		e1.setName("Axel");
		e1.setJoiningDate(new LocalDate());
		e1.setSalary(new BigDecimal(10000));
		e1.setSsn("XXX111");
		
		Client e2 = new Client();
		e2.setId(2);
		e2.setName("Jeremy");
		e2.setJoiningDate(new LocalDate());
		e2.setSalary(new BigDecimal(20000));
		e2.setSsn("XXX222");
		
		clients.add(e1);
		clients.add(e2);
		return clients;
	}
	
}
