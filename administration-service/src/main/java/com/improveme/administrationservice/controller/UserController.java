package com.improveme.administrationservice.controller;

import com.improveme.administrationservice.entities.User;
import com.improveme.administrationservice.services.UserService;
import org.keycloak.admin.client.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
            if (userService.checkUsername(user.getUsername()))
                return new ResponseEntity("username existe deja",HttpStatus.BAD_REQUEST);
            else
                if (userService.checkEmail(user.getEmail()))
                    return new ResponseEntity("Email existe deja",HttpStatus.BAD_REQUEST);
                else
                    return new ResponseEntity(userService.addUser(user),HttpStatus.CREATED);

    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody User user ) {
        try {
            UserResource userResource = userService.getUser(user.getId());

                    userService.upadate(userResource,user);
                    return new ResponseEntity(HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity("Utilisateur n'existe pas",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/Coaches/", method = RequestMethod.GET)
    public List<User> getCoaches()
    {
        return userService.getALL("COACH");
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") String userId)
    {
        return userService.getUserDto(userId);
    }

    @RequestMapping(value = "/Email/{mail}", method = RequestMethod.GET)
    public ResponseEntity verifMail(@PathVariable("mail") String mail)
    {

        return ResponseEntity.ok(userService.checkEmail(mail));
    }

    @RequestMapping(value = "/Username/{username}", method = RequestMethod.GET)
    public ResponseEntity verifUsername(@PathVariable("username") String username)
    {
        return ResponseEntity.ok(userService.checkUsername(username));
    }


    @RequestMapping(value = "/Entreprises/", method = RequestMethod.GET)
    public List<User> getEntreprises()
    {
        return userService.getALL("ENTREPRISE");
    }

    @RequestMapping(value = "/Enabled/", method = RequestMethod.PUT)
    public ResponseEntity setEnabled(@RequestBody User user )
    {
        try {
            UserResource userResource = userService.getUser(user.getId());
            userService.switchEnabled(userResource,true);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity("Utilisateur n'existe pas",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/Disabled/", method = RequestMethod.PUT)
    public ResponseEntity setDisabled(@RequestBody User user)
    {
        try {
            UserResource userResource = userService.getUser(user.getId());
            userService.switchEnabled(userResource,false);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity("Utilisateur n'existe pas",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("id") String userId)
    {

        try {
            UserResource userResource = userService.getUser(userId);
            userService.remove(userResource);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity("Utilisateur n'existe pas",HttpStatus.NOT_FOUND);
        }
    }
}
