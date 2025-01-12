package cc.k3521004.barang.service;

import cc.k3521004.barang.controller.StudentDto;
import cc.k3521004.barang.controller.StudentRegisterDto;
import cc.k3521004.barang.entity.StudentEntity;
import cc.k3521004.barang.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentService() {
    }

    public StudentDto registerNewStudent(StudentRegisterDto studentRegisterDto) {
        StudentEntity newStudentEntity = new StudentEntity();
        newStudentEntity.setNama(studentRegisterDto.getNama());
        newStudentEntity.setAlamat(studentRegisterDto.getAlamat());
        newStudentEntity.setNim(studentRegisterDto.getNim());
        newStudentEntity.setUsername(studentRegisterDto.getUsername());
        newStudentEntity.setPassword(studentRegisterDto.getPassword());
        newStudentEntity.setEmail(studentRegisterDto.getEmail());
        StudentEntity registeredStudent = (StudentEntity)this.studentRepository.save(newStudentEntity);
        StudentDto studentDto = new StudentDto();
        studentDto.setEmail(registeredStudent.getEmail());
        studentDto.setNama(registeredStudent.getNama());
        studentDto.setNim(registeredStudent.getNim());
        studentDto.setUsername(registeredStudent.getUsername());
        studentDto.setAlamat(registeredStudent.getAlamat());
        return studentDto;
    }

    public List<StudentDto> getAllStudents(){
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (int i = 0; i < studentEntityList.size(); i++){
            StudentDto studentDto = new StudentDto();
            StudentEntity studentEntity = studentEntityList.get(i);
            studentDto.setEmail(studentEntity.getEmail());
            studentDto.setNama(studentEntity.getNama());
            studentDto.setNim(studentEntity.getNim());
            studentDto.setUsername(studentEntity.getUsername());
            studentDto.setAlamat(studentEntity.getAlamat());
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }

    public StudentDto getStudent(Long id){
        StudentDto studentDto = new StudentDto();
        Optional<StudentEntity> studentCheck = studentRepository.findById(id);
        if (studentCheck.isPresent()){
            StudentEntity studentEntity = studentCheck.get();
            studentDto.setEmail(studentEntity.getEmail());
            studentDto.setNama(studentEntity.getNama());
            studentDto.setNim(studentEntity.getNim());
            studentDto.setUsername(studentEntity.getUsername());
            studentDto.setAlamat(studentEntity.getAlamat());
            return studentDto;
        } else {
            return null;
        }
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        // Ambil entitas dari database berdasarkan ID
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with ID " + id + " not found"));

        // Perbarui properti dari studentDto
        if (studentDto.getNama() != null) studentEntity.setNama(studentDto.getNama());
        if (studentDto.getNim() != null) studentEntity.setNim(studentDto.getNim());
        if (studentDto.getUsername() != null) studentEntity.setUsername(studentDto.getUsername());
        if (studentDto.getEmail() != null) studentEntity.setEmail(studentDto.getEmail());
        if (studentDto.getAlamat() != null) studentEntity.setAlamat(studentDto.getAlamat());

        // Simpan entitas yang diperbarui ke database
        StudentEntity updatedEntity = studentRepository.save(studentEntity);

        // Konversi entitas yang diperbarui menjadi DTO
        StudentDto updatedDto = new StudentDto();
        updatedDto.setNama(updatedEntity.getNama());
        updatedDto.setNim(updatedEntity.getNim());
        updatedDto.setUsername(updatedEntity.getUsername());
        updatedDto.setEmail(updatedEntity.getEmail());
        updatedDto.setAlamat(updatedEntity.getAlamat());

        return updatedDto;
    }

    public List<StudentDto> getStudentByName(String name){
        List<StudentDto> studentDtoList = new ArrayList<>();
        List<StudentEntity> studentEntityList = studentRepository.findStudentName(name);
        for (int i = 0; i <studentEntityList.size(); i++){
            StudentDto studentDto = new StudentDto();
            StudentEntity studentEntity = studentEntityList.get(i);
            studentDto.setEmail(studentEntity.getEmail());
            studentDto.setNama(studentEntity.getNama());
            studentDto.setNim(studentEntity.getNim());
            studentDto.setUsername(studentEntity.getUsername());
            studentDto.setAlamat(studentEntity.getAlamat());
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }
}
