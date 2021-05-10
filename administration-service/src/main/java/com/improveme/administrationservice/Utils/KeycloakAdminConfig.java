package com.improveme.administrationservice.Utils;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakAdminConfig {

    static Keycloak keycloak = null;
    final static String serverUrl = "http://10.0.0.2:8080/auth";
    final static String realm = "master";
    final static String clientId = "admin-cli";
    final static String userName = "bayram";
    final static String password = "bayram";



    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                    .build();


        }
        keycloak.tokenManager().getAccessToken();
        return keycloak;
    }

}
