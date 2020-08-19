package com.cliente.brasilbras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cliente.brasilbras.models.Users;


public interface UsersRepository extends JpaRepository<Users, String>{

	Users findByNome(String nome);
}
