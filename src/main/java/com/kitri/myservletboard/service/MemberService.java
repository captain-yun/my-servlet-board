package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.board.BoardJdbcDao;
import com.kitri.myservletboard.dao.member.MemberDao;
import com.kitri.myservletboard.dao.member.MemberJdbcDao;
import com.kitri.myservletboard.data.Member;

import java.util.ArrayList;

public class MemberService {
    MemberDao memberDao = MemberJdbcDao.getInstance();

    private MemberService() {};
    private static final MemberService instance = new MemberService();
    public static MemberService getInstance() {
        return instance;
    }
    public Member getBoard(Long id) {
        return memberDao.getById(id);
    }
    public void addBoard(Member member) {
        memberDao.save(member);
    }
    public void updateBoard(Member member) {
        memberDao.update(member);
    }
    public void deleteBoard(Member member) {
        memberDao.delete(member);
    }
}
