package com.mcd.scheduler.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcd.scheduler.sms.model.SmsEmail;
import java.util.List;

@Repository
public interface SmsEmailRepository extends JpaRepository<SmsEmail, String>{

	@Query("SELECT E FROM SmsEmail E WHERE E.isSuccess=:isSms")
	public List<SmsEmail> getIsSmsTrue(@Param("isSms")Boolean isSms);
	
	
}
