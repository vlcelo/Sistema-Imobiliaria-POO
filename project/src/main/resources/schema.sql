PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS cliente (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nome TEXT NOT NULL,
  cpf TEXT UNIQUE NOT NULL,
  telefone TEXT,
  email TEXT
);

CREATE TABLE IF NOT EXISTS imovel (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  endereco TEXT NOT NULL UNIQUE,
  cidade TEXT NOT NULL,
  tipo TEXT NOT NULL,
  quartos INTEGER NOT NULL,
  banheiros INTEGER NOT NULL,
  area_m2 REAL NOT NULL,
  valor_aluguel REAL NOT NULL,
  status TEXT NOT NULL DEFAULT 'DISPONIVEL'
);

CREATE TABLE IF NOT EXISTS contrato (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  cliente_id INTEGER NOT NULL,
  imovel_id INTEGER NOT NULL,
  valor_mensal REAL NOT NULL,
  data_inicio TEXT NOT NULL,
  data_fim TEXT NOT NULL,
  status TEXT NOT NULL DEFAULT 'ATIVO',
  FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE RESTRICT,
  FOREIGN KEY (imovel_id) REFERENCES imovel(id) ON DELETE RESTRICT
);

-- Seed
INSERT OR IGNORE INTO cliente (nome, cpf, telefone, email) VALUES
('Maria Silva','11111111111','11900000001','maria@email.com'),
('João Souza','22222222222','11900000002','joao@email.com'),
('Ana Lima','33333333333','11900000003','ana@email.com');

INSERT OR IGNORE INTO imovel (endereco, cidade, tipo, quartos, banheiros, area_m2, valor_aluguel, status) VALUES
('Rua A, 100','Joinville','Apartamento',2,1,60,1800,'DISPONIVEL'),
('Rua B, 200','Joinville','Casa',3,2,120,3000,'DISPONIVEL'),
('Av. Central, 50','Joinville','Sala Comercial',0,1,35,1500,'DISPONIVEL');
