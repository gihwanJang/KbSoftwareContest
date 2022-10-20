package com.foch.springserver.controller;

import com.foch.springserver.model.manager.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
public class DonateContoller {

    @Autowired
    private ManagerDao donateDAO;



    @RequestMapping(value = "/donate/{userId}", method = RequestMethod.POST)
    public boolean donateUser(@PathVariable("userId")String userId, @Param("point")int point){
        return donateDAO.donateUser(point, userId);
    }



    
}
