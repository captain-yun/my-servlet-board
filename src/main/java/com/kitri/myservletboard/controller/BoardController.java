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
import java.time.LocalDateTime;
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

        request.setCharacterEncoding("UTF-8");
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

            // 데이터를 읽고 ( ok )
            // 등록 시키면 된다

            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String writer = request.getParameter("writer");

            Board board = new Board(null, title, content, writer, LocalDateTime.now(), 0, 0);
            boardService.addBoard(board);

            response.sendRedirect("/board/list");
            return;

        } else if (command.equals("/board/updateForm")) {

            Board board = boardService.getBoard(Long.parseLong(request.getParameter("id")));
            request.setAttribute("board", board);
            view += "updateForm.jsp";

        } else if (command.equals("/board/update")) {

            // 수정폼에서 보낸 데이터를 읽는다.
            // 수정하려는 데이터를 수정한다.
            Long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            boardService.updateBoard(new Board(id, title, content, null, null, 0, 0));

             response.sendRedirect("/board/list");
             return;

        } else if (command.equals("/board/delete")) {

            Board board = boardService.getBoard(Long.parseLong(request.getParameter("id")));
            boardService.deleteBoard(board);

            response.sendRedirect("/board/list");
            return;

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
