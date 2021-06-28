package com.espinoza.demo.repository;

import com.espinoza.demo.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    @Modifying
    @Transactional
    @Query("update User user set user.userName=:userName, user.password=:password, user.active=:active where user.id=:id")
    void updateUser(@Param("userName") String userName, @Param("password") String password,
                    @Param("active") boolean active, @Param("id") Integer id);

}
