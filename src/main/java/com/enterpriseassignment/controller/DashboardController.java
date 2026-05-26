package com.enterpriseassignment.controller;

import com.enterpriseassignment.dto.response.ApiResponse;
import com.enterpriseassignment.dto.response.DashboardResponseDto;
import com.enterpriseassignment.service.DashboardService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/metrics")
    public ResponseEntity<ApiResponse<DashboardResponseDto>> getDashboardMetrics() {

        DashboardResponseDto metrics =
                dashboardService.getDashboardMetrics();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Dashboard metrics fetched successfully",
                        metrics
                )
        );
    }

    @GetMapping("/project-progress/{projectId}")
    public ResponseEntity<ApiResponse<Double>> getProjectProgress(
            @PathVariable Long projectId) {

        Double progress =
                dashboardService.getProjectProgress(projectId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Project progress fetched successfully",
                        progress
                )
        );
    }
}