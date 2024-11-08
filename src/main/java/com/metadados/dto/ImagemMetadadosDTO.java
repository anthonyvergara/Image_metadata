package com.metadados.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.metadados.model.ImagemMetadados;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ImagemMetadadosDTO{
	
	private String tag;
	private String titulo;
	private String nome;
	private String tamanho;
	private String autor;
	private String tipo;
	private String localizacao;
	private Long imagem_id;
	
	public ImagemMetadadosDTO convertToDto(ImagemMetadados imagem) {
		
		BeanUtils.copyProperties(imagem, this);
		
		return this;
	}
	
	public Map<String,ImagemMetadadosDTO> listToDto(Map<String,ImagemMetadados> imagens){
		Map<String,ImagemMetadadosDTO> listaImagens = new HashMap<>();
		
		for(Map.Entry<String,ImagemMetadados> img : imagens.entrySet()) {
			ImagemMetadadosDTO imagemMetadados = new ImagemMetadadosDTO();
			
			listaImagens.put(img.getKey(), imagemMetadados.convertToDto(img.getValue()));
		}
		
		return listaImagens;
	}
}
