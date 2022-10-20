package com.foch.springserver.model.benefit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenefitDao {
	
    @Autowired
    private BenefitRepository repository;

    public Benefit find(String benefitId) {
    	return repository.findById(benefitId).get();
    }
    
    public Benefit save(Benefit benefit) {
    	return repository.save(benefit);
    }
    
    public void delete(String benefitId) {
    	repository.delete(find(benefitId));
    }
    
}
