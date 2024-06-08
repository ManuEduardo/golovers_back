package utp.edu.pe.bsckendgroup.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtpRepository;
import utp.edu.pe.bsckendgroup.Domain.ImgGroup.*;
import utp.edu.pe.bsckendgroup.ServicesDto.DataAlterImgGroup;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;
import utp.edu.pe.bsckendgroup.Domain.UserGroup.UserGroup;
import utp.edu.pe.bsckendgroup.Domain.UserGroup.UserGroupRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ImgGroupService {
    @Autowired
    private ImgGroupRepository imgGroupRepository;
    @Autowired
    private GroupUtpRepository groupUtpRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;

    public Boolean registerImgGroup(DataRegisterImgGroup data){
        List<UserGroup> userRoleGroup = userGroupRepository.findByUserIdAndGroupId(data.groupId());
        for (UserGroup userGroup : userRoleGroup) {
            if (userGroup.getRole().getName().equals("ROLE_LEADER") || userGroup.getRole().getName().equals("ROLE_MEMBER")) {
                imgGroupRepository.save(new ImgGroup(data));
                return true;
            }
        }
        return false;
    }
    public List<DataListImgGroup> listImgGroup(Long groupId, Long userId) {
        List<UserGroup> userRoleGroup = userGroupRepository.findByUserIdAndGroupId(groupId);
        boolean isMember = false;
        for (UserGroup userGroup : userRoleGroup) {
            if (userGroup.getRole().getName().equals("ROLE_LEADER") || userGroup.getRole().getName().equals("ROLE_MEMBER")) {
                isMember = true;
                break;
            }
        }
        if (isMember) {
            List<ImgGroup> imgGroups = imgGroupRepository.findByGroupId(groupId);
            List<DataListImgGroup> dataListImgGroups = imgGroups.stream().map(DataListImgGroup::new).toList();
            return dataListImgGroups;
        }
        return Collections.emptyList();
    }
    public Boolean deleteImgGroup(DataAlterImgGroup data){
        List<UserGroup> userRoleGroup = userGroupRepository.findByUserIdAndGroupId(data.groupId());
        for (UserGroup userGroup : userRoleGroup) {
            if (userGroup.getRole().getName().equals("ROLE_LEADER" )) {
                imgGroupRepository.deleteById(data.imgId());
                return true;
            }
        }
        return false;
    }

    public Boolean updateImgGroup(DataUpdateImgGroup data) {
        List<UserGroup> userRoleGroup = userGroupRepository.findByUserIdAndGroupId(data.groupId());
        for (UserGroup userGroup : userRoleGroup) {
            if (userGroup.getRole().getName().equals("ROLE_LEADER")) {
                Optional<ImgGroup> imgGroup = imgGroupRepository.findById(data.id());
                if (imgGroup.isPresent()) {
                    imgGroupRepository.save(new ImgGroup(data));
                    return true;
                }
            }
        }
        return false;
    }

    public DataListImgGroup getImgGroup(Long id){
        Optional<ImgGroup> imgGroup = imgGroupRepository.findById(id);
        return imgGroup.map(DataListImgGroup::new).orElse(null);
    }

}
