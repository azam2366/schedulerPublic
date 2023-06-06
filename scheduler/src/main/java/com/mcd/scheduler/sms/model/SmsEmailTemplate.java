package com.mcd.scheduler.sms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smsemail_template", schema = "mst")
public class SmsEmailTemplate implements Serializable {

	private static final long serialVersionUID = 100001L;

	@Column(name="smsemail_template_guid")
	private String smsemailTemplateGuid;
	
	@Column(name = "smsemail_linked_header")
	private String smsemailLinkedHeader;

	@Column(name = "smsemail_entity_id ")
	private String smsemailEntityId;

	@Id
	@Column(name = "smsemail_template_name")
	private String smsemailTemplateName;

	@Column(name = "smsemail_template_code")
	private String smsemailTemplateCode;

	@Column(name = "sms_service_body_en")
	private String smsServiceBodyEn;

	@Column(name = "is_active")
	private String isActive;

	public String getSmsemailLinkedHeader() {
		return smsemailLinkedHeader;
	}

	public void setSmsemailLinkedHeader(String smsemailLinkedHeader) {
		this.smsemailLinkedHeader = smsemailLinkedHeader;
	}

	public String getSmsemailEntityId() {
		return smsemailEntityId;
	}

	public void setSmsemailEntityId(String smsemailEntityId) {
		this.smsemailEntityId = smsemailEntityId;
	}

	public String getSmsemailTemplateName() {
		return smsemailTemplateName;
	}

	public void setSmsemailTemplateName(String smsemailTemplateName) {
		this.smsemailTemplateName = smsemailTemplateName;
	}

	public String getSmsemailTemplateCode() {
		return smsemailTemplateCode;
	}

	public void setSmsemailTemplateCode(String smsemailTemplateCode) {
		this.smsemailTemplateCode = smsemailTemplateCode;
	}

	public String getSmsServiceBodyEn() {
		return smsServiceBodyEn;
	}

	public void setSmsServiceBodyEn(String smsServiceBodyEn) {
		this.smsServiceBodyEn = smsServiceBodyEn;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getSmsemailTemplateGuid() {
		return smsemailTemplateGuid;
	}

	public void setSmsemailTemplateGuid(String smsemailTemplateGuid) {
		this.smsemailTemplateGuid = smsemailTemplateGuid;
	}

}
