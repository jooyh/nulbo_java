<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<section class='contents'>
	<div id="logo-box" class="login-visual">
	    <button type="button" class="btn-back" onclick="fn_moveLoginPage()" style="display:none">뒤로가기</button>
	    <!-- <div class="logo"></div> -->
	</div>
	<div id="login-box" class="login-content">
	    <h2 class="login-tit">Hello</h2>
	    <p class="login-txt">Sign in your accaunt</p>
	    <div class="login-form-box">
	        <!-- <form action="/login" method="POST"> -->
	            <div class="login-area">
	                <div class="input-box">
	                    <input type="email" name="email" id="login_email" placeholder="E-MAIL" value="admin@a.com"/>
	                    <button type="button" class="btn-clear">입력글 지우기</button>
	                </div>
	                <div class="input-box">
	                    <input type="password" name="pw" id="login_pw" placeholder="PASS" value="admin"/>
	                    <button type="button" class="btn-clear">입력글 지우기</button>
	                </div>
	                <div class="join"><a href="javascript:fn_moveJoinPage();">join</a></div>
	                <div class="btn-area">
	                    <button type="submit" class="btn-basic" onclick="fn_login()">Login</button>
	                </div>
	            </div>
	        <!-- </form> -->
	    </div>
	</div>

	<div id="join-box" class="login-content" style="display:none;">
	    <div class="profile-sumnail">
	        <input type="file" />
	        <div class="image-box">
	            <img src="/resources/images/img_user.png" alt="프로필">
	        </div>
	    </div>
	    <div class="login-form-box">
	        <!-- <form action="/login" method="POST"> -->
	            <div class="login-area">
	                <div class="input-btn-wrap">
	                    <div class="input-box">
	                        <input type="email" name="email" id ="reg-email" doubleCheck = "N" placeholder="E-MAIL" title="이메일"/>
	                        <button type="button" class="btn-clear">입력글 지우기</button>
	                    </div>
	                    <div class="btn-area">
	                        <button type="button" class="btn-basic" onclick ="fn_check()">중복확인</button>
	                    </div>
	                </div>
	                <p id="email-notice" class="notice-txt" style="display:none;"></p>
	                <div class="input-box">
	                    <input type="password" name="pw" id ="reg-pw" placeholder="PASS" title="비밀번호"/>
	                    <button type="button" class="btn-clear">입력글 지우기</button>
	                </div>
	                <div class="input-box">
	                    <input type="password" name="re_pw" id ="reg-re-pw" placeholder="PASS" title="비밀번호"/>
	                    <button type="button" class="btn-clear">입력글 지우기</button>
	                </div>
	                <p id="pw-notice" class="notice-txt" style="display:none;"></p>
	                <div class="input-box">
	                    <input type="text" name="name" id="reg-name" placeholder="NAME" title="닉네임"/>
	                    <button type="button" class="btn-clear">입력글 지우기</button>
	                </div>
	                <div class="check-agree">
	                    <input type="checkbox" id="check01"/>
	                    <label for="check01">
	                        개인정보 활용에 동의하시겠습니까?
	                    </label>
	                </div>
	                <div class="btn-area">
	                    <button type="submit" class="btn-basic" onclick="fn_join()">Join</button>
	                </div>
	            </div>
	        <!-- </form> -->
	    </div>
	</div>
</section>
<script src="/resources/pagejs/login.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>