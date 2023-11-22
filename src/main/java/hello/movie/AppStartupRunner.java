package hello.movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import hello.movie.dto.MemberDto.CreateMemberDto;
import hello.movie.service.MemberService;
import hello.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AppStartupRunner implements CommandLineRunner {

    private final MemberService memberService;
    private final MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        createTestMember();
    }

    /**
     * 기본 계정 생성
     */
    private void createTestMember() throws JsonProcessingException {
        // 계정 하나 생성
        CreateMemberDto createMemberDto1 = new CreateMemberDto();
        createMemberDto1.setUserId("test");
        createMemberDto1.setPassword("0000");

        // 계정 하나 생성
        CreateMemberDto createMemberDto2 = new CreateMemberDto();
        createMemberDto2.setUserId("test2");
        createMemberDto2.setPassword("0000");

        // 영화 데이터
        movieService.getMovieById(496243L);
        movieService.getMovieById(372058L);

        memberService.join(createMemberDto1);
        memberService.join(createMemberDto2);
    }
}
