package com.jms.fix.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jms.fix.entity.Quotation;

@Repository("quotationDao")
public class QuotationDaoImpl extends AbstractDao<Integer, Quotation> implements QuotationDao{

	@Override
	public Quotation findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveQuotation(Quotation quotation) {
		persist(quotation);		
	}

	@Override
	public void deleteQuotationByName(String name) {
		Query query = getSession().createSQLQuery("delete from Quotation where name = :name");
		query.setString("name", name);
		query.executeUpdate();
	}
	
	@Override
	public void deleteAllQuotations() {
		Query query = getSession().createSQLQuery("delete from Quotation");
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quotation> findAllQuotations() {
		Criteria criteria = createEntityCriteria();
		return (List<Quotation>) criteria.list();
	}

	@Override
	public Quotation findQuotationByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (Quotation) criteria.uniqueResult();
	}

}
