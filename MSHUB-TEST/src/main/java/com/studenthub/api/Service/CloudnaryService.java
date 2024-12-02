package com.studenthub.api.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudnaryService {

    private Cloudinary cloudinary;

    @Autowired
    public CloudnaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Map<String, Object> uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("O arquivo não pode estar vazio");
        }

        try {
            // Cria um mapa de parâmetros para o upload
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", "student");
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            return uploadedFile;
        } catch (IOException e) {
            throw new IOException("Erro ao fazer upload da imagem: " + e.getMessage(), e);
        }


    }

    // Método para excluir a imagem pelo public ID
    public String deleteImageByUrl(String imageUrl) {
        try {
            // Extrai o public ID completo, incluindo o caminho da pasta
            String publicId = extractPublicIdFromUrl(imageUrl); // Isso retorna o publicId completo
//            System.out.println(publicId);  TESTE -> RETORNAR NA TELA(OUTPUT) O PUBLIC ID DA IMAGEM ANTIGA
            Map<String, Object> deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

            // Verifica se a exclusão foi bem-sucedida
            String result = (String) deleteResult.get("result");
            if ("ok".equals(result)) {
                return result; // Retorna "ok" se a exclusão foi bem-sucedida
            } else {
                System.err.println("Falha ao excluir a imagem: " + deleteResult);
                return "falha"; // Retorna "falha" se não foi bem-sucedido
            }
        } catch (Exception e) {
            System.err.println("Erro ao excluir a imagem: " + e.getMessage());
            return "erro";
        }
    }

    private String extractPublicIdFromUrl(String imageUrl) {
        // Certifique-se de que a URL não esteja vazia ou nula
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new IllegalArgumentException("A URL da imagem não pode ser nula ou vazia.");
        }

        // Divide a URL para extrair o caminho completo até o ID
        String[] parts = imageUrl.split("/");
        if (parts.length < 2) {
            throw new IllegalArgumentException("URL inválida: não contém informações suficientes para extração.");
        }

        // Obtém a parte da URL que inclui o caminho da pasta e o ID
        String relevantPart = parts[parts.length - 2] + "/" + parts[parts.length - 1];

        // Remove a extensão do arquivo do último segmento
        String[] idParts = relevantPart.split("\\.");
        return idParts[0]; // Retorna o caminho da pasta e o ID
    }



}
