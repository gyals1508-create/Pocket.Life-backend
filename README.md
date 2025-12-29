# 🍃 Pocket Life Backend (API Server)

### 📖 프로젝트 소개

Pocket Life의 데이터 처리를 담당하는 **Spring Boot REST API 서버**입니다.
식단 기록과 장보기 목록 데이터를 저장하고, 프론트엔드와 JSON 형식으로 통신합니다.

---

### 🛠 기술 스택 (Tech Stack)

- **Language**: Java 17
- **Framework**: Spring Boot 3.x
- **Build Tool**: Gradle
- **Database**: MySQL, Spring Data JPA
- **Library**: Lombok (Getter/Setter 자동화)
- **Tool**: IntelliJ / VS Code, MySQL Workbench

---

### 🔌 API 명세 (Endpoints)

프론트엔드에서 요청하는 주요 주소입니다. (Base URL: `http://localhost:8080`)

| 기능               | HTTP Method | Endpoint                        | 설명                          |
| :----------------- | :---------: | :------------------------------ | :---------------------------- |
| **식단 조회**      |    `GET`    | `/api/meals?date=yyyy-mm-dd`    | 해당 날짜의 식단 목록 조회    |
| **식단 추가**      |   `POST`    | `/api/meals`                    | 새로운 식단 기록 저장         |
| **식단 삭제**      |  `DELETE`   | `/api/meals/{id}`               | 특정 식단 삭제                |
| **장보기 조회**    |    `GET`    | `/api/shopping?date=yyyy-mm-dd` | 해당 날짜의 장보기 목록 조회  |
| **장보기 추가**    |   `POST`    | `/api/shopping`                 | 장볼 물건 추가                |
| **구매 상태 변경** |    `PUT`    | `/api/shopping/{id}`            | 구매 완료(체크) 상태 업데이트 |
| **장보기 삭제**    |  `DELETE`   | `/api/shopping/{id}`            | 장보기 항목 삭제              |

---

### 💾 데이터베이스 설정 (Database Setup)

프로젝트 실행 전, MySQL Workbench에서 아래 SQL을 실행하여 테이블을 생성해주세요.

**1. 스키마 생성**

```sql
CREATE DATABASE life_manager;
USE life_manager;
```
