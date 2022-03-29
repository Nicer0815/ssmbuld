# 1、需求分析

######



# 2、数据库设计



## 2.1、数据表-MySQL

```sql
create table books(
    bookId varchar(50) not null ,
    title varchar(50) not null ,
    author varchar(50) not null ,
    pubDate DATE,
    press varchar(50),
    amount int not null ,
    subject varchar(18) not null ,
    price numeric(6,2),
    state varchar(50) not null ,
    primary key (bookId)
);
# 插入数据
insert into books values ('11111','现代操作系统','Andrew S.Tanenbaum',
                          date('2014-09-09'),'机械工业出版社',20,'计算机',60.00,'正常');
```

```sql
create table readers(
    readerId varchar(30) not null ,
    name varchar(30) not null ,
    sex char(1) default 'F',
    phoneNum int not null ,
    primary key (readerId)
);

# method 1
insert into readers (readerId, name, phoneNum)
values('000000','admin','123456');

# method 2
insert into readers
values ('000001','Nicer','M','111111');
```

```sql
create table credentials (
    readerId varchar(30) not null ,
    handleDate DATE not null ,
    invalidDate DATE ,
    password varchar(30) not null ,
    score int default 80,
    borrowNum int default 0,
    state varchar(16) default '正常',
    primary key (readerId),
    foreign key (readerId) references readers(readerId)
);

# 数据
insert into credentials values ('000000',date(0),null,'admin',80,0,'正常');
insert into credentials values ('000001',date(0),null,'123456',80,0,'正常');
```

## 2.2、Oracle配置

![image-20220327125643072](image-20220327125643072.png)

```sql
--创建表空间
create tablespace jlussm
datafile 'D:\jlussm.dbf'
size 500m
autoextend on
next 20m;

--创建用户
create user lyn
identified by lyn
default tablespace jlussm;

create user wry
identified by wry
default tablespace jlussm;

create user lly
identified by lly
default tablespace jlussm;

--给用户授权
--常用角色
connect --连接角色，基本角色
resource --开发者角色
dba --超级管理员角色

--给用户授予角色
grant dba to lyn;
grant connect to wry;
grant resource to lly;


C:\Users\Administrator\Desktop\JAVAEE\ssmbuld>mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar -Dfile=ojdbc6.jar
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------< org.example:ssmbuld >-------------------------
[INFO] Building ssmbuld 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-install-plugin:2.4:install-file (default-cli) @ ssmbuld ---
[INFO] Installing C:\Users\Administrator\Desktop\JAVAEE\ssmbuld\ojdbc6.jar to C:\Users\Administrator\.m2\repository\com\oracle\ojdbc6\11.2.0.3\ojdbc6-11.2.0.3.jar
[INFO] Installing C:\Users\ADMINI~1\AppData\Local\Temp\mvninstall7972904160730413300.pom to C:\Users\Administrator\.m2\repository\com\oracle\ojdbc6\11.2.0.3\ojdbc6-11.2.0.3.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.371 s
[INFO] Finished at: 2022-03-28T16:39:33+08:00
[INFO] ------------------------------------------------------------------------
```







## 2.3、Oracle学习

```sql
--创建一个person表
create table person(
       pid number(20),
       pname varchar2(10)
);



--序列不真的属于任何一张表，但是可以逻辑和表做绑定
--序列：默认从1开始，依次递增，主要用来给主键赋值使用
--dual: 虚表，知识为了补全语法，没有任何意义
create sequence s_person;
select s_person.nextval from dual;
select s_person.currval from dual;


insert into person(pid,pname) values (s_person.nextval,'小明');
commit;

select * from person;

--scott用户，密码tiger
--解锁scott用户
alter user scott account unlock;

--解锁scott的密码【也可重置】
alter user scott identified by tiger;


--视图--就是提供一个查询的窗口，所有数据来自原表
--查询语句创建表
create table emp as select * from scott.emp;
--创建视图【必须有dba权限】
create view v_emp as select ename, job from emp;

--查询视图
select * from v_emp;

--修改视图【不推荐】
--只读视图
create view v_emp1 as select ename, job from emp with read only;


--视图的作用：1、屏蔽敏感字段 如：salary 2、数据统一

--索引：在表的列上构建一个二叉树
----达到大幅度提高查询效率的目的，但是索引会影响增删改的效率

---单列索引 触发规则：条件必须是索引列中的原始值 where ename = 'scott'
---                   单行函数，模糊查询，都会影响索引的触发
create index idx_ename on emp(ename);

---复合索引
create index idx_enamejob on emp(ename,job);


--存储过程： 提前编译好的一段pl/sql语言，防止在数据库端
-----可以直接被调用、一般都是固定步骤的业务

create or replace procedure p1(eno emp.empno%type)
is

begin
  update emp set sal = sal+100 where empno = eno;
  commit;
end;


---调用有 两种方法 sql、java
select * from emp where empno = 7788;

declare

begin
  p1(7788);
end;

--存储函数
----eg：实现指定员工的年薪 (过程的参数都不能带长度，函数的返回值类型不能带长度)
create or replace function f_yearsal(eno emp.empno% type) return number
is 
   s number(10);
begin 
  select sal*12+nvl(comm, 0)into s from emp where empno = eno;
  return s;
end;  

----测试 f_yearsal (返回值需要接收)
declare 
  s number(10);
begin
  s :=f_yearsal(7788);
  dbms_output.put_line(s);
end;


--触发器：1、语句级  
--        2、行级
--插入一条记录，输出一个新员工入职
create or replace trigger t1
after
insert
on person

declare

begin
  dbms_output.put_line('一个新员工入职');
end;

select * from person;

insert into person values(1,'小红');

--行级别触发器
--不能给员工降薪
create or replace trigger t2
before
update
on emp
for each row
declare

begin
  if :old.sal>:new.sal then
    raise_application_error(-20001,'不能给员工降薪');
  end if;
end;

----触发t2
update emp set sal=sal-1 where empno = 7788;

```



## 2.4、移植到Oracle

```sql
create table books(
    bookId varchar(50) not null ,
    title varchar(50) not null ,
    author varchar(50) not null ,
    pubDate DATE,
    press varchar(50),
    amount int not null ,
    subject varchar(18) not null ,
    price numeric(6,2),
    state varchar(50) not null ,
    primary key (bookId)
);



insert into books values ('11111','现代操作系统','Andrew S.Tanenbaum',
                          to_date('2014-09-09','YYYY-MM-DD'),
                          '机械工业出版社',20,'计算机',60.00,'正常');
                          

create table readers(
    readerId varchar2(30) not null ,
    name varchar2(30) not null ,
    sex char(1) default 'F',
    phoneNum int not null ,
    primary key (readerId)
);


create table credentials (
     readerId varchar2(30) not null ,
     handleDate DATE not null ,
     invalidDate DATE ,
     password varchar2(30) not null ,
     score int default 80,
     borrowNum int default 0,
     state varchar2(16) default '正常',
     primary key (readerId),
     foreign key (readerId) references readers(readerId)
);



create table book_state(
    bookId varchar2(50),
    title varchar2(50) not null ,
    location varchar2(50),
    remainNum int not null ,
    primary key (bookId),
    foreign key (bookId) references books(bookId)
);


create table books_cover(
    bookId varchar2(50),
    cover BLOB,
    primary key (bookId),
    foreign key (bookId) references books(bookId)
);


create table staff(
    jobId varchar2(30),
    personId varchar2(30),
    name varchar2(30) not null ,
    password varchar2(30) default '000000',
    rank varchar2(30) default '用户管理员',
    sex char(1) default 'F',
    phoneNum int,
    primary key (jobId)
);




create table entry_info(
    checkId varchar2(30),
    bookId varchar2(50),
    entryDate DATE,
    amount int not null,
    jobId varchar2(30),
    price numeric(6,2),
    primary key (checkId,bookId),
    foreign key (bookId) references books(bookId),
    foreign key (jobId) references staff(jobId)
);


create table report_book(
    checkId varchar2(30),
    bookId varchar2(50),
    jobId varchar2(30),
    reportDate DATE,
    disposeDate DATE,
    amount int,
    reason varchar2(50),
    state varchar2(18),
    primary key (checkId,bookId),
    foreign key (bookId) references books(bookId),
    foreign key (jobId) references staff(jobId)
);

create table comments(
    bookId varchar2(50),
    readerId varchar2(30),
    commentDate DATE,
    content varchar2(100),
    primary key (bookId,readerId,commentDate),
    foreign key (readerId) references readers(readerId),
    foreign key (bookId) references books(bookId)
);

create table ques_ans(
    readerId varchar2(30),
    askDate DATE,
    jobId varchar2(30),
    question varchar2(100),
    answer varchar2(100),
    primary key (readerId,askDate),
    foreign key (readerId) references readers(readerId),
    foreign key (jobId) references staff(jobId)
);

create table book_collect(
    readerId varchar2(30),
    bookId varchar2(50),
    collectDate DATE,
    primary key (readerId,bookId),
    foreign key (readerId) references readers(readerId),
    foreign key (bookId)references books(bookId)
);

create table handle_reader(
    readerId varchar2(50),
    reportDate DATE,
    jobId varchar2(30),
    disposeDate DATE,
    state varchar2(10),
    primary key (readerId,reportDate),
    foreign key (readerId) references readers(readerId),
    foreign key (jobId) references staff(jobId)
);

create table record(
    readerId varchar2(30),
    bookId varchar2(50),
    borrowDate DATE,
    state varchar2(10),
    expectDate DATE,
    returnDate DATE,
    primary key (readerId,bookId,borrowDate),
    foreign key (readerId) references readers(readerId),
    foreign key (bookId) references books(bookId)
);
```

## 2.5、数据插入

#### 1、books表

```sql
insert into books values ('11111','现代操作系统','Andrew S.Tanenbaum',
                          to_date('2014-09-09','YYYY-MM-DD'),'机械工业出版社',20,
                          '计算机',60.00,'正常');
insert into books values ('9787111599715','计算机网络','James.F.Kurose',
                          to_date('2018-06-01','YYYY-MM-DD'),'机械工业出版社',20,
                          '计算机',89.00,'正常');
insert into books values ('9787111618331','设计模式','Erich Gamma',
                          to_date('2019-05-01','YYYY-MM-DD'),'机械工业出版社',20,
                          '计算机',79.00,'正常');
insert into books values ('9787506365437','活着','余华',
                          to_date('2017-07','YYYY-MM'),'作家出版社',20,'文学',31.00,'正常');
insert into books values ('9787532734030','月亮和六便士','威廉·萨默塞特·毛姆',
                          to_date('2019-03-01','YYYY-MM-DD'),'光明日报出版社',20,'文学',
                          37.00,'正常');
insert into books values ('9787570510542','热风','鲁迅',
                          to_date('2019-06-01','YYYY-MM-DD'),'江西教育出版社',20,
                          '文学',60.00,'正常');
insert into books values ('9787519300203','中国通史','吕思勉',
                          to_date('2016-02-01','YYYY-MM-DD'),'群言出版社',20,'历史',
                          45.50,'正常');
insert into books values ('9787550280469','史记','司马迁',
                          to_date('2016-08-01','YYYY-MM-DD'),'北京联合出版公司',20,
                          '历史',198.00,'正常');
insert into books values ('9787545559804','帝国的崩裂','李奕定',
                          to_date('2020-11-01','YYYY-MM-DD'),'天地出版社',20,'历史',
                          79.00,'正常');
insert into books values ('9787030201041','内科学案例版','刘世明、罗兴林',
                          to_date('2008-05-01','YYYY-MM-DD'),'科学出版社',20,'医学',
                          158.00,'正常');
insert into books values ('9787519249496','局部解剖学','丁自海',
                          to_date('2018-11-01','YYYY-MM-DD'),'世界图书出版公司',20,
                          '医学',38.00,'正常');
insert into books values ('9787122356840','外科学','王小农、王建忠',
                          to_date('2020-03-01','YYYY-MM-DD'),'化学工业出版社',20,'医学',
                          60.00,'正常');
insert into books values ('9787107278303','标准日本语','光村图书出版株式会社',
                          to_date('2013-05-01','YYYY-MM-DD'),'人民教育出版社',20,
                          '外国语言文学',78.00,'正常');
insert into books values ('9787562832614','日语教程','Reika',
                          to_date('2012-05-01','YYYY-MM-DD'),'华东理工大学出版社',20,
                          '外国语言文学',64.00,'正常');
insert into books values ('9787513542272','书虫','吕游',
                          to_date('2014-04-01','YYYY-MM-DD'),'外语教育与研究出版社',20,
                          '外国语言文学',65.00,'正常');
insert into books values ('9787010009223','毛泽东选集','毛泽东',
                          to_date('1991-06-01','YYYY-MM-DD'),'人民出版社',20,'政治',
                          81.00,'正常');
insert into books values ('9787550218222','资本论','马克思',
                          to_date('2013-08-01','YYYY-MM-DD'),'北京联合出版公司',20,
                          '政治',49.90,'正常');
insert into books values ('9787514709858','中国制度面对面','中央宣传部理论局',
                          to_date('2020-07-01','YYYY-MM-DD'),'学习出版社',20,'政治',
                          25.00,'正常');
insert into books values ('9787010221779','中华人民共和国民法典','人民出版社',
                          to_date('2020-06-01','YYYY-MM-DD'),'人民出版社',20,'法律',
                          49.80,'正常');
insert into books values ('9787100074483','中国法律与中国社会','瞿同祖',
                          to_date('2010-12-01','YYYY-MM-DD'),'商务印书馆',20,'法律',
                          66.00,'正常');
insert into books values ('9789301312889','法学引注手册','法学引注手册编写组',
                          to_date('2020-05-01','YYYY-MM-DD'),'北京大学出版社',20,'法律',
                          28.00,'正常');
insert into books values ('9787508091044','周易','冯国超',
                          to_date('2017-02-01','YYYY-MM-DD'),'华夏出版社',20,'哲学',
                          29.00,'正常');
insert into books values ('9787550282131','道德经','老子',
                          to_date('2019-06-01','YYYY-MM-DD'),'北京联合出版公司',20,
                          '哲学',68.00,'正常');
insert into books values ('9787506046114','易经','傅佩荣',
                          to_date('2012-05-01','YYYY-MM-DD'),'东方出版社',20,'哲学',
                          64.00,'正常');
insert into books values ('9787806928622','中国钢琴音乐研究','代白生',
                          to_date('2014-01-01','YYYY-MM-DD'),'上海音乐学院出版社',20,
                          '音乐',40.00,'正常');
insert into books values ('9787806672693','钢琴基础教程','韩林申',
                          to_date('2003-05-01','YYYY-MM-DD'),'上海音乐出版社',20,
                          '音乐',42.00,'正常');
insert into books values ('9787115519955','音乐制作自学手册','陈飞',
                          to_date('2019-11-01','YYYY-MM-DD'),'人民邮电出版社',20,
                          '音乐',65.00,'正常');
insert into books values ('9787301284964','摄影美学','秦大唐、秦鹏',
                          to_date('2017-07-01','YYYY-MM-DD'),'北京大学出版社',20,
                          '摄影',79.00,'正常');
insert into books values ('9787115498489','光影艺术','Chris Knight',
                          to_date('2019-04-01','YYYY-MM-DD'),'人民邮电出版社',20,
                          '摄影',108.00,'正常');
insert into books values ('9787547430798','老照片','冯克力',
                          to_date('2019-02-01','YYYY-MM-DD'),'山东画报出版社',20,
                          '摄影',20.00,'正常');
commit ;
```

#### 2、readers表

```sql
insert into readers (readerId, name, phoneNum)
values('000000','admin','123456');
insert into readers (readerId, name, phoneNum)
values('55100101','史珍香','139100101');
insert into readers (readerId, name, phoneNum)
values('55100102','蔡泰贤','139100102');
insert into readers (readerId, name, phoneNum)
values('55100103','范统','139100103');
insert into readers (readerId, name, phoneNum)
values('55100104','安亚平','139100104');
insert into readers (readerId, name, phoneNum)
values('55100105','白百何','139100105');
insert into readers (readerId, name, phoneNum)
values('55100106','白冰','139100106');
insert into readers (readerId, name, phoneNum)
values('55100107','陈雨奇','139100107');
insert into readers (readerId, name, phoneNum)
values('55100108','陈乔恩','139100108');
insert into readers (readerId, name, phoneNum)
values('55100109','杨紫','139100109');
insert into readers (readerId, name, phoneNum)
values('55100110','刘亦菲','139100110');
insert into readers (readerId, name, phoneNum)
values('55100111','贾玲','139100111');
insert into readers (readerId, name, phoneNum)
values('55100112','关晓彤','139100112');
insert into readers (readerId, name, phoneNum)
values('55100113','阚清子','139100113');
insert into readers (readerId, name, phoneNum)
values('55100114','沈月','139100114');

insert into readers
values ('000001','Nicer','M','111111');
insert into readers
values ('55100201','邓超','M','186100201');
insert into readers
values ('55100202','王俊凯','M','186100202');
insert into readers
values ('55100203','王北车','M','186100203');
insert into readers
values ('55100204','胡海泉','M','186100204');
insert into readers
values ('55100205','薛之谦','M','186100205');
insert into readers
values ('55100206','胡夏','M','186100206');
insert into readers
values ('55100207','陈奕迅','M','186100207');
insert into readers
values ('55100208','黄景瑜','M','186100208');
insert into readers
values ('55100209','白敬亭','M','186100209');
insert into readers
values ('55100210','张学友','M','186100210');
insert into readers
values ('55100211','苏见信','M','186100211');
insert into readers
values ('55100212','萧敬腾','M','186100212');
insert into readers
values ('55100213','朱星杰','M','186100213');
insert into readers
values ('55100214','胡梦周','M','186100214');

commit ;
```

#### 3、Credentials表

```sql
insert into Credentials
values('55100101', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 83, 3, '正常');
insert into Credentials
values('55100102', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'wxc922', 78, 1, '正常');
insert into Credentials
values('55100103', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 80, 0, '正常');
insert into Credentials
values('55100104', to_date('2022-09-01','YYYY-MM-DD'), to_date('2023-10-13','YYYY-MM-DD'), 'ayp0444', 58, 4, '注销');
insert into Credentials
values('55100105', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 81, 1, '正常');
insert into Credentials
values('55100106', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'zzz456', 82, 2, '正常');
insert into Credentials
values('55100107', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 80, 0, '正常');
insert into Credentials
values('55100108', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 80, 0, '正常');
insert into Credentials
values('55100109', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'wwrrtt', 59, 3, '锁定');
insert into Credentials
values('55100110', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 78, 1, '正常');
insert into Credentials
values('55100111', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 80, 0, '正常');
insert into Credentials
values('55100112', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'wdcvv67', 84, 4, '正常');
insert into Credentials
values('55100113', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'qqpvv24', 79, 2, '正常');
insert into Credentials
values('55100114', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'ntr000', 76, 2, '正常');


insert into Credentials
values('55100201', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 80, 0, '正常');
insert into Credentials
values('55100202', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 80, 0, '正常');
insert into Credentials
values('55100203', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'wc12389', 81, 1, '正常');
insert into Credentials
values('55100204', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 78, 1, '正常');
insert into Credentials
values('55100205', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'xzcee1', 59, 3, '锁定');
insert into Credentials
values('55100206', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 80, 3, '正常');
insert into Credentials
values('55100207', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 80, 0, '正常');
insert into Credentials
values('55100208', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'hjy2245', 78, 1, '正常');
insert into Credentials
values('55100209', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'bzz2022', 80, 2, '正常');
insert into Credentials
values('55100210', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 80, 1, '正常');
insert into Credentials
values('55100211', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'rtuy67', 80, 2, '正常');
insert into Credentials
values('55100212', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), 'sjx200', 78, 1, '正常');
insert into Credentials
values('55100213', to_date('2022-09-01','YYYY-MM-DD'), to_date('2023-03-01','YYYY-MM-DD'), 'zxxc22', 59, 3, '注销');
insert into Credentials
values('55100214', to_date('2022-09-01','YYYY-MM-DD'), to_date('2026-06-01','YYYY-MM-DD'), '000000', 80, 2, '正常');
commit ;
```

#### 4、book_state表

馆藏位置：1A-01-1代表 一楼A区，01号书架，第一层

```sql
insert into book_state values('11111','现代操作系统'，'1A-01-1',20);
insert into book_state values('9787111599715','计算机网络'，'1A-01-1',18);
insert into book_state values('9787111618331','设计模式'，'1A-01-2',20);
insert into book_state values('9787506365437','活着'，'1B-01-1',20);
insert into book_state values('9787532734030','月亮和六便士'，'1B-01-1',18);
insert into book_state values('9787570510542','热风'，'1B-01-2',20);
```

#### 5、staff表

```sql
--男用户管理员，初始密码
insert into staff (jobId, personId, name, sex, phoneNum)
values('1001','1110002221001','李强','M','1351001');
insert into staff (jobId, personId, name, sex, phoneNum)
values('1002','1110002221002','张三','M','1351002');
insert into staff (jobId, personId, name, sex, phoneNum)
values('1003','1110002221003','曹操','M','1351003');
insert into staff (jobId, personId, name, sex, phoneNum)
values('1004','1110002221004','刘备','M','1351004');

--女用户管理员，初始密码
insert into staff (jobId, personId, name, phoneNum)
values('2001','1110002222001','林黛玉','1652001');
insert into staff (jobId, personId, name, phoneNum)
values('2002','1110002222002','薛宝钗','1652002');
insert into staff (jobId, personId, name, phoneNum)
values('2003','1110002222003','王熙凤','1652003');
insert into staff (jobId, personId, name, phoneNum)
values('2004','1110002222004','贾迎春','1652004');


--男图书管理员，初始密码
insert into staff (jobId, personId, name, rank, sex, phoneNum)
values('3001','1110002223001','贾宝玉','用户管理员','M','1803001');
insert into staff (jobId, personId, name, rank, sex, phoneNum)
values('3002','1110002223002','曹雪芹','用户管理员','M','1803002');
insert into staff (jobId, personId, name, rank, sex, phoneNum)
values('3003','1110002223003','蒋玉涵','用户管理员','M','1803003');
insert into staff (jobId, personId, name, rank, sex, phoneNum)
values('3004','1110002223004','周汝昌','用户管理员','M','1803004');

--女图书管理员，初始密码
insert into staff (jobId, personId, name, rank, phoneNum)
values('4001','1110002224001','王小花','用户管理员','1504001');
insert into staff (jobId, personId, name, rank, phoneNum)
values('4002','1110002224002','李红','用户管理员','1504002');
insert into staff (jobId, personId, name, rank, phoneNum)
values('4003','1110002224003','沈美','用户管理员','1504003');
insert into staff (jobId, personId, name, rank, phoneNum)
values('4004','1110002224004','刘悦','用户管理员','1504004');

--男馆长，密码123456
insert into staff values(jobId, personId, name, password, rank, sex, phoneNum)
values('54321','11100022254321','李大力','123456','馆长','M','18954321');
```

#### 6、entry_info表

```sql
insert into entry_info values ('20220327-0001','11111',
                               to_date('2022-03-27','YYYY-MM-DD'),
                                20,'3001',60.00);
                                --现代操作系统
insert into entry_info values ('20220327-0002','9787111599715',
                               to_date('2022-03-27','YYYY-MM-DD'), 
                               20,'3002',89.00);
                               --计网
insert into entry_info values ('20220327-0003','9787111618331',
                               to_date('2022-03-27','YYYY-MM-DD'),  
                               30,'3003',79.00);
                               --设计模式
insert into entry_info values ('20220328-0004','9787506365437',
                               to_date('2022-03-28','YYYY-MM-DD'),
                               20,'3004',31.00);
                               --活着


insert into entry_info values ('20220328-0005','9787532734030',
                               to_date('2022-03-28','YYYY-MM-DD'),
                               20,'4001',37.00);
                               --月亮和六便士
insert into entry_info values ('20220327-0006','9787570510542',
                               to_date('2022-03-27','YYYY-MM-DD'),
                               20,'4002',60.00);
                               --热风
insert into entry_info values ('20220329-0007','9787519300203',
                               to_date('2022-03-29','YYYY-MM-DD'),
                               20,'4003',45.50);
                               --中国通史
insert into entry_info values ('20220329-0008','9787550280469',
                               to_date('2022-03-29','YYYY-MM-DD'),
                               10,'4004',198.00);
                               --史记                               
```

#### 7、record表

```sql
insert into record 
values('55100101', '11111', to_date('2022-10-12','YYYY-MM-DD'), '已还', to_date('2022-11-12','YYYY-MM-DD'), to_date('2022-10-21','YYYY-MM-DD'));
insert into record 
values('55100102', '9787111599715', to_date('2022-9-22','YYYY-MM-DD'), '逾还', to_date('2022-11-22','YYYY-MM-DD'), to_date('2022-12-10','YYYY-MM-DD'));
insert into record (readerId, bookId, borrowDate, state, expectDate)
values('55100104', '9787506365437', to_date('2023-2-10','YYYY-MM-DD'), '丢失', to_date('2023-3-10','YYYY-MM-DD'));
insert into record 
values('55100205', '9787570510542', to_date('2022-10-12','YYYY-MM-DD'), '损坏', to_date('2022-11-27','YYYY-MM-DD'), to_date('2022-11-30','YYYY-MM-DD'));
insert into record (readerId, bookId, borrowDate, state, expectDate)
values('55100209', '9787545559804', to_date('2023-3-12','YYYY-MM-DD'), '未还', to_date('2023-4-12','YYYY-MM-DD'));
```

#### 8、handle_reader表

```sql
insert into handle_reader
values('55100105', to_date('2022-11-30','YYYY-MM-DD'), '2001', to_date('2022-12-1','YYYY-MM-DD'), '注销');
insert into handle_reader(readerId, reportDate)
values('55100104', to_date('2023-3-10','YYYY-MM-DD'));
insert into handle_reader
values('55100213', to_date('2023-2-11','YYYY-MM-DD'), '1001', to_date('2023-2-13','YYYY-MM-DD'), '注销');
insert into handle_reader(readerId, reportDate)
values('55100109', to_date('2023-3-7','YYYY-MM-DD'));

```

#### 9、book_collect表

```sql
insert into book_collect
values('55100101' , '11111', to_date('2022-10-14','YYYY-MM-DD'));
insert into book_collect
values('55100108' , '9787506365437', to_date('2023-1-14','YYYY-MM-DD'));
insert into book_collect
values('55100111' , '9787301284964', to_date('2022-10-24','YYYY-MM-DD'));
insert into book_collect
values('55100207' , '9787806672693', to_date('2022-12-23','YYYY-MM-DD'));
insert into book_collect
values('55100210' , '9787550282131', to_date('2022-9-17','YYYY-MM-DD'));

```

#### 10、ques_ans表

```sql
insert into ques_ans
values('55100101', to_date('2022-11-14','YYYY-MM-DD'), '2001', '图书馆可以嗑瓜子吗？', '你好同学，不能');
insert into ques_ans
values('55100205', to_date('2022-12-14','YYYY-MM-DD'), '1001', '账户锁定怎么解除', '你好同学，去办事大厅处理');
insert into ques_ans
values('55100208', to_date('2022-9-29','YYYY-MM-DD'), '2004', '逾期还书会扣信誉积分吗', '你好同学，逾期还书扣两分');
insert into ques_ans
values('55100112', to_date('2022-10-2','YYYY-MM-DD'), '4001', '可以要管理员姐姐的联系方式吗？', '你好同学，公事可以，私事不可以');
insert into ques_ans
values('55100104', to_date('2023-3-5','YYYY-MM-DD'), '2002', '书丢了怎么办？', '你好同学，按市场现价赔偿或者补还一本丢失的书');
insert into ques_ans (readerId, askDate, question)
values('55100106', to_date('2023-3-28','YYYY-MM-DD'), '图书馆什么时候重新开馆？');

```

#### 11、comments表

```sql
insert into comments
values('11111', '55100101', to_date('2022-11-21','YYYY-MM-DD'), '书很好，敏感肌也可以用');
insert into comments
values('9787111599715', '55100104', to_date('2022-9-11','YYYY-MM-DD'), '还不错，已经学会拔网线了');
insert into comments
values('9787532734030', '55100202', to_date('2023-1-21','YYYY-MM-DD'), '强强强');
insert into comments
values('9787519249496', '55100205', to_date('2023-3-21','YYYY-MM-DD'), '找一位小伙伴一起实践本书内容');
insert into comments
values('9787506365437', '55100207', to_date('2022-10-23','YYYY-MM-DD'), '呜呜呜呜，余华好“温柔”，我真得哭死');
insert into comments
values('9787111618331', '55100209', to_date('2022-9-27','YYYY-MM-DD'), '很不错，已经给孩子买了三箱了');

```

#### 12、report_book表

```sql
insert into report_book
values('20220328-0001', '9787111599715', '3001', to_date('2022-03-28','YYYY-MM-DD'), to_date('2022-03-31','YYYY-MM-DD'), 2, '损坏', '已处理');
insert into report_book
values('20220328-0002', '9787111618331', '3001', to_date('2022-03-28','YYYY-MM-DD'), to_date('2022-03-31','YYYY-MM-DD'), 3, '损坏', '已处理');
insert into report_book
values('20220326-0001', '9787570510542', '3003', to_date('2022-03-26','YYYY-MM-DD'), to_date('2022-03-31','YYYY-MM-DD'), 1, '损坏', '已处理');
insert into report_book(checkId, bookId, jobId, reportDate, amount, reason, state)
values('20220329-0001', '9787545559804', '4001', to_date('2022-03-29','YYYY-MM-DD'),  2, '损坏', '未处理');
insert into report_book(checkId, bookId, jobId, reportDate, amount, reason, state)
values('20220329-0002', '9787030201041', '4001', to_date('2022-03-29','YYYY-MM-DD'),  4, '损坏', '未处理');
insert into report_book
values('20220327-0001', '9787122356840', '3002', to_date('2022-03-27','YYYY-MM-DD'), to_date('2022-03-31','YYYY-MM-DD'), 2, '丢失', '已处理');
insert into report_book
values('20220327-0002', '9787513542272', '4002', to_date('2022-03-27','YYYY-MM-DD'), to_date('2022-03-31','YYYY-MM-DD'), 2, '丢失', '已处理');
```

