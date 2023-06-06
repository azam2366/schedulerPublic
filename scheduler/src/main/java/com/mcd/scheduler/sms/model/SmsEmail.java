package com.mcd.scheduler.sms.model;

	

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;


@Entity
@Table(name = "smsemail", schema = "audit_trail")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)


public class SmsEmail implements Serializable {

	private static final long serialVersionUID = -1816157657236495026L;
	
	@Transient
	@Column(name = "smsemail_id", unique = true, nullable = false, precision = 19)
	private long smsemailId;
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "smsemail_guid", unique = true, nullable = false, length = 36)
	private String smsemailGuid;
	@Column(name = "smsemail_template_guid")
	private String smsemailTemplateGuid;
	@Column(name = "application_id")
	private String applicationId;
	@Column(name = "user_type")
	private String userType;
	@Column(name = "sms_to")
	private String smsTo;
	@Column(name = "sms_from")
	private String smsFrom;
	@Column(name = "sms_body_en")
	private String smsBodyEn;
	@Column(name = "sms_body_hi")
	private String smsBodyHi;
	@Column(name = "sms_body_rl")
	private String smsBodyRl;
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb", name = "sms_data_json")
	private String smsDataJson;
	@Column(name = "email_to")
	private String emailTo;
	@Column(name = "email_cc")
	private String emailcc;
	@Column(name = "email_bcc")
	private String emailbcc;
	@Column(name = "email_from")
	private String emailfrom;
	@Column(name = "email_subject_en")
	private String emailsubjecten;
	@Column(name = "email_subject_hi")
	private String emailsubjecthi;
	@Column(name = "email_subject_rl")
	private String emailsubjectrl;
	@Column(name = "email_body_en")
	private String emailbodyen;
	@Column(name = "email_body_hi")
	private String emailbodyhi;
	@Column(name = "email_body_rl")
	private String emailbodyrl;
	
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb", name = "email_data_json")
	private String emaildatajson;
	@Column(name = "email_priority")
	private boolean emailpriority;
	
	@Column(name = "is_email_attachment")
	private boolean isemailattachment;
	
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb", name = "email_attachment_details")
	private String emailattachmentdetails;
	@Column(name = "is_email")
	private boolean isEmail;
	@Column(name = "is_sms")
	private boolean isSms;
	@Column(name = "is_gims")
	private boolean isGims;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "sender_ip")
	private String senderIp;
	
	@Column(name = "is_sms_success")
	private boolean isSmsSuccess;
	
	@Column(name = "is_email_success")
	private boolean isEmailSuccess;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "send_date")
	private Date sendDate;
	@Column(name = "is_success")
	private boolean isSuccess;
	@Column(name = "created_by")
	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "created_ip_addr")
	private String createdIpAddr;
	@Column(name = "created_mac_addr")
	private String createdMacAddr;
	@Column(name = "creator_remarks")
	private String creatorRemarks;
	@Column(name = "created_uri")
	private String createdUri;
	public long getSmsemailId() {
		return smsemailId;
	}
	public void setSmsemailId(long smsemailId) {
		this.smsemailId = smsemailId;
	}
	public String getSmsemailGuid() {
		return smsemailGuid;
	}
	public void setSmsemailGuid(String smsemailGuid) {
		this.smsemailGuid = smsemailGuid;
	}
	public String getSmsemailTemplateGuid() {
		return smsemailTemplateGuid;
	}
	public void setSmsemailTemplateGuid(String smsemailTemplateGuid) {
		this.smsemailTemplateGuid = smsemailTemplateGuid;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSmsTo() {
		return smsTo;
	}
	public void setSmsTo(String smsTo) {
		this.smsTo = smsTo;
	}
	public String getSmsFrom() {
		return smsFrom;
	}
	public void setSmsFrom(String smsFrom) {
		this.smsFrom = smsFrom;
	}
	public String getSmsBodyEn() {
		return smsBodyEn;
	}
	public void setSmsBodyEn(String smsBodyEn) {
		this.smsBodyEn = smsBodyEn;
	}
	public String getSmsBodyHi() {
		return smsBodyHi;
	}
	public void setSmsBodyHi(String smsBodyHi) {
		this.smsBodyHi = smsBodyHi;
	}
	public String getSmsBodyRl() {
		return smsBodyRl;
	}
	public void setSmsBodyRl(String smsBodyRl) {
		this.smsBodyRl = smsBodyRl;
	}
	public String getSmsDataJson() {
		return smsDataJson;
	}
	public void setSmsDataJson(String smsDataJson) {
		this.smsDataJson = smsDataJson;
	}
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public String getEmailcc() {
		return emailcc;
	}
	public void setEmailcc(String emailcc) {
		this.emailcc = emailcc;
	}
	public String getEmailbcc() {
		return emailbcc;
	}
	public void setEmailbcc(String emailbcc) {
		this.emailbcc = emailbcc;
	}
	public String getEmailfrom() {
		return emailfrom;
	}
	public void setEmailfrom(String emailfrom) {
		this.emailfrom = emailfrom;
	}
	public String getEmailsubjecten() {
		return emailsubjecten;
	}
	public void setEmailsubjecten(String emailsubjecten) {
		this.emailsubjecten = emailsubjecten;
	}
	public String getEmailsubjecthi() {
		return emailsubjecthi;
	}
	public void setEmailsubjecthi(String emailsubjecthi) {
		this.emailsubjecthi = emailsubjecthi;
	}
	public String getEmailsubjectrl() {
		return emailsubjectrl;
	}
	public void setEmailsubjectrl(String emailsubjectrl) {
		this.emailsubjectrl = emailsubjectrl;
	}
	public String getEmailbodyen() {
		return emailbodyen;
	}
	public void setEmailbodyen(String emailbodyen) {
		this.emailbodyen = emailbodyen;
	}
	public String getEmailbodyhi() {
		return emailbodyhi;
	}
	public void setEmailbodyhi(String emailbodyhi) {
		this.emailbodyhi = emailbodyhi;
	}
	public String getEmailbodyrl() {
		return emailbodyrl;
	}
	public void setEmailbodyrl(String emailbodyrl) {
		this.emailbodyrl = emailbodyrl;
	}
	
	public boolean isEmailpriority() {
		return emailpriority;
	}
	public void setEmailpriority(boolean emailpriority) {
		this.emailpriority = emailpriority;
	}
	public boolean isIsemailattachment() {
		return isemailattachment;
	}
	public void setIsemailattachment(boolean isemailattachment) {
		this.isemailattachment = isemailattachment;
	}
	public String getEmailattachmentdetails() {
		return emailattachmentdetails;
	}
	public void setEmailattachmentdetails(String emailattachmentdetails) {
		this.emailattachmentdetails = emailattachmentdetails;
	}
	public boolean isEmail() {
		return isEmail;
	}
	public void setEmail(boolean isEmail) {
		this.isEmail = isEmail;
	}
	public boolean isSms() {
		return isSms;
	}
	public void setSms(boolean isSms) {
		this.isSms = isSms;
	}
	public boolean isGims() {
		return isGims;
	}
	public void setGims(boolean isGims) {
		this.isGims = isGims;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSenderIp() {
		return senderIp;
	}
	public void setSenderIp(String senderIp) {
		this.senderIp = senderIp;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedIpAddr() {
		return createdIpAddr;
	}
	public void setCreatedIpAddr(String createdIpAddr) {
		this.createdIpAddr = createdIpAddr;
	}
	public String getCreatedMacAddr() {
		return createdMacAddr;
	}
	public void setCreatedMacAddr(String createdMacAddr) {
		this.createdMacAddr = createdMacAddr;
	}
	public String getCreatorRemarks() {
		return creatorRemarks;
	}
	public void setCreatorRemarks(String creatorRemarks) {
		this.creatorRemarks = creatorRemarks;
	}
	public String getCreatedUri() {
		return createdUri;
	}
	public void setCreatedUri(String createdUri) {
		this.createdUri = createdUri;
	}
	public String getEmaildatajson() {
		return emaildatajson;
	}
	public void setEmaildatajson(String emaildatajson) {
		this.emaildatajson = emaildatajson;
	}
	public boolean isSmsSuccess() {
		return isSmsSuccess;
	}
	public void setSmsSuccess(boolean isSmsSuccess) {
		this.isSmsSuccess = isSmsSuccess;
	}
	public boolean isEmailSuccess() {
		return isEmailSuccess;
	}
	public void setEmailSuccess(boolean isEmailSuccess) {
		this.isEmailSuccess = isEmailSuccess;
	}
	

	
}