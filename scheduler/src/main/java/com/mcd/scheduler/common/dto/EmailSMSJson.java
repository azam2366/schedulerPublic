package com.mcd.scheduler.common.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;


import org.codehaus.jackson.annotate.JsonProperty;
//@XmlRootElement
public class EmailSMSJson implements Serializable {

	private String applicationCode, applicationPassword, eMailContent, senderMailId, senderPassword, mailSubject,
			smsCallingFuncFlag, appServiceUserName, appServicePassword;

	private String smsContent;

	private Map<String,String> attachments;
	
	private Long templateId, entityId;

	private boolean isMailChecked, isSMSChecked;
	
	private String attachment;
	
	private List<String> multipleTemplateId = null;

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationPassword() {
		return applicationPassword;
	}

	public void setApplicationPassword(String applicationPassword) {
		this.applicationPassword = applicationPassword;
	}

	public String geteMailContent() {
		return eMailContent;
	}

	public void seteMailContent(String eMailContent) {
		this.eMailContent = eMailContent;
	}

	public String getSenderMailId() {
		return senderMailId;
	}

	public void setSenderMailId(String senderMailId) {
		this.senderMailId = senderMailId;
	}

	public String getSenderPassword() {
		return senderPassword;
	}

	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getSmsCallingFuncFlag() {
		return smsCallingFuncFlag;
	}

	public void setSmsCallingFuncFlag(String smsCallingFuncFlag) {
		this.smsCallingFuncFlag = smsCallingFuncFlag;
	}

	public String getAppServiceUserName() {
		return appServiceUserName;
	}

	public void setAppServiceUserName(String appServiceUserName) {
		this.appServiceUserName = appServiceUserName;
	}

	public String getAppServicePassword() {
		return appServicePassword;
	}

	public void setAppServicePassword(String appServicePassword) {
		this.appServicePassword = appServicePassword;
	}

	public boolean getisMailChecked() {
		return isMailChecked;
	}

	public void setisMailChecked(boolean isMailChecked) {
		this.isMailChecked = isMailChecked;
	}

	public boolean getisSMSChecked() {
		return isSMSChecked;
	}

	public void setisSMSChecked(boolean isSMSChecked) {
		this.isSMSChecked = isSMSChecked;
	}

	public String[] getSendToOfficereMail() {
		return sendToOfficereMail;
	}

	public void setSendToOfficereMail(String[] sendToOfficereMail) {
		this.sendToOfficereMail = sendToOfficereMail;
	}

	public String[] getSendToOfficereMobile() {
		return sendToOfficereMobile;
	}

	public void setSendToOfficereMobile(String[] sendToOfficereMobile) {
		this.sendToOfficereMobile = sendToOfficereMobile;
	}

	public String[] getSendToOfficerId() {
		return sendToOfficerId;
	}

	public void setSendToOfficerId(String[] sendToOfficerId) {
		this.sendToOfficerId = sendToOfficerId;
	}

	public Map<String, String> getOfficerSMSDetails() {
		return officerSMSDetails;
	}

	public void setOfficerSMSDetails(Map<String, String> officerSMSDetails) {
		this.officerSMSDetails = officerSMSDetails;
	}

	@JsonProperty("SMSContent")
	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	private String[] sendToOfficereMail, sendToOfficereMobile, sendToOfficerId;
	private Map<String, String> officerSMSDetails;

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public List<String> getMultipleTemplateId() {
		return multipleTemplateId;
	}

	public void setMultipleTemplateId(List<String> multipleTemplateId) {
		this.multipleTemplateId = multipleTemplateId;
	}

	public boolean isMailChecked() {
		return isMailChecked;
	}

	public void setMailChecked(boolean isMailChecked) {
		this.isMailChecked = isMailChecked;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public boolean isSMSChecked() {
		return isSMSChecked;
	}

	public void setSMSChecked(boolean isSMSChecked) {
		this.isSMSChecked = isSMSChecked;
	}

	public Map<String, String> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}

//	public Map<String, String> getAttachments() {
//		return attachments;
//	}
//
//	public void setAttachments(Map<String, String> attachments) {
//		this.attachments = attachments;
//	}

//	public String getMailAttachment() {
//		return mailAttachment;
//	}
//
//	public void setMailAttachment(String mailAttachment) {
//		this.mailAttachment = mailAttachment;
//	}

}