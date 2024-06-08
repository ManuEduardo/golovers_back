package utp.edu.pe.bsckendgroup.ServicesDto;

import org.apache.catalina.LifecycleState;
import utp.edu.pe.bsckendgroup.Domain.Task.DataListTask;

import java.util.List;

public record ColumsKanban(
        Long id,
        Long kanbanId,
        Long typeColumnId,
        String color,
        String title,
        Integer orderColum,
        List<DataListTask> tasks
) {
}
