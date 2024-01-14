package com.kitri.myservletboard.data.vo;

import com.kitri.myservletboard.util.TimeUtil;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

public class Board {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private int viewCount;
    private int commentCount;
    private Long memberId;
    private ArrayList<Comment> comments = new ArrayList<>();
    public Board() {
    }
    public Board(Long id, String title, String content, String writer, LocalDateTime createdAt, int viewCount, int commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
    }

    public Board(Long id, String title, String content, String writer, LocalDateTime createdAt, int viewCount, int commentCount, Long memberId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.memberId = memberId;
    }

    public int getDaysAfterCreated() {
        LocalDateTime from = this.getCreatedAt() ;
        LocalDateTime to = LocalDateTime.now() ;
        Period period = Period.between(from.toLocalDate(), to.toLocalDate());
        return period.getDays();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getPassedTime() {
        return TimeUtil.getPassedTime(this.getCreatedAt());
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
