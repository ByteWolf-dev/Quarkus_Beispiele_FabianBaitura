package htl.leonding.control.Validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class GenericValidator {
    private final static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final static Validator validator = validatorFactory.getValidator();

    public static <T> ValidatorResponse validate(T obj){
        Set<ConstraintViolation<T>> violations = validator.validate(obj);

        ValidatorResponse response = new ValidatorResponse();
        if(violations.isEmpty()){
            response.setValid(true);
        } else {
            response.setValid(false);
            violations.forEach(violation -> {
                response.getViolations().add(violation.getMessage());
            });
        }
        return response;
    }

}
