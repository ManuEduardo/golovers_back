package utp.edu.pe.bsckendgroup.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtp;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtpRepository;
import utp.edu.pe.bsckendgroup.Domain.ImgGroup.*;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;

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

    public Boolean registerImgGroup(DataRegisterImgGroup data){
        Optional<GroupUtp> groupUtp = groupUtpRepository.findById(data.groupId()).map(Optional::of).orElse(null);
        Optional<Student> student = studentRepository.findById(data.userId()).map(Optional::of).orElse(null);
        if(groupUtp.isPresent() && student.isPresent()){
            imgGroupRepository.save(new ImgGroup(data));
            return true;
        }
        return false;
    }
    public List<DataListImgGroup> listImgGroup(Long groupId){
        List<ImgGroup> imgGroups = imgGroupRepository.findByGroupId(groupId);
        List<DataListImgGroup> dataListImgGroups = imgGroups.stream().map(DataListImgGroup::new).toList();
        return dataListImgGroups;
    }
    public Boolean deleteImgGroup(Long id){
        Optional<ImgGroup> imgGroup = imgGroupRepository.findById(id);
        if(imgGroup.isPresent()){
            imgGroupRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Boolean updateImgGroup(DataUpdateImgGroup data){
        Optional<ImgGroup> imgGroup = imgGroupRepository.findById(data.id());
        if(imgGroup.isPresent()){
            imgGroupRepository.save(new ImgGroup(data));
            return true;
        }
        return false;
    }
    public DataListImgGroup getImgGroup(Long id){
        Optional<ImgGroup> imgGroup = imgGroupRepository.findById(id);
        return imgGroup.map(DataListImgGroup::new).orElse(null);
    }
}
