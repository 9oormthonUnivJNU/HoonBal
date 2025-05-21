package hello.inflearn_spring.service;

import hello.inflearn_spring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


// COnfiguration과 Bean을 통해 의존성을 주입하여 생성자 연결
// 해당 코드는 Service, Repository 에너테이션은 지워야함 Autowired는 냅둠
@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}