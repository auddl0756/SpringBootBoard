var idUnique=false;
var nicknameUnique=false;
var pwdCoincedence=false;

function memberIdSend() {
    // var id = $("#memberId").val();

    $.ajax({
        url: "/register/duplication/id",
        type: "POST",
        data: $('#memberId').val(),
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            // alert(result);
            if(result==="OK"){
                alert("사용할 수 있는 아이디입니다.");
                idUnique=true;
                // $('#regForm').submit();
            }else if(result==="BAD_REQUEST"){
                alert("이미 사용 중인 아이디입니다.");
                idUnique=false;
                // location.href="/register";
            }else{
                alert("매우 심각한 오류. 아이디 중복 검사 실패");
            }
        },
        error: function (error) {
            alert("ajax 통신에서 오류 발생");
            canSubmit=false;
        }
    });
}

function memberNicknameSend() {
    // var id = $("#memberId").val();

    $.ajax({
        url: "/register/duplication/nickname",
        type: "POST",
        data: $('#memberNickname').val(),
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            // alert(result);
            if(result==="OK"){
                alert("사용할 수 있는 닉네임입니다.");
                nicknameUnique=true;
                // $('#regForm').submit();
            }else if(result==="BAD_REQUEST"){
                alert("이미 사용 중인 닉네임입니다.");
                nicknameUnique=false;
                // location.href="/register";
            }else{
                alert("매우 심각한 오류. 닉네임 중복 검사 실패");
            }
        },
        error: function (error) {
            alert("ajax 통신에서 오류 발생");
            canSubmit=false;
        }
    });

}

function submitForm(){
    if($('#memberPwd').val()===$('#memberPwdConfirm').val()){
        // alert($('#memberPwd').val()+" "+$('#memberPwdConfirm').val());
        pwdCoincedence=true;
    }else{
        alert("비밀번호를 다시 입력해 주세요.");
        pwdCoincedence=false;
    }

    if(idUnique===false){
        alert("아이디 중복 검사를 해주세요.");
    }

    if(nicknameUnique===false){
        alert("닉네임 중복 검사를 해주세요.");
    }

    var canSubmit = idUnique && pwdCoincedence && nicknameUnique;
    // alert(idUnique+" "+pwdCoincedence+" "+canSubmit);

    if(canSubmit===true){
        var confirmResult=confirm("이 양식으로 제출하시겠습니까?");
        if(confirmResult===true){
            $('#regForm').submit();
        }
    }

}
