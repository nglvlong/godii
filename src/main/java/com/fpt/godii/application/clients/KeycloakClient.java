package com.fpt.godii.application.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "keycloak")
public interface KeycloakClient {
    @PostMapping(value = "/admin/realms/${KEYCLOAK_REALM}/users")
    void postUser(@RequestHeader String authorization, @RequestBody Map<String, Object> user);

    @PutMapping(value = "/admin/realms/${KEYCLOAK_REALM}/users/{id}")
    void updateUser(@RequestHeader String authorization, @PathVariable("id") String id,
                    @RequestBody Map<String, Object> user);

    @DeleteMapping(value = "/admin/realms/${KEYCLOAK_REALM}/users/{id}")
    void deleteUser(@RequestHeader String authorization, @PathVariable("id") String id);

    @GetMapping(value = "/admin/realms/${KEYCLOAK_REALM}/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<LinkedHashMap<String, Object>> getUserByUserName(@RequestHeader String authorization, @RequestParam String username);

    @GetMapping(value = "/admin/realms/${KEYCLOAK_REALM}/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<LinkedHashMap<String, Object>> getUserByEmail(@RequestHeader String authorization, @RequestParam String email);

//    @PostMapping(value = "/realms/${KEYCLOAK_REALM}/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    ClientToken getToken(@RequestBody AuthenKeyCloak authenKeyCloak);
//
//    @PutMapping(value = "/admin/realms/${KEYCLOAK_REALM}/users/{id}/reset-password", consumes = MediaType.APPLICATION_JSON_VALUE)
//    Object putChangePassword(@PathVariable("id") UUID id, @RequestBody CredentialsDTO data,
//                             @RequestHeader String authorization);
//
//    @PostMapping(value = "/realms/${KEYCLOAK_REALM}/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    Optional<ClientToken> login(PostLoginRequest loginRequest);
//
//    @PostMapping(value = "/realms/${KEYCLOAK_REALM}/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    ClientToken getTokenPortal(PostClient postClient);
//
//    @GetMapping(value = "/realms/${KEYCLOAK_REALM}/protocol/openid-connect/userinfo", consumes = MediaType.APPLICATION_JSON_VALUE)
//    UserKeyCloakInfoDTO getUserKeyCloakInfo(@RequestHeader String authorization);

    @PostMapping(value = "/admin/realms/${KEYCLOAK_REALM}/users/{userId}/logout")
    String logOut(@PathVariable(value = "userId") String userId, @RequestHeader String authorization);
}
