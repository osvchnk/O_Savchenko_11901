SELECT model, price 
FROM Printer
WHERE price = (SELECT MAX(price) FROM Printer)