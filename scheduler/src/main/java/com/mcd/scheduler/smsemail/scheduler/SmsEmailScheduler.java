package com.mcd.scheduler.smsemail.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mcd.scheduler.sms.model.SmsEmail;
import com.mcd.scheduler.sms.service.SmsService;

@Component

public class SmsEmailScheduler 
{
	@Autowired
	SmsService smsService;
	
	@Autowired
	Environment env;
	
	Logger logger = LoggerFactory.getLogger(SmsEmailScheduler.class);
	
	@Scheduled(cron="${scheduler.cron.expression}") 
	public void sendEmailSms() 
	{
		List<SmsEmail>  smsEmailList =  null;
		try 
		{
			
			System.out.println("start of SmsEmailScheduler ");	        
			logger.info("start of SmsEmailScheduler ");
			smsEmailList = smsService.getSmsEmail();		
			
			logger.debug("end of SmsEmailScheduler "+smsEmailList.size());
		}
		catch(Exception ex)
		{
			 logger.info("Problem in SmsEmailScheduler "+ex.getMessage());
			 ex.printStackTrace();
			
		}
		
	}

}
