import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        // Запрос информации от API NASA
        HttpGet firstRequest = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=g9qMWQoE0nVoaH0v1DGngPZ1Th7ycZE3DHZwdaKa");
        CloseableHttpResponse firstResponse = httpClient.execute(firstRequest);

        NASAResponse nasaFirstResponse = mapper.readValue(firstResponse.getEntity().getContent(),
                new TypeReference<>() {
                });
        System.out.println(nasaFirstResponse);

        // Запрос изображения
        String nasaFileUrl = nasaFirstResponse.getUrl();
        HttpGet secondRequest = new HttpGet(nasaFileUrl);
        CloseableHttpResponse secondResponse = httpClient.execute(secondRequest);
        byte[] nasaSecondResponse = secondResponse.getEntity().getContent().readAllBytes();

        // Запись в файл
        String[] parseUrl = nasaFileUrl.split("/");
        String fileName = parseUrl[parseUrl.length - 1];
        File nasaFile = new File(fileName);

        try (FileOutputStream fileOutputStream = new FileOutputStream(nasaFile)) {
            fileOutputStream.write(nasaSecondResponse, 0, nasaSecondResponse.length);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
