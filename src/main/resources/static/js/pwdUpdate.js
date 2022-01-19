/**
 * 
 */
 let userPwd = document.getElementById("userPwd");
    let userPwd2 = document.getElementById("userPwd2");
	
    let userPwdresult = document.getElementById("pwdCheck_div");
    let userPwd2result = document.getElementById("pwdCheck_div2");
	
    let pwdCheck = false;
    let pwd2Check = false;
    
    
    userPwd.onblur = function(){
        let regExp = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}/;
        if(regExp.test(this.value)) {
            userPwdresult.classList.remove('invalid');
            userPwdresult.innerHTML = '&nbsp;';
            pwdCheck = true;
        }
        else {
            userPwdresult.classList.add('invalid');
            userPwdresult.innerHTML = '비밀번호 양식을 확인하십시오.';
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
    
    
    function validate(){
		if(pwdCheck == false){
			console.log("비밀번호 변경 불가");
			userPwd.focus();
			return;
		}
		if(pwd2Check == false){
			console.log("비밀번호 변경 불가");
			userPwd2.focus();
			return;
		}
		else{
			console.log("비밀번호 변경 가능");
			document.forms.pwdUpdateForm.submit();
			alert("비밀번호가 변경되었습니다.");
		}
	}