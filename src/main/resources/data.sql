insert into company
    (name, nation, location, create_date_time)
values ('원티드', '한국', '서울', now()),
       ('네이버', '한국', '경기도', now()),
       ('카카오', '한국', '제주도', now())
;

insert into member
    (email, create_date_time)
values ('apple@apple.com', now()),
       ('banana@banana.com', now()),
       ('orange@orange.com', now())
;