package com.enterpriseassignment.service;

import com.enterpriseassignment.dto.response.DashboardResponseDto;

public interface DashboardService {

    DashboardResponseDto getDashboardMetrics();

    Double getProjectProgress(Long projectId);
}