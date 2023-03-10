package top.mxzero.uaa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.mxzero.uaa.dto.UserParam;
import top.mxzero.uaa.entity.Member;
import top.mxzero.uaa.exception.ServiceException;
import top.mxzero.uaa.service.MemberService;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/6
 */

@Controller
public class RegisterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    private MemberService memberService;

    @RequestMapping("/register.html")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView registerAction(UserParam param, Model model) {
        ModelAndView mav = new ModelAndView("register");
        if (!"1024".equals(param.getCode())) {
            mav.addObject("error", "验证码错误");
            return mav;
        }

        Member member = new Member();
        member.setUsername(param.getUsername());
        member.setPassword(param.getPassword());
        member.setEmail(param.getEmail());

        try {
            memberService.save(member);
            mav.setViewName("redirect:/sso_login");
            return mav;
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                mav.addObject("error", e.getMessage());
            } else {
                mav.addObject("error", "系统异常");
            }
            return mav;
        }
    }
}
