--- Sistemas de reserva (hotevel/pousada)

-- Considerando o aprendizado dos últimos assuntos, Procedures, Functions e Triggers no
-- PostgreSQL, o aluno irá desenvolver, ao menos:
-- Um procedimento (procedure) no SGBD Postgres;
-- Uma função (function) no SGBD Postgres; e
-- Um trigger, também no SGBD Postgres.

CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE quartos (
    id SERIAL PRIMARY KEY,
    numero INT UNIQUE NOT NULL,
    tipo VARCHAR(50), -- exemplo: solteiro, casal, luxo
    preco_diaria NUMERIC(10,2) NOT NULL
);

CREATE TABLE reservas (
    id SERIAL PRIMARY KEY,
    cliente_id INT REFERENCES clientes(id),
    quarto_id INT REFERENCES quartos(id),
    data_checkin DATE NOT NULL,
    data_checkout DATE NOT NULL,
    valor_total NUMERIC(10,2)
);

-- Function: calcular valor total da reserva
CREATE OR REPLACE FUNCTION total_reserva(quarto INT, entrada DATE, saida DATE)
  RETURNS NUMERIC
  LANGUAGE plpgsql
  AS
$$
DECLARE
  dias INT;
  preco NUMERIC;
BEGIN
  SELECT preco_diaria INTO preco FROM quartos WHERE id = quarto;
  
  dias := GREATEST((saida - entrada), 1);
  
  RETURN dias * preco;
END;
$$

-- Procedure: realizar reserva
CREATE OR REPLACE PROCEDURE realizar_reserva(
  p_cliente_id INT, 
  p_quarto_id INT, 
  p_checkin DATE, 
  p_checkout DATE
)
LANGUAGE plpgsql
AS $$
DECLARE
  v_valor NUMERIC;
BEGIN
    -- calcula o valor da reserva
    v_valor := total_reserva(p_quarto_id, p_checkin, p_checkout);

    -- insere a reserva
    INSERT INTO reservas (cliente_id, quarto_id, data_checkin, data_checkout, valor_total)
    VALUES (p_cliente_id, p_quarto_id, p_checkin, p_checkout, v_valor);
END;
$$;

-- Trigger: impedir reserva duplicada para o mesmo quarto
CREATE OR REPLACE FUNCTION verificar_conflito_reserva()
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM reservas
        WHERE quarto_id = NEW.quarto_id
          AND (
            (NEW.data_checkin BETWEEN data_checkin AND data_checkout) OR
            (NEW.data_checkout BETWEEN data_checkin AND data_checkout) OR
            (data_checkin BETWEEN NEW.data_checkin AND NEW.data_checkout)
          )
    ) THEN
        RAISE EXCEPTION 'Já existe uma reserva para este quarto nesse período.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_verifica_reserva
BEFORE INSERT ON reservas
FOR EACH ROW
EXECUTE FUNCTION verificar_conflito_reserva();

-- testand
INSERT INTO clientes (nome, email) VALUES ('Maria Silva', 'maria@email.com');

INSERT INTO quartos (numero, tipo, preco_diaria) VALUES (101, 'casal', 200.00);

CALL realizar_reserva(1, 1, '2025-07-20', '2025-07-23');