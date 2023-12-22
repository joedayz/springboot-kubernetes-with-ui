package pe.joedayz.bookmarker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
public class BookmarksDTO {
  private List<Bookmark> data;
  private long totalElements;
  private int totalPages;
  private int currentPage;
  @JsonProperty("isFirst")
  private boolean isFirst;
  @JsonProperty("isLast")
  private boolean isLast;
  private boolean hasNext;
  private boolean hasPrevious;

  public BookmarksDTO(Page<Bookmark> bookmarkPage){
    this.setData(bookmarkPage.getContent());
    this.setTotalElements(bookmarkPage.getTotalElements());
    this.setTotalPages(bookmarkPage.getTotalPages());
    this.setCurrentPage(bookmarkPage.getNumber() + 1);
    this.setFirst(bookmarkPage.isFirst());
    this.setLast(bookmarkPage.isLast());
    this.setHasNext(bookmarkPage.hasNext());
    this.setHasPrevious(bookmarkPage.hasPrevious());
  }
}