package com.kitm.library.api.order;

import com.kitm.library.api.order.dto.CreateOrderDto;
import com.kitm.library.api.order.dto.OrderDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 17.04.22
 */
@Api(value = "Order")
@RestController
@RequestMapping(path = "/api/orders")
@RequiredArgsConstructor
public class OrderRestController {

  private final IOrderService orderService;

  @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<OrderDto> getOrdersByUserId(@PathVariable("userId") UUID userId) {

    return orderService.findAllByUserId(userId);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public OrderDto createOne(@RequestBody @Valid CreateOrderDto createOrderDto) {

    return orderService.createOne(createOrderDto);
  }

  @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void deleteOne(@PathVariable("userId") UUID orderId) {

    orderService.deleteOne(orderId);
  }
}
