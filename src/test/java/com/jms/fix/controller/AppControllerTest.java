package com.jms.fix.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.atLeastOnce;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jms.fix.controller.AppController;
import com.jms.fix.entity.Client;
import com.jms.fix.service.ClientService;

public class AppControllerTest {

	@Mock
	ClientService service;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	AppController appController;
	
	@Spy
	List<Client> clients = new ArrayList<Client>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		clients = getClientList();
	}
	
	@Test
	public void listClients(){
		when(service.findAllClients()).thenReturn(clients);
		Assert.assertEquals(appController.listClients(model), "allclients");
		Assert.assertEquals(model.get("clients"), clients);
		verify(service, atLeastOnce()).findAllClients();
	}
	
	@Test
	public void newClient(){
		Assert.assertEquals(appController.newClient(model), "registration");
		Assert.assertNotNull(model.get("client"));
		Assert.assertFalse((Boolean)model.get("edit"));
		Assert.assertEquals(((Client)model.get("client")).getId(), 0);
	}


	@Test
	public void saveClientWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveClient(any(Client.class));
		Assert.assertEquals(appController.saveClient(clients.get(0), result, model), "registration");
	}

	@Test
	public void saveClientWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isClientSsnUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.saveClient(clients.get(0), result, model), "registration");
	}

	
	@Test
	public void saveClientWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isClientSsnUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).saveClient(any(Client.class));
		Assert.assertEquals(appController.saveClient(clients.get(0), result, model), "success");
		Assert.assertEquals(model.get("success"), "Client Axel registered successfully");
	}

	@Test
	public void editClient(){
		Client emp = clients.get(0);
		when(service.findClientBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(appController.editClient(anyString(), model), "registration");
		Assert.assertNotNull(model.get("client"));
		Assert.assertTrue((Boolean)model.get("edit"));
		Assert.assertEquals(((Client)model.get("client")).getId(), 1);
	}

	@Test
	public void updateClientWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateClient(any(Client.class));
		Assert.assertEquals(appController.updateClient(clients.get(0), result, model,""), "registration");
	}

	@Test
	public void updateClientWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isClientSsnUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.updateClient(clients.get(0), result, model,""), "registration");
	}

	@Test
	public void updateClientWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isClientSsnUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).updateClient(any(Client.class));
		Assert.assertEquals(appController.updateClient(clients.get(0), result, model, ""), "success");
		Assert.assertEquals(model.get("success"), "Client Axel updated successfully");
	}
	
	
	@Test
	public void deleteClient(){
		doNothing().when(service).deleteClientBySsn(anyString());
		Assert.assertEquals(appController.deleteClient("123"), "redirect:/list");
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
