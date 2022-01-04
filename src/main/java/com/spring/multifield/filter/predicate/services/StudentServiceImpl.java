package com.spring.multifield.filter.predicate.services;

import com.spring.multifield.filter.predicate.dao.entity.StudentDAO;
import com.spring.multifield.filter.predicate.dto.FilterRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StudentDAO> applyFilter(FilterRequest filterRequest) {

        List<String> firstName = new ArrayList<>();
        if (filterRequest.getFirstName() != null) {
            firstName = Arrays.asList(filterRequest.getFirstName().split(","));
        }

        List<String> lastName = new ArrayList<>();

         if (filterRequest.getLastName() != null) {
            lastName = Arrays.asList(filterRequest.getLastName().split(","));
        }

        List<String> parentName = new ArrayList<>();

        if (filterRequest.getParentName() != null) {
            parentName = Arrays.asList(filterRequest.getParentName().split(","));
        }

        List<String> rollNo = new ArrayList<>();

        if (filterRequest.getRollNo() != null) {
            rollNo = Arrays.asList(filterRequest.getRollNo().split(","));
        }

        List<String> grade = new ArrayList<>();

        if (filterRequest.getGrade() != null) {
            grade = Arrays.asList(filterRequest.getGrade().split(","));
        }

        List<String> section = new ArrayList<>();

        if (filterRequest.getSection() != null) {
            section = Arrays.asList(filterRequest.getSection().split(","));
        }


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentDAO> q = cb.createQuery(StudentDAO.class);
        Root<StudentDAO> studentDAORoot = q.from(StudentDAO.class);

        List<Predicate> firstNamePredicate = new ArrayList<>();

        List<Predicate> lastNamePredicate = new ArrayList<>();

        List<Predicate> parentNamePredicate = new ArrayList<>();

        List<Predicate> rollNoPredicate = new ArrayList<>();

        List<Predicate> gradePredicate = new ArrayList<>();

        List<Predicate> sectionPredicate = new ArrayList<>();


        if (firstName.size() != 0) {

            firstNamePredicate.add(studentDAORoot.get("firstName").in(firstName));
        }

        if (lastName.size() != 0) {

            lastNamePredicate.add(studentDAORoot.get("lastName").in(lastName));
        }

        if (parentName.size() != 0) {

            parentNamePredicate.add(studentDAORoot.get("parentName").in(parentName));
        }

        if (rollNo.size() != 0) {

            rollNoPredicate.add(studentDAORoot.get("rollNo").in(rollNo));
        }

        if (grade.size() != 0) {

            gradePredicate.add(studentDAORoot.get("grade").in(grade));
        }

        if (section.size() != 0) {

            sectionPredicate.add(studentDAORoot.get("section").in(section));
        }

        q.select(studentDAORoot).where(

                cb.and(firstNamePredicate.toArray(new Predicate[firstNamePredicate.size()])),
                cb.and(lastNamePredicate.toArray(new Predicate[lastNamePredicate.size()])),
                cb.and(parentNamePredicate.toArray(new Predicate[parentNamePredicate.size()])),
                cb.and(rollNoPredicate.toArray(new Predicate[rollNoPredicate.size()])),
                cb.and(gradePredicate.toArray(new Predicate[gradePredicate.size()])),
                cb.and(sectionPredicate.toArray(new Predicate[sectionPredicate.size()]))

        );
        List<StudentDAO> resultList = entityManager.createQuery(q).getResultList();

        return resultList;

    }
}
