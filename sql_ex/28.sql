SELECT COUNT(qty) FROM
(SELECT COUNT(maker) as qty FROM Product
GROUP BY maker
HAVING COUNT(maker) = 1) x