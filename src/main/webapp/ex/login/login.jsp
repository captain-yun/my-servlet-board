<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-01-15
  Time: 오후 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String id = (String) session.getAttribute("id");

    if(session.getAttribute("id") != null) { %>
    <h2>안녕하세요! ${sessionScope.id} 님 접속중입니다.</h2>
    <a href="/ex/logout">로그아웃</a>
<% } else { %>
    <form method="post" action="login">
        <label for="id">아이디: </label>
        <input type="text" name="id" id="id"><br>

        <label for="pw">비밀번호: </label>
        <input type="password" name="pw" id="pw"><br>

        <input type="submit" value="로그인">
    </form>
<div class="notified">${requestScope.loginFailed ? "로그인이 실패하였습니다. 아이디 혹은 비밀번호를 정확하게 입력해주세요." : ""}</div>
<% } %>
</body>
<script>
    setTimeout(() => {
        document.querySelector(".notified").hidden = true;
    }, 2000)
</script>
</html>
