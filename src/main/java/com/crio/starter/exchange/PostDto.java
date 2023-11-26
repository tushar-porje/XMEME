package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class PostDto {
    long id;
    String name;
    String url;
    String caption;
}
