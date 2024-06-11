package utp.edu.pe.bsckendgroup.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Task.DataFinishTask;
import utp.edu.pe.bsckendgroup.Domain.Task.DataListTask;
import utp.edu.pe.bsckendgroup.Domain.Task.DataRegisterTask;
import utp.edu.pe.bsckendgroup.Domain.Task.DataUpdateTask;
import utp.edu.pe.bsckendgroup.Service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public DataListTask create(@RequestBody @Valid DataRegisterTask data){
        return taskService.create(data);
    }

    @GetMapping("/find/{id}")
    public DataListTask findById(@PathVariable Long id){
        return taskService.findById(id);
    }

    @GetMapping("/findByKanbanId/{id}")
    public ResponseEntity<?> findByKanbanId(@PathVariable Long id){
        return new ResponseEntity<>(taskService.findByKanbanId(id), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update")
    public DataListTask update(@RequestBody @Valid DataUpdateTask data){
        return taskService.update(data);
    }
    @Transactional
    @PutMapping("/finish")
    public ResponseEntity<?> finish(@RequestBody @Valid DataFinishTask data){
        return new ResponseEntity<>(taskService.fisnishTask(data), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
