package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.data.*;
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

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

    MemberService memberService = MemberService.getInstance();

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

        String view = "/view/member/";

        if (command.equals("/member/join")) {

            String id = request.getParameter("loginId");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String email = request.getParameter("email");

            Member member = new Member(id, password, name, email);

            if (! memberService.joinMember(member)) {
                view += "joinFail.jsp";
            } else {
                request.setAttribute("member", member);
                view += "joinSuccess.jsp";
            }

        } else if (command.equals("/member/login")) {

            String id = request.getParameter("loginId");
            String password = request.getParameter("password");

            Member loginMember
                    = memberService.authenticateLoginInfo(new LoginRequestInfo(id, password));

            if(loginMember == null) {
                response.sendRedirect("/member/loginForm");
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("loginMember", loginMember);
            response.sendRedirect("/board/list");
            return;

        } else if (command.equals("/member/logout")) {

            HttpSession session = request.getSession();
            Object member = session.getAttribute("loginMember");
            if (member != null) {
                session.invalidate();
            }

            response.sendRedirect("/member/loginForm");
            return;

        } else if (command.equals("/member/joinForm")) {
            view += "joinForm.jsp";
        } else if (command.equals("/member/loginForm")) {
            view += "loginForm.jsp";
        } else if (command.equals("/member/loginInfoUpdateForm")) {
            view += "loginInfoUpdateForm.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
