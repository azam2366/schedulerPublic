package com.mcd.scheduler.mst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;


@Entity
@Table(name = "geo_country", schema = "mst")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Geo_country {

	private static final long serialVersionUID = -1816157657236495026L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "country_mst_guid", unique = true, nullable = false, length = 36)
	private String countryMstGuid;
	
	@Column(name = "country_mst_id")
	private String country_mst_id;
	
	@Column(name = "country_mobile_code")
	private String country_mobile_code;
	
	@Column(name = "country_code")
	private String country_code;
	
	@Column(name = "country_name_en")
	private String country_name_en;

	public String getCountryMstGuid() {
		return countryMstGuid;
	}

	public void setCountryMstGuid(String countryMstGuid) {
		this.countryMstGuid = countryMstGuid;
	}

	public String getCountry_mst_id() {
		return country_mst_id;
	}

	public void setCountry_mst_id(String country_mst_id) {
		this.country_mst_id = country_mst_id;
	}

	public String getCountry_mobile_code() {
		return country_mobile_code;
	}

	public void setCountry_mobile_code(String country_mobile_code) {
		this.country_mobile_code = country_mobile_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getCountry_name_en() {
		return country_name_en;
	}

	public void setCountry_name_en(String country_name_en) {
		this.country_name_en = country_name_en;
	}
	
	
}
