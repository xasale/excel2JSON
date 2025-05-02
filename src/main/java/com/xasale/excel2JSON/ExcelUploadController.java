package com.xasale.excel2JSON;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ExcelUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> processExcel(@RequestParam(name = "file") MultipartFile file) {

        String result = ExcelProcessingHelper.convertExcelRows(file);

        return ResponseEntity.ok(result);
    }
}
