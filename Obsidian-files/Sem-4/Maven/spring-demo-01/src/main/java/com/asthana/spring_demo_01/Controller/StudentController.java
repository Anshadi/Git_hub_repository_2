package com.asthana.spring_demo_01.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.asthana.spring_demo_01.DTO.Studentdto;
import com.asthana.spring_demo_01.Service.studentService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;

// import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


// GET /student
// POST /student
// DELETE /student/{id}


// public String requestMethodName(@RequestParam String param) {
//     return new String();
//     }
    
    
    @RestController
    @RequestMapping(path ="/student")
public class StudentController {
    
    // @GetMapping("/student/{Id}")
    // public Studentdto getStudent(@PathVariable("Id") Long Student_id) {
    //         return new Studentdto(Student_id,"Adit", LocalDate.of(2024,2,1) ,true);
    //     }
        

    

        
    // @GetMapping("/student/{Name}")
    // public Studentdto getStudent(@PathVariable String Name) {
    //     return new Studentdto(1L,Name, LocalDate.of(2024,2,1) ,true);
    // }


    // @GetMapping("/student")
    // public Studentdto getStudent() {
    //     return new Studentdto(1L,"Adi", LocalDate.of(2024,2,1) ,true);
    // }
    
    // @GetMapping("/student")
    // public String getdata(@PathParam("Kya") String Kya, @PathParam("Tera") String Tera){
        //     return "Kya "+Kya+" Tera "+Tera ;                           // kya hua tera vaada  -- http://localhost:8000/student?Kya=Hua&Tera=Vaada
        // }
        
    private final studentService studentService;

    public StudentController(com.asthana.spring_demo_01.Service.studentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{Id}")
    public Studentdto getStudent(@PathVariable("Id") Long Student_id) {
            return studentService.getStudentById(Student_id);
        }

        @PostMapping
        public Studentdto getAllStudent(@RequestBody Studentdto studentdto) {
            return studentService.addStudent(studentdto);
        }

    @GetMapping("/all")
    public List<Studentdto> getAllStudents() {
        return studentService.getAllStudents();
    }
    

    @DeleteMapping("/{Id}")
    public boolean deleteStudentById(@PathVariable("Id") Long Id){

    return studentService.deleteStudentById(Id);
    }

    

}
