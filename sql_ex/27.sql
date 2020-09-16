SELECT maker, AVG(hd)
FROM Product INNER JOIN PC
ON PC.model = Product.model
WHERE maker IN(SELECT DISTINCT maker FROM Product WHERE type = 'printer')
GROUP BY maker
