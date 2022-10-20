package com.foch.springserver.model.user;

import com.foch.springserver.model.store.Store;
import com.foch.springserver.model.store.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserDao{
    @Autowired
    private UserRepository repository;

    //유저 정보 추가하는 함수
    //있는지 확인해보고 해야할듯(중복방지 필요)
    public void addUser(User user){
        repository.save(user);        
    }


    //유저의 패스워드를 변경하는 함수
    public boolean ChangePassword(String userId, String password)
    {
        User user = findUser(userId);

        if(user!=null){

            return true;
        }
        return false;
    }

    //유저가 상점유저에게 포인트를 지불하는 함수
    public boolean payPoint(String business_number, int pay, String id)
    {
        StoreDao dao = new StoreDao();

        Store store = dao.findStore(business_number);

        User user = findUser(id);

        User storeUser = findUser(store.getId());

        if(user != null && storeUser != null)
        {
            user.setPoint(user.getPoint()-pay);

            repository.changePoint(user.getPoint(), user.getId());

            storeUser.setPoint(storeUser.getPoint()+pay);

            repository.changePoint(storeUser.getPoint(), storeUser.getId());

            return true;
        }

        return false;
    }

    //모든 유저의 정보를 불러오는 함수
    public List<User> getAllUser(){
        List<User> users = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(users::add);
        return users;
    }



    public void delete(User user){
        repository.delete(user);
    }
    
    //userId를 통해 유저정보를 반환하는 함수
    public User findUser(String userId){
        return repository.findById(userId).orElse(null);
    }

    //user type를 변경하는 함수
    public void changeType(String userId, int type){
        repository.changeValue(userId, type);
    }
}
