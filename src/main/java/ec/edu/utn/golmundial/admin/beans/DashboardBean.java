package ec.edu.utn.golmundial.admin.beans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("dashboardBean")
@ViewScoped
public class DashboardBean implements Serializable {

    private int totalPartidos;
    private int usuariosRegistrados;
    private int totalPredicciones;
    private double monedasCirculando;

    @PostConstruct
    public void init() {
        // Datos mock para el resumen principal
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