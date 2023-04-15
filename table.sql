-- 멤버 테이블 생성

create table tbl_member(
userIdx number not null,
userId varchar2(80) not null,
userpw varchar2(80) not null,
userName varchar2(80) not null,
userEmail varchar2(80) not null,
regDate DATE DEFAULT SYSDATE,
updateDate DATE DEFAULT SYSDATE,
primary key (userid) --pk를 userIdx로 변경?
);


CREATE SEQUENCE MEMBER_SEQ
START WITH 1
INCREMENT BY 1;


-- 게시판 테이블 생성

create table tbl_board(
bno number not null,              -- 게시물 번호
title varchar2(200) not null,     -- 제목
content varchar2(4000) not null,           -- 내용
writer varchar2(50) not null,     -- 작성자 이름 -- TODO: 비회원일시만 저장하고, 회원일시 공백으로? 그리고 회원일시 join으로 실제 이름을 가져옴
writerid  varchar2(50), -- 작성자 id
-- ip varchar2(50) not null, -- ip주소 -- 별도의 필드로 두는 대신 비회원일시 writerid에 저장 고려?
loginuser number default 0, -- 작성자 타입
password varchar2(10),     -- 비밀번호
regdate date default sysdate,     -- 작성일자  
viewcnt number default 0,         -- 조회수
primary key(bno)                  -- 기본키 설정
);


CREATE SEQUENCE BOARD_SEQ
START WITH 1
INCREMENT BY 1;

-- 댓글 테이블 생성


create table tbl_reply(
rno number not null,              -- 댓글 번호
bno number not null REFERENCES tbl_board (bno) on delete cascade,              -- 게시물 번호
content varchar2(4000),           -- 내용
writer varchar2(50) not null,     -- 작성자 이름 -- TODO: 비회원일시만 저장하고, 회원일시 공백으로? 그리고 회원일시 join으로 실제 이름을 가져옴
writerid  varchar2(50), -- 작성자 id
-- ip varchar2(50) not null, -- ip주소 -- 별도의 필드로 두는 대신 비회원일시 writerid에 저장 고려?
loginuser number default 0, -- 작성자 타입
password varchar2(20),     -- 비밀번호
regdate date default sysdate,     -- 작성일자  
updatedate date default sysdate,
deleted varchar2(1) default 'N' not null,
primary key(rno)                  -- 기본키 설정
);

-- token 생성 방법 고민중
-- 로그인 remember 테이블
drop TABLE remember_login;
CREATE TABLE remember_login (
    userId varchar(50) not null,
    token varchar(100) not null,
    valid timestamp not null
);

-- 첨부파일 테이블
CREATE TABLE MP_FILE
(
    FILE_NO NUMBER,                         --파일 번호
    BNO NUMBER,                    --게시판 번호
    ORG_FILE_NAME VARCHAR2(260) NOT NULL,   --원본 파일 이름
    STORED_FILE_NAME VARCHAR2(36) NOT NULL, --변경된 파일 이름
    FILE_SIZE NUMBER,                       --파일 크기
    REGDATE DATE DEFAULT SYSDATE NOT NULL,  --파일등록일
    DEL_GB VARCHAR2(1) DEFAULT 'N' NOT NULL,--삭제구분
    PRIMARY KEY(FILE_NO)                    --기본키 FILE_NO
);
COMMIT;


CREATE SEQUENCE SEQ_MP_FILE_NO;

/*
create table tbl_likes(
    idx number,
    tbl_idx number,
    like number,
    dislike number
);

CREATE TABLE tbl_message(
      messageid NUMBER NOT NULL,
      sender VARCHAR2(50) NOT NULL,
      reciever VARCHAR2(50) NOT NULL,
      message VARCHAR2(4000) NOT NULL,
      senddate DATE DEFAULT SYSDATE,
      PRIMARY KEY(mid)
 );

*/
commit;




