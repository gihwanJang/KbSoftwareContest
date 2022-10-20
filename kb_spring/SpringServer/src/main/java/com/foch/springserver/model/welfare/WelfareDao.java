package com.foch.springserver.model.welfare;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class WelfareDao {
	
    @Autowired
    private WelfareRepository repository;

    public Welfare findWelfare(String id) {
    	return repository.findById(id).orElse(null);
    }
    
    public Welfare addWelfare(Welfare welfare) {
    	return repository.save(welfare);
    }
    
    public void delete(String benefitId) {
    	repository.delete(findWelfare(benefitId));
    }
    
    public List<Welfare> getAllUser(){
        List<Welfare> welfares = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(welfares::add);
        return welfares;
    }
}
