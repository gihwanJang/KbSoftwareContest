package com.foch.springserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foch.springserver.model.welfare.Welfare;
import com.foch.springserver.model.welfare.WelfareDao;

@RestController
public class WelfareController {

	@Autowired
	private WelfareDao welfareDao;

    @RequestMapping(value = "/welfares", method = RequestMethod.POST)
    public List<Welfare> getAllWelfares(){
        return welfareDao.getAllUser();
    }

    @RequestMapping(value = "/welfares/{welfareId}", method = RequestMethod.GET)
    public Welfare findWelfare(@PathVariable("welfareId") String welfareId){
        Welfare welfare = welfareDao.findWelfare(welfareId);
        return welfare;
    }

    @RequestMapping(value = "/welfares/register", method = RequestMethod.POST)
    public Welfare registerWelfare(@RequestBody Welfare welfare){
        Welfare finder = welfareDao.findWelfare(welfare.getId());
        if(finder == null){
            welfareDao.addWelfare(welfare);
            return welfare;
        }
        else
            return null;
    }


}
