SELECT maker, MAX(price) as max_price
FROM Product INNER JOIN PC
ON Product.model = PC.model
GROUP BY maker
