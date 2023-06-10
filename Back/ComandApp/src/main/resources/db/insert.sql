INSERT INTO mesas(ID, NUMERO) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
    (4, 4),
    (5, 5);

INSERT INTO productos(ID, CANTIDAD, NOMBRE, PRECIO, TIPO) VALUES
	(1, 500, 'Coca-Cola', 1.80, 0),
    (2, 500, 'Agua pequeña', 1.50, 0),
    (3, 500, 'Agua grande', 2.30, 0),
    (4, 500, 'Fanta limon', 1.80, 0),
    (5, 500, 'Fanta naranja', 1.80, 0),
    (6, 500, 'Cerveza', 2, 0),
    (7, 500, 'Copa tinto', 1.80, 0),
    (8, 500, 'Copa Verdejo', 1.80, 0),
    (9, 200, 'Patatas fritas', 2.30, 1),
    (10, 200, 'Ensaladilla rusa', 2.80, 1),
    (11, 200, 'Croquetas', 3.50, 1),
    (12, 200, 'Hamburguesa', 6.70, 1),
    (13, 200, 'Flamenquin', 1.80, 1),
    (14, 200, 'Pizza', 7.30, 1),
    (15, 200, 'Entrecot', 14.30, 1),
    (16, 200, 'Campero', 5.30, 1),
    (17, 200, 'Rabo de toro', 12.30, 1),
    (18, 200, 'Pulpo', 9.50, 1),
    (19, 200, 'Rosada', 8.60, 1),
    (20, 100, 'Flan', 3.99, 2),
    (21, 100, 'Natillas', 3.99, 2),
    (22, 100, 'Tarta de queso', 4.50, 2),
    (23, 100, 'Arroz con leche', 4.50, 2),
    (24, 100, 'Tarta de zanahoria', 4.50, 2),
    (25, 100, 'Milhojas', 4.50, 2),
    (26, 100, 'Melocoton', 4.50, 2),
    (27, 100, 'Piña', 4.50, 2),
    (28, 100, 'Helado', 4.50, 2);

INSERT INTO productos_mesa(MESA_ID ,PRODUCTO_ID) VALUES
	(1, 1),
    (1, 10),
    (1, 25),
    (1, 6),
    (1, 28),
    (1, 1),
    (1, 22),
    (1, 10),
    (1, 14);


