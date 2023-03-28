package com.java.backend.enums;

import lombok.Getter;

@Getter
public enum GroupAuthority {
    MEMBER_MANAGEMENT("Quản lý thành viên"),
    STAFF_MANAGEMENT("Quản lý nhân sự"),
    TAG_MANAGEMENT("Quản lý các Tag"),
    ROUTE_MANAGEMENT("Quản lý tuyến đường"),
    OTHER("Cài đặt khác"),
    TRIP_MANAGEMENT("Quản lý lịch"),
    REPORT_MANAGEMENT("Quản lý tố cáo");
    private final String vi;
    GroupAuthority(String vi){
        this.vi = vi;
    }
}
