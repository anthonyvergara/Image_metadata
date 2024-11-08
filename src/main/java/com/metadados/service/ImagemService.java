package com.metadados.service;

import org.springframework.web.multipart.MultipartFile;

import com.metadados.model.Imagem;

public interface ImagemService {
	
	public Imagem save(MultipartFile arquivo, String tag, String titulo);

}
