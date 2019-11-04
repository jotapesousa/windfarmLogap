package br.com.logap.windfarm.Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class Validators implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value.getClass() == Float.class) {
            Float valor = (Float) value;

            if (valor == 0.0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "");

                throw new ValidatorException(msg);
            }
        }
    }
}
