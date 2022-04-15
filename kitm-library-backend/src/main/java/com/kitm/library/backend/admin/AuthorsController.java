package com.kitm.library.backend.admin;

import com.kitm.library.backend.admin.dto.SubmitAuthorFormDto;
import com.kitm.library.backend.admin.models.Form;
import com.kitm.library.backend.admin.models.ItemList;
import com.kitm.library.backend.admin.models.Layout;
import com.kitm.library.backend.domain.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

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

    model.addAttribute("itemList", createItemList());

    return "authors/authors";
  }

  @PostMapping("/admin/authors/update")
  public String updateAuthor(@ModelAttribute SubmitAuthorFormDto upsertAuthorDto, Model model) {

    switch (upsertAuthorDto.getAction()) {
      case Add -> authorService.createOne(upsertAuthorDto);
      case Update -> authorService.updateOne(upsertAuthorDto.getId(), upsertAuthorDto);
      case Delete -> authorService.deleteOne(upsertAuthorDto.getId());
    }

    return "redirect:/admin/authors";
  }

  @GetMapping("/admin/authors/add")
  public String addAuthor(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Authors));

    model.addAttribute("form", Form.add("Add author", "Add author page", "/admin/authors/update"));
    model.addAttribute("dto", SubmitAuthorFormDto.create());

    return "authors/update-author";
  }

  @GetMapping("/admin/authors/{id}")
  public String updateAuthor(@PathVariable("id") UUID id, Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Authors));

    model.addAttribute("form", Form.update("Update author", "Update author page", "/admin/authors/update"));
    model.addAttribute("dto", SubmitAuthorFormDto.update(authorService.getOne(id)));

    return "authors/update-author";
  }

  private ItemList createItemList() {

    return new ItemList(
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
    );
  }
}
