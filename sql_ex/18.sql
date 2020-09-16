SELECT DISTINCT maker, price
FROM Product INNER JOIN Printer
ON Product.model = Printer.model
WHERE price = (SELECT MIN(price) FROM Printer WHERE color = 'y') AND
color = 'y'