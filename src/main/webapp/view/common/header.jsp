<%@ page import="com.kitri.myservletboard.data.vo.Member" %>
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
            <li><a href="/member/joinForm">회원가입</a></li>
            <li><a href="/member/loginInfoUpdateForm">회원정보수정</a></li>
            <li><a href="<%=session.getAttribute("loginMember") != null ? "/member/logout" : "/member/loginForm"%>"><%=session.getAttribute("loginMember") != null ? "로그아웃" : "로그인"%></a></li>
        </ul>
    </nav>
    <form class="form-inline my-2 my-lg-0 ml-auto pr-5" action="/board/list">
        <select name="period">
            <option value="" ${param.period == "" ? "selected": ""}>전체기간</option>
            <option value="day" ${param.period == "day" ? "selected": ""}>1일</option>
            <option value="week" ${param.period == "week" ? "selected": ""}>1주</option>
            <option value="month" ${param.period == "month" ? "selected": ""}>1개월</option>
            <option value="halfYear" ${param.period == "halfYear" ? "selected": ""}>6개월</option>
            <option value="year" ${param.period == "year" ? "selected": ""}>1년</option>
        </select>
        &nbsp;
        <select name="type">
            <option value="title" ${param.type == "title" ? "selected": ""}>제목</option>
            <option value="writer" ${param.type == "writer" ? "selected": ""}>작성자</option>
        </select>
        &nbsp;
        <input name="keyword" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" value="${param.keyword}">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</header>
<style>
    .dropdown-toggle, button[type] { background-color: white; color: #1b1b1b; border-color: #1b1b1b}
    select { height: 60%; border-radius: 5px }
</style>