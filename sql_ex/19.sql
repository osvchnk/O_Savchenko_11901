SELECT maker, AVG(screen) as avg 
FROM Product INNER JOIN Laptop
ON Product.model = Laptop.model
GROUP BY maker