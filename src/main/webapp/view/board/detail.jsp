<%@ page import="com.kitri.myservletboard.data.vo.Member" %>
<%@ page import="com.kitri.myservletboard.data.vo.Board" %>
<%@ page import="com.kitri.myservletboard.data.vo.Comment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="상세조회"/>
</jsp:include>
<body class="sb-nav-fixed">
<jsp:include page="/view/common/header.jsp"/>
<%
    Member loginMember = (Member) session.getAttribute("loginMember");
    Board board = (Board) request.getAttribute("board");
    ArrayList<Comment> comments = board.getComments();
%>
<main class="mt-5 pt-5">
    <div class="container-fluid px-4 ">

        <div class="card mb-4 w-50 mx-auto">
            <div>
                <h2 class="mt-3" style="text-align: center;"><b>게시판 상세</b></h2>
                <hr class="mb-0">
            </div>
            <div class="d-flex flex-column" style="height: 500px;">
                <div class="p-2 border-bottom border-dark-subtle d-flex flex-row-reverse">
                    <div class="pd opacity-75 bg-info-subtle border border-dark rounded-pill"><small>조회수 : ${board.getViewCount()}</small></div>
                    &nbsp
                    <div class="pd opacity-75 bg-success-subtle border border-dark rounded-pill"><small>댓글수 : ${board.getCommentCount()}</small></div>
                    <div class="d-flex flex-row flex-fill">
                        <div class="pd opacity-75 border border-dark rounded-pill">#${board.getId()}</div>
                    </div>
                </div>
                <div class="p-2 border-bottom">
                    <b>${board.getTitle()}</b>
                </div>
                <div class="p-2 border-bottom">
                    <b>저자 :</b> ${board.getWriter()}
                </div>
                <div class="p-2 border-bottom">
                    <b>등록일시 :</b> ${board.getPassedTime()}
                </div>
                <div class="m-3 h-75">
                    <textarea class="h-100 form-control bg-white" id="content" name="content"
                              disabled>${board.getContent()}</textarea>
                </div>
                <div class="d-flex flex-row-reverse mb-3 mr-3">
                    &nbsp
                    &nbsp
                    <%
                        if(loginMember != null) {
                        if(loginMember.getId().equals(board.getMemberId())) {
                    { %>
                    <a href="/board/delete?id=${board.getId()}" class="btn btn-secondary btn-sm" onclick="return confirm('삭제하시겠습니까?')"><small>삭제하기</small></a>
                                &nbsp
                                <a href="/board/updateForm?id=${board.getId()}" class="btn btn-secondary btn-sm"><small>수정하기</small></a>
                                &nbsp
                    <%}}}%>

                    <a href="/board/list" class="btn btn-secondary btn-sm"><small>목록으로</small></a>
                    &nbsp
                </div>
            </div>
        </div>
    </div>
</main>
<div class="comment">
    <div class="container-fluid px-4 ">
        <div class="card mb-4 w-50 mx-auto">
            <div >
                <h5 class="mt-3" style="text-align: center;"><b>댓글</b></h5>
                <hr class="mb-0">
            </div>
            <div class="list-group">
            <%
                for (int i = 0; i < comments.size(); i++) {
            %>

                <div class="border border-light list-group-item list-group-item-action" aria-current="true">
                    <div class="d-flex w-100 justify-content-between">
                        <h6 class="mb-1"><b><%=comments.get(i).getMember().getName() %></b></h6>
                        <small><%=comments.get(i).getPassedTime()%></small>
                    </div>
                    <p class="mb-1"><%=comments.get(i).getContent()%></p>

                    <div class="d-inline d-flex flex-row-reverse">
                        <% if(comments.get(i).getMember().getId().equals(loginMember.getId())) {%>
                        <button type="submit" class="badge rounded-pill text-bg-light" onclick="this.form.submit()">삭제</button>
                        &nbsp
                        <button type="submit" class="badge rounded-pill text-bg-light" onclick="this.form.submit()">수정</button>
                        <%}%>
                        <small class="flex-fill">답글 쓰기</small>
                    </div>

                </div>

            <%}%>
            </div>

            <% if (loginMember == null) { %>
            <div class="list-group">
                <div class="list-group-item list-group-item-action" aria-current="true">
                    <div class="d-flex w-100 justify-content-between">
                        <h6 class="mb-1"><b>댓글을 작성하시려면 <a href='/member/login'>로그인</a> 하세요</b></h6>
                    </div>
                </div>
            </div>
            <%} else {%>
            <div class="list-group">
                <div class="list-group-item list-group-item-action" aria-current="true">
                    <div class="d-flex w-100 justify-content-between">
                        <h6 class="mb-2"><b><%= loginMember.getName() %></b></h6>
                    </div>
                    <form class="mb-3" action="/comment/create">
                        <input name="boardId" type="text" value="${board.getId()}" hidden/>
                        <textarea name="content" class="form-control" rows="3" placeholder="댓글을 남겨보세요"></textarea>
                        <div class="mt-2 d-flex justify-content-end" role="group">
                            <button type="submit" class="badge rounded-pill text-bg-light" onclick="updateComment(event.type)">등록</button>
                        </div>
                    </form>
                </div>
            </div>
            <%}%>
        </div>
    </div>
</div>
</body>
<style>
    .pd {
        padding-left: 5px;
        padding-right: 5px;
    }
</style>
<script>
    function updateComment(e) {
        alert(e);
    }
</script>
</html>