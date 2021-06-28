package com.espinoza.demo.repository;

import com.espinoza.demo.model.entity.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    @Modifying
    @Transactional
    @Query("update Contact contact set contact.active=:active, contact.address=:address, contact.city=:city, contact.contract=:contract, contact.country=:country," +
            "contact.email=:email, contact.last_name=:last_name,contact.mobile=:mobile, contact.photo=:photo, contact.state=:state,contact.salary=:salary where contact.id=:id")
    void updateContact(@Param("active") boolean active, @Param("address") String address, @Param("city") String city, @Param("contract") String contract,
                       @Param("country") String country, @Param("email") String email, @Param("last_name") String last_name, @Param("mobile") String mobile,
                       @Param("photo") String photo, @Param("state") String state, @Param("salary") String salary, @Param("id") Integer id);


}
