package com.java.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Authority {
    CREATE_DRIVER(GroupAuthority.MEMBER_MANAGEMENT, "Tạo mới tài xế"),
    GET_LIST_DRIVER(GroupAuthority.MEMBER_MANAGEMENT, "Xem danh sách tài xế"),
    UPDATE_DRIVER(GroupAuthority.MEMBER_MANAGEMENT, "Sửa tài xế"),
    DELETE_DRIVER(GroupAuthority.MEMBER_MANAGEMENT, "Xóa tài xế"),

    CREATE_STAFF(GroupAuthority.STAFF_MANAGEMENT, "Tạo mới nhân viên"),
    GET_LIST_STAFF(GroupAuthority.STAFF_MANAGEMENT, "Xem danh sách nhân viên"),
    UPDATE_STAFF(GroupAuthority.STAFF_MANAGEMENT, "Sửa nhân viên"),
    DELETE_STAFF(GroupAuthority.STAFF_MANAGEMENT, "Xóa nhân viên"),

    CREATE_TAG(GroupAuthority.TAG_MANAGEMENT, "Tạo mới Tag"),
    GET_LIST_TAG(GroupAuthority.TAG_MANAGEMENT, "Xem danh sách Tag"),
    UPDATE_TAG(GroupAuthority.TAG_MANAGEMENT, "Sửa Tag"),
    DELETE_TAG(GroupAuthority.TAG_MANAGEMENT, "Xóa Tag"),

    CREATE_TRIP(GroupAuthority.TRIP_MANAGEMENT, "Tạo mới lịch"),
    GET_LIST_TRIP(GroupAuthority.TRIP_MANAGEMENT, "Xem danh sách lịch"),
    UPDATE_TRIP(GroupAuthority.TRIP_MANAGEMENT, "Sửa lịch"),
    DELETE_TRIP(GroupAuthority.TRIP_MANAGEMENT, "Xóa lịch"),

    CREATE_REPORT(GroupAuthority.REPORT_MANAGEMENT, "Tạo mới tố cáo"),
    GET_LIST_REPORT(GroupAuthority.REPORT_MANAGEMENT, "Xem danh sách tố cáo"),
    UPDATE_REPORT(GroupAuthority.REPORT_MANAGEMENT, "Sửa tố cáo"),
    DELETE_REPORT(GroupAuthority.REPORT_MANAGEMENT, "Xóa tố cáo");


    private final GroupAuthority group;
    private final String vi;

    public static List<Authority> getAllAuthorities() {
        return new ArrayList<>(EnumSet.allOf(Authority.class));
    }
}
