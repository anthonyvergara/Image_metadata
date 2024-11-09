package com.metadados.serviceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageProcessingException;
import com.metadados.model.Imagem;
import com.metadados.model.ImagemMetadados;
import com.metadados.repository.ImagemRepository;
import com.metadados.service.ImagemService;

import jakarta.transaction.Transactional;

@Service
public class ImagemServiceImpl implements ImagemService{
	
	private final ImagemRepository IMAGEM_REPOSITORY;
	private final ImagemMetadadosServiceImpl IMAGEM_METADADOS_SERVICE;
	
	public ImagemServiceImpl(ImagemRepository imagemRepository, ImagemMetadadosServiceImpl imagemMetadadosService) {
		this.IMAGEM_REPOSITORY = imagemRepository;
		this.IMAGEM_METADADOS_SERVICE = imagemMetadadosService;
	}
	
	@Transactional
	@Override
	public Imagem save(MultipartFile arquivo, String tag, String titulo) throws ImageProcessingException {
		
		Imagem imagem = new Imagem();
		try {
			
			imagem.setImagem(arquivo.getBytes());
			imagem.setNome(arquivo.getName());
			imagem.setTipo(arquivo.getContentType());
			
			imagem = this.IMAGEM_REPOSITORY.save(imagem);
			
			this.IMAGEM_METADADOS_SERVICE.save(arquivo, tag, titulo, imagem);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return imagem;
	}

}
