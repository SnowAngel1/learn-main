package com.sharding;

import com.sharding.entity.Dict;
import com.sharding.mapper.DictMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ChenYP
 * @date 2023/7/19 9:00
 * @Describe
 */
public class ShardingDictTest extends ShardingTest{

    @Autowired
    private DictMapper dictMapper;




    @Test
    public void addDict(){
        Dict dict1 = new Dict();
        dict1.setUstatus("1");
        dict1.setUvalue("正常");
        dictMapper.insert(dict1);


        Dict dict2 = new Dict();
        dict2.setUstatus("1");
        dict2.setUvalue("正常");
        dictMapper.insert(dict2);
    }

}
