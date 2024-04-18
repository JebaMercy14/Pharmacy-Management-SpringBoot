package com.pharmacy.Management.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.pharmacy.Management.models.Stock;

public interface StockRepository extends JpaRepositoryImplementation<Stock, Integer> {
	
	

}
