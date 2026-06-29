package com.school.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.school.entity.Marks;
import com.school.entity.ResultStatus;
import com.school.entity.Student;
import com.school.entity.SubjectAssignment;
import com.school.entity.Teacher;
import com.school.repository.MarksRepository;
import com.school.repository.ResultStatusRepository;
import com.school.repository.StudentRepository;
import com.school.repository.SubjectAssignmentRepo;
import com.school.repository.SubjectRepository;
import com.school.repository.TeacherRepository;
import com.school.repository.TeacherSubjectRepository;
import com.school.service.NoticeService;
import com.school.service.TeacherService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

        @Autowired
        private TeacherService service;

        // ==========================
        // REGISTER PAGE
        // ==========================

        @GetMapping("/register")
        public String registerPage(Model model) {

                model.addAttribute(
                                "teacher",
                                new Teacher());

                return "teacherRegistration";

        }

        @GetMapping("/login")
        public String loginPage() {

                return "teacher-login";
        }

        // ==========================
        // SAVE TEACHER
        // ==========================

        @PostMapping("/save")
        public String saveTeacher(

                        @RequestParam("fullName") String fullName,
                        @RequestParam("fatherOrMotherName") String fatherOrMotherName,
                        @RequestParam("gender") String gender,
                        @RequestParam("dateOfBirth") String dateOfBirth,
                        @RequestParam("mobileNumber") String mobileNumber,
                        @RequestParam("email") String email,

                        @RequestParam("address") String address,
                        @RequestParam("city") String city,
                        @RequestParam("state") String state,
                        @RequestParam("pincode") String pincode,

                        @RequestParam("qualification") String qualification,
                        @RequestParam("specialization") String specialization,
                        @RequestParam("experienceYears") Integer experienceYears,
                        @RequestParam("previousSchoolName") String previousSchoolName,
                        @RequestParam("joiningDate") String joiningDate,

                        @RequestParam("profilePhoto") MultipartFile profilePhoto,
                        @RequestParam("aadhaarOrIdProof") MultipartFile aadhaar,
                        @RequestParam("qualificationCertificate") MultipartFile qualificationFile,
                        @RequestParam("experienceCertificate") MultipartFile experience,
                        @RequestParam("resume") MultipartFile resume,
                        @RequestParam("aadhaarNumber") String aadhaarNumber,

                        RedirectAttributes redirectAttributes

        ) throws Exception {

                Teacher teacher = new Teacher();

                teacher.setFullName(fullName);
                teacher.setFatherOrMotherName(fatherOrMotherName);
                teacher.setGender(gender);

                teacher.setDateOfBirth(
                                java.time.LocalDate.parse(dateOfBirth));

                teacher.setMobileNumber(mobileNumber);

                teacher.setEmail(email);

                teacher.setAddress(address);

                teacher.setCity(city);

                teacher.setState(state);

                teacher.setPincode(pincode);

                teacher.setAadhaarNumber(aadhaarNumber);
                teacher.setQualification(qualification);

                teacher.setSpecialization(specialization);

                teacher.setExperienceYears(experienceYears);

                teacher.setPreviousSchoolName(previousSchoolName);

                teacher.setJoiningDate(
                                java.time.LocalDate.parse(joiningDate));

                // FILE UPLOAD

                teacher.setProfilePhoto(upload(profilePhoto));

                teacher.setAadhaarOrIdProof(upload(aadhaar));

                teacher.setQualificationCertificate(upload(qualificationFile));

                teacher.setExperienceCertificate(upload(experience));

                teacher.setResume(upload(resume));

                // LOGIN DETAILS

                String id = "TCH" + System.currentTimeMillis();

                teacher.setTeacherId(id);

                teacher.setPassword(
                                dateOfBirth.replace("-", ""));

                service.saveTeacher(teacher);

                // REDIRECT DATA

                redirectAttributes.addFlashAttribute(
                                "teacherId",
                                id);

                redirectAttributes.addFlashAttribute(
                                "password",
                                teacher.getPassword());

                return "redirect:/teacher/success";

        }

        @GetMapping("/success")
        public String success() {

                return "teacher-success";

        }

        // ==========================
        // EDIT PAGE
        // ==========================

        @GetMapping("/edit/{id}")
        public String editPage(

                        @PathVariable Long id,

                        Model model

        ) {

                Teacher teacher = service.getTeacherById(id);

                model.addAttribute(
                                "teacher",
                                teacher);

                return "teacher-update";

        }

        // ==========================
        // UPDATE TEACHER
        // ==========================

        @PostMapping("/update")
        public String updateTeacher(

                        @ModelAttribute Teacher teacher,

                        @RequestParam("profilePhoto") MultipartFile profilePhoto,

                        @RequestParam("aadhaarOrIdProof") MultipartFile aadhaar,

                        @RequestParam("qualificationCertificate") MultipartFile qualification,

                        @RequestParam("experienceCertificate") MultipartFile experience,

                        @RequestParam("resume") MultipartFile resume

        ) throws Exception {

                Teacher old = service.getTeacherById(
                                teacher.getId());

                // FILE UPDATE

                if (!profilePhoto.isEmpty()) {

                        old.setProfilePhoto(
                                        upload(profilePhoto));

                }

                if (!aadhaar.isEmpty()) {

                        old.setAadhaarOrIdProof(
                                        upload(aadhaar));

                }

                if (!qualification.isEmpty()) {

                        old.setQualificationCertificate(
                                        upload(qualification));

                }

                if (!experience.isEmpty()) {

                        old.setExperienceCertificate(
                                        upload(experience));

                }

                if (!resume.isEmpty()) {

                        old.setResume(
                                        upload(resume));

                }

                // TEXT UPDATE

                old.setFullName(
                                teacher.getFullName());

                old.setFatherOrMotherName(
                                teacher.getFatherOrMotherName());

                old.setGender(
                                teacher.getGender());

                old.setMobileNumber(
                                teacher.getMobileNumber());

                old.setEmail(
                                teacher.getEmail());

                old.setAddress(
                                teacher.getAddress());

                old.setCity(
                                teacher.getCity());

                old.setState(
                                teacher.getState());

                old.setPincode(
                                teacher.getPincode());

                old.setQualification(
                                teacher.getQualification());

                old.setSpecialization(
                                teacher.getSpecialization());

                old.setExperienceYears(
                                teacher.getExperienceYears());

                old.setPreviousSchoolName(
                                teacher.getPreviousSchoolName());

                old.setJoiningDate(
                                teacher.getJoiningDate());

                service.saveTeacher(old);

                return "redirect:/teacher/list";

        }

        // ==========================
        // DELETE
        // ==========================

        @GetMapping("/delete/{id}")
        public String delete(

                        @PathVariable Long id

        ) {

                service.deleteTeacher(id);

                return "redirect:/teacher/list";

        }

        private String upload(MultipartFile file) throws Exception {

                if (file == null || file.isEmpty()) {
                        return null;
                }

                String uploadDir = "src/main/resources/static/document/";

                File folder = new File(uploadDir);

                if (!folder.exists()) {
                        folder.mkdirs();
                }

                String fileName = System.currentTimeMillis()
                                + "_"
                                + file.getOriginalFilename();

                Path path = Paths.get(uploadDir + fileName);

                Files.write(
                                path,
                                file.getBytes());

                return "/document/" + fileName;

        }

        @Autowired
        private TeacherRepository teacherRepository;

        @GetMapping("/teacher")
        public String teacherLoginPage() {
                return "teacher-login";
        }

        // @GetMapping("/dashboard")
        // public String dashboard(HttpSession session, Model model){

        // Teacher teacher = (Teacher) session.getAttribute("teacher");

        // if(teacher == null){
        // return "redirect:/teacher/login";
        // }

        // model.addAttribute("teacher", teacher);

        // return "teacher-dashboard";
        // }
        @Autowired
        private NoticeService noticeService;

        @Autowired
        private ResultStatusRepository resultStatusRepository;

        @GetMapping("/dashboard")
        public String dashboard(
                        HttpSession session,
                        Model model) {

                Teacher teacher = (Teacher) session.getAttribute("teacher");

                if (teacher == null) {
                        return "redirect:/teacher/login";
                }

                model.addAttribute("teacher", teacher);

                model.addAttribute(
                                "notices",
                                noticeService.getTeacherNotices());

                // Result Status
                boolean resultDeclared = resultStatusRepository
                                .findById(1L)
                                .map(ResultStatus::isDeclared)
                                .orElse(false);

                model.addAttribute("resultDeclared", resultDeclared);

                return "teacher-dashboard";
        }

        @PostMapping("/login")
        public String login(
                        @RequestParam String teacherId,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

                Teacher teacher = teacherRepository.findByTeacherId(teacherId);

                if (teacher != null && teacher.getPassword().equals(password)) {

                        session.setAttribute("teacher", teacher);

                        return "redirect:/teacher/dashboard";
                }

                model.addAttribute("error", "Invalid Teacher ID or Password");
                return "teacher-login";
        }

        @GetMapping("/teacher/dashboard")
        public String teacherDashboard(
                        HttpSession session,
                        Model model) {

                Teacher teacher = (Teacher) session.getAttribute("teacher");

                if (teacher == null) {

                        return "redirect:/teacher/login";

                }

                model.addAttribute("teacher", teacher);

                return "teacher-dashboard";

        }

        @Autowired
        private TeacherSubjectRepository tsRepo;

        @Autowired
        private SubjectRepository subjectRepo;

        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private SubjectAssignmentRepo subjectAssignmentRepo;

        @GetMapping("/add-marks")
        public String addMarks(
                        @RequestParam(required = false) Long subjectId,
                        @RequestParam(required = false) String className,
                        Model model,
                        HttpSession session) {

                Teacher teacher = (Teacher) session.getAttribute("teacher");

                if (teacher == null) {
                        return "redirect:/teacher/login";
                }

                String teacherName = teacher.getFullName();

                List<SubjectAssignment> assigned = subjectAssignmentRepo.findByTeacherName(teacherName);

                model.addAttribute("assignedSubjects", assigned);

                List<String> classes = assigned.stream()
                                .map(SubjectAssignment::getClassName)
                                .distinct()
                                .toList();

                model.addAttribute("classes", classes);

                // Default value
                boolean editEnabled = true;

                // Result Status
                boolean resultDeclared = resultStatusRepository
                                .findById(1L)
                                .map(ResultStatus::isDeclared)
                                .orElse(false);

                model.addAttribute("resultDeclared", resultDeclared);

                if (subjectId != null && className != null) {

                        SubjectAssignment assignment = subjectAssignmentRepo
                                        .findById(subjectId)
                                        .orElse(null);

                        if (assignment != null) {
                                editEnabled = assignment.isEditEnabled();
                        }

                        // Result declared hone ke baad editing hamesha band
                        if (resultDeclared) {
                                editEnabled = false;
                        }

                        List<Student> students = studentRepository.findByClassName(className);

                        model.addAttribute("students", students);
                        model.addAttribute("selectedSubjectId", subjectId);
                        model.addAttribute("selectedClassName", className);
                }

                model.addAttribute("editEnabled", editEnabled);

                return "teacher/add-marks";
        }

        @Autowired
        private MarksRepository marksRepository;

        @PostMapping("/save-marks")
        public String saveMarks(

                        @RequestParam Long subjectId,
                        @RequestParam List<Long> studentId,
                        @RequestParam List<Integer> marks,
                        HttpSession session

        ) {

                Teacher teacher = (Teacher) session.getAttribute("teacher");

                if (teacher == null) {
                        return "redirect:/teacher/login";
                }

                SubjectAssignment assignment = subjectAssignmentRepo
                                .findById(subjectId)
                                .orElseThrow();

                for (int i = 0; i < studentId.size(); i++) {

                        Student student = studentRepository
                                        .findById(studentId.get(i))
                                        .orElseThrow();

                        Marks m = marksRepository
                                        .findByStudentAndSubject(student, assignment.getSubjectName())
                                        .orElse(new Marks());

                        m.setStudent(student);
                        m.setTeacher(teacher);
                        m.setSubject(assignment.getSubjectName());
                        m.setExam("Unit Test");
                        m.setObtainedMarks(marks.get(i));
                        m.setTotalMarks(100);
                        m.setStatus("SUBMITTED");

                        marksRepository.save(m);
                }

                // ===== Auto Disable Editing =====
                assignment.setEditEnabled(false);
                subjectAssignmentRepo.save(assignment);

                return "redirect:/teacher/add-marks";
        }
}