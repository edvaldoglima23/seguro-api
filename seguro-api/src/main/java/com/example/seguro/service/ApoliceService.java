package com.example.seguro.service;

import com.example.seguro.dto.ApoliceDTO;
import com.example.seguro.model.Apolice;
import com.example.seguro.model.TipoSeguro;
import com.example.seguro.repository.ApoliceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApoliceService {

    private final ApoliceRepository apoliceRepository;
    private final RabbitTemplate rabbitTemplate;
    
    private static final String EXCHANGE_NOTIFICACAO = "seguro.notificacao";
    private static final String ROUTING_KEY_NOVA_APOLICE = "apolice.nova";
    private static final String ROUTING_KEY_VENCIMENTO = "apolice.vencimento";

    @Transactional(readOnly = true)
    public List<ApoliceDTO> listarTodasApolices() {
        return apoliceRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ApoliceDTO buscarPorId(Long id) {
        Apolice apolice = apoliceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apólice não encontrada com o ID: " + id));
        return converterParaDTO(apolice);
    }

    @Transactional(readOnly = true)
    public ApoliceDTO buscarPorNumero(String numeroApolice) {
        Apolice apolice = apoliceRepository.findByNumeroApolice(numeroApolice)
                .orElseThrow(() -> new EntityNotFoundException("Apólice não encontrada com o número: " + numeroApolice));
        return converterParaDTO(apolice);
    }

    @Transactional(readOnly = true)
    public List<ApoliceDTO> buscarPorTipo(TipoSeguro tipoSeguro) {
        return apoliceRepository.findByTipoSeguro(tipoSeguro).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ApoliceDTO> buscarPorNomeSegurado(String nomeSegurado) {
        return apoliceRepository.findByNomeSeguradoContainingIgnoreCase(nomeSegurado).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ApoliceDTO> buscarPorDocumentoSegurado(String documentoSegurado) {
        return apoliceRepository.findByDocumentoSegurado(documentoSegurado).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ApoliceDTO> buscarApolicesVigentes() {
        return apoliceRepository.findApolicesVigentesNaData(LocalDate.now()).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ApoliceDTO> buscarApolicesAVencer30Dias() {
        List<Apolice> apolicesAVencer = apoliceRepository.findApolicesAVencer30Dias();
        
        // Enviar notificação para as apólices a vencer
        apolicesAVencer.forEach(apolice -> {
            rabbitTemplate.convertAndSend(EXCHANGE_NOTIFICACAO, ROUTING_KEY_VENCIMENTO, 
                    "Apólice " + apolice.getNumeroApolice() + " irá vencer em breve.");
        });
        
        return apolicesAVencer.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ApoliceDTO salvar(ApoliceDTO apoliceDTO) {
        Apolice apolice = converterParaEntidade(apoliceDTO);
        Apolice apoliceGravada = apoliceRepository.save(apolice);
        
        // Enviar notificação de nova apólice
        rabbitTemplate.convertAndSend(EXCHANGE_NOTIFICACAO, ROUTING_KEY_NOVA_APOLICE, 
                "Nova apólice criada: " + apoliceGravada.getNumeroApolice());
        
        return converterParaDTO(apoliceGravada);
    }

    @Transactional
    public ApoliceDTO atualizar(Long id, ApoliceDTO apoliceDTO) {
        if (!apoliceRepository.existsById(id)) {
            throw new EntityNotFoundException("Apólice não encontrada com o ID: " + id);
        }

        Apolice apolice = converterParaEntidade(apoliceDTO);
        apolice.setId(id);
        return converterParaDTO(apoliceRepository.save(apolice));
    }

    @Transactional
    public void cancelar(Long id) {
        Apolice apolice = apoliceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Apólice não encontrada com o ID: " + id));
        
        apolice.setAtivo(false);
        apoliceRepository.save(apolice);
    }
    
    private ApoliceDTO converterParaDTO(Apolice apolice) {
        ApoliceDTO dto = new ApoliceDTO();
        BeanUtils.copyProperties(apolice, dto);
        return dto;
    }
    
    private Apolice converterParaEntidade(ApoliceDTO dto) {
        Apolice apolice = new Apolice();
        BeanUtils.copyProperties(dto, apolice);
        return apolice;
    }
} 