CREATE TABLE library_books (
    book_id int AUTO_INCREMENT,
    title varchar(255),
    author varchar(255),
    copies int,
    PRIMARY KEY (book_id)
);

CREATE TABLE library_members (
    member_id int AUTO_INCREMENT,
    forename varchar(255),
    surname varchar(255),
	librarian boolean,
    PRIMARY KEY (member_id)
);

CREATE TABLE books_taken_out (
    transaction_id int AUTO_INCREMENT,
    book_id int,
    member_id int,
    date_due dateDue,
    PRIMARY KEY (transaction_id),
    FOREIGN KEY (book_id) REFERENCES library_books(book_id),
    FOREIGN KEY (member_id) REFERENCES library_members(member_id)
);
