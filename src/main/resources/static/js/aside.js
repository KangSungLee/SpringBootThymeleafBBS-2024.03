/*
 * Aside menu control
 */ 
$(document).ready(function() {       // 이벤트 등록
    $('#stateMsgBtn').click(function(e){
        $('#stateMsgInput').attr({'class': 'mt-2'})     // d-none을 없애고 입력창이 보이게
        $('#stateInput').val($('#stateMsg').text());    // 입력창에 stateMsg 내용이 보이게
    });
    $('#stateMsgSubmit').click(changStateMsg);      //이벤트 등록

});

function changStateMsg() {
    let stateInputVal = $('#stateInput').val();     // 사용자가 수정한 글 읽기
    $('#stateMsgInput').attr({'class': 'mt-2 d-none'}); // d-none을 생성하고 입력창이 안보이게
    $.ajax({    // Asynchronous Javascript and XML, 화면의 일부분만 바꿀 때 주로 사용
        type: 'GET',
        url: '/abbs/aside/stateMsg',
        data: {stateMsg: stateInputVal},
        success: function(result){
            console.log('state message:', stateInputVal, result);
            $('#stateMsg').html(stateInputVal);
        }
    });
}