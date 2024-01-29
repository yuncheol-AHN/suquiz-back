package com.example.entity.education.service;

import com.example.entity.domain.Subject;
import com.example.entity.dto.subject.SubjectDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectService {

    SubjectDTO.Response findAllSubWith(String subjectName) throws Exception;

    List<SubjectDTO.AllSubject> findAll();

}
