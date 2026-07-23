package ec.edu.utn.golmundial.admin.client;

import ec.edu.utn.golmundial.admin.dto.GolCoinMetricasDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class UtnGolCoinApiClient {

    // IMPORTANTE: Esta URL debe coincidir con el backend UTNGolCoin
    // añadir '/' al final si hace falta
    private static final String BASE_URL = "https://defacing-cognition-cardboard.ngrok-free.dev";

    public String obtenerEstadoConexion() {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL).path("/ranking").request(MediaType.APPLICATION_JSON).get();
            return response.getStatus() == 200 ? "Conectado a UTNGolCoin" : "Error de respuesta";
        } catch (Exception e) {
            return "Backend UTNGolCoin desconectado";
        }
    }

    public double obtenerUgcEnCirculacion() {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL)
                    .path("/api/admin/metricas/ugc-en-circulacion")
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            if (response.getStatus() == 200) {
                GolCoinMetricasDto metrica = response.readEntity(GolCoinMetricasDto.class);
                return metrica != null ? metrica.getTotalUgcEnCirculacion() : 0.0;
            }
            return 0.0;
        } catch (Exception e) {
            System.err.println("Error al conectar con AdminMetricas de GolCoin: " + e.getMessage());
            return 0.0;
        }
    }
}