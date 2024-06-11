package utp.edu.pe.bsckendgroup.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Link.DataRegisterLink;
import utp.edu.pe.bsckendgroup.Domain.Link.DataUpdateLink;
import utp.edu.pe.bsckendgroup.Service.LinksService;

import javax.swing.text.html.HTML;

@RestController
@RequestMapping("/links")
@Tag(name = "Links", description = "Links")
public class LinksController {
    @Autowired
    private LinksService linksService;

    @Operation(summary = "Create link", description = "Create link")
    @PostMapping("/newLinkGroupUtp")
    public ResponseEntity<?> newLinkGroupUtp(@RequestBody @Valid DataRegisterLink data){
        return ResponseEntity.ok(linksService.newLinkGroupUtp(data));
    }

    @Operation(summary = "List links group", description = "List links group")
    @GetMapping("/list/{groupId}")
    public ResponseEntity<?> listLinksGroup(@PathVariable Long groupId){
        return ResponseEntity.ok(linksService.listLinksGroup(groupId));
    }

    @Operation(summary = "Delete link", description = "Delete link")
    @Transactional
    @PutMapping("/updateLink")
    public ResponseEntity<?> updateLink(@RequestBody @Valid DataUpdateLink data){
        return ResponseEntity.ok(linksService.updateLink(data));
    }
}
