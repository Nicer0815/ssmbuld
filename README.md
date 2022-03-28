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

