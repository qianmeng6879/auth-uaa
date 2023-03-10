package top.mxzero.uaa.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.mxzero.uaa.entity.Member;
import top.mxzero.uaa.service.MemberService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/5
 */
public class UserDetailsServiceImpl implements UserDetailsService, ApplicationContextAware {
    private MemberService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = service.getByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException("用户名错误");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        if (member.getUsername().equals("admin")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        return new User(member.getUsername(), member.getPassword(), grantedAuthorities);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.service = applicationContext.getBean(MemberService.class);
    }
}
