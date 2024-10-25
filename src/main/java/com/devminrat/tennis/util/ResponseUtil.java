package com.devminrat.tennis.util;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class ResponseUtil {
    private ResponseUtil() {
    }

    public static void writeInternalServerErrorResponse(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        resp.getWriter().write("{\"error\":\"Internal Server Error\"}");
    }

    public static void writeInternalServerErrorResponse(HttpServletResponse resp, String msg) throws IOException {
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        resp.getWriter().write("{\"error\":\"" + msg + "\"}");
    }

    public static void writeNotFoundResponse(HttpServletResponse resp, String msg) throws IOException {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        resp.getWriter().write("{\"error\":\"" + msg + "\"}");
    }

    public static void writeConflictResponse(HttpServletResponse resp, String msg) throws IOException {
        resp.setStatus(HttpServletResponse.SC_CONFLICT);
        resp.getWriter().write("{\"error\":\"" + msg + "\"}");
    }

}
