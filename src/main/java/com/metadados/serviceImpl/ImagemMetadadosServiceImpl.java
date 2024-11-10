package com.metadados.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.GpsDirectory;
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
	public ImagemMetadados save(MultipartFile arquivo, String tag, String titulo, Imagem imagem) throws ImageProcessingException, IOException {
		ImagemMetadados imagemMetadados = new ImagemMetadados();
		
		 Metadata metadata = ImageMetadataReader.readMetadata(arquivo.getInputStream());
			
		BufferedImage imagemBuffered = ImageIO.read(arquivo.getInputStream());
		int largura = imagemBuffered.getWidth(); // Largura da imagem
        int altura = imagemBuffered.getHeight(); // Altura da imagem
        
        imagemMetadados.setAltura(altura);
        imagemMetadados.setLargura(largura);
			
		 for (Directory directory : metadata.getDirectories()) {
	            // Verificando se é um diretório EXIF (ExifIFD0Directory)
			 if (directory instanceof ExifIFD0Directory) {
	                ExifIFD0Directory exifDirectory = (ExifIFD0Directory) directory;

	                // Acessando o modelo da câmera
	                String modeloDispositivo = exifDirectory.getString(0x0110); // Model
	                imagemMetadados.setModelo(modeloDispositivo);

	                String datafoto = exifDirectory.getString(0x132);
	                
	                System.out.println(datafoto);
	                
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
	                LocalDateTime data = LocalDateTime.parse(datafoto, formatter);
	                imagemMetadados.setData(data);
	            }

	            if (directory instanceof GpsDirectory) {
	                GpsDirectory gpsDirectory = (GpsDirectory) directory;

	                // Latitude e Longitude (GPS)
	                String latitude = gpsDirectory.getString(0x0002); // GPS Latitude
	                imagemMetadados.setLatitude(latitude);
	                
	                String longitude = gpsDirectory.getString(0x0004); // GPS Longitude
	                imagemMetadados.setLongitude(longitude);
	            }
	        }
		 
		imagemMetadados.setTag(tag);
		imagemMetadados.setTitulo(titulo);
		imagemMetadados.setImagem(imagem);
		imagemMetadados.setResolucao(altura * largura);	 
		imagemMetadados.setTamanho(arquivo.getSize());
		 
		this.IMAGEM_METADADOS_REPOSITORY.save(imagemMetadados);
		 
		return imagemMetadados;
	}
	
	private void downloadImagens(List<ImagemMetadadosDTO> imagens) throws IOException{
		
		for(ImagemMetadadosDTO img : imagens) {
			String caminho = "/Users/anthonyvergara/Pictures/up/novaimagem"+ img.getImagem_id() +".jpeg";
			BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream(caminho));
			
			buffer.write(img.getImagem());
			buffer.flush();
		}
	}
	
	public List<ImagemMetadadosDTO> getByPalavraNoContextoGeral(String palavra){
		List<Map<String,Object>> listaDeImagens = this.IMAGEM_METADADOS_REPOSITORY.listByPalavra(palavra);
		
		listaDeImagens.stream()
			.forEach(lista -> {
				lista.entrySet().forEach(valor -> System.out.println("valores: "+ valor.getValue()));
			});
		
		return imgDTO.listToDto(listaDeImagens);
	}
	
	public List<ImagemMetadadosDTO> getByTitulo(String titulo){
		List<Map<String,Object>> listaDeImagens = this.IMAGEM_METADADOS_REPOSITORY.listByTitulo(titulo);
		
		
		return imgDTO.listToDto(listaDeImagens);
	}

	@Override
	public List<ImagemMetadadosDTO> getByTag(String tag) throws IOException {
		List<Map<String,Object>> listaDeImagens = this.IMAGEM_METADADOS_REPOSITORY.listByTag(tag);
		
		List<ImagemMetadadosDTO> imagens = imgDTO.listToDto(listaDeImagens);
		
		this.downloadImagens(imagens);
		
		return imagens;
	}

}
