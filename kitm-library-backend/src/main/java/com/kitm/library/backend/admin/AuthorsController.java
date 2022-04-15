package com.kitm.library.backend.admin;

import com.kitm.library.backend.admin.models.ItemList;
import com.kitm.library.backend.admin.models.Layout;
import com.kitm.library.backend.domain.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
@RequiredArgsConstructor
public class AuthorsController {

  private final AuthorService authorService;

  @GetMapping("/admin/authors")
  public String authorsList(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Authors));

    model.addAttribute("itemList", new ItemList(
        "Authors",
        "Authors page",
        new ItemList.Action("Add author", "/admin/authors/add"),
        authorService.findAll().stream()
            .map(author -> ItemList.Item.builder()
                .name(author.getName())
                .description(author.getDescription())
                .info("Books: " + author.getBooks())
                .href("/admin/authors/" + author.getId())
                .build())
            .toList()
    ));

    return "authors";
  }
}
