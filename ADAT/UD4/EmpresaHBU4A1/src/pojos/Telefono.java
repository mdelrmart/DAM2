package pojos;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Telefono {
    @Column(name = "informacion", length = 9)
    private String informacion;
    @Column(name = "numero", length = 25, nullable = false)
    private String numero;

    public Telefono() {
    }

    public Telefono(String informacion, String numero) {
        this.informacion = informacion;
        this.numero = numero;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // Necesitamos comparar objectos para borrar un teléfono, ya que el objeto es una referencia de memoria y lo tenemos que buscar así.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefono telefono = (Telefono) o;
        return Objects.equals(numero, telefono.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

}
