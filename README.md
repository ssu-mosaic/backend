# 캡스톤디자인종합프로젝트-easystock 백엔드 

---
## Entity 
- User(유저)
- Customer(거래처)
- Stock(재고)
- Admin(관리자)
- Inquiry(문의)
- Answer(문의에 대한 답변)
- Notice(공지사항)

---
## 어노테이션
@Getter, @Setter : Lombok 기능으로, get과 set 메소드를 생성해주는 어노테이션 - Lombok 플러그인 설치해야 함
@NoArgsConstuctor : Lombok 기능으로, 인자가 없는 생성자를 생성해주는 어노테이션 - Lombok 플러그인 설치해야 함

---
## BaseTimeEntity
생성일자와 마지막 수정일자를 자동으로 할당하기 위한 추상 클래스

---
## 임시 데이터베이스
1. localhost:8080/h2-console에 접속
2. JDBC URL에 jdbc:h2:mem:testdb 입력
3. User Name에 admin 입력
