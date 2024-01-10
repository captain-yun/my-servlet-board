<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="d-flex flex-row">
    <a class="logo" href="/board/list">
        <span class="pb-5 material-symbols-outlined">
            clear_day
        </span>
    </a>
    <nav>
        <ul class="nav-items">
            <li><a href="/board/list">게시글목록</a></li>
            <li><a href="/view/member/join.jsp">회원가입</a></li>
            <li><a href="/view/member/registration.jsp">회원정보수정</a></li>
            <li><a href="/view/member/login.jsp">로그인</a></li>
        </ul>
    </nav>
    <form class="form-inline my-2 my-lg-0 ml-auto pr-5" action="/board/list?">
    <select name="type" class="bootstrap-select">
        <option value="title" ${param.type == "title" ? "selected": ""}>제목</option>
        <option value="writer" ${param.type == "writer" ? "selected": ""}>작성자</option>
    </select>
        <input name="keyword" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" value="${param.keyword}">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</header>
<style>
    .dropdown-toggle, button[type] { background-color: white; color: #1b1b1b; border-color: #1b1b1b}
</style>