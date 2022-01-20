/**
 * 
 */

 
 //  "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다
 $(function() { $("#postcodify_search_button").postcodifyPopUp(); });
 
 
 		let userId = document.getElementById("userId");
        let userPwd = document.getElementById("userPwd");
        let userPwd2 = document.getElementById("userPwd2");
        let userName = document.getElementById("userName");
        let phone = document.getElementById("phone");
        let email = document.getElementById("email");
        let petName = document.getElementById("petName");
        let petAge = document.getElementById("petAge");
        let address = document.getElementById("address");
        
        
        let userPwdresult = document.getElementById("pwdCheck_div");
        let userPwd2result = document.getElementById("pwdCheck_div2");
        let userNameresult = document.getElementById("userNameCheck_div");
        let phoneresult = document.getElementById("phoneCheck_div");
        let emailresult = document.getElementById("emailCheck_div");
        let petNameresult = document.getElementById("petNameCheck_div");
        let petAgeresult = document.getElementById("petAgeCheck_div");
        let addressresult = document.getElementById("addressCheck_div");
        
        
        let pwdCheck = false;
        let pwd2Check = false;
        let nameCheck = false;
        let phoneCheck = false;
        let emailCheck = false;
        let petNameCheck = false;
        let petAgeCheck = false;
        let addressCheck = false; 
        
		
		// 트림처리 후 못넘어가게 trim 공백 제거 함수  = ""빈문자열로 확인
		
		
		$("#idCheck").on('click', function () {
			var userId = $("[name=id]");
			let regExp = /(?=.*[a-z])(?=.*[0-9]).{8,10}/;
			// 아이디 중복 시 false, 아이디 사용 가능 시 true 값을 가지는 변수
			var isUsable = false;
			console.log(userId);
			if(!regExp.test(userId.val())){
				alert("아이디는 영문 소문자와 숫자를 포함하여 최소 8자리 이상이어야 합니다.");
				userId.focus();
			}else{
				//
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
					},
					error : function (e) {
						console.log(e);
					}
				});
			}
			
		});
		
		
		userPwd.onblur = function(){
            let regExp = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}/;
            if(regExp.test(this.value)) {
                userPwdresult.classList.remove('invalid');
                userPwdresult.innerHTML = '&nbsp;';
                pwdCheck = true;
            }
            else {
                userPwdresult.classList.add('invalid');
                userPwdresult.innerHTML = '영문 대소문자 숫자 특수문자 포함 8~15 자리로 입력';
                pwdCheck = false;
            }
            if(this.value == "") {
                userPwdresult.innerHTML = '&nbsp;';
                pwdCheck = false;
            }
        };
        userPwd.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                userPwdresult.innerHTML = '&nbsp;';
                pwdCheck = false;
            }
        }

        userPwd2.onblur = function(){
            if(this.value == userPwd.value) {
                userPwd2result.classList.remove('invalid');
                userPwd2result.innerHTML = '&nbsp;';
                pwd2Check = true;
            }
            else {
                userPwd2result.classList.add('invalid');
                userPwd2result.innerHTML = '비밀번호가 일치하지 않습니다.';
                pwd2Check = false;
            }
            if(this.value == "") {
                userPwd2result.innerHTML = '&nbsp;';
                pwd2Check = false;
            }
        };
        userPwd2.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                userPwd2result.innerHTML = '&nbsp;';
                pwd2Check = false;
            }
        }
   
        userName.onblur = function(){
            let regExp = /^[가-힣]{2,}$/;
            if(regExp.test(this.value)) {
                userNameresult.classList.remove('invalid');
                userNameresult.innerHTML = '&nbsp;';
                nameCheck = true;
            }
            else {
                userNameresult.classList.add('invalid');
                userNameresult.innerHTML = '한글로 2~10 자리로 입력하세요.';
                nameCheck = false;
            }
            if(this.value == "") {
                userNameresult.innerHTML = '&nbsp;';
                nameCheck = false;
            }
        };
        userName.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                userNameresult.innerHTML = '&nbsp;';
                nameCheck = false;
            }
        }

        email.onblur = function(){
            let regExp = /^([a-zA-z0-9])+@([a-zA-Z])+\.[a-zA-Z]{2,3}/;
            if(regExp.test(this.value)) {
                emailresult.classList.remove('invalid');
                emailresult.innerHTML = '&nbsp;';
                emailCheck = true;
            }
            else {
                emailresult.classList.add('invalid');
                emailresult.innerHTML = '이메일 형식을 확인하세요.';
                emailCheck = false;
            }
            if(this.value == "") {
                emailresult.innerHTML = '&nbsp;';
                emailCheck = false;
            }
        };
        email.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                emailresult.innerHTML = '&nbsp;';
                emailCheck = false;
            }
        }

        phone.onblur = function(){
            let regExp = /^01[0-9]{1,}$/;
            if(regExp.test(this.value)) {
                phoneresult.classList.remove('invalid');
                phoneresult.innerHTML = '&nbsp;';
                phoneCheck = true;
            }
            else {
                phoneresult.classList.add('invalid');
                phoneresult.innerHTML = '전화번호 형식을 확인하세요.';
                phoneCheck = false;
            }
            if(this.value == "") {
                phoneresult.innerHTML = '&nbsp;';
                phoneCheck = false;
            }
        };

        phone.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                phoneresult.innerHTML = '&nbsp;';
                phoneCheck = false;
            }
        }
		
        
        petName.onblur = function(){
            let regExp = /^[가-힣]{1,}$/;
            if(regExp.test(this.value)) {
            	 petNameresult.classList.remove('invalid');
            	 petNameresult.innerHTML = '&nbsp;';
            	 petNameCheck = true;
            }
            else {
            	 petNameresult.classList.add('invalid');
            	 petNameresult.innerHTML = '한글로 1~10 자리로 입력하세요.';
            	 petNameCheck = false;
            }
            if(this.value == "") {
            	 petNameresult.innerHTML = '&nbsp;';
            	 petNameCheck = false;
            }
        };
        petName.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                petNameresult.innerHTML = '&nbsp;';
                petNameCheck = false;
            }
        }
        
        
        petAge.onblur = function(){
            let regExp = /^[0-9]{1,}$/;
            if(regExp.test(this.value)) {
            	petAgeresult.classList.remove('invalid');
            	petAgeresult.innerHTML = '&nbsp;';
            	petAgeCheck = true;
            }
            else {
            	petAgeresult.classList.add('invalid');
            	petAgeresult.innerHTML = '숫자로만 입력하세요';
            	petAgeCheck = false;
            }
            if(this.value == "") {
            	petAgeresult.innerHTML = '&nbsp;';
            	petAgeCheck = false;
            }
        };

        petAge.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                petAgeresult.innerHTML = '&nbsp;';
                petAgeCheck = false;
            }
        }	
        
        
        
        
        address.onblur = function(){
            let regExp =  /^[0-9]{1,}$/
            if(regExp.test(this.value)) {
                userPwdresult.classList.remove('invalid');
                userPwdresult.innerHTML = '&nbsp;';
                addressCheck = true;
            }
            else {
            	addressresult.classList.add('invalid');
            	addressresult.innerHTML = '주소를 입력하십시오';
                addressCheck = false;
            }
            if(this.value == "") {
            	addressresult.innerHTML = '&nbsp;';
                addressCheck = false;
            }
        };
        address.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                addressresult.innerHTML = '&nbsp;';
                addressCheck = false;
            }
        }
        
        
        
        
        
         address.onblur = function(){
        	let regExp = /^\d{1,}$/
            if(regExp.test(input.value)) {
            	addressCheck = true;
            }
            else {
            	addressCheck = false;
            }
        };
        
        
        function validate(){
			if(idCheck == false){
				console.log("회원가입 불가");
				userId.focus();
				return;
			}
			if(pwdCheck == false){
				console.log("회원가입 불가");
				userPwd.focus();
				return;
			}
			if(pwd2Check == false){
				console.log("회원가입 불가");
				userPwd2.focus();
				return;
			}
			if(nameCheck == false){
				console.log("회원가입 불가");
				userName.focus();
				return;
			}
			if(phoneCheck == false){
				console.log("회원가입 불가");
				phone.focus();
				return;
			}
			if(emailCheck == false){
				console.log("회원가입 불가");
				email.focus();
				return;
			}
			if(petNameCheck == false){
				console.log("회원가입 불가");
				console.log(petNameCheck);
				petName.focus();
				return;
			}
			if(petAgeCheck == false){
				console.log("회원가입 불가");
				console.log(petAgeCheck);
				petAge.focus();
				return;
			}
			if(addressCheck == false){
				address.focus();
				return;	
			} 
			else{
				console.log("회원가입 가능");
				document.forms.joinForm.submit();
			}
		}
        