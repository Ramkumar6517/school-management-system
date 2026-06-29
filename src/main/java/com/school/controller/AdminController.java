package com.school.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.school.entity.ContactMessage;
import com.school.entity.ResultStatus;
import com.school.entity.Student;
import com.school.entity.Subject;
import com.school.entity.SubjectAssignment;
import com.school.entity.Teacher;
import com.school.repository.ContactMessageRepository;
import com.school.repository.ResultStatusRepository;
import com.school.repository.StudentRepository;
import com.school.repository.SubjectAssignmentRepo;
import com.school.repository.SubjectRepository;
import com.school.repository.TeacherAssignmentRepo;
import com.school.repository.TeacherRepository;
import com.school.repository.TeacherSubjectRepository;
import com.school.service.StudentService;



@Controller
@RequestMapping("/admin")
public class AdminController {

    // ===================== STUDENT =====================

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    // ===================== CONTACT =====================

    @Autowired
    private ContactMessageRepository repo;

    // ===================== TEACHER / SUBJECT =====================

    @Autowired
    private TeacherRepository teacherRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    @Autowired
    private TeacherSubjectRepository tsRepo;

    @Autowired
    private TeacherAssignmentRepo teacherAssignmentRepo;

    @Autowired
private SubjectAssignmentRepo assignmentRepo;

 

    // ===================== APPROVAL =====================


//     @GetMapping("/dashboard")
// public String dashboard() {
//     return "admin-dashboard";
// }

    @GetMapping("/approval")
    public String allStudents(Model model) {

        model.addAttribute(
                "students",
                studentRepository.findAll());

        return "admin-students";
    }

    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Long id) {

        Student student = studentRepository.findById(id).orElse(null);

        if (student != null) {

            student.setStatus("APPROVED");

            studentRepository.save(student);
        }

        return "redirect:/admin/approval";

    }

    @GetMapping("/reject/{id}")
    public String reject(@PathVariable Long id) {

        Student student = studentRepository.findById(id).orElse(null);

        if (student != null) {

            student.setStatus("REJECTED");

            student.setStudentId(null);

            studentRepository.save(student);
        }

        return "redirect:/admin/approval";

    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentRepository.deleteById(id);

        return "redirect:/admin/approval";

    }

    // ===================== STUDENT SEARCH =====================

    @GetMapping("/student-id-list")
    public String studentIdList(
            @RequestParam(required = false) String keyword,
            Model model) {

        List<Student> students;

        if (keyword != null && !keyword.isEmpty()) {

            students = studentRepository
                    .findByStudentNameContainingIgnoreCaseOrClassNameContainingIgnoreCaseOrApplicationIdContainingIgnoreCaseOrEmailContainingIgnoreCase(
                            keyword,
                            keyword,
                            keyword,
                            keyword);

        } else {

            students = studentRepository.findByStatus("APPROVED");

        }

        model.addAttribute(
                "students",
                students);

        return "admin-students";

    }

    // ===================== CONTACT MESSAGES =====================

    @GetMapping("/messages")
    public String messages(Model model) {

        model.addAttribute(
                "messages",
                repo.findAll());

        return "admin/messages";

    }

    @GetMapping("/api/messages")
    @ResponseBody
    public List<ContactMessage> getMessages() {

        return repo.findAll();

    }

    @DeleteMapping("/api/messages/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {

        if (!repo.existsById(id)) {

            return ResponseEntity.notFound().build();

        }

        repo.deleteById(id);

        return ResponseEntity.ok("Deleted");

    }

    // ===================== STUDENT DISABLE =====================

    @GetMapping("/student/disable")
    public String disableStudentPage(
            @RequestParam(required = false) String studentId,
            Model model) {

        if (studentId != null) {

            Student student = studentService.findByStudentId(studentId);

            model.addAttribute(
                    "student",
                    student);

        }

        return "student-disable";

    }

    // ===================== ASSIGN SUBJECT =====================
@GetMapping("/assign-subject")
public String page(
        @RequestParam(required=false) String teacherName,
        Model model) {


    model.addAttribute(
            "teachers",
            teacherRepo.findAll()
    );


    model.addAttribute(
            "subjects",
            subjectRepo.findAll()
    );



    if(teacherName != null){


        model.addAttribute(
                "assignments",
                assignmentRepo.findByTeacherName(teacherName)
        );


        model.addAttribute(
                "selectedTeacher",
                teacherName
        );


    }else{


        model.addAttribute(
                "assignments",
                assignmentRepo.findAll()
        );

    }



    return "admin/assign-subject";
}

 @PostMapping("/assign-subject")
public String assignSubject(

        @RequestParam Long teacherId,
        @RequestParam List<Long> subjectId,
        @RequestParam List<String> className

){

    Teacher teacher = teacherRepo
            .findById(teacherId)
            .orElseThrow();


    for(Long sId : subjectId){


        Subject subject = subjectRepo
                .findById(sId)
                .orElseThrow();


        for(String c : className){


            SubjectAssignment a =
                    new SubjectAssignment();


            a.setTeacherName(
                    teacher.getFullName()
            );


            a.setSubjectName(
                    subject.getName()
            );


            a.setClassName(
                    "Class " + c
            );


            a.setAssignedBy("Admin");


            a.setAssignedAt(
                    LocalDateTime.now()
            );


            assignmentRepo.save(a);

        }

    }


    return "redirect:/admin/assign-subject";
}

@GetMapping("/add-marks")
public String addMarks(Model model) {

    String teacherName = "Nitesh Kumar Yadav";

    List<SubjectAssignment> list =
            assignmentRepo.findByTeacherName(teacherName);

    model.addAttribute("assignedSubjects", list);

    return "teacher/add-marks";
}

    @PostMapping("/add-subject-ajax")
    @ResponseBody
    public Subject addSubjectAjax(@RequestBody Subject subject) {

        subject.setClassName("12");

        return subjectRepo.save(subject);
    }

    @DeleteMapping("/subject/{id}")
    @ResponseBody
    public String deleteSubject(@PathVariable Long id) {

        subjectRepo.deleteById(id);

        return "deleted";
    }

    @PutMapping("/subject/edit/{id}")
    @ResponseBody
    public Subject editSubject(
            @PathVariable Long id,
            @RequestBody Subject updated) {

        Subject s = subjectRepo.findById(id).orElse(null);

        if (s != null) {
            s.setName(updated.getName());
            subjectRepo.save(s);
        }

        return s;
    }

@DeleteMapping("/assignment/{id}")
@ResponseBody
public String deleteAssignment(@PathVariable Long id){


    assignmentRepo.deleteById(id);


    return "Deleted";
}
@PutMapping("/assignment/{id}")
@ResponseBody
public SubjectAssignment editAssignment(
        @PathVariable Long id,
        @RequestBody SubjectAssignment updated){


    SubjectAssignment assignment =
            assignmentRepo.findById(id)
            .orElse(null);



    if(assignment != null){


        assignment.setClassName(
                updated.getClassName()
        );


        assignmentRepo.save(assignment);

    }


    return assignment;
}

@Autowired
private SubjectAssignmentRepo subjectAssignmentRepo;

@PutMapping("/assignment/edit-status/{id}")
@ResponseBody
public ResponseEntity<String> updateEditStatus(
        @PathVariable Long id,
        @RequestBody SubjectAssignment dto) {

    SubjectAssignment assignment = subjectAssignmentRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Assignment not found"));

    assignment.setEditEnabled(dto.isEditEnabled());

    subjectAssignmentRepo.save(assignment);

    return ResponseEntity.ok("Updated");
}
@Autowired
ResultStatusRepository resultStatusRepository;



@PostMapping("/declare-result")
public String declareResult(RedirectAttributes redirectAttributes){


    ResultStatus status =
    resultStatusRepository.findById(1L)
    .orElse(new ResultStatus());


    status.setId(1L);
    status.setDeclared(true);


    resultStatusRepository.save(status);


    redirectAttributes.addFlashAttribute(
        "success",
        "Result Declared Successfully"
    );


    return "redirect:/admin/dashboard";

}



@PostMapping("/unlock-result")
public String unlockResult(
RedirectAttributes redirectAttributes
){


    ResultStatus status =
    resultStatusRepository.findById(1L)
    .orElse(new ResultStatus());


    status.setDeclared(false);


    resultStatusRepository.save(status);


    redirectAttributes.addFlashAttribute(
        "success",
        "Result Unlocked Successfully"
    );


    return "redirect:/admin/dashboard";

}
}