package org.techmate.techmate_be.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Page<T> {
    private List<T> content;
    private PageInfo page;

    public Page(List<T> content, PageInfo page) {
        this.content = content;
        this.page = page;
    }
}
