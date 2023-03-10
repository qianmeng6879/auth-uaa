package top.mxzero.uaa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mxzero.uaa.entity.Member;
import top.mxzero.uaa.exception.ServiceException;
import top.mxzero.uaa.mapper.MemberMapper;
import top.mxzero.uaa.service.MemberService;

import java.util.Date;
import java.util.List;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/5
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Member get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Member> list() {
        return mapper.selectList(null);
    }

    @Override
    public Member getByUsername(String username) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>().eq("username", username);
        return mapper.selectOne(queryWrapper);
    }

    @Override
    public Member getByEmail(String email) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>().eq("email", email);
        return mapper.selectOne(queryWrapper);
    }

    @Override
    public Member getByPhone(String phone) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>().eq("phone", phone);
        return mapper.selectOne(queryWrapper);
    }

    @Transactional
    @Override
    public boolean save(Member member) {
        if (mapper.exists(new QueryWrapper<Member>().eq("email", member.getEmail()))) {
            throw new ServiceException("邮箱已被注册");
        }

        if (mapper.exists(new QueryWrapper<Member>().eq("username", member.getUsername()))) {
            throw new ServiceException("用户名已存在");
        }

        member.setCreateTime(new Date());
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        return mapper.insert(member) > 0;
    }

    @Transactional
    @Override
    public boolean update(Member member) {
        member.setUpdateTime(new Date());
        return mapper.updateById(member) > 0;
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        return mapper.deleteById(id) > 0;
    }
}
