<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 수정 - Bootstrap</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style>
        * {
            font-family: Arial, Helvetica, sans-serif;
        }

        .input-form {
            max-width: 680px;

            margin-top: 100px;
            padding: 32px;

            background: #fff;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            border-radius: 10px;
            -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
            -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
            box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
        }

        .footer {
            font-size: 12px;
        }

        h4 {
            text-align: center;
        }

        #wrap {
            width: 100%;
            /* margin-top = header height */
            margin-top: 60px;
        }

        /* Navigation bar */
        header {
            /* for sticky header */
            position: fixed;
            top: 0;

            width: 100%;
            height: 60px;
            z-index: 2000;
            background-color: #fff;
            box-shadow: 0 2px 2px rgba(0, 0, 0, 0.05), 0 1px 0 rgba(0, 0, 0, 0.05);
        }

        .logo {
            display: inline-block;
            height: 36px;
            margin: 12px 0 12px 25px;
        }

        .logo>img {
            height: 36px;
        }

        nav {
            float: right;

        }

        .nav-items {
            margin-right: 20px;
            text-decoration: none;
        }

        .nav-items>li {
            display: inline-block;
            /* 가로정렬 */
        }

        .nav-items>li>a {
            /* for Vertical Centering */
            line-height: 60px;
            padding: 0 30px;
            color: rgba(0, 0, 0, 0.4);
        }

        .nav-items>li>a:hover {
            color: rgba(0, 0, 0, 0.8);
        }
    </style>
</head>

<body>

<header>
    <a class="logo" href="/board/list"><img src="https://poiemaweb.com/img/logo.png"></a>
    <nav>
        <ul class="nav-items">
            <li><a href="/board/list">게시글목록</a></li>
            <li><a href="/board/createForm">게시글등록</a></li>
            <li><a href="/board/updateForm">게시글수정</a></li>
            <li><a href="/view/member/join.jsp">회원가입</a></li>
            <li><a href="/view/member/registration.jsp">회원정보수정</a></li>
            <li><a href="/view/member/login.jsp">로그인</a></li>
        </ul>
    </nav>
</header>


    <div class="container">
        <div class="input-form-backgroud row">
            <div class="input-form col-md-12 mx-auto">
                <h4 class="mb-3"><b>게시물 수정</b></h4>
                <hr>
                <br>
                <form class="validation-form" novalidate>

                    <div class="mb-3">
                        <label for="address">제목</label>
                        <input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요" required>
                        <div class="invalid-feedback">
                            제목을 입력해주세요.
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="name">작성자</label>
                            <input type="text" class="form-control" id="name" placeholder="" value="" required>
                            <div class="invalid-feedback">
                                작성자를 입력해주세요.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="name">비밀번호</label>
                            <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력해주세요"
                                value="" required>
                            <div class="invalid-feedback">
                                비밀번호를 입력해주세요.
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="contents" class="form-label">내용</label>
                        <textarea name="contents" class="form-control" cols="30" rows="5"
                            placeholder="내용을 입력해주세요"></textarea>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <button class="btn btn-secondary btn-block" type="submit">게시물 등록하기</button>
                        </div>
                        <div class="col-md-6 mb-3">
                            <button class="btn btn-secondary btn-block" type="submit">취소</button>
                        </div>
                    </div>
            </div>

            </form>
        </div>
    </div>
    <div class="p-2">
        <div class="footer">
            <footer>
                <span class="text-muted d-flex justify-content-center">Copyright &copy; 2024 Bootstrap board</span>
            </footer>
        </div>
    </div>
    </div>
    <script>
        window.addEventListener('load', () => {
            const forms = document.getElementsByClassName('validation-form');

            Array.prototype.filter.call(forms, (form) => {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    </script>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3b