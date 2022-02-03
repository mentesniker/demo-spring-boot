CREATE VIEW ADOPTADOS AS (SELECT perrito.id, perrito.nombre, dueno.nombre AS pertenece, perrito.imagen FROM perrito JOIN dueno ON perrito.p
ertenece = dueno.id);