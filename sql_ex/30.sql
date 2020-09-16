WITH t1 AS (
SELECT point, date, SUM(inc) AS inc
FROM Income
GROUP BY point, date),
t2 AS (
SELECT point, date, SUM(out) AS out
FROM Outcome
GROUP BY point, date)

SELECT t1.point, t1.date, t2.out, t1.inc
FROM t1 LEFT JOIN t2
ON t1.point = t2.point AND t1.date = t2.date
UNION
SELECT t2.point, t2.date, t2.out, t1.inc
FROM t1 RIGHT JOIN t2
ON t1.point = t2.point AND t1.date = t2.date