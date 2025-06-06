package br.com.prjojetoqrcode.app_qr_code.controller;

import br.com.prjojetoqrcode.app_qr_code.dto.QrCodeGenerarionRequestDTO;
import br.com.prjojetoqrcode.app_qr_code.dto.QrCodeGenerarionResponseDTO;
import br.com.prjojetoqrcode.app_qr_code.service.QrCodeGeneretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    @Autowired
    private QrCodeGeneretorService qrCodeGeneretorService;

    @PostMapping
    public ResponseEntity<QrCodeGenerarionResponseDTO> generate(@RequestBody QrCodeGenerarionRequestDTO request) {
        try {
            QrCodeGenerarionResponseDTO response = this.qrCodeGeneretorService.generateAndUploadQrCode(request.text());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
