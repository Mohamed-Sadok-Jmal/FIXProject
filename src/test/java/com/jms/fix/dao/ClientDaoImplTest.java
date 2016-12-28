package com.jms.fix.dao;

import java.math.BigDecimal;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jms.fix.dao.ClientDao;
import com.jms.fix.entity.Client;


public class ClientDaoImplTest extends EntityDaoImplTest{

	@Autowired
	ClientDao clientDao;

	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Client.xml"));
		return dataSet;
	}
	
	/* In case you need multiple datasets (mapping different tables) and you do prefer to keep them in separate XML's
	@Override
	protected IDataSet getDataSet() throws Exception {
	  IDataSet[] datasets = new IDataSet[] {
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Client.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Benefits.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Departements.xml"))
	  };
	  return new CompositeDataSet(datasets);
	}
	*/

	@Test
	public void findById(){
		Assert.assertNotNull(clientDao.findById(1));
		Assert.assertNull(clientDao.findById(3));
	}

	
	@Test
	public void saveClient(){
		clientDao.saveClient(getSampleClient());
		Assert.assertEquals(clientDao.findAllClients().size(), 3);
	}
	
	@Test
	public void deleteClientBySsn(){
		clientDao.deleteClientBySsn("11111");
		Assert.assertEquals(clientDao.findAllClients().size(), 1);
	}
	
	@Test
	public void deleteClientByInvalidSsn(){
		clientDao.deleteClientBySsn("23423");
		Assert.assertEquals(clientDao.findAllClients().size(), 2);
	}

	@Test
	public void findAllClients(){
		Assert.assertEquals(clientDao.findAllClients().size(), 2);
	}
	
	@Test
	public void findClientBySsn(){
		Assert.assertNotNull(clientDao.findClientBySsn("11111"));
		Assert.assertNull(clientDao.findClientBySsn("14545"));
	}

	public Client getSampleClient(){
		Client client = new Client();
		client.setName("Karen");
		client.setSsn("12345");
		client.setSalary(new BigDecimal(10980));
		client.setJoiningDate(new LocalDate());
		return client;
	}

}
