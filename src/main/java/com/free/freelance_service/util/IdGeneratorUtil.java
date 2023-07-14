package com.free.freelance_service.util;

import java.util.UUID;

public final class IdGeneratorUtil {

    private IdGeneratorUtil () {}
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
