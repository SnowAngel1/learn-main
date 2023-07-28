package com.example.elasticsearch.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 江南
 * @date 2023/3/12
 */

public class JobDetailBean {
    @JSONField(serialize = false)
    private Long id;

    private String area;

    private String exp;

    private String edu;

    private String salary;

    private String jobType;

    private String cmp;

    private String pv;

    private String title;

    private String jd;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "JobDetailBean{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", exp='" + exp + '\'' +
                ", edu='" + edu + '\'' +
                ", salary='" + salary + '\'' +
                ", jobType='" + jobType + '\'' +
                ", cmp='" + cmp + '\'' +
                ", pv='" + pv + '\'' +
                ", title='" + title + '\'' +
                ", jd='" + jd + '\'' +
                ", age=" + age +
                '}';
    }
}
