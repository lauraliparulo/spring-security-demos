INSERT INTO users (username, password, enabled)
  values ('alice',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
    1);
    
INSERT INTO users (username, password, enabled)
  values ('admin',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
    1);
    

INSERT INTO authorities (username, authority)
  values ('alice', 'ROLE_USER');
  INSERT INTO authorities (username, authority)
  values ('admin', 'ROLE_ADMIN');