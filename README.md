# KH-Final-Project
예약 가능한 동물병원 사이트 '다나은'

## 설계의 주안점

간편하게 진료예약을 할 수 있고, 후기게시판을 통해
병원 이용하려는 고객과 정보 공유를 할 수 있는 웹페이지 

## 구현 기능

[회원가입] 회원가입(개인/총관리자/서브관리자)    
[로그인] 로그인, 아이디 찾기, 비밀번호 찾기    
[마이페이지] 회원정보 수정, 탈퇴하기 , 예약 확인 및 예약 취소    
[진료예약] 진료 유형, 날짜, 시간 선택 후 진료 받을 동물 정보 입력하여 진료 예약    
등록한 예약은 마이페이지에서 조회, 수정 및 삭제 가능    
[의료진 등록 및 조회] 새로운 의료진 등록, 등록된 의료진 이름으로 조회, 조회한 의료진 정보 수정    
[의료진 휴무일 관리] 의료진 휴무일 변경 및 삭제    
[의료장비 등록 및 조회] 카테고리별 장비 등록, 장비정보 수정 및 삭제,    
장비소개페이지에서 카테고리별 장비 조회    
**[고객센터 메인] 자주묻는 질문, 문의게시판 이동    
[문의게시판] 페이징,검색,관리자 답변,답변 수정,삭제, 조회수,게시글 crud    
[치료 리뷰] 게시판 목록 슬라이드, 썸네일 기능(파일 업로드), 리뷰 crud, cookie 활용한 조회수 무한 증가 방지 처리**   


### 1. 리뷰 게시판    

* 목록 및 상세 페이지

![ezgif com-gif-maker (4)](https://user-images.githubusercontent.com/86585267/153907406-ca2b218b-b4f5-4fcf-9813-6b01602412a5.gif)


* 리뷰 작성

   - 마이페이지 - 예약 확인 진료완료건에 대해 '리뷰쓰기' 버튼이 뜨고, 리뷰 작성 가능
   - 작성 시 진료과목 카테고리는 변경 못하도록 처리

![ezgif com-gif-maker (5)](https://user-images.githubusercontent.com/86585267/153909450-a6506115-e348-468a-85b5-29543343cb8e.gif)


* 리뷰 수정

![ezgif com-gif-maker (6)](https://user-images.githubusercontent.com/86585267/153911211-dac203bb-2d48-484f-8e79-5c0c2b58ad82.gif)


* 리뷰 삭제

![ezgif com-gif-maker (7)](https://user-images.githubusercontent.com/86585267/153912227-7874802d-30aa-4c67-8e8a-0b82bb195b61.gif)



### 2. 고객센터 메인 페이지

자주묻는질문, 문의게시판 이동 아이콘

![ezgif com-gif-maker](https://user-images.githubusercontent.com/86585267/153899123-fc5770fe-800c-41d3-8f75-088540dc0c96.gif)


### 3. 문의 게시판

* 페이징 처리

![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/86585267/153903931-d60262d7-0d2d-4861-8408-dc6915a4f03d.gif)

* 관리자 답변 기능

![ezgif com-gif-maker (8)](https://user-images.githubusercontent.com/86585267/153922185-8118f279-3bc5-40d0-8822-69d5a8a59f19.gif)


