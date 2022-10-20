package com.foch.springserver.controller; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foch.springserver.model.user.User;
import com.foch.springserver.model.user.UserDao;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User findUser(@PathVariable("userId") String userId){
        User user = userDao.findUser(userId);
        return user;
    }

    @RequestMapping("/user/sign-up")
    public User userSignUp(@RequestBody User user){
        User finder = userDao.findUser(user.getId());
        if(finder == null){
            userDao.addUser(user);
            return user;
        }
        else
            return null;
    }

    
	@PostMapping("/user/changeType")
	public boolean userChangeType(@RequestParam("userId")String userId,@RequestParam("type") int type) {
        User changer = userDao.findUser(userId);
        if(changer == null)
            return false;
        else{
            userDao.changeType(userId, type);
            return true;
        }
    }
}
