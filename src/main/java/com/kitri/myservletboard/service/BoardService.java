package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.board.BoardDao;
import com.kitri.myservletboard.dao.board.BoardJdbcDao;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.Search;

import java.util.ArrayList;

public class BoardService {
//    BoardDao boardDao = BoardMemoryDao.getInstance();
    BoardDao boardDao = BoardJdbcDao.getInstance();

    private BoardService() {};
    private static final BoardService instance = new BoardService();

    public static BoardService getInstance() {
        return instance;
    }
    public Board getBoard(Long id) {
        return boardDao.getById(id);
    }
    public ArrayList<Board> getBoards() {
        return boardDao.getAll();
    }
    public ArrayList<Board> getBoards(Pagination pagination) {

        pagination.setTotalRecords(((BoardJdbcDao)boardDao).count()); // totalRecords의 값 계산
        pagination.calcPagination();

        return boardDao.getAll(pagination);
    }
    public ArrayList<Board> getBoards(Pagination pagination, Search search) {

        pagination.setTotalRecords(((BoardJdbcDao)boardDao).count(search)); // totalRecords의 값 계산
        pagination.calcPagination();

        return boardDao.getAll(pagination, search);
    }
    public void addBoard(Board board) {
        boardDao.save(board);
    }
    public void updateBoard(Board board) {
        boardDao.update(board);
    }
    public void deleteBoard(Board board) {
        boardDao.delete(board);
    }

}
