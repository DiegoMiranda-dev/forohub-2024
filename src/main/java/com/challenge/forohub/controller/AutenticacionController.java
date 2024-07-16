package com.challenge.forohub.controller;

import com.challenge.forohub.infra.security.TokenService;
import com.challenge.forohub.usuarios.DatosAutenticacionUsuario;
import com.challenge.forohub.usuarios.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
       Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.username(), datosAutenticacionUsuario.password());
       var authUsuario =  authenticationManager.authenticate(authToken);
        var tokenJWT = tokenService.generarToken((Usuario) authUsuario.getPrincipal());
        return ResponseEntity.ok(tokenJWT);
    }
}
