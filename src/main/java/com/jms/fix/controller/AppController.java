package com.jms.fix.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jms.fix.entity.Client;
import com.jms.fix.entity.Order;
import com.jms.fix.entity.Quotation;
import com.jms.fix.service.ClientService;
import com.jms.fix.service.FixInitiator;
import com.jms.fix.service.OrderService;
import com.jms.fix.service.QuotationService;

import quickfix.ConfigError;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.ClOrdID;
import quickfix.field.HandlInst;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TransactTime;
import quickfix.fix42.NewOrderSingle;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	ClientService clientService;
	@Autowired
	QuotationService quotationService;
	@Autowired
	OrderService orderService;
	
	@Autowired
	MessageSource messageSource;
	
	FixInitiator fixInitiator = null;

	/*
	 * This method will list all existing clients.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listClients(ModelMap model) {
		List<Client> clients = clientService.findAllClients();
		quotationService.deleteAllQuotations();
		List<Quotation> quotations = quotationService.saveAllQuotationsFinded();
//		List<Quotation> quotations = quotationService.findAllQuotations();
		
		//get client
		//Client client = clientService.findById(1);
		
		/* begin fix */
		try {
			fixInitiator = FixInitiator.getInstance();
		} catch (ConfigError e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		SessionID sessionID = null;
		sessionID = fixInitiator.connectAndGetSession();
		
		NewOrderSingle order = new NewOrderSingle(new ClOrdID("APPL12456S"),
				new HandlInst(HandlInst.MANUAL_ORDER), new Symbol("APPL"),
				new Side(Side.BUY), new TransactTime(new Date()), new OrdType(OrdType.MARKET));
		
		order.set(new OrderQty(4500));
		order.set(new Price(200.9d));
		
		System.out.println("Sending Order to Server");
		try {
			Session.sendToTarget(order, sessionID);
		} catch (SessionNotFound e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		/* end fix */	
		
		model.addAttribute("clients", clients);
		model.addAttribute("quotations", quotations);
		return "allemployees";
	}

	/*
	 * This method will provide the medium to add a new client.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newClient(ModelMap model) {
		Client client = new Client();
		model.addAttribute("client", client);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving client in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveClient(@Valid Client client, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Client].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!clientService.isClientSsnUnique(client.getId(), client.getSsn())){
			FieldError ssnError =new FieldError("client","ssn",messageSource.getMessage("non.unique.ssn", new String[]{client.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}
		
		clientService.saveClient(client);

		model.addAttribute("success", "Client " + client.getName() + " registered successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing client.
	 */
	@RequestMapping(value = { "/edit-{ssn}-client" }, method = RequestMethod.GET)
	public String editClient(@PathVariable String ssn, ModelMap model) {
		Client client = clientService.findClientBySsn(ssn);
		model.addAttribute("client", client);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating client in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{ssn}-client" }, method = RequestMethod.POST)
	public String updateClient(@Valid Client client, BindingResult result,
			ModelMap model, @PathVariable String ssn) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!clientService.isClientSsnUnique(client.getId(), client.getSsn())){
			FieldError ssnError =new FieldError("client","ssn",messageSource.getMessage("non.unique.ssn", new String[]{client.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}

		clientService.updateClient(client);

		model.addAttribute("success", "Client " + client.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an client by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{ssn}-client" }, method = RequestMethod.GET)
	public String deleteClient(@PathVariable String ssn) {
		clientService.deleteClientBySsn(ssn);
		return "redirect:/list";
	}

}
