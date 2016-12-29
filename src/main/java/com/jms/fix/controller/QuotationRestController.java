package com.jms.fix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jms.fix.entity.Quotation;
import com.jms.fix.service.QuotationService;

@RestController
public class QuotationRestController {
	@Autowired
	QuotationService quotationService;
	
	@Autowired
	MessageSource messageSource;

	//-------------------Retrieve All Quotations----------------------------------------
	@RequestMapping(value = { "/quotation/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Quotation>> listAllQuotations() {
		quotationService.deleteAllQuotations();
		List<Quotation> quotations = quotationService.saveAllQuotationsFinded();
//		List<Quotation> quotations = quotationService.findAllQuotations();
		if(quotations.isEmpty()){
            return new ResponseEntity<List<Quotation>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Quotation>>(quotations, HttpStatus.OK);
	}
	
	//-------------------Retrieve Single Quotation----------------------------------------
    @RequestMapping(value = "/quotation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quotation> getQuotation(@PathVariable("id") int id) {
    	Quotation quotation = quotationService.findById(id);
        if (quotation == null) {
            return new ResponseEntity<Quotation>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Quotation>(quotation, HttpStatus.OK);
    }
	

}
