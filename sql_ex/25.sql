SELECT DISTINCT maker FROM Product
INNER JOIN PC 
ON PC.model = Product.model
WHERE maker IN(SELECT maker FROM Product WHERE type = 'printer')
AND ram = (SELECT MIN(ram) FROM PC)
AND speed = (SELECT MAX(speed) FROM PC WHERE ram = (SELECT MIN(ram) FROM PC))
