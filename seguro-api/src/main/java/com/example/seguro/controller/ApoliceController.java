package com.example.seguro.controller;

import com.example.seguro.dto.ApoliceDTO;
import com.example.seguro.model.TipoSeguro;
import com.example.seguro.service.ApoliceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/apolices")
@RequiredArgsConstructor
@Api(tags = "Apólice", description = "API para gerenciamento de apólices de seguro")
public class ApoliceController {

    private final ApoliceService apoliceService;

    @GetMapping
    @ApiOperation("Listar todas as apólices")
    public ResponseEntity<List<ApoliceDTO>> listarTodas() {
        return ResponseEntity.ok(apoliceService.listarTodasApolices());
    }

    @GetMapping("/{id}")
    @ApiOperation("Buscar apólice por ID")
    public ResponseEntity<ApoliceDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(apoliceService.buscarPorId(id));
    }

    @GetMapping("/numero/{numeroApolice}")
    @ApiOperation("Buscar apólice por número")
    public ResponseEntity<ApoliceDTO> buscarPorNumero(@PathVariable String numeroApolice) {
        return ResponseEntity.ok(apoliceService.buscarPorNumero(numeroApolice));
    }

    @GetMapping("/tipo/{tipoSeguro}")
    @ApiOperation("Buscar apólices por tipo de seguro")
    public ResponseEntity<List<ApoliceDTO>> buscarPorTipo(@PathVariable TipoSeguro tipoSeguro) {
        return ResponseEntity.ok(apoliceService.buscarPorTipo(tipoSeguro));
    }

    @GetMapping("/segurado")
    @ApiOperation("Buscar apólices por nome do segurado")
    public ResponseEntity<List<ApoliceDTO>> buscarPorNomeSegurado(@RequestParam String nome) {
        return ResponseEntity.ok(apoliceService.buscarPorNomeSegurado(nome));
    }

    @GetMapping("/documento/{documento}")
    @ApiOperation("Buscar apólices por documento do segurado (CPF/CNPJ)")
    public ResponseEntity<List<ApoliceDTO>> buscarPorDocumentoSegurado(@PathVariable String documento) {
        return ResponseEntity.ok(apoliceService.buscarPorDocumentoSegurado(documento));
    }

    @GetMapping("/vigentes")
    @ApiOperation("Listar apólices vigentes")
    public ResponseEntity<List<ApoliceDTO>> listarVigentes() {
        return ResponseEntity.ok(apoliceService.buscarApolicesVigentes());
    }

    @GetMapping("/a-vencer")
    @ApiOperation("Listar apólices a vencer nos próximos 30 dias")
    public ResponseEntity<List<ApoliceDTO>> listarAVencer() {
        return ResponseEntity.ok(apoliceService.buscarApolicesAVencer30Dias());
    }

    @PostMapping
    @ApiOperation("Criar nova apólice")
    public ResponseEntity<ApoliceDTO> criar(@Valid @RequestBody ApoliceDTO apoliceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(apoliceService.salvar(apoliceDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar apólice existente")
    public ResponseEntity<ApoliceDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ApoliceDTO apoliceDTO) {
        return ResponseEntity.ok(apoliceService.atualizar(id, apoliceDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Cancelar apólice")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        apoliceService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
} 