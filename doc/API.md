# API 명세서

## 오류

예외가 발생했을 때, 본문에 해당 문제를 기술한 JSON 객체가 담겨있습니다.

| Path           | Type     | Description |
|----------------|----------|-------------|
| `errorCode`    | `String` | 에러 코드       |
| `errorMessage` | `String` | 에러 메세지      |

예를 들어, 찾을 수 없는 채용공고 번호일 경우 다음과 같은 응답을 받게 됩니다.

``` http request
HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 04 Oct 2023 10:05:44 GMT
Connection: close

{
  "errorCode": "INVALID_RECRUITMENT_ID",
  "errorMessage": "찾을 수 없는 채용공고 번호입니다."
}
```

## 채용공고

> 채용공고 리소스는 공고 등록, 수정 삭제, 목록 조회, 검색, 상세 페이지 조회를 할 때 사용됩니다.

### 등록

`POST` 요청을 사용해서 채용 공고를 등록할 수 있습니다.

#### Request fields

| Path        | Type     | Description |
|-------------|----------|-------------|
| `companyId` | `Long`   | 회사 ID       |
| `position`  | `String` | 채용 포지션      |
| `reward`    | `Long`   | 채용 보상금      |
| `content`   | `String` | 채용 내용       |
| `skill`     | `String` | 사용 기술       |

#### Example request

``` http request
POST http://localhost:8080/api/v1/recruit
Content-Type: application/json

{
  "companyId": 1,
  "position": "프론트엔드 주니어 개발자",
  "reward": 1000000,
  "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "skill": "Python"
}
```

#### Response fields

| Path        | Type     | Description |
|-------------|----------|-------------|
| `companyId` | `Long`   | 회사 ID       |
| `position`  | `String` | 채용 포지션      |
| `reward`    | `Long`   | 채용 보상금      |
| `content`   | `String` | 채용 내용       |
| `skill`     | `String` | 사용 기술       |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 04 Oct 2023 10:09:54 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "companyId": 1,
  "position": "프론트엔드 주니어 개발자",
  "reward": 1000000,
  "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "skill": "Python"
}
```

### 수정

`PUT` 요청을 사용해서 채용 공고를 수정할 수 있습니다.

#### Request fields

| Path       | Type     | Description |
|------------|----------|-------------|
| `position` | `String` | 채용 포지션      |
| `reward`   | `Long`   | 채용 보상금      |
| `content`  | `String` | 채용 내용       |
| `skill`    | `String` | 사용 기술       |

#### Example request

``` http request
PUT http://localhost:8080/api/v1/recruit/1
Content-Type: application/json

{
  "position": "백엔드 주니어 개발자",
  "reward": 1000000,
  "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "skill": "Django"
}
```

#### Response fields

| Path       | Type     | Description |
|------------|----------|-------------|
| `position` | `String` | 채용 포지션      |
| `reward`   | `Long`   | 채용 보상금      |
| `content`  | `String` | 채용 내용       |
| `skill`    | `String` | 사용 기술       |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 04 Oct 2023 10:12:17 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "position": "백엔드 주니어 개발자",
  "reward": 1000000,
  "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "skill": "Django"
}
```

### 삭제

`DELETE` 요청을 사용해서 채용 공고를 삭제할 수 있습니다.

#### Path parameters

> /api/v1/recruit/{id}

| Path | Type   | Description |
|------|--------|-------------|
| `id` | `Long` | 채용공고 ID     |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 04 Oct 2023 10:14:39 GMT
Keep-Alive: timeout=60
Connection: keep-alive

"OK"
```

### 목록

`GET` 요청을 사용해서 채용 공고 목록을 확인할 수 있습니다.

> /api/v1/recruit/all

#### Response fields

| Path          | Type     | Description |
|---------------|----------|-------------|
| `id`          | `Long`   | 채용 공고 ID    |
| `companyName` | `String` | 회사명         |
| `nation`      | `String` | 국가          |
| `location`    | `String` | 지역          |
| `position`    | `String` | 채용 포지션      |
| `reward`      | `Long`   | 채용 보상금      |
| `skill`       | `String` | 사용 기술       |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 04 Oct 2023 10:15:41 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "companyName": "원티드랩",
    "nation": "한국",
    "location": "서울",
    "position": "프론트엔드 주니어 개발자",
    "reward": 1000000,
    "skill": "Python"
  },
  {
    "id": 2,
    "companyName": "원티드랩",
    "nation": "한국",
    "location": "서울",
    "position": "백엔드 주니어 개발자",
    "reward": 1000000,
    "skill": "Python"
  }
]
```

### 검색

`GET` 요청을 사용해서 채용 공고를 검색할 수 있습니다.

#### Request parameters

> /api/v1/recruit?search={내용}

| Path | Type     | Description |
|------|----------|-------------|
| `내용` | `String` | 검색할 내용      |

#### Response fields

| Path          | Type     | Description |
|---------------|----------|-------------|
| `id`          | `Long`   | 채용 공고 ID    |
| `companyName` | `String` | 회사명         |
| `nation`      | `String` | 국가          |
| `location`    | `String` | 지역          |
| `position`    | `String` | 채용 포지션      |
| `reward`      | `Long`   | 채용 보상금      |
| `skill`       | `String` | 사용 기술       |

#### Example response

``` http request
[
  {
    "id": 1,
    "companyName": "원티드랩",
    "nation": "한국",
    "location": "서울",
    "position": "프론트엔드 주니어 개발자",
    "reward": 1000000,
    "skill": "Python"
  },
  {
    "id": 2,
    "companyName": "원티드랩",
    "nation": "한국",
    "location": "서울",
    "position": "백엔드 주니어 개발자",
    "reward": 1000000,
    "skill": "Python"
  }
]
```

### 세부 페이지

`GET` 요청을 사용해서 채용 공고의 세부 페이지를 확인할 수 있습니다.

#### Path parameters

> /api/v1/recruit/{id}

| Path | Type   | Description |
|------|--------|-------------|
| `id` | `Long` | 채용 공고 ID    |

#### Response fields

| Path                | Type     | Description        |
|---------------------|----------|--------------------|
| `id`                | `Long`   | 채용 공고 ID           |
| `companyName`       | `String` | 회사명                |
| `nation`            | `String` | 국가                 |
| `location`          | `String` | 지역                 |
| `position`          | `String` | 채용 포지션             |
| `reward`            | `Long`   | 채용 보상금             |
| `skill`             | `String` | 사용 기술              |
| `content`           | `String` | 채용 내용              |
| `recruitmentIdList` | `Long`   | 회사가 올린 다른 채용 공고 ID |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 04 Oct 2023 10:22:38 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "companyName": "원티드랩",
  "nation": "한국",
  "location": "서울",
  "position": "백엔드 주니어 개발자",
  "reward": 1000000,
  "skill": "Python",
  "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "recruitmentIdList": [
    2,
    3
  ]
}
```

## 채용공고 지원

> 채용공고 지원 리소스는 채용공고에 지원 할 때 사용됩니다.

### 지원

`POST` 요청을 사용해서 채용 공고에 지원할 수 있습니다.

#### Request fields

| Path            | Type   | Description |
|-----------------|--------|-------------|
| `recruitmentId` | `Long` | 채용 공고 ID    |
| `memberId`      | `Long` | 사용자 ID      |

#### Example request

``` http request
POST http://localhost:8080/api/v1/history
Content-Type: application/json

{
  "recruitmentId": 1,
  "memberId": 1
}
```

#### Response fields

| Path            | Type   | Description |
|-----------------|--------|-------------|
| `recruitmentId` | `Long` | 채용 공고 ID    |
| `memberId`      | `Long` | 사용자 ID      |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 04 Oct 2023 10:26:18 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "recruitmentId": 1,
  "memberId": 1
}
```