-- data.sql
INSERT INTO PUBLISHER (id, name) VALUES (1, 'Penguin Random House');
INSERT INTO AUTHOR (id, name) VALUES (1, 'J.R.R. Tolkien');
INSERT INTO CATEGORY (id, name) VALUES (1, 'Fantasy');
INSERT INTO BOOK (title, isbn, reading_status, publisher_id, category_id)
VALUES ('The Hobbit', '978-0547928227', 'COMPLETED', 1, 1);