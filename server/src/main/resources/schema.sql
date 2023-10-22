
INSERT INTO service_types(id, tag, service_time)
VALUES
    (1, 'Payments', 10 ),
    (2, 'Delivery', 15 ),
    (3, 'Cards', 20); -- ci sono 3 tipi di servizio, pagamenti, delivery e carte
INSERT INTO counters(id, number)
VALUES
    (1, 1),
    (2, 2 ); -- ci sono 2 counter
INSERT INTO counter_service_types(id, counter_id, service_type_id)
VALUES
    (1,1,2),
    (2,2,2),
    (3,1,1); -- il counter 1 fa i servici 1 e 2, il counter 2 fa solo il servizio 2

INSERT INTO tickets(id, number, timestamp, service_type_id, counter_id, status)
VALUES
    (1, '1', '2023-10-22 13:10:00', '1', '1','waiting'),
    (2, '2', '2023-10-22 13:13:00', '1', '1','waiting'),
    (3, '1', '2023-10-22 13:16:00', '2', '2','waiting'),
    (4, '2', '2023-10-22 13:17:00', '2', '2','waiting'),
    (5, '3', '2023-10-22 13:18:00', '3', '2','waiting'); -- ci sono 5 ticket tutti in attesa
