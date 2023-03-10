package top.mxzero.uaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/6
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(Principal principal) {
        ModelAndView mav = new ModelAndView("index");
        String name = principal.getName();
        mav.addObject("name", name);
        return mav;
    }
}
