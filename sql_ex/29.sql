SELECT Outcome_o.point, Outcome_o.date, inc, out
FROM Outcome_o LEFT JOIN Income_o 
ON Income_o.point = Outcome_o.point AND Income_o.date = Outcome_o.date
UNION
SELECT Income_o.point, Income_o.date, inc, out
FROM Income_o LEFT JOIN Outcome_o 
ON Outcome_o.point = Income_o.point AND Outcome_o.date = Income_o.date