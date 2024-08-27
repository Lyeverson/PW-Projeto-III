package ufrn.api.controller;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import ufrn.api.service.PedidoService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ufrn.api.domain.Camisa;
import ufrn.api.domain.Pedido;
import ufrn.api.domain.Pessoa;
import ufrn.api.dto.PedidoDTO.PedidoRequestDto;
import ufrn.api.dto.PedidoDTO.PedidoResponseDto;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos/")
public class PedidoController {
    

    private final PedidoService service;
    private final ModelMapper mapper;

   @GetMapping
    public Page<PedidoResponseDto> listAll(Pageable pageable) {
        Page<Pedido> pedidosPage = service.listAll(pageable);
        return pedidosPage.map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoResponseDto> listById(@PathVariable("id") Long id) {
        Pedido pedido = service.listById(id);
        PedidoResponseDto dto = mapper.map(pedido, PedidoResponseDto.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDto> create(@RequestBody PedidoRequestDto pedidoRequest) {
        Pedido created = service.create(convertToEntity(pedidoRequest));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<PedidoResponseDto> update(@RequestBody PedidoRequestDto requestDto, @PathVariable("id") Long id) {
        Pedido pedidoExistente;
        try {
            pedidoExistente = service.listById(id);
        } catch (Exception e) {

            Pedido novoPedido = service.create(convertToEntity(requestDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(novoPedido));
        }

        Pedido pedidoAtualizado = service.update(mapper.map(requestDto, Pedido.class), id);
        return ResponseEntity.ok(convertToDto(pedidoAtualizado));
    }

    private PedidoResponseDto convertToDto(Pedido pedido) {
        PedidoResponseDto dto = mapper.map(pedido, PedidoResponseDto.class);
        dto.addLinks(pedido);
        return dto;
    }

    private Pedido convertToEntity(PedidoRequestDto requestDto) {
        Pedido pedido = mapper.map(requestDto, Pedido.class);
       

        Pessoa EntityPessoa = mapper.map(pedido.getPessoa(), Pessoa.class);

        List<Camisa> camisas = requestDto.getCamisas().stream()
        .map(camisaRequestDto -> mapper.map(camisaRequestDto, Camisa.class))
        .toList();

        pedido.setCamisas(camisas);
        pedido.setPessoa(EntityPessoa);

        return pedido;
    }


}
