package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.comment.CommentDao;
import com.kitri.myservletboard.dao.comment.CommentJdbcDao;
import com.kitri.myservletboard.data.vo.Comment;

import java.util.ArrayList;

public class CommentService {

    CommentDao commentDao = CommentJdbcDao.getInstance();
    private CommentService() {};
    private static final CommentService instance = new CommentService();

    public static CommentService getInstance() {
        return instance;
    }
    public Comment getComment(Long id) {
        return commentDao.getById(id);
    }
    public ArrayList<Comment> getCommentsByBoardId(Long boardId) {
        return commentDao.getAllByBoardId(boardId);
    }
    public void addComment(Comment comment) {
        this.commentDao.save(comment);
    }
    public void updateComment(Comment comment) {
        this.commentDao.update(comment);
    }
    public void deleteComment(Comment comment) {
        this.commentDao.delete(comment);
    }

}