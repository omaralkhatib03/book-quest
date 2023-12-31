-- use BOOKQUEST;
create table Book (ISBN varchar(255) not null, title varchar(255) not null, author varchar(255) not null, primary key (ISBN));
create table Fiction (ISBN varchar(255) not null, numpages integer not null, genre varchar(255) not null, isPaperBack bit not null,  price REAL not null, primary key (ISBN));
create table NonFiction (ISBN varchar(255) not null, numpages integer not null, genre varchar(255) not null, isPaperBack bit not null, isTextBook bit not null, price REAL not null, primary key (ISBN));
create table Periodical (ISBN varchar(255) not null, numpages integer not null, seriesNo integer not null, issueNo integer not null, price REAL not null, primary key (ISBN));
create table tokens (TID integer not null auto_increment, EXPIRATION bigint, ISSUED_AT bigint, TOKEN varchar(255), UID integer, primary key (TID));
create table users (DTYPE varchar(31) not null, UID integer not null auto_increment, EMAIL varchar(255), PASSWORD varchar(255), role enum ('ADMIN','USER'), USERNAME varchar(255), primary key (UID));
alter table tokens add constraint UK_7clorenuvp43nv0nl21avhq8x unique (TOKEN);
alter table users add constraint UK_ku29j688xlci1ksopvjfgpswp unique (EMAIL);
alter table Fiction add constraint FK4ouyj03gb403yl9dplxv1nx8y foreign key (ISBN) references Book (ISBN);
alter table NonFiction add constraint FK2bnhq4oh5juiule9l6q9iuex foreign key (ISBN) references Book (ISBN);
alter table Periodical add constraint FKhqbsa93y7hpxsrrhxod14dywm foreign key (ISBN) references Book (ISBN);
alter table tokens add constraint FKh2mrrpcg63hfh6y3yy67xpo0d foreign key (UID) references users (UID);

INSERT INTO Book VALUES
('978-2-16-148410-0', 'Crime and Punishment', 
'Fyodor Dostoevsky');
INSERT INTO Book VALUES
('955-3-54-338410-0', 'Superintelligence', 
'Nick Bostrom');
INSERT INTO Book VALUES
('953-5-23-378610-0', 'The Journal of Funny Science', 
'Robert Spade editor');
INSERT INTO Book VALUES
('902-1-98-334410-0', 'The Periodical of Precautions', 
'Peter Cautious editor');
INSERT INTO Book VALUES
('978-0-43-893210-0', 'Norwegian Wood', 'Haruki Murakami');
INSERT INTO Book VALUES('978-9-22-148410-0', 'The Garlic Ballads', 'Mo Yan');
INSERT INTO Book VALUES('953-7-21-334410-0', 'Alchemy', 'Marco Polo editor');
INSERT INTO Book VALUES
('902-4-44-365310-0', 'A Short History of Europe', 'Simon Jenkins');
INSERT INTO Book VALUES
('978-8-61-892810-0', 'Slaughterhouse-Five', 
'Kurt Vonnegut');
INSERT INTO Book VALUES
('978-6-09-177710-0',
'Sapiens: A Brief History of Humankind', 
'Yuval Noah Harari');

INSERT INTO Fiction VALUES
('978-2-16-148410-0', 610, 'Drama', 1, 12.20);
INSERT INTO Fiction VALUES('978-0-43-893210-0', 302, 'Drama', 1, 20.56);
INSERT INTO Fiction VALUES('978-9-22-148410-0', 502, 'Historical', 0, 30.10); 
INSERT INTO Fiction VALUES('978-8-61-892810-0', 170, 'War', 1, 7.5);

INSERT INTO NonFiction VALUES
('955-3-54-338410-0', 410, 'Science', 1, 0, 27.20);
INSERT INTO NonFiction VALUES
('902-4-44-365310-0', 350, 'History', 0, 1, 65.80);
INSERT INTO NonFiction VALUES
('978-6-09-177710-0', 450, 'Anthro', 1, 0, 18.10); 

INSERT INTO Periodical VALUES
('953-5-23-378610-0', 420, 7, 21, 5.5);
INSERT INTO Periodical VALUES
('902-1-98-334410-0', 101, 5, 12, 2.5);
INSERT INTO Periodical VALUES
('953-7-21-334410-0', 190, 12, 13, 6.8);

