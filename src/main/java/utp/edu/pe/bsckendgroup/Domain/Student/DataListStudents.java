package utp.edu.pe.bsckendgroup.Domain.Student;

public record DataListStudents(
        Long id,
        String name,
        String lastName,
        String email,
        String password,
        int ciclo
) {
    public DataListStudents(Student student) {
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
