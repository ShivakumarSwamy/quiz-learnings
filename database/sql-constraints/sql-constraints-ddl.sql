PRAGMA synchronous = OFF;
PRAGMA journal_mode = OFF;
PRAGMA count_changes = OFF;
PRAGMA cache_size = 10000;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    -- to have username as primary key for it to be unique, NOT NULL for sqlite
      username TEXT PRIMARY KEY NOT NULL,
      password TEXT NOT NULL,
      role TEXT NOT NULL
);

INSERT INTO users VALUES ("admin", "19700224", "administrator");
INSERT INTO users VALUES ("john", "johnny1", "user");
INSERT INTO users VALUES ("kim", "12345", "user");
INSERT INTO users VALUES ("dani", "password", "user");

VACUUM;
