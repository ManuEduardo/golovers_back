package utp.edu.pe.bsckendgroup.Domain.Student;

public record DataResponseStudent(
        Long id,
        String name,
        String lastName,
        String email,
        String password,
        int ciclo
) {
    public DataResponseStudent(Student student) {
        this(
                student.getId(),
                student.getName(),
                student.getLastName(),
                student.getEmail(),
                student.getPassword(),
                student.getCiclo()
        );
    }
}
