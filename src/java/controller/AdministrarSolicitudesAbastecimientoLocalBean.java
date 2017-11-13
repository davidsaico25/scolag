package controller;

import dao.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.*;

@Named(value = "administrarSolicitudesAbastecimientoLocalBean")
@ViewScoped
public class AdministrarSolicitudesAbastecimientoLocalBean implements Serializable {

    private int idDiv;
    
    public AdministrarSolicitudesAbastecimientoLocalBean() {

        idDiv = 1;
    }
}
