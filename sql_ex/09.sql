SELECT DISTINCT maker 
FROM Product INNER JOIN PC 
ON Product.model=PC.model
WHERE speed>=450