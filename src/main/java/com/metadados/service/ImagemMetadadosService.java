package com.metadados.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.metadados.dto.ImagemMetadadosDTO;
import com.metadados.model.Imagem;
import com.metadados.model.ImagemMetadados;

public interface ImagemMetadadosService {
	
	public ImagemMetadados save (MultipartFile arquivo, String tag, String titulo, Imagem imagem);
	public List<ImagemMetadadosDTO> getByTitulo(String titulo);
	public List<Map<String,Object>> getByTag(String tag);
	public List<Map<String,Object>> getByPalavra(String palavra);

}
