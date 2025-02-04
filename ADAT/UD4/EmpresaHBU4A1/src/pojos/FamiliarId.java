package pojos;

import java.io.Serializable;
import java.util.Objects;

public class FamiliarId implements Serializable {

    private String NSSempregado;
    private Integer numero;

    public String getNSSEmpregado() {
        return this.NSSempregado;
    }

    public void setNSSEmpregado(String nSSEmpregado) {
        this.NSSempregado = nSSEmpregado;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FamiliarId that = (FamiliarId) o;
        return Objects.equals(this.NSSempregado, that.NSSempregado) && Objects.equals(this.numero, that.numero);
    }

    public int hashCode() {
        int result = Objects.hashCode(NSSempregado);

        result = 31 * result * Objects.hashCode(numero);

        return result;
    }

}
