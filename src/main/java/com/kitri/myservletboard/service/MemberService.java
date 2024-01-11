package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.member.MemberDao;
import com.kitri.myservletboard.dao.member.MemberJdbcDao;
import com.kitri.myservletboard.data.Member;

public class MemberService {
    MemberDao memberDao = MemberJdbcDao.getInstance();

    private MemberService() {};
    private static final MemberService instance = new MemberService();
    public static MemberService getInstance() {
        return instance;
    }
    public Member getMember(Long id) {
        return memberDao.getById(id);
    }
    public boolean joinMember(Member member) {
        if ( memberDao.checkDuplicatedLoginId(member.getLoginId()) ) {
            System.out.println("중복됨");
            return false;
        }
        memberDao.save(member);
        return true;
    }
    public void updateMember(Member member) {
        memberDao.update(member);
    }
    public void deleteMember(Member member) {
        memberDao.delete(member);
    }
}
