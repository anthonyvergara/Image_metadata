package com.metadados.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class ImagemMetadados implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "tag", nullable = true)  // Permite nulos
	private String tag;
	
	@Column(name = "titulo", nullable = true)  // Permite nulos
	private String titulo;
	
	@Column(name = "autor", nullable = true)  // Permite nulos
	private String autor;
	
	@Column(name = "localizacao", nullable = true)  // Permite nulos
	private String localizacao;
	
	@Column(name = "data", nullable = true)  // Permite nulos
	private LocalDateTime data;
	
	@Column(name = "resolucao", nullable = true)  // Permite nulos
	private int resolucao;
	
	@Column(name = "tamanho", nullable = true)  // Permite nulos
	private Long tamanho;
	
	@JsonIgnore
	@JoinColumn(name = "imagem_id")
	@ForeignKey(name = "fk_imagem_id")
	@OneToOne(cascade = CascadeType.REMOVE)
	private Imagem imagem;

}
