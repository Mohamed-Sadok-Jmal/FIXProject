package com.jms.fix.dao;

import java.util.List;

import com.jms.fix.entity.Order;

public interface OrderDao {

	Order findById(int id);

	void saveOrder(Order order);
	
	void saveOrderWithClientAndQuotation(Order order, int clientId, int quotationId);
	
	void deleteOrderById(int id);
	
	List<Order> findAllOrders();
}
