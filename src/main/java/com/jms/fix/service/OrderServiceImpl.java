package com.jms.fix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jms.fix.dao.OrderDao;
import com.jms.fix.entity.Order;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao dao;

	@Override
	public Order findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveOrder(Order order) {
		dao.saveOrder(order);
	}

	@Override
	public void deleteOrderById(int id) {
		dao.deleteOrderById(id);		
	}

	@Override
	public List<Order> findAllOrders() {
		return dao.findAllOrders();
	}	
	
}
