SELECT DISTINCT maker FROM Product INNER JOIN PC
ON Product.model = PC.model
WHERE speed >= 750
INTERSECT
SELECT DISTINCT maker FROM Product INNER JOIN Laptop
ON Product.model = Laptop.model
WHERE speed >= 750