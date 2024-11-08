package com.metadados.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.metadados.dto.ImagemMetadadosDTO;
import com.metadados.model.Imagem;
import com.metadados.model.ImagemMetadados;
import com.metadados.service.ImagemMetadadosService;
import com.metadados.service.ImagemService;

@RestController
@RequestMapping(value = "/imagem")
public class ImagemController {
	
	private final ImagemService IMAGEM_SERVICE;
	private final ImagemMetadadosService IMAGEM_METADADOS_SERVICE;
	
	public ImagemController(ImagemService imagemService, ImagemMetadadosService imagemMetadadosService) {
		this.IMAGEM_SERVICE = imagemService;
		this.IMAGEM_METADADOS_SERVICE = imagemMetadadosService;
	}
	
	@PostMapping(value = "/tag/{tag}/titulo/{titulo}")
	public ResponseEntity<Imagem> save(@RequestParam("file") MultipartFile arquivo, @PathVariable(value = "tag") String tag,
																			@PathVariable(value = "titulo") String titulo){
		
		return new ResponseEntity<Imagem>(this.IMAGEM_SERVICE.save(arquivo, tag, titulo),HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/titulo/{titulo}")
	public ResponseEntity<List<ImagemMetadadosDTO>> getByTitulo(@PathVariable("titulo") String titulo){
		
		return new ResponseEntity<List<ImagemMetadadosDTO>>(this.IMAGEM_METADADOS_SERVICE.getByTitulo(titulo),HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/tag/{tag}")
	public ResponseEntity<List<ImagemMetadadosDTO>> getByTag(@PathVariable("tag") String tag){
		
		return new ResponseEntity<List<ImagemMetadadosDTO>>(this.IMAGEM_METADADOS_SERVICE.getByTag(tag),HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{palavra}")
	public ResponseEntity<List<ImagemMetadadosDTO>> getByPalavra(@PathVariable("palavra") String palavra){
		return new ResponseEntity<List<ImagemMetadadosDTO>>(this.IMAGEM_METADADOS_SERVICE.getByPalavraNoContextoGeral(palavra),HttpStatus.OK);
	}

}
