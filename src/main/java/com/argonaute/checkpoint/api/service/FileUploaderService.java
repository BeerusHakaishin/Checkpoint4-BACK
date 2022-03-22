package com.argonaute.checkpoint.api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploaderService {

	@Value("${api.app.pictureLocation}")
	String locationFile;

	@Value("#{'${api.app.allowedExtension}'.split(',')}")
	private List<String> allowedExtension;

	public String uploadFile(MultipartFile file) throws IllegalStateException, IOException {
		Path root = Paths.get(locationFile);
		String extension = "";
		String fileName = file.getOriginalFilename();
		// Si le fichier existe
		if (fileName != null) {
			int index = fileName.lastIndexOf('.');
			if (index > 0) {
				extension = fileName.substring(index + 1);
			}
			// Si l'extension du fichier ne fait pas partie de la liste des extension
			// autorisé
			if (!allowedExtension.contains(extension)) {
				throw new IOException("File extension must be png, jpg, jpeg or svg");
				// Si l'extension du fichier est bonne
			} else {
				// On initialise la variable du fichier a transferer
				File fileToTransfer;
				// On fait
				do {
					// Generer un nom aléatoire pour le fichier
					fileName = UUID.randomUUID().toString().replace("-", "") + "." + fileName.substring(index + 1);
					// On crée une nouvelle instance du fichier avec le nom généré au dessus
					fileToTransfer = new File(locationFile + fileName);
				}
				// Tant que le nom généré existe ( s'il existe on regenere le nom aléatoire
				// jusqu'a qu'il n'existe pas)
				while (fileToTransfer.exists());
				// On copie le fichier dans l'application
				Files.copy(file.getInputStream(), root.resolve(fileName));
				return fileName;
			}
		}
		// Renvoie une erreur si le nom du fichier n'est pas trouver.
		else {
			throw new IOException("File name is not found.");
		}
	}

	public Resource load(String fileName) throws IOException {
		Path root = Paths.get(locationFile);
		if (fileName != null) {
			Path file = root.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new IOException("Resource not here");
			}
		} else {
			throw new IOException("Give a name to the file you're searching");
		}
	}

	public void deleteFile(String fileName) throws IOException {
		String fileToDelete = locationFile + fileName;
		File existFile = new File(locationFile + fileName);
		Path path = FileSystems.getDefault().getPath(fileToDelete);
		if (existFile.exists() && !existFile.isDirectory()) {
			Files.delete(path);
		}
	}
}
