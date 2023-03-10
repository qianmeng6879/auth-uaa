package top.mxzero.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class CurrentUSerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUSerController.class);

    @RequestMapping("/user")
    public Object currentUser(Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;

            Map<String, Object> attributes = token.getPrincipal().getAttributes();
            return attributes;
        }
        LOGGER.info(principal.getClass().getName());
        return principal;
    }
}
