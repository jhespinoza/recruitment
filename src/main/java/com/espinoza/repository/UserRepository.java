package com.espinoza.repository;

import com.espinoza.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Anshad Vattapoyil on 10/06/17 10:05 PM.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query("update User user set user.userName=:userName, user.password=:password, user.active=:active where user.id=:id")
    void updateUser(@Param("userName") String userName, @Param("password") String password,
                    @Param("active") boolean active, @Param("id") Integer id);
}
