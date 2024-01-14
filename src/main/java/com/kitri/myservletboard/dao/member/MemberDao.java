package com.kitri.myservletboard.dao.member;

import com.kitri.myservletboard.data.vo.Member;

public interface MemberDao {
    public Member getById(Long id);
    public Member getByLoginId(String loginId);
    public void save(Member member);
    public void update(Member member);
    public void delete(Member member);
    public boolean checkDuplicatedLoginId(String loginId);
}
