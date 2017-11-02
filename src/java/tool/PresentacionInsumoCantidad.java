package tool;

import java.io.Serializable;
import model.PresentacionInsumo;

public class PresentacionInsumoCantidad implements Serializable {
    private PresentacionInsumo presentacionInsumo;
        private int cantidad;

        public PresentacionInsumo getPresentacionInsumo() {
            return presentacionInsumo;
        }

        public void setPresentacionInsumo(PresentacionInsumo presentacionInsumo) {
            this.presentacionInsumo = presentacionInsumo;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
}
