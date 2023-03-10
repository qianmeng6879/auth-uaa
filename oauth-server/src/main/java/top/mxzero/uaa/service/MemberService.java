package top.mxzero.uaa.service;

import top.mxzero.uaa.entity.Member;

import java.util.List;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/5
 */
public interface MemberService {
    Member get(Long id);

    List<Member> list();

    Member getByUsername(String username);

    Member getByEmail(String email);

    Member getByPhone(String phone);

    boolean save(Member member);

    boolean update(Member member);

    boolean remove(Long id);

}
