/**
 * 
 */
 
 src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"
 
// "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 
	 $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); 
	
	
        let userPwd = document.getElementById("pwd");
        let userPwd2 = document.getElementById("pwd2");
        let phone = document.getElementById("phone");
        let email = document.getElementById("email");
        let petName = document.getElementById("petName");
        let petAge = document.getElementById("petAge");
        let address = document.getElementById("address");
        
        
        let userPwdresult = document.getElementById("pwdCheck_div");
        let userPwd2result = document.getElementById("pwdCheck_div2");
        let phoneresult = document.getElementById("phoneCheck_div");
        let emailresult = document.getElementById("emailCheck_div");
        let petNameresult = document.getElementById("petNameCheck_div");
        let petAgeresult = document.getElementById("petAgeCheck_div");
        
        
        let pwdCheck = false;
        let pwd2Check = false;
        let phoneCheck = false;
        let emailCheck = false;
        let petNameCheck = false;
        let petAgeCheck = false;
        
		

		
		
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
            if(address == null) {
            	address = false;
            }
            else {
            	address = true;
            }
        };
        
        
        function validate(){
			
			if(pwdCheck == false){
				console.log("정보수정 불가");
				userPwd.focus();
				return;
			}
			if(pwd2Check == false){
				console.log("정보수정 불가");
				userPwd2.focus();
				return;
			}
			
			if(phoneCheck == false){
				console.log("정보수정 불가");
				phone.focus();
				return;
			}
			if(emailCheck == false){
				console.log("정보수정 불가");
				email.focus();
				return;
			}
			if(petNameCheck == false){
				console.log("정보수정 불가");
				console.log(petNameCheck);
				petName.focus();
				return;
			}
			if(petAgeCheck == false){
				console.log("정보수정 불가");
				console.log(petAgeCheck);
				petAge.focus();
				return;
			}
			else{
				console.log("정보수정 가능");
				document.forms.myPageForm.submit();
			}
		}