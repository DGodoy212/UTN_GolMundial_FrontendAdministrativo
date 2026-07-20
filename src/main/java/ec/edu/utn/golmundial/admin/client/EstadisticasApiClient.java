package ec.edu.utn.golmundial.admin.client;

import ec.edu.utn.golmundial.admin.dto.AuditoriaDto;
import ec.edu.utn.golmundial.admin.dto.PartidoDto;
import ec.edu.utn.golmundial.admin.dto.ResultadoPartidoRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EstadisticasApiClient {

    // IMPORTANTE: Esta URL debe coincidir con el backend Estadisticas
    // Puede ser https://localhost:7001/api o http://localhost:7001/api
    private static final String BASE_URL = "http://localhost:7001/api";

    public List<PartidoDto> obtenerPartidos() {
        try (Client client = ClientBuilder.newClient()) {
            return client.target(BASE_URL)
                    .path("/partidos")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<PartidoDto>>() {}); // JSON -> List<PartidoDto>

        } catch (Exception e) {
            System.err.println("Error grave, no se pudo conectar al backend Estadísticas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean registrarResultado(Long partidoId, ResultadoPartidoRequest request) {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL)
                    .path("/admin/partidos/" + partidoId + "/resultado")
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(request));

            return response.getStatus() == 200 || response.getStatus() == 204;
        } catch (Exception e) {
            System.err.println("Error al registrar resultado: " + e.getMessage());
            return false;
        }
    }

    public List<AuditoriaDto> obtenerAuditoria() {
        try (Client client = ClientBuilder.newClient()) {
            return client.target(BASE_URL)
                    .path("/admin/auditoria")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<AuditoriaDto>>() {});
        } catch (Exception e) {
            System.err.println("Error al obtener auditoría: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean actualizarPartido(Long id, PartidoDto partidoEditado) {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL)
                    .path("/admin/partidos/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(partidoEditado));

            return response.getStatus() == 200 || response.getStatus() == 204;
        } catch (Exception e) {
            System.err.println("Error al actualizar partido: " + e.getMessage());
            return false;
        }
    }
}