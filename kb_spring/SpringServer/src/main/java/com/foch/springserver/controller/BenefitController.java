package com.foch.springserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.foch.springserver.model.benefit.BenefitDao;

@RestController
public class BenefitController {

	@SuppressWarnings("unused")
	@Autowired
	private BenefitDao BenefitDao;
	
	

}
