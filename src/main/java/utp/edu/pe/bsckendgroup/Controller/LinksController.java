package utp.edu.pe.bsckendgroup.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Link.DataRegisterLink;
import utp.edu.pe.bsckendgroup.Domain.Link.DataUpdateLink;
import utp.edu.pe.bsckendgroup.Service.LinksService;

@RestController
@RequestMapping("/links")
public class LinksController {
    @Autowired
    private LinksService linksService;
    @PostMapping("/newLinkGroupUtp")
    public ResponseEntity<?> newLinkGroupUtp(@RequestBody @Valid DataRegisterLink data){
        return ResponseEntity.ok(linksService.newLinkGroupUtp(data));
    }
    @GetMapping("/list/{groupId}")
    public ResponseEntity<?> listLinksGroup(@PathVariable Long groupId){
        return ResponseEntity.ok(linksService.listLinksGroup(groupId));
    }
    @PutMapping("/updateLink")
    public ResponseEntity<?> updateLink(@RequestBody @Valid DataUpdateLink data){
        return ResponseEntity.ok(linksService.updateLink(data));
    }
}
