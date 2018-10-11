drop table if exists book_loans;
drop table if exists borrowers;
drop table if exists book_copies;
drop table if exists branches;
drop table if exists book_authors;
drop table if exists books;
drop table if exists publishers;


create table publishers
	(name varchar(25) not null, 
	address varchar(55), 
	phone varchar(12),
	PRIMARY KEY(name)) ENGINE = INNODB;

create table books
	(book_id int(4) not null, 
	title varchar(40), 
	publisher_name varchar(25),
	primary key (book_id),
	foreign key (publisher_name) references publishers(name) ON UPDATE CASCADE ON DELETE CASCADE 
	) ENGINE=INNODB;
	
create table book_authors
	(book_id int(4) not null,
	author_name varchar(20) not null,
	primary key (book_id, author_name), #composite primary id
	foreign key (book_id) references books(book_id) ON UPDATE CASCADE ON DELETE CASCADE 
	) ENGINE=INNODB;
	
create table branches
	(branch_id int(4) NOT NULL, 
	branch_name varchar(25), 
	address varchar(40),
	PRIMARY KEY(branch_id)
	) ENGINE = INNODB;	
	
create table book_copies
	(book_id int(4) NOT NULL, 
	branch_id int(4) NOT NULL, 
	no_copies INT(3),
	PRIMARY KEY(book_id, branch_id),
	FOREIGN KEY(book_id) REFERENCES books(book_id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(branch_id) REFERENCES branches(branch_id) ON UPDATE CASCADE ON DELETE CASCADE
	) ENGINE = INNODB;
	
create table borrowers
	(card_no int(4) NOT NULL, 
	name varchar(20), 
	address varchar(45),
	phone varchar(12), 
	unpaid_dues NUMERIC(5, 2),
	PRIMARY KEY(card_no)
	) ENGINE = INNODB;
			
create table book_loans(
	book_id int(4) not null,
	branch_id int(4) not null,
	card_no int(4) not null,
	date_out date,
	date_due date,
	date_returned date,
	primary key (book_id, branch_id, card_no),
	foreign key (book_id) references books(book_id) on update cascade on delete cascade,
	foreign key (branch_id) references branches(branch_id) on update cascade on delete cascade,
	foreign key (card_no) references borrowers(card_no) on update cascade on delete cascade
	) ENGINE = INNODB;