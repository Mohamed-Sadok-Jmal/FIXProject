package com.jms.fix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jms.fix.entity.Order;
import com.jms.fix.model.OrderModel;
import com.jms.fix.service.ClientService;
import com.jms.fix.service.OrderService;
import com.jms.fix.service.QuotationService;

@RestController
public class OrderRestController {
	
	@Autowired
	QuotationService quotationService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	OrderService orderService;
	
	//-------------------Retrieve Single Order----------------------------------------
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable("id") int id) {
    	Order order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    
  //-------------------Post New Order----------------------------------------
    @RequestMapping(value = "/order/", method = RequestMethod.POST)
    public ResponseEntity<Void> addNewOrder(@RequestBody OrderModel orderModel) {
    	Order order = new Order();
    	order.setType(orderModel.getType());
    	order.setSide(orderModel.getSide());
    	order.setQte(orderModel.getQte());
    	order.setPrice(orderModel.getPrice());
        orderService.saveOrderWithClientAndQuotation(order, orderModel.getId_client(), orderModel.getId_quotation());
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
