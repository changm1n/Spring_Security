package com.example.demo.service;

import com.example.demo.dto.MemberRequestDTO;
import com.example.demo.dto.MemberResponseDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public MemberResponseDTO.MemberJoinDTO Join(MemberRequestDTO.MemberJoinDTO memberJoinDTO){
        // 동일한 이메일이 존재하는가? 검증로직
        boolean isUser = memberRepository.existsByMemberName(memberJoinDTO.getMemberName());
        if(isUser){
            return null;
        }
        //memberJoinDTO.setMemberPassword(bCryptPasswordEncoder.encode(memberJoinDTO.getMemberPassword()));
        Member member = memberJoinDTO.toEntity();
        member.setMemberPassword(bCryptPasswordEncoder.encode(memberJoinDTO.getMemberPassword()));
        memberRepository.save(member);
        return new MemberResponseDTO.MemberJoinDTO(member);
    }
}
