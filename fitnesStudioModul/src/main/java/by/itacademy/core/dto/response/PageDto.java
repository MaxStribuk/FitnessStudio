package by.itacademy.core.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({"number", "size", "total_pages", "total_elements",
        "first", "number_of_elements", "last", "content"})
public class PageDto<T> implements Serializable {

    private int number;

    private int size;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_elements")
    private long totalElements;

    private boolean first;

    @JsonProperty("number_of_elements")
    private int numberElements;

    private boolean last;

    private List<T> content;

    public PageDto(int number, int size, int totalPages, long totalElements,
                   boolean first, int numberElements, boolean last, List<T> content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberElements = numberElements;
        this.last = last;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public int getNumberElements() {
        return numberElements;
    }

    public boolean isLast() {
        return last;
    }

    public List<T> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageDto<?> pageDto = (PageDto<?>) o;
        return number == pageDto.number
                && size == pageDto.size
                && totalPages == pageDto.totalPages
                && totalElements == pageDto.totalElements
                && first == pageDto.first
                && numberElements == pageDto.numberElements
                && last == pageDto.last
                && Objects.equals(content, pageDto.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, size, totalPages, totalElements,
                first, numberElements, last, content);
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "number=" + number +
                ", size=" + size +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", first=" + first +
                ", numberElements=" + numberElements +
                ", last=" + last +
                ", content=" + content +
                '}';
    }
}