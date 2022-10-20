package com.foch.springserver.model.manager;


import com.foch.springserver.model.user.User;
import com.foch.springserver.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerDao {

    @Autowired
    private ManagerRepository repository;

    @Autowired
    private UserRepository userRepository;

    public boolean donateUser(int pay, String userId){

        User user = userRepository.findById(userId).orElse(null);
        if(user!= null){
            userRepository.changePoint(user.getPoint()-pay, userId);

            Manager manager = repository.getPoint();
            int point = manager.getAccPoint();
            repository.insertManager(pay+point, manager.getSequence_num());
            return true;
        }

        return false;
    }
}
