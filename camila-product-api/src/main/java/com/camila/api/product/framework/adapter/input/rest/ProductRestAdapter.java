package com.camila.api.product.framework.adapter.input.rest;

import com.camila.api.product.application.usercase.ProductUserCase;
import com.camila.api.product.domain.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * The type Product rest adapter.
 */
@Tag(name = "Products", description = "Products API")
@RestController
@RequestMapping(value = "/products")
@Validated
@CrossOrigin
class ProductRestAdapter {

  private static final String SORTED_REQUEST_PARAMS = """
    {
      "salesUnits": 0.8,
      "stock": 0.2,
      "page": 0,
      "size": 50
    }
    """;
  private final ProductUserCase productUserCase;

  /**
   * Instantiates a new Product controller.
   *
   * @param productUserCase the product service
   */
  public ProductRestAdapter(ProductUserCase productUserCase) {
    this.productUserCase = productUserCase;
  }

  /**
   * Find by id response entity.
   *
   * @param internalId the internal id
   * @return the response entity
   */
  @Operation(
    summary = "Find product by internal identified",
    description = "Consult product by ID")
  @ApiResponse(responseCode = "200", description = "Success",
    content = {
      @Content(mediaType = MediaType.TEXT_EVENT_STREAM_VALUE),
      @Content(mediaType = MediaType.APPLICATION_NDJSON_VALUE),
      @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "500", description = "Server Error")
  @GetMapping(value = "/{id}", produces = {
    MediaType.TEXT_EVENT_STREAM_VALUE,
    MediaType.APPLICATION_NDJSON_VALUE,
    MediaType.APPLICATION_JSON_VALUE})
  ResponseEntity<Mono<Product>> findById(@PathVariable("id") String internalId) {
    return ResponseEntity.ok(productUserCase.findByInternalId(internalId));
  }

  /**
   * Sort products response entity.
   *
   * @param requestParams the request params
   * @return the response entity
   */
  @Operation(
    summary = "Find products sort by weight",
    description = "Consult products sorted by metric:weight rules")
  @ApiResponse(responseCode = "200", description = "Success",
    content = {
      @Content(mediaType = MediaType.TEXT_EVENT_STREAM_VALUE),
      @Content(mediaType = MediaType.APPLICATION_NDJSON_VALUE),
      @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "500", description = "Server Error")
  @GetMapping(produces = {
    MediaType.TEXT_EVENT_STREAM_VALUE,
    MediaType.APPLICATION_NDJSON_VALUE,
    MediaType.APPLICATION_JSON_VALUE})
  ResponseEntity<Flux<Product>> sortProducts(
    @RequestParam
    @Parameter(name = "requestParams", description = "Parameters map", example = SORTED_REQUEST_PARAMS)
    @Valid Map<@NotBlank String, @NotBlank @Min(0L) @Max(1000L) @PositiveOrZero String> requestParams) {
    return ResponseEntity.ok(productUserCase.sortByMetricsWeights(requestParams));
  }
}
