package com.daym.blogs.header;

import lombok.Builder;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Date: 2024/4/15
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
@Builder
public class RequestHeader {
    private String sign ;
    private Long timestamp ;
    private String nonce;
}