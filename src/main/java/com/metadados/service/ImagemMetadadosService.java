package com.metadados.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageProcessingException;
import com.metadados.dto.ImagemMetadadosDTO;
import com.metadados.model.Imagem;
import com.metadados.model.ImagemMetadados;

public interface ImagemMetadadosService {
	
	public ImagemMetadados save (MultipartFile arquivo, String tag, String titulo, Imagem imagem) throws ImageProcessingException, IOException;
	public List<ImagemMetadadosDTO> getByTitulo(String titulo);
	public List<ImagemMetadadosDTO> getByTag(String tag) throws IOException;
	public List<ImagemMetadadosDTO> getByPalavraNoContextoGeral(String palavra);

}
