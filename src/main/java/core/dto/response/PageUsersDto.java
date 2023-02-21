package core.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({"number", "size", "total_pages", "total_elements",
        "first", "number_of_elements", "last", "content"})
public class PageUsersDto implements Serializable {

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

    @JsonProperty("content")
    private List<PageUserDto> users;

    public PageUsersDto(int number, int size, int totalPages, long totalElements,
                        boolean first, int numberElements, boolean last, List<PageUserDto> users) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberElements = numberElements;
        this.last = last;
        this.users = users;
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

    public List<PageUserDto> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageUsersDto that = (PageUsersDto) o;
        return number == that.number
                && size == that.size
                && totalPages == that.totalPages
                && totalElements == that.totalElements
                && first == that.first
                && numberElements == that.numberElements
                && last == that.last
                && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, size, totalPages,
                totalElements, first, numberElements, last, users);
    }

    @Override
    public String toString() {
        return "PageUsersDto{" +
                "number=" + number +
                ", size=" + size +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", first=" + first +
                ", numberElements=" + numberElements +
                ", last=" + last +
                ", users=" + users +
                '}';
    }
}