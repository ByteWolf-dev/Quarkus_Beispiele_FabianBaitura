package htl.leonding.control.Validating;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponse {
    private boolean valid;

    List<String> violations = new ArrayList<>();

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getViolations() {
        return violations;
    }

    public void setViolations(List<String> violations) {
        this.violations = violations;
    }
}
