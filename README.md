# 내일배움캠프 Spring 개인 프로젝트 #4 Spring 플러스

프로젝트 기간 : 2025.12.18 ~ 30

개발 환경 : Java OpenJDK 17, Spring Boot 3.3.3

진행 내용 정리 : [Notion](https://road-cartoon-1a1.notion.site/Spring-2cc1d6e7a68c804d8118ff093e37e435?source=copy_link)

## 요구사항

**Lv 1. 코드 개선(1)**

- 코드 개선 퀴즈 - `@Transactional`의 이해
- 코드 추가 퀴즈 - JWT의 이해
- 코드 개선 퀴즈 - JPA의 이해
- 테스트 코드 퀴즈 - 컨트롤러 테스트의 이해
- 코드 개선 퀴즈 - AOP의 이해

**Lv 2. 코드 개선(2)**

- JPA Cascade
- N+1
- QueryDSL
- Spring Security

**Lv 3-1. QueryDSL을 사용하여 검색 기능 만들기**

- 검색 기능의 성능 및 사용성을 높이기 위해 QueryDSL을 활용한 쿼리 최적화
- 검색 조건 : 일정 제목, 생성일 범위, 담당자 닉네임

**Lv 3-2. Transaction 심화**

- `@Transactional`의 옵션 중 하나를 활용하여 매니저 등록과 로그 기록이 각각 독립적으로 처리될 수 있도록 진행

**Lv 3-3. AWS 활용**

- EC2, RDS, S3를 사용해서 프로젝트 관리 및 배포

  <details>
  <summary>AWS 서비스 설정</summary>
  <h3>1. RDS</h3>
  <img width=70% alt="image" src="https://github.com/user-attachments/assets/0eb9cfba-692c-4b2a-b24f-ec19b0a1c534" />
    
  <h3>2. EC2</h3>
  
  - EC2 설정
  
    <img width=70% alt="스크린샷 2025-12-26 오후 1 51 47" src="https://github.com/user-attachments/assets/f27968c4-b647-4601-a628-bd50690a8976" />
  
  - 보안그룹 설정(인바운드 규칙 : 80(Http), 8080(Spring), 22(ssh) 추가)
  
    <img width=70% alt="image" src="https://github.com/user-attachments/assets/6321198c-85d3-4a62-ba9e-6e7c4f905369" />
  
  - Health Check API 확인
  
    <img width=70% alt="image" src="https://github.com/user-attachments/assets/a6552de0-ed2c-4877-a534-35a9d886db95" />
  
  <h3>3. S3</h3>
  
  - S3 생성
    
    <img width=70% alt="image" src="https://github.com/user-attachments/assets/df84b24c-6cc9-48d7-ab9d-6910279e70d5" />
  
  - S3 버킷 조회 권한 설정
    
    <img width=70% alt="스크린샷 2025-12-26 오후 1 58 52" src="https://github.com/user-attachments/assets/0d77d1c0-9717-4bcf-87eb-a2e2f7c52545" />
  
  - IAM 유저 생성 : S3 버킷 업로드 권한 부여
    
    <img width=70% alt="스크린샷 2025-12-26 오후 2 00 22" src="https://github.com/user-attachments/assets/db035486-6b62-421c-90ad-16a71727b516" />
  
  - S3 이미지 업로드 테스트
    
    <img width=70% alt="스크린샷 2025-12-26 오후 2 01 54" src="https://github.com/user-attachments/assets/7b348f2c-f640-4bd4-960a-3367924268a0" />
    
    <img width=70% alt="스크린샷 2025-12-26 오후 2 02 44" src="https://github.com/user-attachments/assets/ee1ffd55-210a-4a98-8b8c-d64f7ba5b774" />
  
  </details>

**Lv 3-4. 대용량 데이터 처리**

- 대용량 데이터를 만들어, 유저 검색 속도 줄이기
