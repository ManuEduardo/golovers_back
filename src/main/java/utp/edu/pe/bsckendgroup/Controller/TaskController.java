package utp.edu.pe.bsckendgroup.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Task.DataFinishTask;
import utp.edu.pe.bsckendgroup.Domain.Task.DataRegisterTask;
import utp.edu.pe.bsckendgroup.Domain.Task.DataUpdateTask;
import utp.edu.pe.bsckendgroup.Service.TaskService;

@RestController
@RequestMapping("/task")
@Tag(name = "Task", description = "Task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Operation(summary = "Create task", description = "Create task")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid DataRegisterTask data){
        return new ResponseEntity<>(taskService.create(data), HttpStatus.CREATED);
    }

    @Operation(summary = "Find all tasks", description = "Find all tasks")
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Find all tasks", description = "Find all tasks")
    @GetMapping("/findByKanbanId/{id}")
    public ResponseEntity<?> findByKanbanId(@PathVariable Long id){
        return new ResponseEntity<>(taskService.findByKanbanId(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Update task",
            responses = {@ApiResponse(responseCode = "200", description = "Task updated"),
                         @ApiResponse(responseCode = "404", description = "Task not found")},
            description = "Update task"
    )
    @Transactional
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Valid DataUpdateTask data){
        return new ResponseEntity<>(taskService.update(data), HttpStatus.OK);
    }

    @Operation(summary = "Finish task", description = "Finish task")
    @Transactional
    @PutMapping("/finish")
    public ResponseEntity<?> finish(@RequestBody @Valid DataFinishTask data){
        return new ResponseEntity<>(taskService.fisnishTask(data), HttpStatus.OK);
    }

    @Operation(summary = "Delete task", description = "Delete task")
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
