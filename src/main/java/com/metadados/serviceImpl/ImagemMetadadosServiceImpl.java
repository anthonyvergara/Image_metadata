package com.metadados.serviceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.metadados.dto.ImagemMetadadosDTO;
import com.metadados.model.Imagem;
import com.metadados.model.ImagemMetadados;
import com.metadados.repository.ImagemMetadadosRepository;
import com.metadados.service.ImagemMetadadosService;

import jakarta.transaction.Transactional;

@Service
public class ImagemMetadadosServiceImpl implements ImagemMetadadosService{
	
	private final ImagemMetadadosRepository IMAGEM_METADADOS_REPOSITORY;
	
	@Autowired
	private ImagemMetadadosDTO imgDTO;
	
	public ImagemMetadadosServiceImpl(ImagemMetadadosRepository imagemMetadadosRepository) {
		this.IMAGEM_METADADOS_REPOSITORY = imagemMetadadosRepository;
	}
	
	@Transactional
	@Override
	public ImagemMetadados save(MultipartFile arquivo, String tag, String titulo, Imagem imagem) {
		ImagemMetadados imagemMetadados = new ImagemMetadados();
		
		Tika tika = new Tika();
		Metadata metadados = new Metadata();
		
		try {
			tika.parse(arquivo.getInputStream(), metadados);
			
			imagemMetadados.setAutor(metadados.get("Author"));
			//imagemMetadados.setData(LocalDateTime.parse(metadados.get("Creation-Date")));
			System.out.println("Tem: "+ metadados.get("Creation-Date"));
			imagemMetadados.setLocalizacao(metadados.get("Location"));
			imagemMetadados.setTamanho(arquivo.getSize());
			imagemMetadados.setTag(tag);
			imagemMetadados.setTitulo(titulo);
			imagemMetadados.setImagem(imagem);
			
			this.IMAGEM_METADADOS_REPOSITORY.save(imagemMetadados);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagemMetadados;
	}
	
		public List<ImagemMetadados> getByTitulo(String titulo){
			List<ImagemMetadados> listaDeImagens = this.IMAGEM_METADADOS_REPOSITORY.listByTitulo(titulo);
			
			
			return listaDeImagens;
		}

}
