// 헤더의 메뉴바
$(document).ready(function(){
    $(".menulist > ul > li").mouseover(function(){
        $(".sub-menulist").removeClass("dropon");

        if(!$(this).children(".sub-menulist").attr("dropon")){
            $(this).children(".sub-menulist").addClass("dropon");
        } 
        $(this).children(".sub-menulist").mouseleave(function(){
            $(".sub-menulist").removeClass("dropon");
        });
    });
});

// 스크롤
$(document).ready(function(){
    $( window ).scroll( function() {
        if ( $( this ).scrollTop() > 200 ) {
            $( '.top' ).fadeIn();
        } else {
            $( '.top' ).fadeOut();
        }
    });
    // 페이지 위로가기 버튼 클릭시 애니메이션효과 - 스르륵올라가도록
    $( '.move-to-top' ).click( function() {
        $( 'html, body' ).animate( { scrollTop : 0 }, 400 );
        return false;
    } );

});

// 헤더 고정
$(function() {
    var header = $("#header").offset().top;    // offset은 선택한 요소의 좌표값 가져오기

    $(window).scroll(function() {
        var window = $(this).scrollTop();

        if(header <= window) {
            $("#header").addClass("fixed");
        } else {
            $("#header").removeClass("fixed");
        }

    })
});

// 관리자페이지 메뉴 슬라이드 효과
$(function(){
    $('.menucate').hide();

    $('.menu-icon > li > a').on('click', function(){
        if($(this).hasClass('activeopen')){
            $(this).next().slideUp('slow');
            $(this).removeClass('activeopen');
        } else{
            $('.menu-icon').find('.activeopen').next().slideUp('slow');
            $('.menu-icon').find('.activeopen').removeClass('.activeopen');
            $(this).next().slideDown('slow');
            $(this).addClass('activeopen');
        }
    });

});

// 관리자 계정
$(document).ready(function(){
    $(".admin-nameinfo > span:first-of-type").mouseover(function(){
        
        $(".sub-menulist2").removeClass("dropon");

        if(!$(".sub-menulist2").attr("dropon")){
            $(".sub-menulist2").addClass("dropon");
        } 
        $(".sub-menulist2").mouseleave(function(){
            $(".sub-menulist2").removeClass("dropon");
        });
    });
});