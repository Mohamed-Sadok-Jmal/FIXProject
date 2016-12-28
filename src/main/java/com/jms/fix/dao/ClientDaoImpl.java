package com.jms.fix.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jms.fix.entity.Client;

@Repository("clientDao")
public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao {

	public Client findById(int id) {
		return getByKey(id);
	}

	public void saveClient(Client client) {
		persist(client);
	}

	public void deleteClientBySsn(String ssn) {
		Query query = getSession().createSQLQuery("delete from Client where ssn = :ssn");
		query.setString("ssn", ssn);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Client> findAllClients() {
		Criteria criteria = createEntityCriteria();
		return (List<Client>) criteria.list();
	}

	public Client findClientBySsn(String ssn) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssn", ssn));
		return (Client) criteria.uniqueResult();
	}
}
