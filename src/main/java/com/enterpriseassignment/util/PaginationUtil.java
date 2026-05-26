package com.enterpriseassignment.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

public final class PaginationUtil {

    private PaginationUtil() {
    }

    
    public static Pageable getPageable(
            int page,
            int size,
            String sortBy,
            String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return PageRequest.of(page, size, sort);
    }

    public static Map<String, Object> buildPageResponse(
            Page<?> pageData) {

        Map<String, Object> response =
                new HashMap<>();

        response.put("content",
                pageData.getContent());

        response.put("currentPage",
                pageData.getNumber());

        response.put("totalItems",
                pageData.getTotalElements());

        response.put("totalPages",
                pageData.getTotalPages());

        response.put("pageSize",
                pageData.getSize());

        response.put("isLast",
                pageData.isLast());

        response.put("isFirst",
                pageData.isFirst());

        return response;
    }
}