package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.Search;

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
