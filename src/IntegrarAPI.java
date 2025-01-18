import Exceptions.ApiException;
import Models.modelApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IntegrarAPI {
    private static final String site = "https://v6.exchangerate-api.com/v6/7d53d2a5cc2c30474986ec71/";
    private static final HttpClient client = HttpClient.newHttpClient();


    public static modelApi setSite(String moedaBase, String moedaAlvo, double valor) {
        String siteModificado = site;
        if (valor == 1) {
            siteModificado += "pair/" + moedaBase + "/" + moedaAlvo;
        } else {
            siteModificado +=  "pair/" + moedaBase + "/" + moedaAlvo + "/" + valor;
        }

        try {
            return modularAPI(getAPI(siteModificado));
        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static HttpResponse<String> getAPI(String site) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(site)).build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    private static modelApi modularAPI(HttpResponse<String> json) throws ApiException {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        var apiModulada = gson.fromJson(json.body(), modelApi.class);

        if (!apiModulada.result().equalsIgnoreCase("success")) {
            throw new ApiException(apiModulada);
        } else {
            return apiModulada;
        }
    }
}
