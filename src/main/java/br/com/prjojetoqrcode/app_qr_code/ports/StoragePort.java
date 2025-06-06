package br.com.prjojetoqrcode.app_qr_code.ports;

public interface StoragePort {
    String uploadFile(
            byte[] fileFata,
            String fileName,
            String contentType
    );
}
