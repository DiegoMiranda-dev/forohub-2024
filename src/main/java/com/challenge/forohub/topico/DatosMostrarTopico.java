package com.challenge.forohub.topico;

import java.time.LocalDateTime;

public record DatosMostrarTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String status,
                                 String autor, String curso) {


    public DatosMostrarTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );

    }
}
