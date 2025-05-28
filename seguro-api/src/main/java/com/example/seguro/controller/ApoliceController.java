package com.example.seguro.controller;

import com.example.seguro.dto.ApoliceDTO;
import com.example.seguro.model.TipoSeguro;
import com.example.seguro.service.ApoliceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Listar todas as apólices", notes = "Retorna todas as apólices cadastradas no sistema")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Apólices encontradas com sucesso"),
        @ApiResponse(code = 401, message = "Não autorizado para acessar este recurso"),
        @ApiResponse(code = 403, message = "Acesso proibido ao recurso"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<List<ApoliceDTO>> listarTodas() {
        return ResponseEntity.ok(apoliceService.listarTodasApolices());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar apólice por ID", notes = "Retorna uma apólice específica com base no ID fornecido")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Apólice encontrada com sucesso"),
        @ApiResponse(code = 404, message = "Apólice não encontrada"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<ApoliceDTO> buscarPorId(
            @ApiParam(value = "ID da apólice", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(apoliceService.buscarPorId(id));
    }

    @GetMapping("/numero/{numeroApolice}")
    @ApiOperation(value = "Buscar apólice por número", notes = "Retorna uma apólice específica com base no número fornecido")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Apólice encontrada com sucesso"),
        @ApiResponse(code = 404, message = "Apólice não encontrada"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<ApoliceDTO> buscarPorNumero(
            @ApiParam(value = "Número da apólice", required = true, example = "AP123456")
            @PathVariable String numeroApolice) {
        return ResponseEntity.ok(apoliceService.buscarPorNumero(numeroApolice));
    }

    @GetMapping("/tipo/{tipoSeguro}")
    @ApiOperation(value = "Buscar apólices por tipo de seguro", notes = "Retorna todas as apólices do tipo de seguro especificado")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Apólices encontradas com sucesso"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<List<ApoliceDTO>> buscarPorTipo(
            @ApiParam(value = "Tipo de seguro", required = true, example = "AUTO", allowableValues = "AUTO, VIDA, RESIDENCIAL, EMPRESARIAL, SAUDE, VIAGEM")
            @PathVariable TipoSeguro tipoSeguro) {
        return ResponseEntity.ok(apoliceService.buscarPorTipo(tipoSeguro));
    }

    @GetMapping("/segurado")
    @ApiOperation(value = "Buscar apólices por nome do segurado", notes = "Retorna todas as apólices cujo segurado contenha o nome especificado")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Apólices encontradas com sucesso"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<List<ApoliceDTO>> buscarPorNomeSegurado(
            @ApiParam(value = "Nome do segurado (parcial ou completo)", required = true, example = "João Silva")
            @RequestParam String nome) {
        return ResponseEntity.ok(apoliceService.buscarPorNomeSegurado(nome));
    }

    @GetMapping("/documento/{documento}")
    @ApiOperation(value = "Buscar apólices por documento do segurado", notes = "Retorna todas as apólices do segurado com o CPF/CNPJ especificado")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Apólices encontradas com sucesso"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<List<ApoliceDTO>> buscarPorDocumentoSegurado(
            @ApiParam(value = "CPF ou CNPJ do segurado", required = true, example = "123.456.789-00")
            @PathVariable String documento) {
        return ResponseEntity.ok(apoliceService.buscarPorDocumentoSegurado(documento));
    }

    @GetMapping("/vigentes")
    @ApiOperation(value = "Listar apólices vigentes", notes = "Retorna todas as apólices que estão em vigência na data atual")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Apólices encontradas com sucesso"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<List<ApoliceDTO>> listarVigentes() {
        return ResponseEntity.ok(apoliceService.buscarApolicesVigentes());
    }

    @GetMapping("/a-vencer")
    @ApiOperation(value = "Listar apólices a vencer nos próximos 30 dias", notes = "Retorna todas as apólices que irão vencer dentro dos próximos 30 dias")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Apólices encontradas com sucesso"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<List<ApoliceDTO>> listarAVencer() {
        return ResponseEntity.ok(apoliceService.buscarApolicesAVencer30Dias());
    }

    @PostMapping
    @ApiOperation(value = "Criar nova apólice", notes = "Cria uma nova apólice no sistema e retorna os dados cadastrados com o ID gerado")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Apólice criada com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos na requisição"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<ApoliceDTO> criar(
            @ApiParam(value = "Dados da apólice", required = true) 
            @Valid @RequestBody ApoliceDTO apoliceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(apoliceService.salvar(apoliceDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar apólice existente", notes = "Atualiza os dados de uma apólice existente com base no ID fornecido")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Apólice atualizada com sucesso"),
        @ApiResponse(code = 400, message = "Dados inválidos na requisição"),
        @ApiResponse(code = 404, message = "Apólice não encontrada"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<ApoliceDTO> atualizar(
            @ApiParam(value = "ID da apólice", required = true, example = "1")
            @PathVariable Long id, 
            @ApiParam(value = "Dados atualizados da apólice", required = true)
            @Valid @RequestBody ApoliceDTO apoliceDTO) {
        return ResponseEntity.ok(apoliceService.atualizar(id, apoliceDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Cancelar apólice", notes = "Cancela uma apólice existente (marcando-a como inativa)")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Apólice cancelada com sucesso"),
        @ApiResponse(code = 404, message = "Apólice não encontrada"),
        @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<Void> cancelar(
            @ApiParam(value = "ID da apólice", required = true, example = "1")
            @PathVariable Long id) {
        apoliceService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
} 