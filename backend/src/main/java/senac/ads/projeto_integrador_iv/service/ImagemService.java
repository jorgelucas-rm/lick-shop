package senac.ads.projeto_integrador_iv.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImagemService {

    @Value("${app.base-url}")
    private String baseUrl;

    private final String uploadDir = Paths.get("uploads", "images").toAbsolutePath().toString();

    public List<String> salvarImagens(List<MultipartFile> imagens) throws IOException {
        List<String> caminhos = new ArrayList<>();

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        for (MultipartFile imagem : imagens) {
            String nomeArquivo = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
            Path caminhoCompleto = uploadPath.resolve(nomeArquivo);
            imagem.transferTo(caminhoCompleto.toFile());

            String url = baseUrl + "/uploads/images/" + nomeArquivo;
            caminhos.add(url);
        }

        return caminhos;
    }

    public void deleteImagesByPaths(List<String> imagePaths) {
        for (String path : imagePaths) {
            try {
                String relativePath = path.replace(baseUrl, "").replace("/uploads/images/", "");
                Path imagePath = Paths.get(uploadDir, relativePath);
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao excluir o arquivo " + path, e);
            }
        }
    }
}
