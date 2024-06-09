package utp.edu.pe.bsckendgroup.ServicesDto;

import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataListGroupUtp;
import utp.edu.pe.bsckendgroup.Domain.Student.DataListStudents;
import utp.edu.pe.bsckendgroup.Infra.Jwt.DataJWTToken;

import java.util.List;

public record DataresponseLogin(
        DataJWTToken token,
        DataListStudents student,
        List<DataListGroupUtp> dataListGroups
) {
}
