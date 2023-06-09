package ru.myskill.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myskill.blog.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Vitaly Krivobokov
 * @Date 14.03.2023
 */
public interface UserDao extends JpaRepository<User, UUID> {
    List<User> findAllByIsDeletedFalse();
    Optional<User> findByNicknameAndVersion(String nickname,int version);

    Optional<User> findByNickname(String nickname);
}
