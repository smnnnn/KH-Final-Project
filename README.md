# [다나은 프로젝트]
   ### 예약 가능한 동물병원 사이트 '다나은'   
   몇군데의 대학 동물병원을 제외하고 동물병원의 예약 시스템은 전화예약이나
   병원 도착 순서대로 진행되는게 대부분입니다.
   전화예약은 전화를 받지 않으면 여러번 전화를 해야하고, 원하는 예약 날짜, 시간을 말하면
   가능한지 확인 시에 대기해야 하는 불편함이 있었습니다.
   이에 동물병원에도 예약시스템을 도입하여 고객이 예약 가능 현황을 한눈에 알아보고,
   원하는 날짜, 시간대에 바로 예약을 할 수 있도록 기획하였습니다.

* ## 개발 기간
    2021.11.24 ~ 2022.01.24
* ## 설계의 주안점

   간편하게 진료 예약을 할 수 있고, 후기 게시판을 통해
   병원 이용하려는 고객과 정보 공유를 할 수 있는 웹페이지 

* ## 사용 기술 및 개발환경

  - Server : Apache Tomcat 8.5    
  - DataBase : Oracle 11g  
  - Development Tool : sqlDeveloper version 21.2.0.187, Visual Studio code, STS 4.13.0     
  - Development Language : JAVA, HTML5, CSS3, JavaScript, jQuery, Ajax, Thymeleaf     
  - Framework : Spring Boot, Mybatis, Maven, Spring Security     
  - Team Coop : Github, ERDCloud, starUML, Figma     
  - Open API : Full Calendar, Summer Note     

* ## ERD
    ![Copy of kh final](https://user-images.githubusercontent.com/86585267/153937736-ba2ba433-50b8-4f88-a432-8f4ae679c39d.png)

* ## 시퀀스

   <div align=center><img width="910" alt="시퀀스" src="https://user-images.githubusercontent.com/86585267/153945296-30953170-6e6a-473a-8b7e-309ef92b58bb.jpg"></div>

* ## 역할 분담
   - 이효은(팀장) : 회원 별 진료 예약 등록, 수정, 취소, 서브 관리자 의료진 관리, 의료진 등록, 휴무일 변경    
   - **김소민 : lombok 추가, log4j2 버전 업그레이드, 고객센터 메인(자주 묻는 질문), 문의 게시판, 치료 후기 게시판**    
   - 박진희 : 회원가입, 로그인, 아이디찾기, 비밀번호 찾기, 회원정보수정, 탈퇴하기    
   - 안나현 : 병원/의료진/의료장비/오시는길 소개, 회원관리, 관리자계정관리, 예약관리, 문의관리, 의료장비관리, 화면관리    
* ## 구현 기능

   - [회원가입] 회원가입(개인/총관리자/서브관리자)    
   - [로그인] 로그인, 아이디 찾기, 비밀번호 찾기    
   - [마이페이지] 회원정보 수정, 탈퇴하기 , 예약 확인 및 예약 취소    
   - [진료예약] 진료 유형, 날짜, 시간 선택 후 진료 받을 동물 정보 입력하여 진료 예약    
   등록한 예약은 마이페이지에서 조회, 수정 및 삭제 가능    
   - [의료진 등록 및 조회] 새로운 의료진 등록, 등록된 의료진 이름으로 조회, 조회한 의료진 정보 수정    
   - [의료진 휴무일 관리] 의료진 휴무일 변경 및 삭제    
   - [의료장비 등록 및 조회] 카테고리별 장비 등록, 장비정보 수정 및 삭제,    
   장비소개페이지에서 카테고리별 장비 조회    
   - **[고객센터 메인] 자주묻는 질문, 문의게시판 이동**    
   - **[문의게시판] 페이징,검색,관리자 답변,답변 수정,삭제, 조회수,게시글 crud**    
   - **[치료 리뷰] 게시판 목록 슬라이드, 썸네일 기능(파일 업로드), 리뷰 crud, cookie 활용한 조회수 무한 증가 방지 처리**   


   ### 1. 리뷰 게시판    

   * 목록 및 상세 페이지

   ![ezgif com-gif-maker (4)](https://user-images.githubusercontent.com/86585267/153907406-ca2b218b-b4f5-4fcf-9813-6b01602412a5.gif)


   * 리뷰 작성

      - 마이페이지-예약 확인 진료완료건에 대해 '리뷰쓰기' 버튼이 뜨고, 리뷰 작성 가능
      - 작성 시 진료과목 카테고리는 변경 못하도록 처리
      - 썸네일 첨부 안할시 기본 이미지 등록

   ![ezgif com-gif-maker (5)](https://user-images.githubusercontent.com/86585267/153909450-a6506115-e348-468a-85b5-29543343cb8e.gif)


   * 리뷰 수정 (썸네일 수정 시 기존 등록된 이미지 있었다면 삭제, 새로 저장 db에는 파일정보 update)

   ![ezgif com-gif-maker (6)](https://user-images.githubusercontent.com/86585267/153911211-dac203bb-2d48-484f-8e79-5c0c2b58ad82.gif)


   * 리뷰 삭제 (업로드된 파일도 함께 삭제)

   ![ezgif com-gif-maker (7)](https://user-images.githubusercontent.com/86585267/153912227-7874802d-30aa-4c67-8e8a-0b82bb195b61.gif)



   ### 2. 고객센터 메인 페이지

   자주묻는질문, 문의게시판 이동 아이콘

   ![ezgif com-gif-maker](https://user-images.githubusercontent.com/86585267/153899123-fc5770fe-800c-41d3-8f75-088540dc0c96.gif)


   ### 3. 문의 게시판

   * 비밀글, 공개글 기능(자물쇠로 구분)
   * 다른 회원의 비밀글 클릭 시 '비밀글입니다' alert
   * 답변 등록시 글 제목 우측에 답변 아이콘
   * 검색 기능
   * 페이징 처리

   ![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/86585267/153903931-d60262d7-0d2d-4861-8408-dc6915a4f03d.gif)

   * 관리자 답변 기능

   ![ezgif com-gif-maker (8)](https://user-images.githubusercontent.com/86585267/153922185-8118f279-3bc5-40d0-8822-69d5a8a59f19.gif)


