package org.techmate.techmate_be.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.techmate.techmate_be.util.PaginationUtils;

@Getter
@Setter
public class PageInfo {
    private int size;
    private int number;
    private int totalElements;
    private int totalPages;

    public PageInfo(int size, int number, int totalElements) {
        this.size = size;
        this.number = number;
        this.totalElements = totalElements;
        this.totalPages = PaginationUtils.getTotalPages(totalElements, size);
    }
}
