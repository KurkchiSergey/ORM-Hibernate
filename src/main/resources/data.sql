
INSERT INTO persons (name, surname, age, phone_number, city_of_living) VALUES
    ('Ivan',   'Ivanov',   30, '+79001112233', 'Moscow'),
    ('Anna',   'Sidorova', 40, '+79007778899', 'Moscow'),
    ('Petr',   'Petrov',   25, '+79004445566', 'Kazan'),
    ('Olga',   'Smirnova', 35, '+79001234567', 'Saint Petersburg')
ON CONFLICT DO NOTHING;
