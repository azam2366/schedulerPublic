package com.mcd.scheduler.mst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcd.scheduler.mst.model.Geo_country;

import java.util.List;

@Repository
public interface GeoCountryRepository extends JpaRepository<Geo_country, String>{

	public List<Geo_country> findAll();
}
