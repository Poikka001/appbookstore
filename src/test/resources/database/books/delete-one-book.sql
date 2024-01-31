DELETE FROM books_categories WHERE book_id = (SELECT id FROM books WHERE title = 'book_title' AND price = 10);
DELETE FROM books WHERE title = 'book_title' AND price = 10;
