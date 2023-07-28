package com.sharding;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sharding.entity.Course;
import com.sharding.mapper.CourseMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenYP
 * @date 2023/7/19 8:58
 * @Describe
 */
public class ShardingCourseTest extends ShardingTest{

    @Resource
    private CourseMapper courseMapper;


    @Test
    public void addCourse(){
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCname("java");
            course.setUserId(100L);
            course.setCstatus("1");
            courseMapper.insert(course);
        }

    }

    @Test
    public void selectCourse(){
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
//        queryWrapper.between("cid",1681238732243574785L,1681238735527714817L);
        queryWrapper.in("cid",1681238732243574785L,1681238735527714817L);
        List<Course> course = courseMapper.selectList(queryWrapper);
        for (Course course1 : course) {
            System.out.println(course1);
        }
    }
}
