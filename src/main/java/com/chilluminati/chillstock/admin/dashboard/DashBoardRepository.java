package com.chilluminati.chillstock.admin.dashboard;

public interface DashBoardRepository {
    Integer countInbounds();
    Integer countOutbounds();
    Integer countTodayInboundRequests();
    Integer countTodayOutboundRequests();
}
