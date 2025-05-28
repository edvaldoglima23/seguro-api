package com.example.seguro.controller;

import com.example.seguro.dto.ApoliceDTO;
import com.example.seguro.model.TipoSeguro;
import com.example.seguro.service.ApoliceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/apolices")
@RequiredArgsConstructor
public class ApoliceController {

    private final ApoliceService apoliceService;

    @GetMapping
    public ResponseEntity<List<ApoliceDTO>> listarTodas() {
        return ResponseEntity.ok(apoliceService.listarTodasApolices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApoliceDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(apoliceService.buscarPorId(id));
    }

    @GetMapping("/numero/{numeroApolice}")
    public ResponseEntity<ApoliceDTO> buscarPorNumero(@PathVariable String numeroApolice) {
        return ResponseEntity.ok(apoliceService.buscarPorNumero(numeroApolice));
    }

    @GetMapping("/tipo/{tipoSeguro}")
    public ResponseEntity<List<ApoliceDTO>> buscarPorTipo(@PathVariable TipoSeguro tipoSeguro) {
        return ResponseEntity.ok(apoliceService.buscarPorTipo(tipoSeguro));
    }

    @GetMapping("/segurado")
    public ResponseEntity<List<ApoliceDTO>> buscarPorNomeSegurado(@RequestParam String nome) {
        return ResponseEntity.ok(apoliceService.buscarPorNomeSegurado(nome));
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<List<ApoliceDTO>> buscarPorDocumentoSegurado(@PathVariable String documento) {
        return ResponseEntity.ok(apoliceService.buscarPorDocumentoSegurado(documento));
    }

    @GetMapping("/vigentes")
    public ResponseEntity<List<ApoliceDTO>> listarVigentes() {
        return ResponseEntity.ok(apoliceService.buscarApolicesVigentes());
    }

    @GetMapping("/a-vencer")
    public ResponseEntity<List<ApoliceDTO>> listarAVencer() {
        return ResponseEntity.ok(apoliceService.buscarApolicesAVencer30Dias());
    }

    @PostMapping
    public ResponseEntity<ApoliceDTO> criar(@Valid @RequestBody ApoliceDTO apoliceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(apoliceService.salvar(apoliceDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApoliceDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ApoliceDTO apoliceDTO) {
        return ResponseEntity.ok(apoliceService.atualizar(id, apoliceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        apoliceService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}