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


 