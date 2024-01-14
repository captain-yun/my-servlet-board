<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.data.vo.Board" %>
<%@ page import="com.kitri.myservletboard.data.common.Pagination" %>
<%@ page import="com.kitri.myservletboard.data.common.Search" %>
<%@ page import="com.mysql.cj.util.StringUtils" %>
<%@ page import="com.kitri.myservletboard.data.vo.Member" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="/view/common/head.jsp">
  <jsp:param name="title" value="게시판 목록"/>
</jsp:include>
<body>
  <%
    Member member = (Member) session.getAttribute("loginMember");
    Search search = (Search) request.getAttribute("search");
    String type = "title";
    String keyword = "";
    String period = "";
    if (!search.isEmpty()) {
      type = search.getType();
      keyword = search.getKeyword();
      period = search.getPeriod();
    }
    Pagination pagination = (Pagination) request.getAttribute("pagination");

    String searchParam = "";

    if (!search.isEmpty()) {
      searchParam
              += "&type=" + search.getType()
              + "&keyword=" + search.getKeyword()
              + "&period=" + search.getPeriod()
              + "&orderBy=" + search.getOrderBy()
              + "&maxRecordsPerPage=" + pagination.getMaxRecordsPerPage();
    }
  %>
  <jsp:include page="/view/common/header.jsp">
    <jsp:param name="type" value="<%=type%>"/>
    <jsp:param name="keyword" value="<%=keyword%>"/>
    <jsp:param name="period" value="<%=period%>"/>
  </jsp:include>
  <div class="d-flex justify-content-around position-relative" style="text-align: center; margin-top: 80px; margin-right: 15%; margin-left: 47%">
    <h2><b>게시판 목록</b></h2>
    <form class="form-inline my-2 my-lg-0 ml-auto pr-5 mt-5" action="/board/list">
      <input hidden type="text" name="type" value="<%=type%>"/>
      <input hidden type="text" name="keyword" value="<%=keyword%>"/>
      <input hidden type="text" name="period" value="<%=period%>"/>
      <select name="orderBy" onchange="this.form.submit()">
        <option value="latest" ${search.getOrderBy() == "latest" ? "selected": ""}>최신순</option>
        <option value="views" ${search.getOrderBy() == "views" ? "selected": ""}>조회순</option>
        <option value="accuracy" ${search.getOrderBy() == "accuracy" ? "selected": ""}>정확도순</option>
      </select>
      &nbsp
      <select name="maxRecordsPerPage" onchange="this.form.submit()">
        <option value="5" ${pagination.getMaxRecordsPerPage() == "5" ? "selected": ""}>5개씩 보기</option>
        <option value="10" ${pagination.getMaxRecordsPerPage() == "10" ? "selected": ""}>10개씩 보기</option>
        <option value="15" ${pagination.getMaxRecordsPerPage() == "15" ? "selected": ""}>15개씩 보기</option>
        <option value="20" ${pagination.getMaxRecordsPerPage() == "20" ? "selected": ""}>20개씩 보기</option>
        <option value="30" ${pagination.getMaxRecordsPerPage() == "30" ? "selected": ""}>30개씩 보기</option>
        <option value="40" ${pagination.getMaxRecordsPerPage() == "40" ? "selected": ""}>40개씩 보기</option>
        <option value="50" ${pagination.getMaxRecordsPerPage() == "50" ? "selected": ""}>50개씩 보기</option>
      </select>
    </form>
  </div>
  <div class="container class=d-flex justify-content-center">
    <div class="p-2 border-primary mb-3">
      <table class="table align-middle table-hover">
        <thead class="table-dark">
          <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">날짜</th>
            <th scope="col">조회수</th>
            <th scope="col">댓글수</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <% ArrayList<Board> boards = (ArrayList<Board>) request.getAttribute("boards");
              for (int i = 0; i < boards.size(); i++) { %>

          <tr>
            <th scope="row"><%= boards.get(i).getId() %></th>
            <td><a href="/board/detail?id=<%= boards.get(i).getId()%>"><%= boards.get(i).getTitle() %></a></td>
            <td><%= boards.get(i).getWriter() %></td>
            <td><%= boards.get(i).getDaysAfterCreated() == 0 ? "오늘" : boards.get(i).getDaysAfterCreated() + "일전" %></td>
            <td><%= boards.get(i).getViewCount() %></td>
            <td><%= boards.get(i).getCommentCount() %></td>
          </tr>

          <%
            }
          %>

        </tbody>
      </table>
      <div>
        <% if( member != null ) { %>
          <a href="/board/createForm" role="button" class="btn btn-outline-dark">글쓰기</a>
        <%}%>
      </div>
      <div class="d-flex justify-content-center">
      <nav aria-label="Page navigation example">
        <ul class="pagination pagination-sm">

          <%
            if (pagination.isHasPrev()) {
          %>
            <li class="page-item">
              <a class="page-link" href="/board/list?page=<%=pagination.getStartPageOnScreen() - 1%><%=searchParam%>" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
          <%} else {%>
            <li class="page-item disabled">
              <a class="page-link" href="/board/list?page=<%=pagination.getStartPageOnScreen() - 1%><%=searchParam%>" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
          <%}%>

          <%
            for(int i = pagination.getStartPageOnScreen(); i <= pagination.getEndPageOnScreen(); i++) {
              if(pagination.getPage() == i ) {
          %>

            <li class="page-item"><a class="page-link active" href="/board/list?page=<%=i%><%=searchParam%>"><%=i%></a></li>
          <%} else {%>
            <li class="page-item"><a class="page-link" href="/board/list?page=<%=i%><%=searchParam%>"><%=i%></a></li>
          <%}}%>

          <%
            if (pagination.isHasNext()) {
          %>
            <li class="page-item">
              <a class="page-link" href="/board/list?page=<%=pagination.getEndPageOnScreen() + 1%><%=searchParam%>">Next</a>
            </li>
          <%} else {%>
            <li class="page-item disabled">
              <a class="page-link" href="/board/list?page=<%=pagination.getEndPageOnScreen() + 1%><%=searchParam%>">Next</a>
            </li>
          <%}%>
        </ul>
      </nav>
    </div>
    </div>
  </div>
  </div>
  <div class="p-2">
    <div class="container d-flex justify-content-center">
      <footer>
        <span class="text-muted">&copy; Company's Bootstrap-board</span>
      </footer>
    </div>
  </div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</html>