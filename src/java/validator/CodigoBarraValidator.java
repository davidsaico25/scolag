package validator;

import dao.PresentacionInsumoDAO;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import model.PresentacionInsumo;
import tool.RegexValidation;

@FacesValidator("codigoBarraValidator")
public class CodigoBarraValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String codigoBarra = (String) value;
        String summary = "Error codigo barra";
        String detail = "";
        
        if (codigoBarra.length() < 13) {
            detail = "Tiene que tener minimo 13 caracteres";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        
        if (!RegexValidation.onlyNumbers(codigoBarra)) {
            detail = "Solo numeros";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        
        PresentacionInsumo presentacionInsumo = null;
        PresentacionInsumoDAO presentacionInsumoDAO = new PresentacionInsumoDAO();
        presentacionInsumo = presentacionInsumoDAO.getPresentacionInsumoByCodBarra("1234567890123");
        if (presentacionInsumo != null) {
            detail = "El codigo de barra es usado por otra presentacion de insumo";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
