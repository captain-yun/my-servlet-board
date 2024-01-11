package com.kitri.myservletboard.dao.member;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Member;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.Search;

import java.util.ArrayList;

public interface MemberDao {
    public Member getById(Long id);
    public void save(Member member);
    public void update(Member member);
    public void delete(Member member);
}
