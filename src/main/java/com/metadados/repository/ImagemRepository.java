package com.metadados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metadados.model.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long>{

}
