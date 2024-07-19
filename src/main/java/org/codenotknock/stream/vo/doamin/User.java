package org.codenotknock.stream.vo.doamin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xiaofu
 * @date 2023/12/16 3:35
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User extends People{
    private String name;

    private int age;

    private List<Book> books;

}
