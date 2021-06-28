package com.espinoza.demo.controller;

import com.espinoza.demo.model.ListResponse;
import com.espinoza.demo.model.ObjectResponse;
import com.espinoza.demo.model.entity.User;
import com.espinoza.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    /**
     * Get all users
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ListResponse getAllUsers(HttpServletResponse http) {
        ListResponse response = new ListResponse();
        response.setMessage("Successfully Retrieved");
        response.setStatusCode(http.getStatus());
        List<User> users = (List<User>) userRepository.findAll();
        response.setData(users);
        return response;
    }

    /**
     * Create new user
     *
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ListResponse saveUser(@RequestBody final User user, HttpServletResponse http) {
        userRepository.save(user);
        ListResponse response = new ListResponse();
        response.setMessage("Successfully Created");
        response.setStatusCode(http.getStatus());
        List<User> users = (List<User>) userRepository.findAll();
        response.setData(users);
        return response;
    }

    /**
     * Get a specific user
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ObjectResponse getUser(@PathVariable("id") Integer userId, HttpServletResponse http) {
        ObjectResponse response = new ObjectResponse();
        if (userRepository.existsById(userId)) {
            response.setMessage("Successfully Retrieved");
            response.setStatusCode(http.getStatus());
            response.setData(userRepository.findById(userId));
        } else {
            response.setMessage("Record not found");
            response.setStatusCode(404);
            response.setData(null);
        }
        return response;
    }

    /**
     * Find and update a user
     *
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ObjectResponse updateUser(@RequestBody final User user, HttpServletResponse http) {
        ObjectResponse response = new ObjectResponse();
        if (userRepository.existsById(user.getId())) {
            userRepository.updateUser(user.getUserName(), user.getPassword(), user.getActive(), user.getId());
            response.setMessage("Successfully Updated");
            response.setStatusCode(http.getStatus());
            response.setData(userRepository.findById(user.getId()));
        } else {
            response.setMessage("Record not found");
            response.setStatusCode(404);
            response.setData(null);
        }
        return response;
    }

    /**
     * Delete a user
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ListResponse deleteUser(@PathVariable("id") Integer userId, HttpServletResponse http) {
        ListResponse response = new ListResponse();
        if(userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            response.setStatusCode(http.getStatus());
            response.setMessage("Successfully Deleted");
        } else {
            response.setStatusCode(404);
            response.setMessage("Record not found");
        }
        List<User> users = (List<User>) userRepository.findAll();
        response.setData(users);
        return response;
    }
}
