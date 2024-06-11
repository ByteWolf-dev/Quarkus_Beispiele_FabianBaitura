package htl.leonding.control.Validating;

import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class GenericValidator {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> ValidationResponse validate(T obj){
        Set<ConstraintViolation<T>> constraintViolation = validator.validate(obj);

        ValidationResponse response = new ValidationResponse();
        if(constraintViolation.isEmpty()){
            response.setValid(true);
        } else {
            response.setValid(false);
            constraintViolation.forEach(violation -> {
                response.getViolations().add(violation.getMessage());
            });
        }
        return response;
    }
}
