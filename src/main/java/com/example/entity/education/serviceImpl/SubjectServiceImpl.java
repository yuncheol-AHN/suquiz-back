package com.example.entity.education.serviceImpl;

import com.example.entity.domain.Subject;
import com.example.entity.dto.subject.SubjectDTO;
import com.example.entity.education.service.EntityAndDtoConversionService;
import com.example.entity.education.service.SubjectService;
import com.example.entity.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final EntityAndDtoConversionService conversionService;
    private final WordServiceImpl wordService;

    @Override
    public List<SubjectDTO.AllSubject> findAll() {
        List<Subject> list = subjectRepository.findAll();
        List<SubjectDTO.AllSubject> newList = new ArrayList<>();
        for (Subject subject : list) {
            newList.add(SubjectDTO.AllSubject.builder().subjectName(subject.getSubjectName()).build());
        }
        return newList;
    }

    @Override
    public SubjectDTO.Response findAllSubWith(String subjectName) throws Exception {
        Subject findSubject = subjectRepository.findBySubjectName(subjectName);

        SubjectDTO.Response res = conversionService.findSubjectEntityToDto(findSubject);

        return res;
    }
}
