
INSERT INTO products(product_id, name, brand, type)
VALUES
    (1, 'Samsung Galaxy S8', 'Samsung', 'Cellulare'),
    (2, 'iPhone X', 'Apple', 'Cellulare');

INSERT INTO users(email, username, name, surname, city, region, phone_number, date_of_birth)
VALUES
    ('client1@gmail.com', 'client1', 'Marco', 'Rossi', 'Roma', 'Lazio', '3331111111', '1990-05-15'),
    ('client5@gmail.com', 'client5', 'Alessandro', 'Ferrari', 'Firenze', 'Toscana', '3335555555', '1994-03-28');

INSERT INTO interns(email, username, name, surname, city, region, phone_number, date_of_birth, role, expertize)
(VALUES
    ('expert1@gmail.com', 'expert1', 'Valentino', 'Verdi', 'Rome', 'Lazio', '3331111111', '1995-06-20', 'app_intern', 'Cellulare'),
    ('manager2@gmail.com', 'manager2', 'Luisa', 'Bara', 'Florence', 'Tuscany', '3335555555', '1988-07-30', 'app_manager', 'Management Expertise 2');
