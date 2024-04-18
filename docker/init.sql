CREATE SCHEMA IF NOT EXISTS veiculos_schema;

CREATE TABLE IF NOT EXISTS veiculos_schema.veiculo (
    id BIGSERIAL PRIMARY KEY,
    veiculo VARCHAR(255) NOT NULL,
    marca VARCHAR(255) NOT NULL,
    cor VARCHAR(255) NOT NULL,
    ano INTEGER NOT NULL,
    descricao TEXT NOT NULL,
    vendido BOOLEAN NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    updated TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()
);

INSERT INTO veiculos_schema.veiculo (veiculo, marca, cor, ano, descricao, vendido, created, updated) VALUES
('gol', 'volkswagen', 'preto', 2019, 'Descrição Gol.', false, NOW(), NOW()),
('civic', 'honda', 'branco', 2018, 'Descrição Civic.', false, NOW(), NOW()),
('fit', 'honda', 'preto', 2018, 'Descrição fit.', false, NOW(), NOW()),
('polo', 'volkswagen', 'cinza', 2018, 'Descrição polo.', false, NOW(), NOW()),
('corolla', 'toyota', 'prata', 2020, 'Descrição Corolla.', true, NOW(), NOW());
