package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest
{
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach()
    {
        repository.clearStore();
    }

    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        // 방법 1. 출력
        // System.out.println("result = " + (result == member));
        //--==>> result = true

        // 방법 2. Assertions.assertEquals() 사용
        // Assertions.assertEquals(member, result);
        //--==>> 녹색 불
        // Assertions.assertEquals(member, null);
        //--==>> x.. Tests failed

        // 방법 3. Assertions.assertThat() 사용
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


    // findAll test 까지 작성하고 전체 테스트를 돌리면 fail 이 뜬다.
    // 그 이유는..테스트 메소드 실행의 순서가 보장되지 않기 때문이다. (save 여러번 된다.)

    // MemoryMemberRepositoryTest 에서 메모리 지워주는 코드를 넣어서 테스트하면 된다.
    // @afterEach : 메소드가 끝날 때마다 호출되는 메소드
    // afterEach() → 테스트가 끝날 때마다 storage 비워줌
}
