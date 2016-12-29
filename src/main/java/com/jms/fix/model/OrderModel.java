package com.jms.fix.model;

public class OrderModel {

	private int id;
	private String type;
	private String side;
	private int qte;
	private float price;
	private int id_quotation;
	private int id_client;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getId_quotation() {
		return id_quotation;
	}
	public void setId_quotation(int id_quotation) {
		this.id_quotation = id_quotation;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	

}
