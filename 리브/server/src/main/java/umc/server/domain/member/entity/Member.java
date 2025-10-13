package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.MemberStatus;
import umc.server.domain.member.enums.TermsAgreed;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.global.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 3, nullable = false)
    private String username;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "address_zipcode", nullable = false)
    private String addressZipcode;

    @Column(name = "address_details", nullable = false)
    private String addressDetails;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @Column(name = "terms_agreed", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermsAgreed termsAgreed;

    @Column(name = "total_points", nullable = false)
    @Builder.Default
    private int totalPoints = 0;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "member_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
