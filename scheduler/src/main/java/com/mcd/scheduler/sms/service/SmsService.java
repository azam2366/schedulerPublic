package com.mcd.scheduler.sms.service;

import com.mcd.scheduler.sms.model.SmsEmail;
import com.mcd.scheduler.sms.model.SmsEmailTemplate;
import java.util.List;

public interface SmsService {
	
	SmsEmailTemplate fetchSmsTemplate(String templateName);
	
	 public List<SmsEmail> getSmsEmail();

}
