package hello.movie.service;


import hello.movie.dto.FollowResponseDto;
import hello.movie.model.Follow;
import hello.movie.model.Member;
import hello.movie.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowerService {

    private final FollowerRepository followerRepository;
    private final MemberService memberService;

    @Transactional
    public void follow(Long followerId, Long followingId){
        Optional<Member> follower = memberService.findById(followerId);
        Optional<Member> following = memberService.findById(followingId);

        Follow follow = Follow.createFollow(follower.get(), following.get());
        followerRepository.save(follow);
    }

    @Transactional
    public void unfollow(Long followerId, Long followingId){
        followerRepository.deleteByFollowerIdAndFolloweeId(followerId, followingId);
    }

    public Boolean isFollowing(Long followerId, Long followingId){
        return followerRepository.existsByFollowerIdAndFolloweeId(followerId, followingId);
    }

    public List<FollowResponseDto> getFollowersList(Long memberId) {
        return followerRepository.findAllFolloweeByFollower(memberId);
    }

    public Follow getFollower(Long followerId, Long followingId) {
        return followerRepository.findByFollowerIdAndFolloweeId(followerId, followingId);
    }
}
