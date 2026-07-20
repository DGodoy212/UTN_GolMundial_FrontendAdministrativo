package ec.edu.utn.golmundial.admin.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class UtnGolCoinApiClient {

    // IMPORTANTE: Esta URL debe coincidir con el backend UTNGolCoin
    // Puede ser https://localhost:7002/api o http://localhost:7002/api
    private static final String BASE_URL = "https://localhost:7002/api";

    public String obtenerEstadoConexion() {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL).path("/ranking").request(MediaType.APPLICATION_JSON).get();
            return response.getStatus() == 200 ? "Conectado a UTNGolCoin" : "Error de respuesta";
        } catch (Exception e) {
            return "Backend UTNGolCoin desconectado";
        }
    }
}