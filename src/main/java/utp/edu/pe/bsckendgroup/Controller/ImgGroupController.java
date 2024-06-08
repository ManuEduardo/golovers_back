package utp.edu.pe.bsckendgroup.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.ImgGroup.DataRegisterImgGroup;
import utp.edu.pe.bsckendgroup.Domain.ImgGroup.DataUpdateImgGroup;
import utp.edu.pe.bsckendgroup.Service.ImgGroupService;

@RestController
@RequestMapping("/imggroup")
public class ImgGroupController {
    @Autowired
    private ImgGroupService imgGroupService;

    @PostMapping("/register")
    public ResponseEntity<?> registerImgGroup(@RequestBody @Valid DataRegisterImgGroup data){
        if(imgGroupService.registerImgGroup(data)){
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>( "Error", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/list")
    public ResponseEntity<?> listImgGroup(Long groupId){
        return ResponseEntity.ok(imgGroupService.listImgGroup(groupId));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteImgGroup(Long id){
        if(imgGroupService.deleteImgGroup(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateImgGroup(@RequestBody @Valid DataUpdateImgGroup data){
        if(imgGroupService.updateImgGroup(data)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/get")
    public ResponseEntity<?> getImgGroup(Long id){
        return ResponseEntity.ok(imgGroupService.getImgGroup(id));
    }
}
