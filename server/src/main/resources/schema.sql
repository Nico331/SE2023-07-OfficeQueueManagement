
INSERT INTO service_types(id, tag, code, service_time, working)
VALUES
    (1, 'Payments', 'P', 10, true ),
    (2, 'Delivery', 'D', 15, true );
INSERT INTO counters(id, number, working)
VALUES
    (1, 1, true),
    (2, 2, true ); -- ci sono 2 counter
INSERT INTO counter_service_types(id, counter_id, service_type_id)
VALUES
    (1,1,2),
    (2,2,2),
    (3,1,1); -- il counter 1 fa i servici 1 e 2, il counter 2 fa solo il servizio 2
