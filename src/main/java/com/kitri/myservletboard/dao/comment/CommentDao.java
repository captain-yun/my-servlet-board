package com.kitri.myservletboard.dao.comment;

import com.kitri.myservletboard.data.vo.Comment;

import java.util.ArrayList;

public interface CommentDao {
    public ArrayList<Comment> getAll();
    public ArrayList<Comment> getAllByBoardId(Long id);
    public Comment getById(Long id);
    public void save(Comment comment);
    public void update(Comment comment);
    public void delete(Comment comment);
}