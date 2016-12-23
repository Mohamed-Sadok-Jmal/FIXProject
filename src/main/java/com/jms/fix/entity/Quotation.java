package com.jms.fix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="QUOTATION")
public class Quotation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min=3, max=50)
	@Column(name = "NAME", unique=true, nullable = false)
	private String name;
	
	@Column(name = "OPENING")
	private float opening;
	
	@Column(name = "TOP")
	private float top;
	
	@Column(name = "Low")
	private float low;
	
	@Column(name = "VOLUME_TITLE")
	private int volumeTitles;
	
	@Column(name = "VOLUME_DT")
	private float volumeDT;
	
	@Column(name = "LATEST")
	private float latest;
	
	@Column(name = "VARIATION")
	private float variation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getOpening() {
		return opening;
	}

	public void setOpening(float opening) {
		this.opening = opening;
	}

	public float getTop() {
		return top;
	}

	public void setTop(float top) {
		this.top = top;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public int getVolumeTitles() {
		return volumeTitles;
	}

	public void setVolumeTitles(int volumeTitles) {
		this.volumeTitles = volumeTitles;
	}

	public float getVolumeDT() {
		return volumeDT;
	}

	public void setVolumeDT(float volumeDT) {
		this.volumeDT = volumeDT;
	}

	public float getLatest() {
		return latest;
	}

	public void setLatest(float latest) {
		this.latest = latest;
	}

	public float getVariation() {
		return variation;
	}

	public void setVariation(float variation) {
		this.variation = variation;
	}

}