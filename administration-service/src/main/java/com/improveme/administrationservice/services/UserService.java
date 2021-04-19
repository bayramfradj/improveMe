package com.improveme.administrationservice.services;

import com.improveme.administrationservice.Utils.KeycloakAdminConfig;
import com.improveme.administrationservice.entities.User;

import org.keycloak.admin.client.CreatedResponseUtil;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {


    public List<User> getALL(String role)
    {
        List<UserRepresentation> userRepresentationList =  KeycloakAdminConfig.getInstance()
                .realm("improveMe")
                .roles()
                .get(role)
                .getRoleUserMembers()
                .stream()
                .filter(u ->  !u.getUsername().equals("service-account-admin-app"))
                .collect(Collectors.toList());
        List<User> userList = new ArrayList<>();

        userRepresentationList.forEach(userRepresentation -> {
            User us = new User();
            us.setId(userRepresentation.getId());
            us.setEmail(userRepresentation.getEmail());
            us.setUsername(userRepresentation.getUsername());
            us.setImg(userRepresentation.getAttributes().get("img").get(0));
            us.setRole(role);
            us.setEnabled(userRepresentation.isEnabled());
            if (role.equals("COACH")){
                us.setFirstName(userRepresentation.getFirstName());
                us.setLastName(userRepresentation.getLastName());
            }else
            {
                us.setEntrepriseName(userRepresentation.getAttributes().get("name").get(0));
            }
            userList.add(us);
        });

        return userList;

    }

    public UserRepresentation addUser(User user) {
        UsersResource usersResource = KeycloakAdminConfig.getInstance().realm("improveMe").users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        Map<String,List<String>> attr = new HashMap<>();
        attr.put("img",Arrays.asList(user.getImg()));

        if (user.getRole().equals("COACH"))
        {
            kcUser.setFirstName(user.getFirstName());
            kcUser.setLastName(user.getLastName());

        }
        else
        {
            attr.put("name",Arrays.asList(user.getEntrepriseName()));

        }
        kcUser.setAttributes(attr);

        Response response =  usersResource.create(kcUser);
        String userId = CreatedResponseUtil.getCreatedId(response);
        UserResource userResource = usersResource.get(userId);




        if (user.getRole().equals("COACH")) {
            RoleRepresentation coachRealmRole = KeycloakAdminConfig.getInstance().realm("improveMe").roles()
                    .get("COACH").toRepresentation();
            userResource.roles().realmLevel().add(Arrays.asList(coachRealmRole));
        }
        else{
            RoleRepresentation entrepriseRealmRole = KeycloakAdminConfig.getInstance().realm("improveMe").roles()
                    .get("ENTREPRISE").toRepresentation();
            userResource.roles().realmLevel().add(Arrays.asList(entrepriseRealmRole));
        }

        return userResource.toRepresentation();
    }

    public void upadate(UserResource userResource, User user)
    {
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.getAttributes().clear();
        Map<String,List<String>> attr = new HashMap<>();
        attr.put("img",Arrays.asList(user.getImg()));

        if (user.getRole().equals("ENTREPRISE"))
        {

            attr.put("name",Arrays.asList(user.getEntrepriseName()));

        }
        else
        {
            userRepresentation.setFirstName(user.getFirstName());
            userRepresentation.setLastName(user.getLastName());

        }

        userRepresentation.setAttributes(attr);

        userResource.update(userRepresentation);
    }


    private  CredentialRepresentation  createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(true);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public void switchEnabled(UserResource userResource, Boolean value)
    {
            UserRepresentation userRepresentation = userResource.toRepresentation();
            userRepresentation.setEnabled(value);
            userResource.update(userRepresentation);
    }

    public Boolean checkUsername(String username)
    {
      int nbr =  KeycloakAdminConfig.getInstance()
                .realm("improveMe")
                .users()
                .list().stream().filter(u -> u.getUsername().equals(username) ).collect(Collectors.toList()).size();

      if (nbr == 0)
          return false;
      else
          return true;
    }

    public Boolean checkEmail(String email)
    {
        int nbr =  KeycloakAdminConfig.getInstance()
                .realm("improveMe")
                .users()
                .list().stream().filter(u -> u.getEmail().equals(email)).collect(Collectors.toList()).size();

        if (nbr == 0)
            return false;
        else
            return true;
    }

    public UserResource getUser(String userId)
    {
        UserResource userResource = KeycloakAdminConfig.getInstance().realm("improveMe").users().get(userId);
        return userResource;
    }

    public User getUserDto(String userId)
    {
        UserResource userResource = KeycloakAdminConfig.getInstance().realm("improveMe").users().get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();

        List<RoleRepresentation> roleRepresentationList = userResource.roles().realmLevel().listAll();

        String role = "";
        if (roleRepresentationList.stream().anyMatch(roleRepresentation -> roleRepresentation.getName().equals("COACH")))
            role = "COACH";
        else
            role = "ENTREPRISE";

        User us = new User();
        us.setId(userRepresentation.getId());
        us.setEmail(userRepresentation.getEmail());
        us.setUsername(userRepresentation.getUsername());
        us.setImg(userRepresentation.getAttributes().get("img").get(0));
        us.setRole(role);
        us.setEnabled(userRepresentation.isEnabled());

        if (role.equals("COACH") ){
            us.setFirstName(userRepresentation.getFirstName());
            us.setLastName(userRepresentation.getLastName());
        }else
        {
            us.setEntrepriseName(userRepresentation.getAttributes().get("name").get(0));
        }

        return us;
    }

    public void remove(UserResource userResource)
    {
        userResource.remove();
    }


}
