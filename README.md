# ![DoCo](https://team-cobo.site/public/image/favicon/favicon-32x32.png) DoCo 지역별 교육 격차 해소를 위한 교육 재능기부 플랫폼

- [목적] DoCo팀이 GLOW HACKATHON에서 <지역 사회의 지속 가능한 발전을 위한 지역 사회 문제 해결을 위한 아이디어>를 주제로 '교육 재능기부 플랫폼'을 개발

- [개발 기간] 24.06.1 ~ 24.06.2

- [서비스 링크](https://glow-hackaton-2024.vercel.app/)

- [프론트엔드 깃허브](https://github.com/JongHyeok-Park/glow-hackaton-frontend)

- [백엔드 깃허브](https://github.com/sieunie/GLOW-Inspiring-Back)

## 목차

- [1. 구성원 및 업무 분담](#구성원-및-업무-분담)

- [2. 페이지 설계 및 디자인](#페이지-설계-및-디자인)

- [3. DB 모델링](#DB-모델링)

- [4. 핵심 기능](#핵심-기능)

- [5. 사용한 기술](#사용한-기술)

- [6. 느낀 점](#느낀-점)

## 구성원 및 업무 분담

박종혁

- 팀장
- 프론트엔드
- 디자인 설계

이시은

- 백엔드
- 배포 환경 구축
- DB 설계

엄윤정

- 프론트엔드

한승규

- 백엔드

## 기능 명세서

<img src = "https://cobo-blog.s3.ap-northeast-2.amazonaws.com/GLOW/a68d925b-e673-456b-88ce-75d3501956bc.png" alt="기능 명세" width="900">

## 페이지 설계 및 디자인

- [페이지 설계 및 디자인 - Figma](https://www.figma.com/design/qUX9HS3Mc37WvOP5BkjLmH/2024-GLOW-HACKATON?node-id=0-1&t=oA2u2brqbUJC7L2U-1)

## DB 모델링

<img src = "https://cobo-blog.s3.ap-northeast-2.amazonaws.com/GLOW/1c1a75c5-ad73-4593-9963-17c4ef210d95.png" alt="db-model" width="900">

## 핵심 기능

### 카카오 로그인

> 카카오 로그인을 통해 사용자의 정보를 얻고 회원이라면 토큰들을 쿠키로 저장

```
- kakao Developer에서 제공하는 로그인을 통해 코드를 받아 옴

- 프론트에서 해당 코드를 파라미터에 넣어 다시 서버의 API를 호출함

- 해당 사용자의 id가 있다면, 로그인에 성공하고 없다면 로그인에 실패

- 사용자의 AccessToken과 RefreshToken을 JWT를 이용하여 생성한 후 발급

- 프론트에서는 받은 AccessToken과 RefreshToken을 쿠키를 이용하여 지정된 시간만큼 저장함

- 인증이 필요한 API에는 AccessToken을 가져와 Authorization에 넣고, AccessToken이 만료되었다면 RefreshToken을 이용하여 재발급

- Authorization 헤더를 확인한 후 유효한 토큰이면, 해당 토큰에서 사용자의 정보를 추출하고 유효하지 않다면 403 에러를 반환
```

### 실시간 채팅

> 웹소켓을 이용해 실시간 채팅 기능을 구현

```
- REST API를 반복적으로 호출하면 서버에 부하가 발생

- WEB SOCKET을 사용하여 유저간에 실시간 통신이 가능하게 구현
```

### 비동기
> 비동기 코드를 사용하여 RESPONSE TIME을 최소화
 ```
- 동기적으로 데이터베이스에 접근하게 된다면 RESPONSE TIME이 커지는 문제가 발생

- COMPLETABLEFUTURE를 사용한 비동기 코드로 사용자의 응답시간 최소화
 ```

### MSA
> 마이크로 서비스 아키텍쳐를 사용
 ```
 - 기능별로 서버를 분리하여 SCALE UP, SCALE OUT이 용이하게 설계
 
 - 개발 과정에서도 개발하는 서버를 분리하여 CONFLICT 방지
 ```

### 강연/과외 CRUD

> 강연이나 과외를 등록하고, 필요한 사용자와 매칭될 수 있도록 함

```
- 강연/과외의 기본적인 CRUD를 구현함

- 이미지를 함께 업로드해야 할 경우, 이미지 업로드 API를 통해 S3에 이미지를 업로드한 후 해당 이미지에 대한 고유한 ID를 받아서 사용

- 강연/과외가 필요한 사용자가 요청을 보내면, 멘토가 요청을 승인하고 멘토링이 성사됨
```

### S3

> 이미지를 AWS S3에 업로드

```
- 사용자가 업로드 한 이미지를 S3에 업로드

- 간단하게 URL만으로도 사진을 공유할 수 있도록 구현
```

## 사용한 기술

|   범위    |                                                                                                                                                                                                                                                                                                                           스택                                                                                                                                                                                                                                                                                                                           |
| :-------: | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| Front End | <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1566995514/noticon/jufppyr8htislboas4ve.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1678672480/noticon/qblxu9uo0uuitucuzhjy.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1567008394/noticon/ohybolu4ensol1gzqas1.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1566557331/noticon/d5hqar2idkoefh6fjtpu.png" alt="" height="50"/> <img src="" alt="" height="50"/>|
| Back End  |                                                                                    <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1567008187/noticon/m4oad4rbf65fjszx0did.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1567064876/noticon/sb5llmvfubuceldbkmx8.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1603423163/noticon/az0cvs28lm7gxoowlsva.png" alt="" height="50"/>                                                                                    |
|  DevOps   |                                                                                                                                                                                                                                          <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1566777755/noticon/yfmwxv8nhnr5aqaxhxpg.png" alt="" height="50"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                                                                           |

