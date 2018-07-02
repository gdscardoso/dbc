package br.com.dbc.projeto.controller;

import br.com.dbc.projeto.model.Cliente;
import br.com.dbc.projeto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;


@Controller
@RequestMapping(path = "/rest/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(clienteService.listAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id", required = true) Integer id) {
        return new ResponseEntity<>(clienteService.findById(id), HttpStatus.OK);
    }

    @PostMapping(path = "create")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.OK);
    }

    @PutMapping(path = "update")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente dto) {
        Cliente update = this.clienteService.update(dto);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Integer id) {
        this.clienteService.delete(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }


    @PostMapping(path = "simular")
    public ResponseEntity<?> simular(@Valid @RequestBody ClienteService.SimulacaoRequestDTO simulacao) {
        ClienteService.SimulacaoResponseDTO response = this.clienteService.simular(simulacao);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
