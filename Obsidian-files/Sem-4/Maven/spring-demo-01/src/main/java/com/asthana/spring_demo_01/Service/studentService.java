package com.asthana.spring_demo_01.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.asthana.spring_demo_01.DTO.Studentdto;
import com.asthana.spring_demo_01.Entities.StudentEntity;
import com.asthana.spring_demo_01.Repository.StudentRepo;

@Service
public class studentService {

    final StudentRepo studentRepo;

    final ModelMapper modelMapper ;

   

    public studentService(StudentRepo studentRepo, ModelMapper modelMapper) {
        this.studentRepo = studentRepo;
        this.modelMapper = modelMapper;
    }

    public Studentdto getStudentById(Long id) {
        Optional<StudentEntity> studentEntityOptional = studentRepo.findById(id);
        if (studentEntityOptional.isPresent()) {
            StudentEntity studentEntity = studentEntityOptional.get();
            return modelMapper.map(studentEntity , Studentdto.class);
        } else {
            // Handle the case where the student is not found
            return null; // Or throw an exception
        }
    }

    public Studentdto addStudent(Studentdto studentdto) {
        StudentEntity studentEntity = modelMapper.map(studentdto ,StudentEntity.class);
        return modelMapper.map( studentRepo.save(studentEntity) , Studentdto.class);
    }

    public List<Studentdto> getAllStudents() {
        return studentRepo.findAll().stream().map(studentEntity -> modelMapper.map(studentEntity , Studentdto.class)).collect(Collectors.toList());
    }

    public boolean deleteStudentById(Long id) {
        boolean isPresent = studentRepo.existsById(id);
        studentRepo.deleteById(id);
        return isPresent ;
}

}