package pe.joedayz.bookmarker.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pe.joedayz.bookmarker.domain.BookmarkDTO;
import pe.joedayz.bookmarker.domain.BookmarkService;
import pe.joedayz.bookmarker.domain.BookmarksDTO;
import pe.joedayz.bookmarker.domain.CreateBookmarkRequest;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
  private final BookmarkService bookmarkService;

  @GetMapping
  public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "query", defaultValue = "") String query) {
        if(query == null || query.trim().length() == 0) {
      return bookmarkService.getBookmarks(page);
    }
    return bookmarkService.searchBookmarks(query, page);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookmarkDTO createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
    return bookmarkService.createBookmark(request);
  }
}
