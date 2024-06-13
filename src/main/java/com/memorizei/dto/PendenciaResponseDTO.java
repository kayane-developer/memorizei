package com.memorizei.dto;

import java.time.LocalDateTime;

public interface PendenciaResponseDTO {

    Long getIdBaralho();
    String getNomeBaralho();
    Long getIdCard();
    String getPerguntaCard();
    String getRespostaCard();
    String getDificuldade();
    LocalDateTime getDataInsercao();

}
