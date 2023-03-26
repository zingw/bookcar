package com.java.backend.entity;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum Authority {
    //  admin authorities
    ADMIN_FUNCTION_A(Role.ADMIN),
    ADMIN_FUNCTION_B(Role.ADMIN),
    ADMIN_FUNCTION_C(Role.ADMIN),
    ADMIN_FUNCTION_D(Role.ADMIN),
    ADMIN_FUNCTION_E(Role.ADMIN),

    //  customer support authorities
    CS_FUNCTION_A(Role.CS),
    CS_FUNCTION_B(Role.CS),
    CS_FUNCTION_C(Role.CS),
    CS_FUNCTION_D(Role.CS),

    //  accountant authorities
    KT_FUNCTION_A(Role.KT),
    KT_FUNCTION_B(Role.KT),
    KT_FUNCTION_C(Role.KT),
    KT_FUNCTION_D(Role.KT),
    KT_FUNCTION_E(Role.KT),
    KT_FUNCTION_F(Role.KT);

    private Role role;

    Authority(Role role) {
        this.role = role;
    }

    public static List<Authority> geAllAuthorities() {
        return new ArrayList<>(EnumSet.allOf(Authority.class));
    }

    public static List<Authority> getAvailableCSAuths() {
        return EnumSet.allOf(Authority.class).stream().filter(item -> item.role == Role.CS).collect(Collectors.toList());
    }

    public static List<Authority> getAvailableAccountantAuths() {
        return EnumSet.allOf(Authority.class).stream().filter(item -> item.role == Role.KT).collect(Collectors.toList());
    }
}
