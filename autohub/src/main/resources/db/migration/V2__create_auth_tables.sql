create table if not exists users(
  id varchar(255) not null ,
  name  varchar(255) not null unique,
  email  varchar(255) not null unique,
  u_password varchar(255) not null unique,
  enabled boolean default false,
  role varchar(255) default USER,
  created_at timestamp(6),
  updated_at timestamp(6),
  primary key(id)
);

-- password reset token table
create table if not exists password_reset_tokens(
  id varchar(255) not null,
  token varchar(255) not null,
  expires_in timestamp(6),
  user_id varchar(255) not null unique,
  created_at timestamp(6),
  updated_at timestamp(6),
  primary key(id)
);

-- verification token
create table if not exists verification_tokens(
  id varchar(255) not null,
  token varchar(255) not null,
  expires_in timestamp(6),
  user_id varchar(255) not null unique,
  created_at timestamp(6),
  updated_at timestamp(6),
  primary key(id)
);