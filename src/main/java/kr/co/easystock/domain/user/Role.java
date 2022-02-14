package kr.co.easystock.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role
{
    MEMBER("MEMBER", "사용자"),
    ADMIN("ADMIN", "관리자");

    private final String key;
    private final String title;
}
