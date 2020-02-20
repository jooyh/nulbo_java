var inputBox = document.getElementsByClassName("input-box");
var input;
// input 감싸고있는 inputBox 반복문
for (var i = 0; i < inputBox.length; i++) {
    input = inputBox[i].getElementsByTagName("input")[0];
    // input 입력시 이벤트
    input.addEventListener('input', function(){
        if(event.target.value) event.target.nextElementSibling.style.display = "block"; // input 버튼 노출
        else event.target.nextElementSibling.style.display = "none"; // input 버튼 미노출
    })
    // input btn 클릭시 이벤트
    inputBox[i].getElementsByClassName("btn-clear")[0].addEventListener("click",function(){
        event.target.previousElementSibling.value = ""; // 입력창 지우기
        event.target.style.display = "none"; // 버튼 미노출
    })
}

var inputBox = document.getElementsByClassName("input-box");
var input;
for (var i = 0; i < inputBox.length; i++) {
    input = inputBox[i].getElementsByTagName("input")[0];
    input.addEventListener('input', function(){
        if(event.target.value) {
            event.target.nextElementSibling.style.display = "block";
        }
    })
}

/* 헤더, 네비 제거 */
document.getElementsByTagName("header")[0].style.display = "none";
document.getElementsByTagName("nav")[0].style.display = "none";
document.getElementsByClassName("container")[0].style.padding = 0;

/* 회원가입페이지 이동 함수 */
function fn_moveJoinPage() {
    document.getElementById("logo-box").classList.add("h-animate");
    document.getElementById("login-box").style.display = "none";
    document.getElementById("join-box").style.display = "block";
    $(".login-visual .btn-back").show();
}
/* 로그인페이지 이동 함수 */
function fn_moveLoginPage() {
    document.getElementById("logo-box").classList.remove("h-animate");
    document.getElementById("login-box").style.display = "block";
    document.getElementById("join-box").style.display = "none";
    $(".login-visual .btn-back").hide();
}

function fn_login () {
    var emailVal = document.getElementById("login_email").value;
    var pwVal = document.getElementById("login_pw").value;
    var loginData = {};
    loginData.userEmail = emailVal;
    loginData.userPw = pwVal;
    transaction(loginData,{
         url : '/user/login.do'
        ,success : function(result){
            console.log('login_result: ', result);
            sessionStorage.setItem("user",JSON.stringify(result[0]));
            location.href='/timeline';
        }
    });
}

function fn_check() {
    var input = document.getElementById("reg-email");
    var data = {};
    data.userEmail = input.value;
    transaction(data,{
        url : '/user/emailCheck.do'
        ,success : function(result){
        	console.error(result);
        }
    	,error : function(e){
    		console.log(e);
    	}
    });
}

function fn_join() {
     if(!fn_inputValCheck()) return;
    var pwVal = document.getElementById("reg-pw").value;
    var rePwVal = document.getElementById("reg-re-pw").value;
    var userInfo = {};
    userInfo.email = document.getElementById("reg-email").value;
    userInfo.pw = rePwVal;
    userInfo.name = document.getElementsByName("name")[0].value;
    console.log('userInfo: ', userInfo);
    if($("#join-box input").parent().hasClass("error")) {
        return
    }
    transaction(userInfo,{
        url : '/user/join.do'
        ,success : function(result){
            alert("회원가입이 완료되었습니다.");
            fn_moveLoginPage();
        }
    });
}
function fn_inputValCheck() {
    var flag = true;
    var input = $("#join-box input").not("input[type='file']")
    input.each(function(i, el){
        // input에 값이 없을 때
        if($(el).val() == "") {
            $(el).parent(".input-box").addClass("error");
            alert ($(el).attr("title")+" 을(를) 입력해 주세요");
            flag = false;
        // input에 값이 있을 때
        } else {
            // 값이 있지만 이메일중복체크를 안한경우
            if( $(el).attr("id") == "reg-email" && $(el).attr("doubleCheck") != "Y") {
                $(el).parent(".input-box").addClass("error");
                // alert ("이메일 중복체크를 해주세요");
                $("#email-notice").show().text("중복 체크를 해주세요");
                flag = false;
            } else {
                $(el).parent(".input-box").removeClass("error");
            }
        }
    })

    /* 비밀번호 두입력란이 일치 한지, 조건에 맞는지 확인 */
    var pwVal = $("#reg-pw").val();
    var rePwVal = $("#reg-re-pw").val();
    var pwcheck = /^[a-zA-Z0-9]{6,15}$/;
    $("#pw-notice").hide();
    // 값이 없거나 두 입력란이 일치하지 않는 경우
    if((!pwVal || !rePwVal) || pwVal != rePwVal) {
        $("#pw-notice").show().text("비밀번호가 일치하지 않습니다.");
        $("#reg-re-pw").parent().addClass("error");
        $("#reg-pw").parent().addClass("error");
        flag = false;
    // 비밀번호 조건에 맞지 않는 경우
    } else if(!pwcheck.test(pwVal)) {
        $("#pw-notice").show().text("비밀번호는 숫자, 영문 조합으로 6~15자리를 사용해야 합니다.");
        $("#reg-re-pw").parent().addClass("error");
        $("#reg-pw").parent().addClass("error");
        return false;
    // 두 입력란에 값이 있고 일치하고 조건에 맞는 경우
    } else {
        $("#reg-re-pw").parent().removeClass("error");
        $("#reg-pw").parent().removeClass("error");
    }
    return flag;
}