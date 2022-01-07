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



 