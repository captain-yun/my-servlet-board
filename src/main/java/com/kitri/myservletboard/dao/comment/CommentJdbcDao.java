package com.kitri.myservletboard.dao.comment;

import com.kitri.myservletboard.dao.board.BoardJdbcDao;
import com.kitri.myservletboard.data.vo.Comment;
import com.kitri.myservletboard.data.vo.Member;
import com.kitri.myservletboard.util.QueryUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentJdbcDao implements CommentDao{
    private static final CommentJdbcDao instance = new CommentJdbcDao();
    public static CommentJdbcDao getInstance() {
        return instance;
    }
    private CommentJdbcDao(){};
    public Connection connectDB() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/my_servlet_board";
            String user = "root";
            String pwd = "1234";
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    @Override
    public ArrayList<Comment> getAll() {
        return null;
    }

    @Override
    public ArrayList<Comment> getAllByBoardId(Long boardId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Comment> comments = new ArrayList<>();

        try {
            connection = connectDB();
            String sql = QueryUtil.buildGetCommentsByBoardId();
            ps = connection.prepareStatement(sql);
            ps.setLong(1, boardId);
            rs = ps.executeQuery();

            while(rs.next()) {
                Comment comment = new Comment();
                Member member = new Member();

                comment.setId(rs.getLong("id"));
                comment.setBoardId(rs.getLong("board_id"));
                comment.setReplyId(rs.getLong("reply_id"));
                comment.setContent(rs.getString("content"));
                comment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                member.setId(rs.getLong("m_id"));
                member.setName(rs.getString("name"));
                member.setLoginId(rs.getString("login_id"));

                comment.setMember(member);
                comments.add(comment);
            }

        } catch (Exception e) {

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return comments;
    }

    @Override
    public Comment getById(Long id) {
        return null;
    }

    @Override
    public void save(Comment comment) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();

            String sql = "INSERT INTO comment (board_id, member_id, content) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, comment.getBoardId());
            ps.setLong(2, comment.getMemberId());
            ps.setString(3, comment.getContent());
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void delete(Comment comment) {

    }
}
