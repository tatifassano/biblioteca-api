package biblioteca.api.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "devolucion")
public class Devolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Prestamo prestamo;
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    private Double multa;


    /**
     * Metodo que calcula multa por devoluciones atrasadas.
     */
    public void calcularMulta() {
        if (this.prestamo != null && this.fechaDevolucion != null) {
            Date fechaDeVencimiento = this.prestamo.getFechaDevolucion();

            if (this.fechaDevolucion.after(fechaDeVencimiento)) {

                long diasDeRetraso = calcularDiferenciaEnDias(fechaDeVencimiento, this.fechaDevolucion);

                double montoMultaPorDia = 25.0;
                this.multa = diasDeRetraso * montoMultaPorDia;
            } else {
                this.multa = 0.0;
            }
        }
    }

    private long calcularDiferenciaEnDias(Date fechaInicio, Date fechaFin) {
        long diferenciaEnMilisegundos = fechaFin.getTime() - fechaInicio.getTime();
        return diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);
    }


}
