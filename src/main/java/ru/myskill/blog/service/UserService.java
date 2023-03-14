package ru.myskill.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myskill.blog.entity.User;
import ru.myskill.blog.repository.UserDao;

import java.util.List;
import java.util.UUID;

/**
 * @author Vitaly Krivobokov
 * @Date 14.03.2023
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public List<User> getAllUsers(){
        return userDao.findAllByIsDeletedFalse();
    }

    public User getUserById(UUID id){
        return userDao.findById(id).orElseThrow();
    }

    public User getUserByNickname(String nickname){
        return userDao.findByNickname(nickname).orElseThrow();
    }

    public User saveUser(User user){
        return userDao.save(user);
    }

    public void deactivateUserById(UUID id){
        if (id ==null){
            throw new NullPointerException();
        }
        User user = userDao.findById(id).orElseThrow();
        user.setDeleted(true);
        userDao.save(user);
    }
}
