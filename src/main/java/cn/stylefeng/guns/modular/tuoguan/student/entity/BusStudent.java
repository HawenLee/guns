package cn.stylefeng.guns.modular.tuoguan.student.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class BusStudent implements Serializable {
    private Long id;

    /**
     * 姓名
     *
     * @mbg.generated
     */
    private String stuName;

    /**
     * 学号
     *
     * @mbg.generated
     */
    private String stuNumber;

    /**
     * 性别
     *
     * @mbg.generated
     */
    private String gender;

    /**
     * 年龄
     *
     * @mbg.generated
     */
    private Integer age;

    /**
     * 学校
     *
     * @mbg.generated
     */
    private String school;

    /**
     * 班级
     *
     * @mbg.generated
     */
    private String clazz;

    /**
     * 年级
     *
     * @mbg.generated
     */
    private String grade;

    /**
     * 0-在，1-离开
     *
     * @mbg.generated
     */
    private Byte leave;

    private LocalDateTime createTime;

    private LocalDateTime updTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClass(String clazz) {
        this.clazz = clazz;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Byte getLeave() {
        return leave;
    }

    public void setLeave(Byte leave) {
        this.leave = leave;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdTime() {
        return updTime;
    }

    public void setUpdTime(LocalDateTime updTime) {
        this.updTime = updTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stuName=").append(stuName);
        sb.append(", stuNumber=").append(stuNumber);
        sb.append(", gender=").append(gender);
        sb.append(", age=").append(age);
        sb.append(", school=").append(school);
        sb.append(", class=").append(clazz);
        sb.append(", grade=").append(grade);
        sb.append(", leave=").append(leave);
        sb.append(", createTime=").append(createTime);
        sb.append(", updTime=").append(updTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}