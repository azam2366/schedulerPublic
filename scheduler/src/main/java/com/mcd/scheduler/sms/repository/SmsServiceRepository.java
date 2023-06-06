package com.mcd.scheduler.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcd.scheduler.sms.model.SmsEmailTemplate;

@Repository
public interface SmsServiceRepository extends JpaRepository<SmsEmailTemplate, String> {
	
	   @Query("SELECT E FROM SmsEmailTemplate E WHERE E.smsemailTemplateGuid=:smsemailTemplateGuid")
       public SmsEmailTemplate getEmsTemplateByTempGuid(@Param("smsemailTemplateGuid")String  smsemailTemplateGuid);

}
