package ru.mortihead.keycloakspring.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {

    /**
     * Custom client property user_id
     * First, setup in KeyCloak: Client -> Client details ->  Dedicated scopes -> Mapper details (user_id -> id)
     * https://medium.com/@lakshminp/adding-user-attributes-to-jwt-token-in-keycloak-f3981b7df310
     * https://stackoverflow.com/questions/74873222/keycloak-with-spring-boot-based-on-roles-does-not-work-which-were-assigned-to-t
     * @param model
     * @return
     */
    @GetMapping(path = "/users")
    public String getUserInfo(HttpServletRequest request, Model model) {

        HttpServletRequest request1 = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) request1.getUserPrincipal();
//        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal) keycloakAuthenticationToken.getPrincipal();
//        String token = principal.getKeycloakSecurityContext().getIdTokenString();

        final DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String userId = "";

        OidcIdToken token = user.getIdToken();

        Map<String, Object> customClaims = token.getClaims();

        if (customClaims.containsKey("user_id")) {
            userId = String.valueOf(customClaims.get("user_id"));
        }

        if (customClaims.containsKey("realm_access")) {
            model.addAttribute("roles", customClaims.get("realm_access"));
        }



        model.addAttribute("username", user.getName());
        model.addAttribute("userID", userId);
        model.addAttribute("IDtoken", token.getTokenValue());
        return "userInfo";
    }

}
