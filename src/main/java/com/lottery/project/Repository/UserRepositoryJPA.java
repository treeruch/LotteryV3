package com.lottery.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lottery.project.entity.User;


@Repository
public interface UserRepositoryJPA extends JpaRepository<User, Long>  {

}
