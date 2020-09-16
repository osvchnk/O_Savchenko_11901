SELECT DISTINCT type, Laptop.model, speed 
FROM Laptop, Product
WHERE Product.type = 'Laptop' AND
Laptop.speed < ALL (SELECT speed FROM PC)
