package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BoardJdbcDao implements BoardDao {
    private static final BoardJdbcDao instance = new BoardJdbcDao();

    public static BoardJdbcDao getInstance() {
        return instance;
    }

    public Connection connDB() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/my_servlet_board?serverTimezone=UTC";
            String user = "root";
            String pwd = "1234";

            conn = DriverManager.getConnection(url, user, pwd);
            if(conn != null) {
                System.out.println("연결 성공");
            }
        } catch(Exception e) {
            System.out.println("연결 오류 발생!");
            e.printStackTrace();
        }
        return conn;
    }
    @Override
    public ArrayList<Board> getAll() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ArrayList<Board> boards = new ArrayList<Board>();

        try {
            conn = connDB();
            String query = "select * from Board";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);

            while(resultSet.next()) { // 결과 세트에서 한 행씩 처리
                // 한 행(회원 1명당) 처리
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String writer = resultSet.getString("writer");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                int viewCount = resultSet.getInt("view_count");
                int commentCount = resultSet.getInt("comment_count");
                // 한 행 정보 가져와 memberVO에 Setter 이용하여 저장
                Board board = new Board();
                board.setId(id);
                board.setTitle(title);
                board.setContent(content);
                board.setWriter(writer);
                board.setCreatedAt(createdAt);
                board.setViewCount(viewCount);
                board.setCommentCount(commentCount);
                // 각 memberVO를 ArrayList에 저장
                boards.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return boards;
    }

    @Override
    public Board getById(Long id) {
        return null;
    }

    @Override
    public void save(Board board) {

    }

    @Override
    public void update(Board board) {

    }

    @Override
    public void delete(Board board) {

    }
}
