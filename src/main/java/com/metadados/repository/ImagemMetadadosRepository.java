package com.metadados.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.metadados.model.ImagemMetadados;

public interface ImagemMetadadosRepository extends JpaRepository<ImagemMetadados, Long>{
	
	@Query(value = """ 
			
			SELECT * FROM imagem img
				INNER JOIN imagem_metadados meta ON img.id = meta.imagem_id
				WHERE meta.titulo LIKE %:titulo%
			
			""", nativeQuery = true)
	Map<String,ImagemMetadados> listByTitulo(@Param("titulo") String titulo);
}
