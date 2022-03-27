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

create table rank_info(
     readerId varchar2(30),
     rank char(1) not null ,
     score int not null ,
     borrowNum int default 0,
     foreign key (readerId) references readers(readerId)
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
```
