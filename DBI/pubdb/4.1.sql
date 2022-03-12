CREATE VIEW pubCounts (pub_name, title_count) AS
SELECT pub_name, count(*)
FROM publishers p, titles t
WHERE p.pub_id  =  t.pub_id
GROUP BY pub_name
go
SELECT pub_name, title_count
FROM pubCounts
WHERE title_count =
(
  SELECT MAX(title_count)
  FROM pubCounts
)
