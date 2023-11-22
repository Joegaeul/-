package hello.movie.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue
    @Column(name = "FOLLOW_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOWER")
    private Member follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOWEE")
    private Member followee;

    @Temporal(TemporalType.DATE)
    private Date followDate;

    public static Follow createFollow(Member follower, Member followee){
        Follow follow = new Follow();
        follow.follower = follower;
        follow.followee = followee;
        follow.followDate = new Date();
        return follow;
    }
}
