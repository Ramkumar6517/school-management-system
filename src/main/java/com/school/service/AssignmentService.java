package com.school.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.school.entity.Subject;
import com.school.entity.Teacher;
import com.school.entity.TeacherAssignment;
import com.school.repository.SubjectRepository;
import com.school.repository.TeacherAssignmentRepo;
import com.school.repository.TeacherRepository;

@Service
public class AssignmentService {

    private final TeacherAssignmentRepo assignmentRepo;
    private final TeacherRepository teacherRepo;
    private final SubjectRepository subjectRepo;

    // ✅ Constructor Injection (BEST PRACTICE)
    public AssignmentService(TeacherAssignmentRepo assignmentRepo,
                             TeacherRepository teacherRepo,
                             SubjectRepository subjectRepo) {
        this.assignmentRepo = assignmentRepo;
        this.teacherRepo = teacherRepo;
        this.subjectRepo = subjectRepo;
    }

    public void assign(Long teacherId,
                       List<Long> subjectIds,
                       List<String> classes,
                       String admin) {

        Teacher teacher = teacherRepo.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        for (Long subId : subjectIds) {

            Subject subject = subjectRepo.findById(subId)
                    .orElseThrow(() -> new RuntimeException("Subject not found"));

            for (String cls : classes) {

                TeacherAssignment a = new TeacherAssignment();

                a.setTeacherId(teacher.getId());
                a.setTeacherName(teacher.getFullName());

                a.setSubjectId(subject.getId());
                a.setSubjectName(subject.getName());

                a.setClassName("Class " + cls);

                a.setAssignedBy(admin);
                a.setAssignedAt(LocalDateTime.now());

                assignmentRepo.save(a);
            }
        }
    }

    public List<TeacherAssignment> getAllAssignments() {
        return assignmentRepo.findAllByOrderByAssignedAtDesc();
    }
}