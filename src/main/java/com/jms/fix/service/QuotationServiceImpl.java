package com.jms.fix.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jms.fix.dao.QuotationDao;
import com.jms.fix.entity.Quotation;

@Service("quotationService")
@Transactional
public class QuotationServiceImpl implements QuotationService {

	@Autowired
	private QuotationDao dao;

	@Override
	public Quotation findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveQuotation(Quotation quotation) {
		dao.saveQuotation(quotation);
	}

	@Override
	public void updateQuotation(Quotation quotation) {
		Quotation entity = dao.findById(quotation.getId());
		if (entity != null) {
			entity.setName(quotation.getName());
			entity.setOpening(quotation.getOpening());
			entity.setTop(quotation.getTop());
			entity.setLow(quotation.getLow());
			entity.setVolumeTitles(quotation.getVolumeTitles());
			entity.setVolumeDT(quotation.getVolumeDT());
			entity.setLatest(quotation.getLatest());
			entity.setVariation(quotation.getVariation());
		}
	}

	@Override
	public void deleteQuotationByName(String name) {
		dao.deleteQuotationByName(name);
	}
	
	@Override
	public void deleteAllQuotations() {
		dao.deleteAllQuotations();
	}

	@Override
	public List<Quotation> findAllQuotations() {
		return dao.findAllQuotations();
	}

	@Override
	public List<Quotation> findAllQuotationsByParsing() {
		List<Quotation> quotationsList = new ArrayList<Quotation>();
		boolean firstSkipped = false;
		
		String html = "http://www.ilboursa.com/marches/aaz.aspx";
		try {
			Document doc = Jsoup.connect(html).get();
			Elements tableElements = doc.select("table");
			Elements tableRowElements = tableElements.select("tr");
			for( Element element : tableRowElements) {
			     // Skip the first 'tr' tag since it's the header
			    if( !firstSkipped ) {
			        firstSkipped = true;
			        continue;
			    }

			    int index = 0; // Instead of index you can use 0, 1, 2, ...
			    Quotation tableEntry = new Quotation();
			    Elements td = element.select("td");
			    tableEntry.setName(td.get(index++).text());
			    tableEntry.setOpening(Float.parseFloat(td.get(index++).text()));
			    tableEntry.setTop(Float.parseFloat(td.get(index++).text()));
			    tableEntry.setLow(Float.parseFloat(td.get(index++).text()));
			    tableEntry.setVolumeTitles(Integer.parseInt(td.get(index++).text()));
			    tableEntry.setVolumeDT(Float.parseFloat(td.get(index++).text()));
			    tableEntry.setLatest(Float.parseFloat(td.get(index++).text()));
			    String str = td.get(index++).text();
			    tableEntry.setVariation(Float.parseFloat(str.substring(0, str.length() - 1)));

			    quotationsList.add(tableEntry); // Finally add it to the list
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return quotationsList;
	}

	@Override
	public List<Quotation> saveAllQuotationsFinded() {
		List<Quotation> quotationsList = this.findAllQuotationsByParsing();
		for (Quotation quotation : quotationsList) {
			dao.saveQuotation(quotation);
		}
		return quotationsList;
	}

	@Override
	public Quotation findQuotationByName(String name) {
		return dao.findQuotationByName(name);
	}

}
