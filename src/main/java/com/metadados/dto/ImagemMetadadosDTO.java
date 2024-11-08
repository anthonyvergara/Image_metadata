package com.metadados.dto;

import java.time.LocalDate;
import java.util.ArrayList;
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
	private Long tamanho;
	private Long imagem_id;
	
	public List<ImagemMetadadosDTO> listToDto(List<Map<String,Object>> listaImagens){
		List<ImagemMetadadosDTO> imagensDto = new ArrayList<ImagemMetadadosDTO>();
		
		for(Map<String,Object> mapImagens : listaImagens) {
			ImagemMetadadosDTO dto = new ImagemMetadadosDTO();
			dto.setTag((String) mapImagens.get("tag"));
			dto.setTitulo((String) mapImagens.get("titulo"));
			dto.setTamanho((Long) mapImagens.get("tamanho"));
			dto.setImagem_id((Long)mapImagens.get("imagem_id"));
			
			imagensDto.add(dto);
		}
		
		return imagensDto;
	}
}
