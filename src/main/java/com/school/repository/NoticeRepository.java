// package com.school.repository;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.school.entity.Notice;

// public interface NoticeRepository extends JpaRepository<Notice, Long> {

//     List<Notice> findByTargetRoleIn(
//         List<String> roles
// );

// List<Notice> findByTargetRole(String targetRole);
// }

package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.Notice;

public interface NoticeRepository
        extends JpaRepository<Notice, Long> {

    List<Notice> findByTargetRoleIn(List<String> roles);

    List<Notice> findBySender(String sender);

}