package ec.edu.utn.golmundial.admin.beans;

import ec.edu.utn.golmundial.admin.client.EstadisticasApiClient;
import ec.edu.utn.golmundial.admin.client.UtnGolCoinApiClient;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("dashboardBean")
@ViewScoped
public class DashboardBean implements Serializable {

    private int totalPartidos;
    private int usuariosRegistrados;
    private int totalPredicciones;
    private double monedasCirculando;

    @Inject
    private UtnGolCoinApiClient golCoinClient;
    @Inject
    private EstadisticasApiClient estadisticasClient;

    @PostConstruct
    public void init() {
        String estado = golCoinClient.obtenerEstadoConexion();
        System.out.println("Conexión con Backend UtnGolCoin: " + estado);

        cargarDatosReales();
    }

    private void cargarDatosReales() {
        try {
            var listaPartidos = estadisticasClient.obtenerPartidos();
            totalPartidos = (listaPartidos != null) ? listaPartidos.size() : 0;

            var listaUsuarios = estadisticasClient.obtenerUsuarios();
            usuariosRegistrados = (listaUsuarios != null) ? listaUsuarios.size() : 0;

            var resumen = estadisticasClient.obtenerResumenDashboard();
            totalPredicciones = (resumen != null && resumen.getTotalPredicciones() != null)
                    ? resumen.getTotalPredicciones()
                    : 0;

            // Obtenemos el UGC real desde el backend de C#
            double ugcReal = golCoinClient.obtenerUgcEnCirculacion();
            monedasCirculando = (ugcReal > 0) ? ugcReal : 15200.00; // Respaldo si devuelve 0

            if (totalPartidos == 0 && usuariosRegistrados == 0 && totalPredicciones == 0) {
                throw new Exception("No se obtuvieron datos de las APIs.");
            }

        } catch (Exception e) {
            System.err.println("Fallo al cargar métricas del dashboard, usando mock: " + e.getMessage());
            cargarMockDeEmergencia();
        }
    }

    private void cargarMockDeEmergencia() {
        totalPartidos = 48;
        usuariosRegistrados = 1520;
        totalPredicciones = 3450;
        monedasCirculando = 15200.00;
    }

    public int getTotalPartidos() { return totalPartidos; }
    public int getUsuariosRegistrados() { return usuariosRegistrados; }
    public int getTotalPredicciones() { return totalPredicciones; }
    public double getMonedasCirculando() { return monedasCirculando; }
}