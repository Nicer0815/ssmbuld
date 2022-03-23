# 1、需求分析

######



# 2、数据库设计



## 2.1、数据表

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

