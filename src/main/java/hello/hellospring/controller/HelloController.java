package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController
{
    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model)
    {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // @ResponseBody 사용 원리
    // · HTTP 의 BODY 에 문자 내용을 직접 반환
    // · 'viewResolver' 대신에 'HttpMessageConverter' 가 동작
    // · 기본 문자 처리 : StringHttpMessageConverter
    // · 기본 객체 처리 : MappingJackson2HttpMessageConverter
    // · byte 처리 등등 기타 여러 HttpMessageConverter 가 기본으로 등록되어있음

    // ※ 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서
    //   'HttpMessageConverter' 가 선택된다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name)
    {
        return "hello " + name;
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello HelloApi(@RequestParam("name") String name)
    {
        Hello hello = new Hello();
        hello.setName(name);

        // 객체오면 json 형식으로 반환한다.(기본)
        return hello;

    }

    static class  Hello
    {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
