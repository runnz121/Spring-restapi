
    -- sql 구문을 적는 파일--
insert into user values(90001, sysdate(), 'User1', 'test1','701010-11111111');
insert into user values(90002, sysdate(), 'User2', 'test2','801010-11111111');
insert into user values(90003, sysdate(), 'User3', 'test3','901010-11111111');

--post 클래스는 user클래스의 id값을 FK로 받기 때문에 이 id값은 서로 일치해야된다.--
insert into post values(10001, 'My first post', 90001);
insert into post values(10002, 'My second post', 90002);
