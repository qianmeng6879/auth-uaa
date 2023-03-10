package top.mxzero.uaa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.mxzero.uaa.dto.MemberDTO;
import top.mxzero.uaa.entity.Member;
import top.mxzero.uaa.service.MemberService;

import java.security.Principal;

@Controller
public class AuthorizeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizeController.class);

    @Autowired
    private MemberService memberService;

    @ResponseBody
    @RequestMapping("/me")
    public Object currentMe(Principal principal) {
        LOGGER.info("type:{}", principal.getClass().getName());
        LOGGER.info("data:{}", principal);
        String username = null;
        if (principal instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
            username = token.getName();
        } else if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
            username = token.getName();
        }

        LOGGER.info("username:{}", username);

        if (username == null) {
            return null;
        }

        Member member = memberService.getByUsername(username);
        MemberDTO memberDTO = new MemberDTO();
        if (member == null) {
            memberDTO.setUsername(username);
            return memberDTO;
        }


        BeanUtils.copyProperties(member, memberDTO);
        return memberDTO;
    }

    @ResponseBody
    @RequestMapping("/user/info")
    public Object currentUser(Principal principal) {
        return principal;
    }

    @RequestMapping("/test")
    public String testPage() {
        return "test";
    }
}
