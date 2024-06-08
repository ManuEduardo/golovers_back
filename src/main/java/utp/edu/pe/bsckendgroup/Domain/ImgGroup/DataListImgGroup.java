package utp.edu.pe.bsckendgroup.Domain.ImgGroup;

import java.time.LocalDateTime;

public record DataListImgGroup(
    Long id,
    Long groupId,
    Long userId,
    LocalDateTime dateTime,
    String src
) {
    public DataListImgGroup(ImgGroup data){
        this(
            data.getId(),
            data.getGroupUtp().getId(),
            data.getStudent().getId(),
            data.getDateTime(),
            data.getSrc()
        );
    }
}
