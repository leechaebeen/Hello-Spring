package hello.hellospring.controller;

public class MemberForm
{
    // form 의 name 이 "name" 이라 스프링이 보고 여기 넣어준다.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
