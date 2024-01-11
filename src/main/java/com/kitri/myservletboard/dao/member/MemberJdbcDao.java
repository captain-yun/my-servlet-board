package com.kitri.myservletboard.dao.member;

import com.kitri.myservletboard.dao.board.BoardJdbcDao;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Member;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.Search;
import com.kitri.myservletboard.util.QueryUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberJdbcDao implements MemberDao {

    private static final MemberJdbcDao instance = new MemberJdbcDao();

    public static MemberJdbcDao getInstance() {
        return instance;
    }

    private MemberJdbcDao(){};

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
    public Member getById(Long id_) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Member member = new Member();

        try {
            conn = connectDB();
            String query = "select * from Board where id=?";
            ps = conn.prepareStatement(query);
            ps.setLong(1, id_);

            rs = ps.executeQuery();

            while(rs.next()) {
                String id = rs.getString("id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

                member.setId(rs.getString("id"));
                member.setPassword(rs.getString("password"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
//                member.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return member;
    }

    @Override
    public void save(Member member) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();

            String sql = "INSERT INTO board (title, content, writer) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);

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
    public void update(Member member) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            String query = "UPDATE board SET title = ?, content = ? WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
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
    public void delete(Member member) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = connectDB();
            String query = "DELETE FROM board WHERE id = ?";
            ps = conn.prepareStatement(query);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
