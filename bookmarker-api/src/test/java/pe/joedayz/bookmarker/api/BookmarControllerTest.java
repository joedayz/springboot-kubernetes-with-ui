package pe.joedayz.bookmarker.api;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import pe.joedayz.bookmarker.domain.Bookmark;
import pe.joedayz.bookmarker.domain.BookmarkRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo",
})
class BookmarControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  BookmarkRepository bookmarkRepository;

  private List<Bookmark> bookmarks;

  @BeforeEach
  void setUp(){
    bookmarkRepository.deleteAllInBatch();
    bookmarks = new ArrayList<>();

    bookmarks.add(new Bookmark(null, "Google", "https://www.google.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Amazon", "https://www.amazon.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Facebook", "https://www.facebook.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Twitter", "https://www.twitter.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Instagram", "https://www.instagram.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "LinkedIn", "https://www.linkedin.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Pinterest", "https://www.pinterest.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Github", "https://www.github.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Gitlab", "https://www.gitlab.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Bitbucket", "https://www.bitbucket.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Stackoverflow", "https://www.stackoverflow.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Youtube", "https://www.youtube.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Netflix", "https://www.netflix.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Spotify", "https://www.spotify.com", Instant.now()));
    bookmarks.add(new Bookmark(null, "Twitch", "https://www.twitch.com", Instant.now()));

    bookmarkRepository.saveAll(bookmarks);
  }
  @ParameterizedTest
  @CsvSource({
      "1,15,2,1,true,false,true,false",
      "2,15,2,2,false,true,false,true",
  })
  void shouldGetBookmarks(int pageNo, int totalElements, int totalPages, int currentPage,
      boolean isFirst, boolean isLast, boolean hasNext, boolean hasPrevious) throws Exception {
    mvc.perform(get("/api/bookmarks?page="+pageNo)).andExpect(status().isOk())
        .andExpect(jsonPath("$.totalElements", equalTo(totalElements)))
        .andExpect(jsonPath("$.totalPages", equalTo(totalPages)))
        .andExpect(jsonPath("$.currentPage", equalTo(currentPage)))
        .andExpect(jsonPath("$.isFirst", equalTo(isFirst)))
        .andExpect(jsonPath("$.isLast", equalTo(isLast)))
        .andExpect(jsonPath("$.hasNext", equalTo(hasNext)))
        .andExpect(jsonPath("$.hasPrevious", equalTo(hasPrevious)));
  }

}