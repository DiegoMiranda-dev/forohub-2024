package com.challenge.forohub.controller;


import com.challenge.forohub.service.TopicoRepository;
import com.challenge.forohub.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicosRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> enviarDatos(@RequestBody @Valid DatosRegistrarTopico datosRegistrarTopico, UriComponentsBuilder uriBuilder) {
        Topico topico = topicosRepository.save(new Topico(datosRegistrarTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso());
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);

    }

    @GetMapping
    public ResponseEntity<Page<DatosMostrarTopico>> listarTopicos(Pageable pageable) {
        return ResponseEntity.ok(topicosRepository.findByActivoTrue(pageable).map(DatosMostrarTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity retornarDatosTopico(@PathVariable Long id) {
        Topico topico = topicosRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso());
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarDatos(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicosRepository.getReferenceById(datosActualizarTopico.id());
        if (topico.getId() != null) {
        topico.actualizarDatos(datosActualizarTopico);
        } else {
            System.out.println("No se encontró el tópico");
        }

        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id ) {
        Topico topico = topicosRepository.getReferenceById(id);
        topicosRepository.delete(topico);
        return ResponseEntity.ok("Tópico eliminado con éxito :)");
    }


}
