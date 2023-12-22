package pe.joedayz.bookmarker.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.joedayz.bookmarker.domain.Bookmark;
import pe.joedayz.bookmarker.domain.BookmarkService;
import pe.joedayz.bookmarker.domain.BookmarksDTO;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarController {

  private final BookmarkService service;

  @GetMapping
  public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {
    return service.getBookmarks(page);
  }
}
