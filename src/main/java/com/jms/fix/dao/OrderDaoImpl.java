package com.jms.fix.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jms.fix.entity.Client;
import com.jms.fix.entity.Order;
import com.jms.fix.entity.Quotation;

@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao{

	@Override
	public Order findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveOrder(Order order) {
		persist(order);		
	}

	@Override
	public void deleteOrderById(int id) {
		Query query = getSession().createSQLQuery("delete from Quotation where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllOrders() {
		Criteria criteria = createEntityCriteria();
		return (List<Order>) criteria.list();
	}

	@Override
	public void saveOrderWithClientAndQuotation(Order order, int clientId, int quotationId) {
		Session session = getSession();
		Client client = (Client) session.get(Client.class, clientId);
		Quotation quotation = (Quotation) session.get(Quotation.class, quotationId);
		order.setClient(client);
		order.setQuotation(quotation);
		persist(order);
	}

}
