package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.service.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
    BoardService boardService = BoardService.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>요청을 잘 응답받았습니다!</h1>");
        
        // URL을 파싱해서 어떤 요청인지 파악
        out.println(request.getRequestURI());

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        String view = "/view/board/";

        if (command.equals("/board/list")) {
            ArrayList<Board> boards = boardService.getBoards(); // 게시판 리스트
            request.setAttribute("boards", boards);
            view += "list.jsp";
        } else if (command.equals("/board/createForm")) {
            view += "createForm.jsp";
        } else if (command.equals("/board/create")) {

        } else if (command.equals("/board/updateForm")) {
            view += "updateForm.jsp";
        } else if (command.equals("/board/update")) {

        } else if (command.equals("/board/delete")) {

        } else if (command.contains("/board/detail")) {
            // id에 해당하는 게시판 하나를 가져오면 된다.
            // /board/detail?id=3
            Long id = Long.parseLong(request.getParameter("id"));
            Board board = boardService.getBoard(id);
            // board 데이터를 detail.jsp 에 전달하기 위해 어딘가에 담아줘야한다.
            request.setAttribute("board", board);

            view += "detail.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
