SELECT Ships.class, name, country 
FROM Ships LEFT JOIN Classes
ON Ships.class = Classes.class
WHERE numGuns >= 10