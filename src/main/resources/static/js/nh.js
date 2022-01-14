// 의료진소개 textarea 자동높이조절
$(function(){
	$('textarea[name=txtarea]').each(function(){
		var tareaHeight = $(this).prop('scrollHeight');
		$(this).css('height', tareaHeight);
	});
});

// 의료장비 선택파일 화면에 표시
$(function(){
    $('#mdevice-img').change(function(){
		$('.dfile').empty();
        if(this.files && this.files[0]){
            let reader = new FileReader();
            reader.onload = function(){
                var str = '<img src="' + reader.result + '">';
                $(str).appendTo('.dfile');
            };
            reader.readAsDataURL(this.files[0]);    
        }
    });
});

// 문의목록에서 정렬 라디오버튼 선택시
$(function(){
    $('input[type=radio][name=csCategory]').change(function(){
		let sort = this.value;
    	location.href="/admin/cs/list?page=1&sort=" + sort;
    });
});

// 검색버튼 클릭시 라디오버튼 체크해제
$(function(){
	$('#searchBtn').click(function(){
	    $('input[type=radio][id=radioSort]').prop('checked', false);
	});
});

// 장비목록에서 정렬 라디오버튼 선택시
$(function(){
    $('input[type=radio][name=deviceCategory]').change(function(){
		let sort = this.value;
    	location.href="/admin/hospital/deviceList?page=1&sort=" + sort;
    });
});

// 예약목록에서 정렬 라디오버튼 선택시
$(function(){
    $('input[type=radio][name=reserveCategory]').change(function(){
		let sort = this.value;
    	location.href="/admin/reservation/list?page=1&sort=" + sort;
    });
});

// 회원목록에서 정렬 라디오버튼 선택시
$(function(){
    $('input[type=radio][name=memCategory]').change(function(){
		let sort = this.value;
    	location.href="/admin/member/list?page=1&sort=" + sort;
    });
});

// 관리자계정 추가
$(function(){
	$('#checkId').on('click', function(){
		// id 중복체크
		var regExp = RegExp(/(?=.*[a-z])(?=.*[0-9]).{8,10}$/);
		var userId = $('#userId');
		// 아이디 중복 시 false, 아이디 사용 가능 시 true 값을 가지는 변수
		var isUsable = false;
		
		if(!regExp.test(userId.val())){
				alert("아이디는 영문 소문자와 숫자를 포함하여 최소 8자리 이상이어야 합니다.");
				userId.focus();
		} else{
			// csrf 오류 방지
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$.ajax({
					url : "/member/idCheck",
					type : "post",
					//
					beforeSend : function(xhr){
						xhr.setRequestHeader(header, token);
					},
					data : {userId : userId.val() },
					success : function (result) {
						console.log(result);
						if(result == "fail"){
							alert("사용할 수 없는 아이디 입니다.");
							userId.focus();
						}else{
							if(confirm("사용 가능한 아이디 입니다. 사용하시겠습니까?")){
								// 더 이상 아이디 입력 공간을 바꿀 수 없도록 처리
								userId.attr('readonly', true);
								userPwd.focus();
								/* 사용 가능한 아이디라는 flag */
								isUsable = true;
							} else{
								// 다시 아이디 입력 공간을 바꿀 수 있도록 처리
								userId.attr('readonly', false);   //attr 제이쿼리 메소드
								userId.focus();
								/* 사용 불가능한 아이디라는 flag */
								isUsable = false;
							}
						}
						
						/* 아이디 중복 체크 후 중복이 아니며 사용하겠다고 선택한 경우
						버튼의 disabled 속성 제거 */
						if(isUsable){
							$("#joinBtn").removeAttr("disabled");	
							$("#joinBtn").removeClass("btn-disabled");
							$("#joinBtn").addClass("btn-b");		
						}else{
							$("#joinBtn").attr("disabled", true);
						}
						
					},
					error : function (e) {
						console.log(e);
					}
			});
		}
	});
	
	// 비밀번호
	var userPwd = $('#userPwd');
	var userPwd2 = $('#userPwd2');
	var pwdresult = $('#pwdresult');
	var pwdresult2 = $('#pwdresult2');	
	var pwdcheck = false;
	var pwdcheck2 = false;
	
	userPwd.on('blur', function(){
	   let regExp = RegExp(/(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,15}$/);
	   console.log(userPwd.val());
	   
	    if(regExp.test(userPwd.val())) {
	        pwdresult.removeClass('error-text');
	        pwdresult.html('');
/*	        userPwd2.focus();*/
	        pwdcheck = true;
	    }
	    else {
	        pwdresult.addClass('error-text');
	        pwdresult.html('영문 대소문자 숫자 특수문자 포함 8~15 자리로 입력');
	        userPwd.focus();
	        pwdcheck = false;
	    }
	    if(userPwd.val() == "") {
/*	        pwdresult.html('');*/
	        pwdcheck = false;
	    }
	});
	
	// 비밀번호 확인
	// 일치하지 않으면 class속성에 error-text 추가하기 & innerHTML '비밀번호가 일치하지 않습니다.'
    userPwd2.on('blur', function(){
	    if(userPwd2.val() == userPwd.val()) {
	        pwdresult2.removeClass('error-text');
	        pwdresult2.html('');
	        userName.focus();
	        pwdcheck2 = true;
	    }
	    else {
	        pwdresult2.addClass('error-text');
	        pwdresult2.html('비밀번호가 일치하지 않습니다.');
	        userPwd2.focus();
	        pwdcheck2 = false;
	    }
	    if(userPwd2.val() == "") {
	        pwdresult2.html('');
	        pwdcheck2 = false;
	    }
	});
	
	
    // 이름	
    var userName = $('#userName');
    var nameresult = $('#nameresult');  
    var nameCheck = false;
   
    userName.on('blur', function(){
	    let regExp = RegExp(/^[가-힣]{2,}$/);
	    if(regExp.test(userName.val())) {
	        nameresult.removeClass('error-text');
	        nameresult.html('');
	        phone.focus();
	        nameCheck = true;
	    }
	    else {
	        nameresult.addClass('error-text');
	        nameresult.html('한글로 2~10 자리로 입력하세요.');
	        userName.focus();
	        nameCheck = false;
	    }
	    if(userName.val() == "") {
	        nameresult.html('');
	        nameCheck = false;
	    }
	});
	
	// 전화번호
	var phone = $('#phone');
	var phoneresult = $('#phoneresult');
	var phoneCheck = false;
	
	phone.on('blur', function(){
	    var regExp = RegExp(/^[0-9]{1,}$/);
	    if(regExp.test(phone.val())) {
	        phoneresult.removeClass('error-text');
	        phoneresult.html('');
	        email.focus();
	        phoneCheck = true;
	    }
	    else {
	        phoneresult.addClass('error-text');
	        phoneresult.html('숫자만 입력해주세요.');
	        phone.focus();
	        phoneCheck = false;
	    }
	    if(this.value == "") {
	        phoneresult.html('');
	        phoneCheck = false;
	    }
	});
	
	// 이메일
	var email = $('#email');
	var eamilresult = $('#eamilresult');
	var emailCheck = false;
	
	email.on('blur', function(){
	      var regExp = RegExp(/^([a-zA-z0-9])+@([a-zA-Z])+\.[a-zA-Z]{2,3}/);
	    if(regExp.test(email.val())) {
	        emailresult.removeClass('error-text');
	        emailresult.html('');
	        emailCheck = true;
	    }
	    else {
	        emailresult.addClass('error-text');
	        emailresult.html('이메일 형식을 확인하세요.');
	        emailCheck = false;
	    }
	    if(email.val() == "") {
	        emailresult.html('');
	        emailCheck = false;
	    }
	});

	
	function validate(){
			if(isUsable == false){
				console.log("회원가입 불가");
				userId.focus();
				return false;
			}
			if(pwdCheck == false){
				console.log("회원가입 불가");
				userPwd.focus();
				return false;
			}
			if(pwdcheck2 == false){
				console.log("회원가입 불가");
				userPwd2.focus();
				return false;
			}
			if(nameCheck == false){
				console.log("회원가입 불가");
				userName.focus();
				return false;
			}
			if(phoneCheck == false){
				console.log("회원가입 불가");
				phone.focus();
				return false;
			}
			if(emailCheck == false){
				console.log("회원가입 불가");
				email.focus();
				return false;
			}
	}
});











