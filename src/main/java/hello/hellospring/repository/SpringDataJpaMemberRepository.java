package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.Optional;

public class SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository
{
    @Override
    Optional<Member> findByName(String name);

}
