CREATE TABLE IF NOT EXISTS comments(
  id varchar(255) primary key,
  comment varchar(255) not null,
  vehicle_id varchar(255) not null,
  created_at timestamp,
  updated_at timestamp
);