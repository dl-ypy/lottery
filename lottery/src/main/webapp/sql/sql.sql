--创建用户
create user lottery identified by lottery;
--给用户赋权限
grant connect,resource to lottery;

--创建用户表
create table t_user (
    userid number(3) primary key,
    username varchar2(20) unique not null,  
    truename varchar2(15) not null,
    password varchar2(10) not null,
    phone varchar2(11) unique not null,
    anumber varchar2(15) unique,
    apassword number(6),
    role number(1) not null,
    status number(1) not null check(status=1 or status=0),
    logindate date not null,
    balance number,
    registerdate date not null
);

--创建用户表序列
create sequence SEQ_T_USER
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

--创建交易表
create table t_deal (
       did number(3) primary key,
       userid number(3) not null,
       constraint fk_user_deal_userid foreign key(userid) references t_user(userid),
       dealdate date not null,
       dealmoney number not null,
       dealinfo varchar2(30)
);

--创建交易表序列
create sequence SEQ_T_DEAL
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

--创建彩票类别表
create table t_lotterytype (
       lid number(3) primary key,
       lotypename varchar2(10) not null,
       lotterdate varchar2(15) not null,
       salemoney number not null,
       lottertime varchar2(5) not null,
       status number(1),
       lotypedesc varchar2(20) not null
);

--创建彩票类别序列
create sequence SEQ_T_LOTTERYTYPE
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

--创建彩票表
create table t_lottery (
       lotterid number(3) primary key,
       lid number(3) not null,
       constraint fk_type_lotter_lid foreign key(lid) references t_lotterytype(lid),
       accumulated number not null,
       starttime date not null,
       issue varchar2(5) not null,
       drawmoney number,
       drawnum number,
       drawno varchar2(14),
       status number(1),
       totalsale number
);

--创建彩票序列
create sequence SEQ_T_LOTTERY
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

--创建开奖详情表
create table t_drawdetails (
       ddid number(3) primary key,
       lotterid number(3) not null,
       constraint fk_lotter_details_lotterid foreign key(lotterid) references t_lottery(lotterid),
       prize varchar2(15) not null,
       drawnum number,
       drawmoney number
);

--创建开奖详情序列
create sequence SEQ_T_DRAWDETAILS
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

--创建购奖信息表
create table t_buy (
       bid number(3) primary key,
       buydate date not null,
       lotterid number(3) not null,
       userid number(3) not null,
       constraint fk_lotter_buy_lotterid foreign key(lotterid) references t_lottery(lotterid),
       constraint fk_user_buy_userid foreign key(userid) references t_user(userid),
       buymoney number not null,
       buyno varchar2(14) not null
);

--创建购奖信息序列
create sequence SEQ_T_BUY
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

--创建新闻表
create table t_news (
       newsid number(3) primary key,
       title varchar2(100) not null,
       adddate date,
       content varchar2(3000)
);

--创建新闻序列
create sequence SEQ_T_NEWS
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

--创建银行账户表
create table t_bank (
       bankid number(3) primary key,
       userid number(3),
       constraint fk_user_bank_userid foreign key(userid) references t_user(userid),
       money number
);

--创建银行账户序列
create sequence SEQ_T_BANK
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;



--用户表添加信息
insert into t_user values(seq_t_user.nextval,'张三','时尚','123456','15555555555','15555555555',222222,1,0,sysdate,0,sysdate); 
insert into t_user values(seq_t_user.nextval,'李四','程度','456444','15545575555','15545575555',333333,1,0,sysdate,0,sysdate); 
commit;

--交易表添加信息
insert into t_deal values(seq_t_deal.nextval,1,to_date('2017-02-22 06:20:20','yyyy-mm-dd hh:mi:ss'),222222,'购买双色球'); 
insert into t_deal values(seq_t_deal.nextval,1,to_date('2017-02-23 12:20:20','yyyy-mm-dd hh:mi:ss'),222,'购买大乐透'); 
commit;

--彩种表添加信息
insert into t_lotterytype values(seq_t_lotterytype.nextval,'双色球','一、三、六',2,'21:15',0,'双色球描述'); 
insert into t_lotterytype values(seq_t_lotterytype.nextval,'大乐透','二、四、日',2,'21:30',0,'大乐透描述'); 
commit;

--彩票表添加信息
insert into t_lottery values(seq_t_lottery.nextval,1,2222,to_date('2017-09-22 09:35:00','yyyy-mm-dd hh:mi:ss'),to_char(sysdate,'yy')||'002',0,0,0,0,4); 
insert into t_lottery values(seq_t_lottery.nextval,2,3333,to_date('2017-09-22 09:30:00','yyyy-mm-dd hh:mi:ss'),to_char(sysdate,'yy')||'002',0,0,0,0,4); 
commit;

--开奖详情表添加信息
insert into t_drawdetails values(seq_t_drawdetails.nextval,2,'一等奖',0,0.5); 
insert into t_drawdetails values(seq_t_drawdetails.nextval,2,'二等奖',0,0.3); 
insert into t_drawdetails values(seq_t_drawdetails.nextval,2,'三等奖',0,3000); 
insert into t_drawdetails values(seq_t_drawdetails.nextval,2,'四等奖',0,200); 
insert into t_drawdetails values(seq_t_drawdetails.nextval,2,'五等奖',0,10); 
insert into t_drawdetails values(seq_t_drawdetails.nextval,2,'六等奖',0,5); 
commit;

--购奖信息表添加信息
insert into t_buy values(seq_t_buy.nextval,sysdate,2,1,2,'01020304050607');  
insert into t_buy values(seq_t_buy.nextval,sysdate,2,1,2,'02030405061107');  
commit;

--新闻表添加信息
insert into t_news values(seq_t_news.nextval,'标题1',sysdate,'内容1');  
insert into t_news values(seq_t_news.nextval,'标题2',sysdate,'内容2');  
commit;

--银行账户信息表添加信息
insert into t_bank values(seq_t_bank.nextval,3,5000);  
insert into t_bank values(seq_t_bank.nextval,2,5000);  
commit;