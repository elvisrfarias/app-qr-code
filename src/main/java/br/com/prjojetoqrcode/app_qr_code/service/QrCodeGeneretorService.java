package br.com.prjojetoqrcode.app_qr_code.service;

import br.com.prjojetoqrcode.app_qr_code.dto.QrCodeGenerarionResponseDTO;
import br.com.prjojetoqrcode.app_qr_code.ports.StoragePort;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeGeneretorService {

    private final StoragePort storagePort;

    public QrCodeGeneretorService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }

    public QrCodeGenerarionResponseDTO generateAndUploadQrCode(String text) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        // Generate QR Code
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", pngOutputStream);
        byte[] pngQrCodeData = pngOutputStream.toByteArray();

        // Upload to storage service
        String url = storagePort.uploadFile(pngQrCodeData, UUID.randomUUID().toString(), "image/png");

        return new QrCodeGenerarionResponseDTO(url);
    }
}
