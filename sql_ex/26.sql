SELECT AVG(price)
FROM (SELECT price FROM Product
INNER JOIN PC ON PC.model = Product.model
WHERE maker = 'A'
UNION ALL
SELECT price FROM Product
INNER JOIN Laptop ON Laptop.model = Product.model
WHERE maker = 'A') x