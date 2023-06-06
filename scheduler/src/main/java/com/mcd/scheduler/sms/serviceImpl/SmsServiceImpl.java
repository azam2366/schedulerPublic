package com.mcd.scheduler.sms.serviceImpl;

import java.util.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.mcd.scheduler.common.dto.EmailSMSJson;
import com.mcd.scheduler.mst.model.Geo_country;
import com.mcd.scheduler.mst.repository.GeoCountryRepository;
import com.mcd.scheduler.sms.model.SmsEmail;
import com.mcd.scheduler.sms.model.SmsEmailTemplate;
import com.mcd.scheduler.sms.repository.SmsEmailRepository;
import com.mcd.scheduler.sms.repository.SmsServiceRepository;
import com.mcd.scheduler.sms.service.SmsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils;

@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	SmsServiceRepository  smsServiceRepository;
	
	@Autowired
	SmsEmailRepository smsEmailRepository;
	
	@Autowired
	GeoCountryRepository geoCountryRepository;
	
	@Autowired
	Environment env;
	
	@Override
	public SmsEmailTemplate fetchSmsTemplate(String templateName) {
		return	smsServiceRepository.getEmsTemplateByTempGuid(templateName);
		
	}

	@Override
	public List<SmsEmail> getSmsEmail() {		
		
		List<SmsEmail> smsEmailList = null;
		//SmsEmail smsEmail = null;
		EmailSMSJson emailSMSJson = null;
		Boolean smsStatus = Boolean.FALSE;
		Boolean mailStatus = Boolean.FALSE;
		smsEmailList =  smsEmailRepository.getIsSmsTrue(false);
		

		if (smsEmailList != null) {
			for (SmsEmail smsEmail : smsEmailList) 
			{
				try {
					
				
				//logger.info("Scheduler SMS Send SmsemailTemplateGuid = "+smsEmail.getSmsemailTemplateGuid());
				if(smsEmail.getSmsemailTemplateGuid()!=null && smsEmail.getSmsTo()!=null && smsEmail.isSms() &&  !smsEmail.isSuccess())
				{
					
					emailSMSJson = populateEmailSmsJson("91"+smsEmail.getSmsTo(),smsEmail.getSmsemailTemplateGuid(),smsEmail.getSmsBodyEn());	
						smsStatus = callPushServiceForSMS(emailSMSJson);
					if (smsStatus == true) {						
						smsEmail.setSuccess(true);
						smsEmail.setSendDate(new Date());
						smsEmailRepository.saveAndFlush(smsEmail);
					}
			    }
				
							if(smsEmail.isEmail()) 
							{		
								/*smsEmail.setEmailTo("azam4176@gmail.com");
								mailStatus = sendEmail(smsEmail.getEmailTo(), smsEmail.getEmailbodyen(), smsEmail.getEmailsubjecten(),smsEmail.getCreatedUri());
								if (mailStatus == true) {									
									smsEmail.setEmailSuccess(true);
									smsEmail.setSendDate(new Date());
									smsEmailRepository.saveAndFlush(smsEmail);
								}
								System.out.print("email send to user");*/
						   }
				} catch (Exception e) {
					continue;
				}
			}

		}	
		
		
		
		
		
		return smsEmailList;
	}

	
	public  EmailSMSJson populateEmailSmsJson(String mobileNumber, String templateGuid,String msgString)
	{
		System.out.println("inside populateEmailSmsJson templateGuid"+templateGuid);
		EmailSMSJson emailSMSJson = new EmailSMSJson();
		Map<String,String> offSmsDet =new HashMap<>();
		//String smsContent = null;
		SmsEmailTemplate smsEmailTemplate = smsServiceRepository.getEmsTemplateByTempGuid(templateGuid) ;
		if(smsEmailTemplate != null) {
			offSmsDet.put(mobileNumber, msgString); 
		    String[] sendToOfficereMobile = new String[1];
		    sendToOfficereMobile[0] = mobileNumber;
			emailSMSJson.setApplicationCode(env.getProperty("emailSMS.application.code"));
			emailSMSJson.setApplicationPassword(env.getProperty("emailSMS.application.password"));
			emailSMSJson.setSmsContent(msgString);
			emailSMSJson.setEntityId(Long.valueOf(smsEmailTemplate.getSmsemailEntityId()));
		    emailSMSJson.setTemplateId(Long.valueOf(smsEmailTemplate.getSmsemailTemplateCode()));
		    List<String> multipleTemplateId = Arrays.asList(smsEmailTemplate.getSmsemailTemplateCode());
		    emailSMSJson.setMultipleTemplateId(multipleTemplateId);
			emailSMSJson.seteMailContent(null);
			emailSMSJson.setisMailChecked(true);
			emailSMSJson.setisSMSChecked(true);
			emailSMSJson.setSenderMailId(env.getProperty("emailSMS.sender.mail.id"));
			emailSMSJson.setSenderPassword(env.getProperty("emailSMS.sender.password"));
			emailSMSJson.setMailSubject(null);
			emailSMSJson.setSendToOfficereMail(null);
			emailSMSJson.setOfficerSMSDetails(offSmsDet);
			emailSMSJson.setSendToOfficereMobile(sendToOfficereMobile);
			emailSMSJson.setSmsCallingFuncFlag("S");
			emailSMSJson.setSendToOfficerId(sendToOfficereMobile);
			emailSMSJson.setAppServiceUserName(env.getProperty("emailSMS.application.service.username"));
			emailSMSJson.setAppServicePassword(env.getProperty("emailSMS.application.service.password"));
			return emailSMSJson;
			
		}
		else
		{
			System.out.println("Template Name not found");
			 return null;
		}
	   
	}
	
	
	
	public Boolean callPushServiceForSMS(EmailSMSJson json){
		System.out.println("Calling SMS SERVICE....");
		try{
		ObjectMapper mapper = new ObjectMapper();
		//this.htlJSONPushed = mapper.writeValueAsString(json);
		//String urlParameters = htlJSONPushed;
		String urlParameters = mapper.writeValueAsString(json);
		System.out.println("JSON::: "+urlParameters);
		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		URL url1 = new URL(env.getProperty("emailSMS.sms.url"));
		HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
		conn.setUseCaches(false);
		try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
			wr.write(postData);
			
		} catch (RestClientException e) {
			System.out.println("RestClientException Exception in callPushServiceForSMS");
			//pushServiceResponse = "RestClientException";
			e.printStackTrace();
			return Boolean.FALSE;
		}catch(Exception e){
			System.out.println("Exception in callPushServiceForSMS");
			//pushServiceResponse = "Exception in writing postData";
			e.printStackTrace();
			return Boolean.FALSE;
		}
		
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
		}
		String serviceContent = getStringFromInputStream(conn.getInputStream());
		System.out.println("SMS PUSH SERVICE RESPONSE "+serviceContent);
	}
	
 	catch(Exception e){
		//pushServiceResponse = "Exception in service";
		e.printStackTrace();
		return Boolean.FALSE;
	}
	return Boolean.TRUE;
	}
	
	
	public  String getStringFromInputStream(InputStream is) {		 
		try {
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder(); 
			String line;
			try { 
				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				} 
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} 
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public boolean sendEmail(String emailId, String emailContent, String emailSubject,String encodedString) {
		
		try {
			EmailSMSJson emailSMSJson = populateEmailJson(emailId, emailContent, emailSubject,encodedString);
			callPushServiceForEmail(emailSMSJson);
			return true;
		} catch (Exception e) {
			
		}
		
		return false;
	}
	
	private EmailSMSJson populateEmailJson(String emailId, String emailContent, String emailSubject,String createdUri)
	{
		EmailSMSJson emailSMSJson = new EmailSMSJson();
		String[] sendToOfficereId = new String[1];
		sendToOfficereId[0]= "123456"; // requirement of alert service. its of no use to us. Sending this null/empty will give us error in response.
	    String[] sendToOfficereEmail = new String[1];
	    sendToOfficereEmail[0] = emailId;
		emailSMSJson.setApplicationCode(env.getProperty("emailSMS.application.code"));
		emailSMSJson.setApplicationPassword(env.getProperty("emailSMS.application.password"));
		emailSMSJson.seteMailContent(emailContent);
		emailSMSJson.setSenderMailId(env.getProperty("emailSMS.sender.mail.id"));
		emailSMSJson.setSenderPassword(env.getProperty("emailSMS.sender.password"));
		emailSMSJson.setMailSubject(emailSubject);
		emailSMSJson.setSmsCallingFuncFlag("M");
		emailSMSJson.setAppServiceUserName(env.getProperty("emailSMS.otp.application.service.username"));
		emailSMSJson.setAppServicePassword(env.getProperty("emailSMS.otp.application.service.password"));
		emailSMSJson.setSmsContent("None");
		emailSMSJson.setisMailChecked(true);
		emailSMSJson.setisSMSChecked(false);
		emailSMSJson.setSendToOfficereMail(sendToOfficereEmail);
		emailSMSJson.setSendToOfficereMobile(null);
		emailSMSJson.setSendToOfficerId(sendToOfficereId);	
		//emailSMSJson.setAttachments("");
//		JSONObject jsonObject = new JSONObject(encodedString);
//		String a=jsonObject.getString("fileName");
//		String b=jsonObject.getString("fileName");		
//		Map<String, String> map = new HashMap<String,String>();
//	map.put(a,b);
		   Map<String,String> attachments = new HashMap<>();
			byte[] fileContent = null;
			try {
				fileContent = FileUtils.readFileToByteArray(new File(createdUri));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//String encodedString = Base64.getEncoder().encodeToString(fileContent);
			String encodedString = encodeBase64(fileContent, StandardCharsets.UTF_8);
			attachments.put("email.pdf",encodedString);
			
		emailSMSJson.setAttachments(attachments);
		//emailSMSJson.setEntityId(Long.valueOf(smsEmailTemplate.getSmsemailEntityId()));
	    //emailSMSJson.setTemplateId(Long.valueOf(smsEmailTemplate.getSmsemailTemplateCode()));
	    //List<String> multipleTemplateId = Arrays.asList(smsEmailTemplate.getSmsemailTemplateCode());
	    //emailSMSJson.setMultipleTemplateId(multipleTemplateId);
		return emailSMSJson;
	
	}
	
	
	public Boolean callPushServiceForEmail(EmailSMSJson json){
		System.out.println("Calling EMAIL SERVICE....");
		try{
		ObjectMapper mapper = new ObjectMapper();
		//this.htlJSONPushed = mapper.writeValueAsString(json);
		//String urlParameters = htlJSONPushed;
		String urlParameters = mapper.writeValueAsString(json);
		System.out.println("EMAIL JSON::: "+urlParameters);
		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		URL url1 = new URL(env.getProperty("emailSMS.email.url"));
		HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
		conn.setUseCaches(false);
		try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
			wr.write(postData);
			
		} catch (RestClientException e) {
			System.out.println("RestClientException Exception in callPushServiceForSMS");
			//pushServiceResponse = "RestClientException";
			e.printStackTrace();
			return Boolean.FALSE;
		}catch(Exception e){
			System.out.println("Exception in callPushServiceForEMAIL");
			//pushServiceResponse = "Exception in writing postData";
			e.printStackTrace();
			return Boolean.FALSE;
		}
		
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			//throw new AppRuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			System.out.println("Response Code "+conn.getResponseCode());
		}
		String serviceContent = getStringFromInputStream(conn.getInputStream());
		System.out.println("EMAIL PUSH SERVICE RESPONSE "+serviceContent);
	}
	
 	catch(Exception e){
		//pushServiceResponse = "Exception in service";
		e.printStackTrace();
		return Boolean.FALSE;
	}
	return Boolean.TRUE;
	}
	
	public static String encodeBase64(byte[] bytes, Charset charset) {
		byte[] dataByte =   Base64.getUrlEncoder().encode(bytes);
		return  new String(dataByte, charset);
	}
	
}
