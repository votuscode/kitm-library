package com.kitm.library.backend.admin.models;

import com.kitm.library.api.author.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Collection;
import java.util.List;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 15.04.22
 */
@AllArgsConstructor
public class ItemList {
  public String header;
  public String description;
  public Action action;
  public List<Item> items;

  @AllArgsConstructor
  public static class Action {
    public String label;
    public String href;
  }

  @Builder
  @AllArgsConstructor
  public static class Item {
    public String name;
    public String description;
    public String info;
    public String badge;
    public String href;
  }
}
