
-- Вставка пользователей
INSERT INTO users (email, password_hash, first_name, last_name, middle_name, phone, created_at, status)
VALUES
('ivanov@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrYV5ZYZqRazIVX8Z7WUeDQCkd4Z4f2', 'Иван', 'Иванов', 'Иванович', '+79161234567', NOW(), 'ACTIVE'),
('petrova@example.com', '$2a$10$BqTb8h5Y9V6eE3wQ6Xr0Ue9vL0JkM5NcR2sD3fG4hH5iJ7kL8m9n0', 'Мария', 'Петрова', 'Сергеевна', '+79167654321', NOW(), 'ACTIVE');

-- Вставка карт (связь с пользователями)
INSERT INTO cards (number_masked, email, password, expiry_date, cvv_encrypted, status, balance, created_at, user_id)
VALUES
('510510******5100', 'ivanov@example.com', '$2a$10$xJwL5v5Jz5UZJf5h5f5Z5.', (NOW() + INTERVAL '3 years'), '123', 'ACTIVE', '10000.00', NOW(), 1),
('411111******1111', 'petrova@example.com', '$2a$10$yJwL5v5Jz5UZJf5h5f5Z5.', (NOW() + INTERVAL '2 years'), '456', 'ACTIVE', '5000.50', NOW(), 2);