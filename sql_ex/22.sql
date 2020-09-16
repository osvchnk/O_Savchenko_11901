SELECT speed, AVG(price) as avg_price
FROM PC
WHERE speed > 600
GROUP BY speed
