SELECT Product.model, price
FROM Product 
INNER JOIN PC ON Product.model = PC.model
WHERE maker = 'B'
UNION
SELECT Product.model, price
FROM Product
INNER JOIN Laptop ON Product.model = Laptop.model
WHERE maker = 'B'
UNION
SELECT Product.model, price
FROM Product
INNER JOIN Printer ON Product.model = Printer.model
WHERE maker = 'B'