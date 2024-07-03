package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class MemberRequestDTO {


    @Data
    public static class MemberJoinDTO{

        private String memberName;
        private String memberEmail;
        private String memberPassword;

        public Member toEntity(){
            return new Member(this.memberName, this.memberEmail, this.memberPassword);
        }
    }

}
