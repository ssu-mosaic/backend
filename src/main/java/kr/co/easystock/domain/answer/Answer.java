package kr.co.easystock.domain.answer;

import kr.co.easystock.domain.BaseTimeEntity;

import javax.persistence.Embeddable;

@Embeddable
public class Answer extends BaseTimeEntity
{
    private String user;
    private String content;
}
