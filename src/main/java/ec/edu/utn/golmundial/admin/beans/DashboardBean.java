package ec.edu.utn.golmundial.admin.beans;

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

    @PostConstruct
    public void init() {
        // Datos mock para el resumen principal
        totalPartidos = 48;
        usuariosRegistrados = 1520;
        totalPredicciones = 3450;
        monedasCirculando = 15200.00;

        String estado = golCoinClient.obtenerEstadoConexion();
        System.out.println("Conexión con Backend UtnGolCoin: " + estado);
    }

    public int getTotalPartidos() { return totalPartidos; }
    public int getUsuariosRegistrados() { return usuariosRegistrados; }
    public int getTotalPredicciones() { return totalPredicciones; }
    public double getMonedasCirculando() { return monedasCirculando; }
}