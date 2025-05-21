package hello.inflearn_spring.controller;

import hello.inflearn_spring.domain.Member;
import hello.inflearn_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    /*
     생성자 생성 -> 다른 곳에서도 중복으로 사용 되므로 하나의 객체로 만든다고 생각하면 된다.
    Autowired 는 스프링 컨테이너에서 memberService를 연결시켜줌
    AutoWired를 위해 사용원하는 해당 class위에 Secvice, Repository 에너테이션 넣기
    Autowired를 위해 springconfig 파일을 만들어서 Bean 을 사용해 관리
    Autowired로 의존성 주입
    */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // members/createMemberForm 으로 이동하여 템플릿 엔진이 렌더링 할 수 있게함
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String List(Model model) {
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
