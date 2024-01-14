package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.data.vo.Comment;
import com.kitri.myservletboard.data.vo.Member;
import com.kitri.myservletboard.data.request.LoginRequestInfo;
import com.kitri.myservletboard.service.CommentService;
import com.kitri.myservletboard.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/comment/*")
public class CommentController extends HttpServlet {

    CommentService commentService = CommentService.getInstance();

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
        Member loginMember = (Member) request.getSession().getAttribute("loginMember");

        String view = "/view/board/";

        if (command.equals("/comment/create")) {

            Comment comment = new Comment();
            comment.setBoardId(Long.parseLong(request.getParameter("boardId")));
            comment.setMemberId(loginMember.getId());
            comment.setContent(request.getParameter("content"));

            commentService.addComment(comment);

            response.sendRedirect("/board/detail?id=" + request.getParameter("boardId"));
            return;

        } else if (command.equals("/member/login")) {

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
