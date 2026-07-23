package ec.edu.utn.golmundial.admin.client;

import ec.edu.utn.golmundial.admin.dto.*;
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
    private static final String BASE_URL = "https://divisive-crested-maker.ngrok-free.dev/GolMundial-Estadisticas-1.0-SNAPSHOT/api";

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
            System.out.println("Código HTTP devuelto: " + response.getStatus());
            if (response.getStatus() != 200 && response.getStatus() != 204) {
                System.out.println("Cuerpo del error: " + response.readEntity(String.class));
            }

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

    public ReporteDto obtenerResumenDashboard() {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL)
                    .path("/admin/dashboard/resumen")
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            System.out.println("Status HTTP resumen dashboard: " + response.getStatus());

            if (response.getStatus() == 200) {
                String rawJson = response.readEntity(String.class);
                System.out.println("JSON recibido del dashboard: " + rawJson);
                jakarta.json.bind.Jsonb jsonb = jakarta.json.bind.JsonbBuilder.create();
                return jsonb.fromJson(rawJson, ReporteDto.class);
            } else {
                System.out.println("Cuerpo del error del dashboard: " + response.readEntity(String.class));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener resumen del dashboard: " + e.getMessage());
        }
        return null;
    }

    public List<UsuarioDto> obtenerUsuarios() {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL)
                    .path("/admin/usuarios")
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            System.out.println("Status de usuarios: " + response.getStatus());

            if (response.getStatus() == 200) {
                return response.readEntity(new GenericType<List<UsuarioDto>>() {});
            }
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean registrarUsuario(UsuarioDto nuevoUsuario) {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL)
                    .path("/auth/registro")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(nuevoUsuario));

            System.out.println("Código HTTP registro usuario: " + response.getStatus());
            if (response.getStatus() != 200 && response.getStatus() != 201 && response.getStatus() != 204) {
                String errorBody = response.readEntity(String.class);
                System.out.println("Cuerpo del error de registro del backend: " + errorBody);
            }

            return response.getStatus() == 200 || response.getStatus() == 201 || response.getStatus() == 204;
        } catch (Exception e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarUsuario(Long id, UsuarioDto usuarioEditado) {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL)
                    .path("/admin/usuarios/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(usuarioEditado));

            System.out.println("Código HTTP actualizar usuario: " + response.getStatus());
            if (response.getStatus() != 200 && response.getStatus() != 204) {
                String errorBody = response.readEntity(String.class);
                System.out.println("Cuerpo del error del backend: " + errorBody);
            }

            return response.getStatus() == 200 || response.getStatus() == 204;
        } catch (Exception e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(Long id) {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL)
                    .path("/admin/usuarios/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .delete();

            System.out.println("Código HTTP eliminar usuario: " + response.getStatus());
            if (response.getStatus() != 200 && response.getStatus() != 204) {
                String errorBody = response.readEntity(String.class);
                System.out.println("Cuerpo del error de eliminación: " + errorBody);
            }

            return response.getStatus() == 200 || response.getStatus() == 204;
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean registrarPartido(PartidoDto nuevoPartido) {
        try (Client client = ClientBuilder.newClient()) {
            var response = client.target(BASE_URL)
                    .path("/admin/partidos")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(nuevoPartido));

            System.out.println("Código HTTP crear partido: " + response.getStatus());
            if (response.getStatus() != 200 && response.getStatus() != 201 && response.getStatus() != 204) {
                String errorBody = response.readEntity(String.class);
                System.out.println("Cuerpo del error de creación de partido: " + errorBody);
            }

            return response.getStatus() == 200 || response.getStatus() == 201 || response.getStatus() == 204;
        } catch (Exception e) {
            System.err.println("Error al crear partido: " + e.getMessage());
            return false;
        }
    }
}