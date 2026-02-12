package org.techmate.techmate_be.util;

public class PaginationUtils {
    public static int getOffset(int page, int size) {
        return (page - 1) * size;
    }

    public static int getTotalPages(int totalElements, int size) {
        return (int) Math.ceil((double) totalElements / size);
    }
}
