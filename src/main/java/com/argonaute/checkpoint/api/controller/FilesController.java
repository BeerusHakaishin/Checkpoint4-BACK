package com.argonaute.checkpoint.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.argonaute.checkpoint.api.service.FileUploaderService;

@Controller
@RequestMapping("/api/file")
public class FilesController {

	@Autowired
	FileUploaderService fileUploaderService;

	@GetMapping("/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable(required = true) String filename) throws IOException {
		Resource file = fileUploaderService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
