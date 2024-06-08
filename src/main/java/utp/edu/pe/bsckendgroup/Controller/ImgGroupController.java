package utp.edu.pe.bsckendgroup.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.ImgGroup.DataRegisterImgGroup;
import utp.edu.pe.bsckendgroup.Domain.ImgGroup.DataUpdateImgGroup;
import utp.edu.pe.bsckendgroup.ServicesDto.DataAlterImgGroup;
import utp.edu.pe.bsckendgroup.Service.ImgGroupService;
import utp.edu.pe.bsckendgroup.ServicesDto.RequestListImg;

import java.util.List;

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

    @PostMapping("/list")
    public ResponseEntity<?> listImgGroup(@RequestBody @Valid RequestListImg data){
        return ResponseEntity.ok(imgGroupService.listImgGroup(data.userId(), data.groupId()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteImgGroup(@RequestBody @Valid DataAlterImgGroup data){
        if(imgGroupService.deleteImgGroup(data)){
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
