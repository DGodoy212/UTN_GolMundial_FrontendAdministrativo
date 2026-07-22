package ec.edu.utn.golmundial.admin.client;

import ec.edu.utn.golmundial.admin.dto.AuditoriaDto;
import ec.edu.utn.golmundial.admin.dto.PartidoDto;
import ec.edu.utn.golmundial.admin.dto.ResultadoPartidoRequest;
import ec.edu.utn.golmundial.admin.dto.LoginRequest;
import ec.edu.utn.golmundial.admin.dto.LoginResponse;
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
    private static final String BASE_URL = "http://172.20.136.192:7001/GolMundial-Estadisticas-1.0-SNAPSHOT/api";

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

    // Metodo para validar credenciales (POST /auth/login)
    public LoginResponse autenticar(LoginRequest request) {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL)
                    .path("/auth/login")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(request));
            if (response.getStatus() == 200) {
                return response.readEntity(LoginResponse.class);
            } else {
                System.err.println("Credenciales incorrectas o error en el backend. Status: " + response.getStatus());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error grave de conexión en el Login: " + e.getMessage());
            return null;
        }
    }
}