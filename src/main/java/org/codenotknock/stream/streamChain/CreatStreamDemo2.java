package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author xiaofu
 * @date 2023/12/16 3:45
 * @description
 */
public class CreatStreamDemo2 {
    public static void main(String[] args) {
        // 数组
        Integer[] arr = {};
        Stream<Integer> integerStream = Arrays.stream(arr);

        // 单列集合
        List<User> list = new ArrayList<>();
        Stream<User> listStream = list.stream();
        HashSet<Object> set = new HashSet<>();
        Stream<Object> setStream = set.stream();

        // 双列集合map 先转化为单列
        HashMap<Object, Object> map = new HashMap<>();
        Stream<Map.Entry<Object, Object>> entryStream = map.entrySet().stream();
    }
}
