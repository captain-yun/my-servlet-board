package com.kitri.myservletboard.dao.member;

import com.kitri.myservletboard.data.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

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

                member.setLoginId(rs.getString("id"));
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
    public Member getByLoginId(String loginId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Member member = new Member();

        try {
            conn = connectDB();
            String query = "select * from member where login_id=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, loginId);

            rs = ps.executeQuery();

            while(rs.next()) {

                member.setId(rs.getString("id"));
                member.setLoginId(rs.getString("login_id"));
                member.setPassword(rs.getString("password"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

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

            String sql = "INSERT INTO member (login_id, password, name, email) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, member.getLoginId());
            ps.setString(2, member.getPassword());
            ps.setString(3, member.getName());
            ps.setString(4, member.getEmail());

            int i = ps.executeUpdate();
            System.out.println();

        } catch (Exception e) {
            System.out.println("중복된 키");
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
            String query = "UPDATE member SET login_id = ?, password = ?, name = ?, email = ? WHERE login_id = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, member.getLoginId());
            ps.setString(2, member.getPassword());
            ps.setString(3, member.getName());
            ps.setString(4, member.getEmail());

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
            String query = "DELETE FROM member WHERE login_id = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, member.getLoginId());
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
    public boolean checkDuplicatedLoginId(String loginId) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {

            conn = connectDB();
            String query = "SELECT * FROM member WHERE login_id = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, loginId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }

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
        return result;
    }
}
