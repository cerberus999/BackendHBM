package com.hirsoshia.motors.api.service;

import com.hirsoshia.motors.api.dto.request.LoginRequest;
import com.hirsoshia.motors.api.dto.request.RegisterRequest;
import com.hirsoshia.motors.api.dto.response.LoginResponse;
import com.hirsoshia.motors.api.exception.BadRequestException;
import com.hirsoshia.motors.api.exception.ResourceNotFoundException;
import com.hirsoshia.motors.api.model.clientela.Usuario;
import com.hirsoshia.motors.api.repository.clientela.UsuarioRepository;
import com.hirsoshia.motors.api.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider,
                       AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        String token = jwtTokenProvider.generateToken(usuario.getUsername(), usuario.getRol(), usuario.getIdUsuario());

        return new LoginResponse(token, "Bearer", usuario.getUsername(), usuario.getRol(), usuario.getIdUsuario());
    }

    public void register(RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.username())) {
            throw new BadRequestException("El username ya existe");
        }

        if (!request.rol().equals("admin") && !request.rol().equals("cliente")) {
            throw new BadRequestException("Rol inválido: debe ser admin o cliente");
        }

        Usuario usuario = Usuario.builder()
                .username(request.username())
                .passwordHash(passwordEncoder.encode(request.password()))
                .rol(request.rol())
                .idGerente(request.idGerente())
                .idCliente(request.idCliente())
                .activo(true)
                .build();

        usuarioRepository.save(usuario);
    }
}
