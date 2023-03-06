CREATE TABLE medication
(
    id                  SERIAL      PRIMARY KEY NOT NULL,
    name                VARCHAR       NOT NULL,
    code                VARCHAR       NOT NULL,
    image               VARCHAR(64)   NOT NULL,
    weight              VARCHAR(30)   NOT NULL,
    drone_id            SMALLINT      DEFAULT NULL,
    created_at          TIMESTAMP     NOT NULL DEFAULT now(),
    updated_at          TIMESTAMP     NOT NULL DEFAULT now(),
    CONSTRAINT medicationDroneId FOREIGN KEY (drone_id) REFERENCES drone(id)
)