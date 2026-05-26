package com.enterpriseassignment.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponseDto {

    private long totalTasks;

    private long completedTasks;

    private long pendingTasks;

    private long blockedTasks;

    private long overdueTasks;
}