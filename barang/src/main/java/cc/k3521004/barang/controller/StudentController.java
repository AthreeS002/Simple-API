package cc.k3521004.barang.controller;

import cc.k3521004.barang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/students"})
public class StudentController {
    @Autowired
    public StudentService studentService;

    public StudentController(){
    }

    @PostMapping({"/registration"})
    public ResponseEntity<OutputDto<StudentDto>> registration(@RequestBody StudentRegisterDto studentRegisterDto) {
        StudentDto studentDto = this.studentService.registerNewStudent(studentRegisterDto);
        OutputDto<StudentDto> output = new OutputDto();
        output.setData(studentDto);
        output.setMessage("Registration successful");
        return ResponseEntity.ok(output);
    }

    @GetMapping("/show-all")
    public ResponseEntity<OutputDto<List<StudentDto>>> showAllStudent(){
        List<StudentDto> studentDtoList = studentService.getAllStudents();
        OutputDto<List<StudentDto>> output = new OutputDto<>();
        output.setMessage("Data succesfully got!");
        output.setData(studentDtoList);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OutputDto<StudentDto>> findStudent(@PathVariable("id") Long id){
        StudentDto studentDto = studentService.getStudent(id);
        OutputDto<StudentDto> output = new OutputDto<>();
        output.setData(studentDto);
        if (studentDto == null) {
            output.setMessage("Can't find student!");
        } else {
            output.setMessage("Found student!");
        }
        return ResponseEntity.ok(output);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<OutputDto<StudentDto>> destroyStudent(@PathVariable("id") Long id){
        StudentDto studentDto = new StudentDto();
        OutputDto<StudentDto> output = new OutputDto<>();
        try {
            // Periksa apakah mahasiswa ada
            studentDto = studentService.getStudent(id);
            if (studentDto == null) {
                // Jika mahasiswa tidak ada, kirim pesan kesalahan
                output.setData(null);
                output.setMessage("Student by id: " + id + ", not found!");
                return ResponseEntity.ok(output);
            }

            // Jika mahasiswa ada, hapus data mahasiswa
            studentService.deleteStudent(id);
            output.setData(null); // Tidak ada data untuk ditampilkan
            output.setMessage("Student data completely deleted!");
            return ResponseEntity.ok(output);

        } catch (Exception e) {
            output.setData(studentDto);
            output.setMessage(e.getMessage());
            return ResponseEntity.ok(output);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<OutputDto<StudentDto>> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto studentDto){
        StudentDto result = studentService.updateStudent(id, studentDto);
        OutputDto<StudentDto> output = new OutputDto<>();
        output.setData(result);
        output.setMessage("Data updated successfully!");
        return ResponseEntity.ok(output);
    }

    @GetMapping("/find-name/{name}")
    public ResponseEntity<OutputDto<List<StudentDto>>> showStudentByName(@PathVariable("name") String name){
        List<StudentDto> studentDtoList = studentService.getStudentByName(name);
        OutputDto<List<StudentDto>> output = new OutputDto<>();
        output.setMessage("Data caught!");
        output.setData(studentDtoList);
        return ResponseEntity.ok(output);
    }
}
