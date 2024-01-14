package com.kitri.myservletboard.dao.board;

import com.kitri.myservletboard.data.vo.Board;
import com.kitri.myservletboard.data.common.Pagination;
import com.kitri.myservletboard.data.common.Search;

import java.util.ArrayList;

public interface BoardDao {
    public ArrayList<Board> getAll();
    public ArrayList<Board> getAll(Pagination pagination);
    public ArrayList<Board> getAll(Pagination pagination, Search search);
    public Board getById(Long id);
    public void save(Board board);
    public void update(Board board);
    public void delete(Board board);
}
