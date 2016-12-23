package com.jms.fix.dao;

import java.util.List;

import com.jms.fix.entity.Quotation;

public interface QuotationDao {
	
	Quotation findById(int id);

	void saveQuotation(Quotation quotation);
	
	void deleteQuotationByName(String name);
	
	void deleteAllQuotations();
	
	List<Quotation> findAllQuotations();

	Quotation findQuotationByName(String name);

}
