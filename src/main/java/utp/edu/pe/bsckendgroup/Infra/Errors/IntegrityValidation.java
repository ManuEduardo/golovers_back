package utp.edu.pe.bsckendgroup.Infra.Errors;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String s) {
        super(s);
    }
}
