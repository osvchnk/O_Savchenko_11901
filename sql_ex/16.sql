SELECT DISTINCT A.model as m1, B.model as m2, A.speed, A.ram 
FROM PC AS A, PC AS B
WHERE A.speed = B.speed AND
A.ram = B.ram AND 
A.model > B.model