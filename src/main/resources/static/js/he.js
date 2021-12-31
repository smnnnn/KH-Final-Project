// 입력 값 정규식
function check(regExp, input, msg){
    // 정규 표현식을 만족할 경우 true 리턴
    if(regExp.test(input.value)) return true;
    // 정규 표현식 불만족할 경우 false 리턴 
    alert(msg);
    input.value = '';  // 값 비우고
    input.focus();  // 포커스 들어가게
    return false;

}

// 예약 동물 정보 입력 폼
function reservation(){
    //let name = document.getElementById('dog_name');
    //let breed = document.getElementById('dog_breed');
    //let gender = document.getElementById('box_select');
    let age = document.getElementById('dog_age');
    //let request = document.getElementById('request');
    //let agree = document.getElementById('agree_chk');
//_disabled
    //console.log(name.value);
    //console.log(breed.value);
    //console.log(age.value);
    //console.log(request.value);
    //console.log(agree.value);

    if(!check(/^[0-9]{1,3}$/, age, "나이는 숫자로만 입력할 수 있습니다.")){
        return false;
    }

}

function doctor_insert(){
    let name = document.getElementById('input_name');
    let holiday = document.getElementById('input_holiday');
    let phone = document.getElementById('input_phone');
    

    if(!check(/^[가-힣]{2,15}$/, name, "이름은 한글로 2~15자 이내만 허용됩니다.")){
        return false;
    }

    if(!check(/^[가-힣]{3}$/, holiday, "휴무일은 O요일의 형태만 허용됩니다.")){
        return false;
    }

    if(!check(/^[0-9]{10,11}$/, phone, "전화번호는 숫자로 10~11자 이내만 허용됩니다.")){
        return false;
    }
}