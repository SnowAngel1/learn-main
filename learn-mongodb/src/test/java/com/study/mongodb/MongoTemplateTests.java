package com.study.mongodb;

import com.mongodb.client.result.UpdateResult;
import com.study.StudyMongodbApplicationTest;
import com.study.mongodb.entity.Employee;
import net.sf.jsqlparser.statement.select.Skip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYP
 * @date 2023/7/12 16:53
 * @describe
 */
public class MongoTemplateTests extends StudyMongodbApplicationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建集合
     */
    @Test
    public void testCreateCollection() {
        boolean exists = mongoTemplate.collectionExists("emp");
        //判断集合是否存在，存在执行删除后创建
        if (exists) {
            mongoTemplate.dropCollection("emp");
        }
        mongoTemplate.createCollection("emp");
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee(1, "陈鹏", 27, 1000.00, new Date());

        //添加文档，save方法执行时，如果_id存在会更新数据
        mongoTemplate.save(employee);
        //添加文档，insert方法执行时，如果_id存在会抛出一场，并支持批量操作
//       mongoTemplate.insert(employee);
        List<Employee> list = Arrays.asList(
                new Employee(2, "张三", 27, 1000.00, new Date()),
                new Employee(3, "李四", 12, 7000.00, new Date()),
                new Employee(4, "王五", 3, 5000.00, new Date()),
                new Employee(5, "张龙", 43, 9000.00, new Date()),
                new Employee(6, "赵虎", 49, 2000.00, new Date()),
                new Employee(7, "赵六", 56, 5000.00, new Date())
        );
//        mongoTemplate.insert(list,Employee.class);
    }

    @Test
    public void testFind() {

        /*System.out.println("====查询所有文档=====");
        List<Employee> allList = mongoTemplate.findAll(Employee.class);
        allList.forEach(System.out::println);

        System.out.println("========根据_id查询====");
        Employee byId = mongoTemplate.findById(1, Employee.class);
        System.out.println(byId);

        System.out.println("=====findOne 返回第一个文档=====");
        Employee one = mongoTemplate.findOne(new Query(), Employee.class);
        System.out.println(one);*/


        System.out.println("=========条件查询==========");
        //表示没有条件
//        Query query  = new Query();
        //查询薪资大于等于8000的员工
//        Query query = new Query(Criteria.where("salary").gte(8000));
        //查询薪资大于4000小于10000的员工
//        Query query = new Query(Criteria.where("salary").gt(4000).lt(10000));
        //正则查询（模糊匹配）Java中正则不需要有 //
//        Query query = new Query(Criteria.where("name").regex("张"));

        //查询结果
//        List<Employee> employees = mongoTemplate.find(query, Employee.class);
//        employees.forEach(System.out::println);

        //多条件查询 and or
        Criteria criteria = new Criteria();
        //and 查询年龄大于25&薪资大于8000的员工
        criteria.andOperator(Criteria.where("age").gt(25), Criteria.where("salary").gt(5000));
        //or 查询姓名时张三或者薪资大于8000的员工
//        criteria.orOperator(Criteria.where("age").gt(25),Criteria.where("salary").gt(5000));

        Query query = new Query(criteria);
        //sort 排序
//        query.with(Sort.by(Sort.Order.desc("salary")));

        //limit分页 skip用于指定跳过的记录数，limit则用于返回限定的返回结果数量
        query.with(Sort.by(Sort.Order.desc("salary")))
                .skip(0)//指定跳过的记录数
                .limit(4);//每页显示记录数

        //查询结果
        List<Employee> employees = mongoTemplate.find(query, Employee.class);
        employees.forEach(System.out::println);
    }


    @Test
    public void testFindByJson() {
        //使用Json字符串方式查询
        //等值查询
//       String json  = "{\"name\":\"张三\"}";

        //多条件查询
        String json = "{$and:[{age: {$gt:25}},{salary: {$gte: 8000}}]}";

        Query query = new BasicQuery(json);
        List<Employee> employees = mongoTemplate.find(query, Employee.class);
        employees.forEach(System.out::println);
    }

    @Test
    public void testUpdate() {
        Query query = new Query(Criteria.where("salary").gt(150000));

        System.out.println("========数据更新前========");

        List<Employee> employees = mongoTemplate.find(query, Employee.class);
        employees.forEach(System.out::println);


        Update update = new Update();
        //设置更新的属性
        update.set("salary", 13000);
        //updateFirst() 只更新满足条件的第一条记录
       // UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Employee.class);
        // updateMulti() 更新所有满足条件的记录
       // UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Employee.class);
       //  upsert() 没有符合条件的记录则插入数据
        update.setOnInsert("id",11);
        UpdateResult updateResult = mongoTemplate.upsert(query, update, Employee.class);


        //返回修改的记录数
        System.out.println("更新记录数:" + updateResult.getModifiedCount());

        System.out.println("============数据更新后=============");
        employees = mongoTemplate.find(query, Employee.class);
        employees.forEach(System.out::println);
    }

    @Test
    public void testDelete(){
        //删除所有文档， 不如使用dropCollection()
        mongoTemplate.remove(new Query(),Employee.class);

        //条件删除
        Query query = new Query(Criteria.where("salary").gte(5000));
        // mongoTemplate.remove(query,Employee.class);
    }
}
