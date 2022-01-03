// 헤더의 메뉴바
$(document).ready(function(){
    $('.menulist > ul > li').mouseenter(function(){
        $(this).children('.sub-menulist').stop().slideDown();
    });
    $('.menulist > ul > li').mouseleave(function(){
        $(this).children('.sub-menulist').stop().slideUp();
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
$(function(){
    $(".admin-nameinfo").mouseenter(function(){
        $(".sub-menulist2").stop().slideDown();       
    });

    $(".admin-nameinfo").mouseleave(function(){
        $(".sub-menulist2").stop().slideUp();
    });
});

// 관리자페이지 서브메뉴 고정
$(function(){
    $(".menucate").parent('li.activemenu').children('ul.menucate').slideDown('slow');
    $(".menucate").parent('li.activemenu').children('ul.menucate').css('background-color', '#2C2C2C');

}); 

