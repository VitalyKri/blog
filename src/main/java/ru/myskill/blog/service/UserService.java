package ru.myskill.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<User> getAllUsers() {
        return userDao.findAllByIsDeletedFalse();
    }

    public User getUserById(UUID id) {
        return userDao.findById(id).orElseThrow();
    }

    public User getUserByNickname(String nickname) {
        return userDao.findByNicknameAndIsDeletedFalse(nickname).orElseThrow();
    }


    public User saveUser(User user) throws Exception {

        boolean present = userDao.findByNicknameAndIsDeletedFalse(user.getNickname()).isPresent();
        if (present) {
            throw new Exception("Пользователь существует");
        }
        return userDao.save(user);
    }

    @Transactional
    public void deactivateUserByNickname(String nickname) {
        if (nickname == null) {
            throw new NullPointerException();
        }
        User user = userDao.findByNicknameAndIsDeletedFalse(nickname).orElseThrow();
        user.setDeleted(true);

        userDao.save(user);

    }
}
