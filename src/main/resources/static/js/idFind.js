/**
 * 
 */
 let userName = document.getElementById("userName");
	let email = document.getElementById("email");
	
	let userNameresult = document.getElementById("userNameCheck_div");
	let emailresult = document.getElementById("emailCheck_div");
	
    let nameCheck = false;
    let emailCheck = false;
	
	
	
		$("#idFindForm input[type=button]").on('click', function(){
			
			var name = $("[name=name]");
			var email = $("[name=email]");
			
			if(name.val() != "" && email.val() != ""){
				
				 var token = $("meta[name='_csrf']").attr("content");
				  var header = $("meta[name='_csrf_header']").attr("content");
				    
				$.ajax({
					url : "/member/idFind",
					type : "post",
					//
					beforeSend : function(xhr){
						xhr.setRequestHeader(header, token);
					},
					data : {name : name.val() ,
							email : email.val()
					},
					success : function (result) {
						console.log(result);
						if(result == ""){
							alert("해당하는 아이디가 없습니다. 다시 확인하여 주십시오.");
							userNameresult.innerHTML ="&nbsp;";
							emailresult.innerHTML ="&nbsp;"; 
							location.replace('idFind'); 
							userNameresult.focus();
						}else{							
								// 다시 아이디 입력 공간을 바꿀 수 있도록 처리
								alert("아이디는 " + result + " 입니다");
								
								userNameresult.innerHTML ="";   //attr 제이쿼리 메소드
								emailresult.innerHTML =""; 
								location.replace('login'); 
						}
					},
					error : function (e) {
						console.log(e);
					}		
					});			
				}else if($("#before").click()){
					
				}else{
					alert("값을 입력하여 주십시오.");
					userNameresult.focus();
				}	
		
	}); 
		
		userName.onblur = function(){
            let regExp = /^[가-힣]{2,}$/;
            if(regExp.test(this.value)) {
                userNameresult.classList.remove('invalid');
                userNameresult.innerHTML = '&nbsp;';
                nameCheck = true;
            }
            else {
                userNameresult.classList.add('invalid');
                userNameresult.innerHTML = '한글 2~10 자리로 입력하세요.';
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