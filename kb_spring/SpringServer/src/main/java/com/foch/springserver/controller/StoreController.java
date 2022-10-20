package com.foch.springserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foch.springserver.model.store.Store;
import com.foch.springserver.model.store.StoreDao;

import java.util.List;

//Store
@RestController
public class StoreController {
    //Store Service 의존성 주입
	@Autowired
    private StoreDao storeDao;

    @RequestMapping(value = "/stores", method = RequestMethod.POST)
    public List<Store> getAllStores(){
        return storeDao.getAllUser();
    }

    //스토어 정보 확인 url
    @RequestMapping(value = "/stores/{storeId}", method = RequestMethod.GET)
    public Store findStore(@PathVariable("storeId") String storeId){
        Store store = storeDao.findStore(storeId);
        return store;
    }

    //스토어 등록 url
    @RequestMapping(value = "/stores/register", method = RequestMethod.POST)
    public Store registerStore(@RequestBody Store store){
        Store finder = storeDao.findStore(store.getId());
        if(finder == null){
            storeDao.addStore(store);
            return store;
        }
        else
            return null;
    }

}
