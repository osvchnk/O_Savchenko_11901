SELECT maker, COUNT(model) as q
FROM Product
WHERE type = 'PC'
GROUP BY maker
HAVING COUNT(model) >= 3
