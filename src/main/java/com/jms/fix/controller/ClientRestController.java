package com.jms.fix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jms.fix.entity.Client;
import com.jms.fix.service.ClientService;

@RestController
public class ClientRestController {
	
	@Autowired
	ClientService clientService;

		//-------------------Retrieve All Clients----------------------------------------
		@RequestMapping(value = { "/client/" }, method = RequestMethod.GET)
		public ResponseEntity<List<Client>> listAllQuotations() {
			List<Client> clients = clientService.findAllClients();
			if(clients.isEmpty()){
	            return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
		}

}
