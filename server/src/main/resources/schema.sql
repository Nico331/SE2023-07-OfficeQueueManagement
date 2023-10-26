
INSERT INTO service_types(id, tag, code, service_time, working)
VALUES
    (1, 'Payments', 'P', 10, true ),
    (2, 'Delivery', 'D', 15, true ),
    (3, 'Cards', 'C', 20, true); -- ci sono 3 tipi di servizio, pagamenti, delivery e carte
INSERT INTO counters(id, number, working)
VALUES
    (1, 1, true),
    (2, 2, true ); -- ci sono 2 counter
INSERT INTO counter_service_types(id, counter_id, service_type_id)
VALUES
    (1,1,2),
    (2,2,2),
    (3,1,1); -- il counter 1 fa i servici 1 e 2, il counter 2 fa solo il servizio 2

INSERT INTO tickets( number, timestamp, date_issued, service_type_id, status)
VALUES
    ( '1', '2023-10-22 13:10:00', '2023-10-22', '1', 'waiting'),
    ( '2', '2023-10-22 13:13:00', '2023-10-22', '1', 'waiting'),
    ( '1', '2023-10-22 13:16:00', '2023-10-22', '2', 'waiting'),
    ( '2', '2023-10-22 13:17:00', '2023-10-22', '2', 'waiting'),
    ( '3', '2023-10-22 13:18:00', '2023-10-22', '3', 'waiting'); -- ci sono 5 ticket tutti in attesa
