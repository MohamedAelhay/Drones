CREATE TABLE drone
(
     id                         SERIAL      PRIMARY KEY NOT NULL,
     serial_number              UUID                    NOT NULL,
     drone_model                VARCHAR(30)             NOT NULL,
     drone_state                VARCHAR(30)             NOT NULL,
     weight_limit               VARCHAR(30)             NOT NULL,
     battery_capacity           VARCHAR(30)             NOT NULL,
     created_at                 TIMESTAMP               NOT NULL DEFAULT now(),
     updated_at                 TIMESTAMP               NOT NULL DEFAULT now()
)