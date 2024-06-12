package htl.leonding.control.Validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorResponse {
    private boolean isValid;
    private List<String> violations = new ArrayList<>();

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<String> getViolations() {
        return violations;
    }

    public void setViolations(List<String> violations) {
        this.violations = violations;
    }
}
