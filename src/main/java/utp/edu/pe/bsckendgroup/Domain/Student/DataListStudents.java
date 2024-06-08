package utp.edu.pe.bsckendgroup.Domain.Student;

public record DataListStudents(
        Long id,
        String name,
        String lastName,
        String email,
        int ciclo
) {
    public DataListStudents(Student student) {
        this(
                student.getId(),
                student.getName(),
                student.getLastName(),
                student.getEmail(),
                student.getCiclo()
        );
    }
}
