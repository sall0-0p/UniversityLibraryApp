-- ==========================================
-- 1. REFERENCE DATA (Publishers, Authors, Categories, Series, Genres, Topics)
-- ==========================================

-- Publishers
INSERT INTO PUBLISHER (id, name) VALUES (1, 'Penguin Random House');
INSERT INTO PUBLISHER (id, name) VALUES (2, 'Bloomsbury Publishing');
INSERT INTO PUBLISHER (id, name) VALUES (3, 'Bantam Books');
INSERT INTO PUBLISHER (id, name) VALUES (4, 'Zondervan');
INSERT INTO PUBLISHER (id, name) VALUES (5, 'HarperCollins');

-- Authors
INSERT INTO AUTHOR (id, name) VALUES (1, 'J.R.R. Tolkien');
INSERT INTO AUTHOR (id, name) VALUES (2, 'J.K. Rowling');
INSERT INTO AUTHOR (id, name) VALUES (3, 'George R.R. Martin');
INSERT INTO AUTHOR (id, name) VALUES (4, 'God / Various');

-- Categories
INSERT INTO CATEGORY (id, name) VALUES (1, 'Fiction');
INSERT INTO CATEGORY (id, name) VALUES (2, 'Non-Fiction');
INSERT INTO CATEGORY (id, name) VALUES (3, 'Religious');

-- Series
INSERT INTO SERIES (id, name) VALUES (1, 'The Lord of the Rings');
INSERT INTO SERIES (id, name) VALUES (2, 'Harry Potter');
INSERT INTO SERIES (id, name) VALUES (3, 'A Song of Ice and Fire');

-- Genres
INSERT INTO GENRE (id, name) VALUES (1, 'High Fantasy');
INSERT INTO GENRE (id, name) VALUES (2, 'Young Adult');
INSERT INTO GENRE (id, name) VALUES (3, 'Political Fantasy');
INSERT INTO GENRE (id, name) VALUES (4, 'Theology');
INSERT INTO GENRE (id, name) VALUES (5, 'History');

-- Topics
INSERT INTO TOPIC (id, name) VALUES (1, 'Magic');
INSERT INTO TOPIC (id, name) VALUES (2, 'Dragons');
INSERT INTO TOPIC (id, name) VALUES (3, 'War');
INSERT INTO TOPIC (id, name) VALUES (4, 'Friendship');
INSERT INTO TOPIC (id, name) VALUES (5, 'Faith');
INSERT INTO TOPIC (id, name) VALUES (6, 'Betrayal');


-- ==========================================
-- 2. BOOKS
-- ==========================================

-- 2.1 TOLKIEN
INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (1, 'The Hobbit', 'J.R.R. Tolkien', '978-0547928227', 'COMPLETED', 1937, 310, 'A classic starting point.', 1, 1, 1);

INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (2, 'The Fellowship of the Ring', 'J.R.R. Tolkien', '978-0547928210', 'READING', 1954, 423, 'Taking it slow.', 1, 1, 1);

-- 2.2 HARRY POTTER
INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (3, 'Harry Potter and the Philosopher''s Stone', 'J.K. Rowling', '978-0747532743', 'COMPLETED', 1997, 223, 'Childhood favorite.', 2, 1, 2);

INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (4, 'Harry Potter and the Chamber of Secrets', 'J.K. Rowling', '978-0747538493', 'UNREAD', 1998, 251, 'Need to re-read.', 2, 1, 2);

INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (5, 'Harry Potter and the Prisoner of Azkaban', 'J.K. Rowling', '978-0747542155', 'UNREAD', 1999, 317, 'Best in the series?', 2, 1, 2);

INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (9, 'Harry Potter and the Goblet of Fire', 'J.K. Rowling', '978-0747546245', 'UNREAD', 2000, 636, 'Things get darker.', 2, 1, 2);

INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (10, 'Harry Potter and the Order of the Phoenix', 'J.K. Rowling', '978-0747551003', 'UNREAD', 2003, 766, 'Longest book.', 2, 1, 2);

INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (11, 'Harry Potter and the Half-Blood Prince', 'J.K. Rowling', '978-0747581086', 'UNREAD', 2005, 607, 'The beginning of the end.', 2, 1, 2);

INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (12, 'Harry Potter and the Deathly Hallows', 'J.K. Rowling', '978-0545010221', 'UNREAD', 2007, 607, 'The grand finale.', 2, 1, 2);

-- 2.3 GAME OF THRONES
INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (6, 'A Game of Thrones', 'George R.R. Martin', '978-0553103540', 'COMPLETED', 1996, 694, 'Winter is coming.', 3, 1, 3);

INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (7, 'A Clash of Kings', 'George R.R. Martin', '978-0553108033', 'READING', 1998, 768, 'Political intrigue.', 3, 1, 3);

-- 2.4 BIBLE
INSERT INTO BOOK (id, title, author_name, isbn, reading_status, publication_year, number_of_pages, notes, publisher_id, category_id, series_id)
VALUES (8, 'The Holy Bible (NIV)', 'Multiple', '978-0310434971', 'READING', 0, 1200, 'Daily reading.', 4, 3, NULL);


-- ==========================================
-- 3. JOIN TABLES
-- ==========================================

-- BOOK_AUTHOR
INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (1, 1), (2, 1);
INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (3, 2), (4, 2), (5, 2), (9, 2), (10, 2), (11, 2), (12, 2);
INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (6, 3), (7, 3);
INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (8, 4);

-- BOOK_GENRE
INSERT INTO BOOK_GENRE (book_id, genre_id) VALUES (1, 1), (2, 1);
INSERT INTO BOOK_GENRE (book_id, genre_id) VALUES (3, 2), (3, 1), (9, 2), (9, 1), (10, 2), (10, 1), (11, 2), (11, 1), (12, 2), (12, 1);
INSERT INTO BOOK_GENRE (book_id, genre_id) VALUES (6, 3), (6, 1);
INSERT INTO BOOK_GENRE (book_id, genre_id) VALUES (8, 4), (8, 5);

-- BOOK_TOPIC
INSERT INTO BOOK_TOPIC (book_id, topic_id) VALUES (1, 2);
INSERT INTO BOOK_TOPIC (book_id, topic_id) VALUES (2, 1), (2, 4);
INSERT INTO BOOK_TOPIC (book_id, topic_id) VALUES (3, 1), (3, 4), (9, 1), (9, 4), (10, 1), (10, 3), (11, 1), (11, 6), (12, 3), (12, 4);
INSERT INTO BOOK_TOPIC (book_id, topic_id) VALUES (6, 6), (6, 3), (6, 2);
INSERT INTO BOOK_TOPIC (book_id, topic_id) VALUES (8, 5);

-- ==========================================
-- 4. SEQUENCE RESET (CRITICAL FIX)
-- ==========================================
-- Manually inserting IDs prevents the DB sequence from updating. 
-- We must restart the sequences so new inserts don't clash with ID 1, 2, etc.

ALTER TABLE PUBLISHER ALTER COLUMN ID RESTART WITH 100;
ALTER TABLE AUTHOR    ALTER COLUMN ID RESTART WITH 100;
ALTER TABLE CATEGORY  ALTER COLUMN ID RESTART WITH 100;
ALTER TABLE SERIES    ALTER COLUMN ID RESTART WITH 100;
ALTER TABLE GENRE     ALTER COLUMN ID RESTART WITH 100;
ALTER TABLE TOPIC     ALTER COLUMN ID RESTART WITH 100;
ALTER TABLE BOOK      ALTER COLUMN ID RESTART WITH 100;