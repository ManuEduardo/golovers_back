package utp.edu.pe.bsckendgroup.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
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
@Tag(name = "ImgGroup", description = "ImgGroup")
public class ImgGroupController {
    @Autowired
    private ImgGroupService imgGroupService;

    @Operation(summary = "Register img group", description = "Register img group")
    @PostMapping("/register")
    public ResponseEntity<?> registerImgGroup(@RequestBody @Valid DataRegisterImgGroup data){
        if(imgGroupService.registerImgGroup(data)){
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>( "Error", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "List img group", description = "List img group")
    @PostMapping("/list")
    public ResponseEntity<?> listImgGroup(@RequestBody @Valid RequestListImg data){
        return ResponseEntity.ok(imgGroupService.listImgGroup(data.userId(), data.groupId()));
    }

    @Operation(summary = "Delete img group", description = "Delete img group")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteImgGroup(@RequestBody @Valid DataAlterImgGroup data){
        if(imgGroupService.deleteImgGroup(data)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Update img group", description = "Update img group")
    @PutMapping("/update")
    public ResponseEntity<?> updateImgGroup(@RequestBody @Valid DataUpdateImgGroup data){
        if(imgGroupService.updateImgGroup(data)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Get img group", description = "Get img group")
    @GetMapping("/get")
    public ResponseEntity<?> getImgGroup(Long id){
        return ResponseEntity.ok(imgGroupService.getImgGroup(id));
    }
}
