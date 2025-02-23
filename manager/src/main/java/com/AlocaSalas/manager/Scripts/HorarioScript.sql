CREATE OR REPLACE FUNCTION gerar_horarios()
RETURNS VOID AS $$
DECLARE
    hora_atual TIME := '07:00';
    hora_fim_turno TIME;
    dia_semana_atual VARCHAR(20);
    turno_atual VARCHAR(20);
    hora_fim TIME;
BEGIN
    -- Loop para gerar horários para cada dia da semana e turno
    FOREACH dia_semana_atual IN ARRAY ARRAY['SEGUNDA', 'TERCA', 'QUARTA', 'QUINTA', 'SEXTA', 'SABADO', 'DOMINGO'] LOOP
        -- Reinicia hora_atual para 07:00 no início de cada dia
        hora_atual := '07:00';

        FOREACH turno_atual IN ARRAY ARRAY['MATUTINO', 'VESPERTINO', 'NOTURNO'] LOOP
            -- Define hora de fim do turno
            CASE turno_atual
                WHEN 'MATUTINO' THEN hora_fim_turno := '12:00';
                WHEN 'VESPERTINO' THEN hora_fim_turno := '18:00';
                WHEN 'NOTURNO' THEN hora_fim_turno := '22:00';
            END CASE;

            -- Loop para gerar horários dentro do turno
            WHILE hora_atual < hora_fim_turno LOOP
                -- Verifica se hora_atual está no intervalo a ser excluído
                IF hora_atual >= '12:00' AND hora_atual < '13:00' THEN
                    hora_atual := '13:00'; -- Ajusta hora_atual para 13:00
                END IF;

                -- Calcula hora de fim com intervalo de 50 minutos
                hora_fim := hora_atual + INTERVAL '50 minutes';

                -- Verifica se hora_fim ultrapassa o limite do turno
                IF hora_fim > hora_fim_turno THEN
                    hora_fim := hora_fim_turno;
                END IF;

                -- Insere horário na tabela Horario
                INSERT INTO Horario (HORA_INICIO, HORA_FIM, DIA_SEMANA, TURNO_HORARIO)
                VALUES (hora_atual::VARCHAR(5), hora_fim::VARCHAR(5), dia_semana_atual, turno_atual);

                -- Avança para próximo horário
                hora_atual := hora_fim;
            END LOOP;
        END LOOP;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT gerar_horarios();