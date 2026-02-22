CREATE DATABASE IF NOT EXISTS synapse_db;
USE synapse_db;

CREATE TABLE IF NOT EXISTS data_flux (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    payload TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

INSERT INTO data_flux (source, destination, payload, status, created_at, updated_at)
VALUES 
    ('ERP', 'WMS', '{"order_id": "CMD-001", "quantity": 150}', 'PENDING', NOW(), NOW()),
    ('ERP', 'WMS', '{"order_id": "CMD-002", "quantity": 75}', 'SUCCESS', NOW(), NOW()),
    ('ERP', 'WMS', '{"order_id": "CMD-003", "quantity": 200}', 'FAILED', NOW(), NOW());
