package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardMemoryDao implements BoardDao {
    private static final BoardMemoryDao instance = new BoardMemoryDao();

    public static BoardMemoryDao getInstance() {
        return instance;
    }
    ArrayList<Board> memoryBoardDB = new ArrayList<>();

    private BoardMemoryDao() {
        memoryBoardDB.add(new Board(1L, "첫번째 글입니다!!!", "반갑습니다1~~~", "김성실", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(2L, "두번째 글입니다!!!", "반갑습니다2~~~", "오시현", LocalDateTime.now(), 3, 0));
        memoryBoardDB.add(new Board(3L, "세번째 글입니다!!!", "반갑습니다3~~~", "김동현", LocalDateTime.now(), 40, 1));
        memoryBoardDB.add(new Board(4L, "네번째 글입니다!!!", "반갑습니다4~~~", "박현오", LocalDateTime.now(), 12, 0));
        memoryBoardDB.add(new Board(5L, "다섯번째 글입니다!!!", "반갑습니다5~~~", "박준혁", LocalDateTime.now(), 33, 3));
        memoryBoardDB.add(new Board(6L, "여섯번째 글입니다!!!", "반갑습니다6~~~", "주나영", LocalDateTime.now(), 15, 1));
        memoryBoardDB.add(new Board(7L, "일곱번째 글입니다!!!", "반갑습니다7~~~", "한민선", LocalDateTime.now(), 7, 4));
        memoryBoardDB.add(new Board(8L, "여덟번째 글입니다!!!", "반갑습니다8~~~", "송준형", LocalDateTime.now(), 19, 2));
        memoryBoardDB.add(new Board(9L, "나홉번째 글입니다!!!", "반갑습니다9~~~", "김미성", LocalDateTime.now(), 20, 5));
        memoryBoardDB.add(new Board(10L, "열번째 글입니다!!!", "반갑습니다10~~~", "박세한", LocalDateTime.now(), 21, 6));
    }

    @Override
    public ArrayList<Board> getAll(Pagination pagination) {
        return memoryBoardDB;
    }


    @Override
    public ArrayList<Board> getAll() {
        return memoryBoardDB;
    }

    @Override
    public Board getById(Long id) {
        return memoryBoardDB.stream().filter(board -> {
            return board.getId() == id;
        }).findFirst().get();
    }

    @Override
    public void save(Board board) {
        // id 자동생성 로직 (단, id가 기존의 id와 중복되지 않도록)
        Long id = 0L;
        boolean flag = false;
        while (!flag) {
            flag = true;
            id++; // 1씩 증가 2, 3, ... 10, 11
            for (Board board_ : memoryBoardDB) {
                if (id == board_.getId()) {
                    // 중복
                    flag = false;
                    break;
                };
            }
        }
        board.setId(id);
        memoryBoardDB.add(board);
    }

    @Override
    public void update(Board board) {
        Board board_ = getById(board.getId());
        board_.setTitle(board.getTitle());
        board_.setContent(board.getContent());
//        memoryBoardDB.remove(board_);
//        memoryBoardDB.add(board);
    }

    @Override
    public void delete(Board board) {
        memoryBoardDB.remove(board);
    }
}
