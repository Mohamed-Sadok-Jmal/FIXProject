package com.jms.fix.service;

import java.util.List;

import com.jms.fix.entity.Quotation;

public interface QuotationService {
	
	Quotation findById(int id);
	
	void saveQuotation(Quotation quotation);
	
	void updateQuotation(Quotation quotation);
	
	void deleteQuotationByName(String name);
	
	void deleteAllQuotations();

	List<Quotation> findAllQuotations();
	
	List<Quotation> findAllQuotationsByParsing();
	
	List<Quotation> saveAllQuotationsFinded();
	
	Quotation findQuotationByName(String name);

}
