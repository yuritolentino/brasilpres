package com.cliente.brasilbras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cliente.brasilbras.models.Cliente;


public interface CadastroRepository extends JpaRepository<Cliente, String>{

	Cliente findByCodigo(long codigo);
}
