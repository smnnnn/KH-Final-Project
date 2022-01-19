/**
 * 
 */
     
    let userId = document.getElementById("userId");
	let email = document.getElementById("email");
	
	let userIdresult = document.getElementById("userIdCheck_div");
	let emailresult = document.getElementById("emailCheck_div");
	
    let idCheck = false;
    let emailCheck = false;
    
    
    
    userId.onblur = function(){
        let regExp = /(?=.*[a-z])(?=.*[0-9]).{8,10}/;
        if(regExp.test(this.value)) {
        	userIdresult.classList.remove('invalid');
        	userIdresult.innerHTML = '&nbsp;';
            idCheck = true;
        }
        else {
        	userIdresult.classList.add('invalid');
        	userIdresult.innerHTML = ' 소문자와 숫자 포함 8~10자리';
            idCheck = false;
        }
        if(this.value == "") {
        	userIdresult.innerHTML = '&nbsp;';
            idCheck = false;
        }
    };
    userId.onfocus = function(){
        if(this.classList.contains('invalid')){
            this.classList.remove('invalid');
            userIdresult.innerHTML = '&nbsp;';
            idCheck = false;
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


		
		
		 function validate(){
				if(idCheck == false){
					console.log("비밀번호 찾기 불가");
					userId.focus();
					return;
				}
				if(emailCheck == false){
					console.log("비밀번호 찾기 불가");
					email.focus();
					return;
				}
				else{
					console.log("비밀번호 찾기 가능");
					document.forms.pwdFindForm.submit();
				}
			}