package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.Data;

@Data
public class MemberResponseDTO {
    @Data
    public static class MemberJoinDTO{
        private Long id;
        private String memberName;
        private String memberEmail;
        private String memberPassword;

        public MemberJoinDTO(Member member){
            this.id = member.getId();
            this.memberName = member.getMemberName();
            this.memberEmail = member.getMemberEmail();
            this.memberPassword = member.getMemberPassword();
        }
    }
}
