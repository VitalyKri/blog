package ru.myskill.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myskill.blog.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao extends JpaRepository<User, UUID> {

    List<User> findAllByIsDeletedFalse();

    Optional<User> findByNicknameAndVersion(String nickname, int version);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByNicknameAndIsDeletedFalse(String nickname);

}
