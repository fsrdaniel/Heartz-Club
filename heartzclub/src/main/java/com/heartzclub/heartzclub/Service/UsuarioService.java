package com.heartzclub.heartzclub.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.Period;

import com.heartzclub.heartzclub.Exception.EmailJaCadastradoException;
import org.springframework.stereotype.Service;

import com.heartzclub.heartzclub.DTO.LoginDto;
import com.heartzclub.heartzclub.DTO.UsuarioRequestDTO;
import com.heartzclub.heartzclub.Exception.UsuarioNotFoundException;
import com.heartzclub.heartzclub.Model.Usuario;
import com.heartzclub.heartzclub.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public Usuario criar(UsuarioRequestDTO dto) {

        if (repository.existsByEmail(dto.email())) {
            throw new EmailJaCadastradoException("Email já cadastrado.");
        }

        // 1. Calcula e valida a idade com base no LocalDate que vem do DTO
        int idadeCalculada = verificaIdade(dto.dataNascimento());

        // 2. Passa a idade calculada para a sua entidade Usuario
        var usuario = new Usuario(
                dto.nome(),
                dto.email(),
                idadeCalculada,
                dto.cpf(),
                dto.endereco(),
                dto.senha()
        );

        return repository.save(usuario);
    }

    public Usuario atualizar(Long id, UsuarioRequestDTO dto) {
        var usuario = findById(id);
        usuario.setNome(dto.nome());
        usuario.setEndereco(dto.endereco());

        // Atualiza a idade dinamicamente no update também, se necessário
        usuario.setIdade(verificaIdade(dto.dataNascimento()));

        return repository.save(usuario);
    }

    public void deletar(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    // --- REESCRITO PARA USAR LOCALDATE ---
    private int verificaIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento é obrigatória.");
        }

        LocalDate dataAtual = LocalDate.now();
        int idade = Period.between(dataNascimento, dataAtual).getYears();

        if (idade < 0) {
            throw new IllegalArgumentException("A data de nascimento não pode ser uma data futura.");
        }

        return idade;
    }

    public Usuario login(LoginDto dto) {
        var usuario = repository.findByEmail(dto.email()).orElseThrow(() -> new UsuarioNotFoundException("Usuario não encontrado"));

        if (!usuario.getSenha().equals(dto.senha())) {
            throw new RuntimeException("Email ou senha incorretas. Tente novamente");
        }

        return usuario;
    }

    public Map<String, Object> obterDadosPerfil(Long id) {
        var usuario = findById(id);

        Map<String, Object> dados = new HashMap<>();
        dados.put("id", usuario.getId());
        dados.put("nome", usuario.getNome());
        dados.put("idade", usuario.getIdade());
        dados.put("endereco", usuario.getEndereco());

        dados.put("seguidores", usuario.getSeguidores() != null ? usuario.getSeguidores().size() : 0);
        dados.put("seguindo", usuario.getSeguindo() != null ? usuario.getSeguindo().size() : 0);
        dados.put("publicacoes", usuario.getPublicacoes() != null ? usuario.getPublicacoes().size() : 0);

        return dados;
    }
}