package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;

import java.util.ArrayList;

public interface BoardDao {
    public ArrayList<Board> getAll();
    public Board getById(Long id);
    public void save(Board board);
    public void update(Board board);
    public void delete(Board board);
}
