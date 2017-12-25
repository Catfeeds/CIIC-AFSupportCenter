package com.ciicsh.gto.demo;

import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TestJackson {

    public static void main(String[] args) {
        String content = "{\n" +
            "  age:11,\n" +
            "  users:[\n" +
            "    {name:'sfasfdsadf',age:19,birthday:'1988-12-23',id:1231231,nickname:'呵呵呵额',headimg:'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaadf',createdTime:'2017-08-08 23:23:23'}\n" +
            "  ]\n" +
            "}";
        Dept dept = JsonKit.parseObject(content, Dept.class);
        System.out.println(dept);

        content = JsonKit.toStr(dept.getUsers());

        List<User> users = JsonKit.parseList(content, User.class);
        System.out.println(users);
    }

    public static class Dept {
        private List<User> users;

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }

    public static class User {
        private int id;
        private String name;
        private int age;
        private Date birthday;
        private String nickname;
        private String headimg;
        private LocalDateTime createdTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public LocalDateTime getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(LocalDateTime createdTime) {
            this.createdTime = createdTime;
        }
    }
}
