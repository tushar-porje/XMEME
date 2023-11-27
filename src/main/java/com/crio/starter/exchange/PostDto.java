package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor@NoArgsConstructor
public class PostDto {
    long id;
    @NotBlank
    String name;
    @NotBlank
    String url;
    @NotBlank
    String caption;
}
