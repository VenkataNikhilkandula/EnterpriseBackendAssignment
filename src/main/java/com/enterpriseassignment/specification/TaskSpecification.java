package com.enterpriseassignment.specification;

import com.enterpriseassignment.entity.Task;
import com.enterpriseassignment.enums.TaskPriority;
import com.enterpriseassignment.enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TaskSpecification {

    private TaskSpecification() {
    }

    
    public static Specification<Task> hasStatus(
            TaskStatus status) {

        return (root, query, criteriaBuilder) -> {

            if (status == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    root.get("status"),
                    status
            );
        };
    }

    
    public static Specification<Task> hasPriority(
            TaskPriority priority) {

        return (root, query, criteriaBuilder) -> {

            if (priority == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    root.get("priority"),
                    priority
            );
        };
    }

    public static Specification<Task> hasAssignedUser(
            Long userId) {

        return (root, query, criteriaBuilder) -> {

            if (userId == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    root.get("assignedUser").get("id"),
                    userId
            );
        };
    }

    
    public static Specification<Task> hasProject(
            Long projectId) {

        return (root, query, criteriaBuilder) -> {

            if (projectId == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    root.get("project").get("id"),
                    projectId
            );
        };
    }

    
    public static Specification<Task> containsKeyword(
            String keyword) {

        return (root, query, criteriaBuilder) -> {

            if (keyword == null || keyword.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            String searchPattern =
                    "%" + keyword.toLowerCase() + "%";

            return criteriaBuilder.or(

                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    root.get("title")),
                            searchPattern
                    ),

                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    root.get("description")),
                            searchPattern
                    )
            );
        };
    }

   
    public static Specification<Task> isOverdue() {

        return (root, query, criteriaBuilder) ->

                criteriaBuilder.lessThan(
                        root.get("dueDate"),
                        LocalDate.now()
                );
    }

    
    public static Specification<Task> dueToday() {

        return (root, query, criteriaBuilder) ->

                criteriaBuilder.equal(
                        root.get("dueDate"),
                        LocalDate.now()
                );
    }

    
    public static Specification<Task> dueBetween(
            LocalDate startDate,
            LocalDate endDate) {

        return (root, query, criteriaBuilder) -> {

            if (startDate == null || endDate == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.between(
                    root.get("dueDate"),
                    startDate,
                    endDate
            );
        };
    }

    
    public static Specification<Task> buildFilter(
            TaskStatus status,
            TaskPriority priority,
            Long userId,
            Long projectId,
            String keyword) {

        return Specification.where(hasStatus(status))
                .and(hasPriority(priority))
                .and(hasAssignedUser(userId))
                .and(hasProject(projectId))
                .and(containsKeyword(keyword));
    }
}