package org.codenotknock.stream.vo.doamin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaofu
 * @date 2023/12/16 3:41
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private String author;
    private int year;
}