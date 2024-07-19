CREATE TABLE IF NOT EXISTS vehicles(
  id VARCHAR(256) PRIMARY KEY,
  name VARCHAR(256) NOT NULL,
   make VARCHAR(256),
  model VARCHAR(256),
  location VARCHAR(256),
   price INTEGER,
   mileage INTEGER,
 description VARCHAR(256),
  fuel_type VARCHAR(256),
   drive_train VARCHAR(256),
   transmission_type VARCHAR(256),
   engine_aspiration VARCHAR(256),
      engine_layout VARCHAR(256),
      created_at TIMESTAMP,
     updated_at TIMESTAMP
);

CREATE TABLE files(
  id VARCHAR(256) PRIMARY KEY,
  fileUrl VARCHAR(256),
  fileName VARCHAR(256),
  fileSize INTEGER,
  vehicle_id VARCHAR(256)
)