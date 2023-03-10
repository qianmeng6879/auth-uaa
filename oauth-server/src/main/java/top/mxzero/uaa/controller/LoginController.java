package top.mxzero.uaa.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/4
 */
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/sso_login")
    public ModelAndView ssoLoginPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("sso_login");
        return mav;
    }
}
