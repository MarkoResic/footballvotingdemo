# Football Voting Demo

### POST Register

Example Request

```
POST http://localhost:8080/api/auth/register
```

Body

```
{
    "email": "email@mail.com",
    "password": "password",
    "nickname": "nick"
}
```

Example Response

```
200 OK
```

Body

```
{
    "userId": "655248d44fb8b616e9a10e97",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbDRAbWFpbC5jb20iLCJpYXQiOjE2OTk4OTE0MTIsImV4cCI6MTY5OTg5NTAxMn0._wBt4VVVPjh466P5pIJJfNk3Dp7jtjW3sZlPF15hfNY",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJ0b2tlbklkIjoiNjU1MjQ4ZDQ0ZmI4YjYxNmU5YTEwZTk4Iiwic3ViIjoiZW1haWw0QG1haWwuY29tIiwiaWF0IjoxNjk5ODkxNDEyLCJleHAiOjE3NDMwOTE0MTJ9.v1h6Pj9uzTrO0sftMT77JY6yNRnt1cegCAMuXfRkXMc"
}
```

### POST Authenticate

Example Request

```
POST http://localhost:8080/api/auth/authenticate
```

Body

```
{
    "email": "email@mail.com",
    "password": "password",
    "nickname": "nick"
}
```

Example Response

```
200 OK
```

Body

```
{
    "userId": "655248d44fb8b616e9a10e97",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbDRAbWFpbC5jb20iLCJpYXQiOjE2OTk4OTE0MTIsImV4cCI6MTY5OTg5NTAxMn0._wBt4VVVPjh466P5pIJJfNk3Dp7jtjW3sZlPF15hfNY",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJ0b2tlbklkIjoiNjU1MjQ4ZDQ0ZmI4YjYxNmU5YTEwZTk4Iiwic3ViIjoiZW1haWw0QG1haWwuY29tIiwiaWF0IjoxNjk5ODkxNDEyLCJleHAiOjE3NDMwOTE0MTJ9.v1h6Pj9uzTrO0sftMT77JY6yNRnt1cegCAMuXfRkXMc"
}
```

### POST Log out

Example Request

```
POST http://localhost:8080/api/auth/logout
```

Body

```
eyJhbGciOiJIUzI1NiJ9.eyJ0b2tlbklkIjoiNjU1MjQ5OGY0ZmI4YjYxNmU5YTEwZTk5Iiwic3ViIjoiZW1haWxAbWFpbC5jb20iLCJpYXQiOjE2OTk4OTE1OTksImV4cCI6MTc0MzA5MTU5OX0.1q080bZof7w03M2Xs44II8H4a-KRzWQGheH4yEeSIWM
```

Example Response

```
200 OK
```

### POST Refresh access token

Example Request

```
POST http://localhost:8080/api/auth/refresh-token
```

Body

```
eyJhbGciOiJIUzI1NiJ9.eyJ0b2tlbklkIjoiNjU1MjQ5OGY0ZmI4YjYxNmU5YTEwZTk5Iiwic3ViIjoiZW1haWxAbWFpbC5jb20iLCJpYXQiOjE2OTk4OTE1OTksImV4cCI6MTc0MzA5MTU5OX0.1q080bZof7w03M2Xs44II8H4a-KRzWQGheH4yEeSIWM
```

Example Response

```
200 OK
```

Body

```
{
    "userId": "655248d44fb8b616e9a10e97",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbDRAbWFpbC5jb20iLCJpYXQiOjE2OTk4OTE0MTIsImV4cCI6MTY5OTg5NTAxMn0._wBt4VVVPjh466P5pIJJfNk3Dp7jtjW3sZlPF15hfNY",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJ0b2tlbklkIjoiNjU1MjQ4ZDQ0ZmI4YjYxNmU5YTEwZTk4Iiwic3ViIjoiZW1haWw0QG1haWwuY29tIiwiaWF0IjoxNjk5ODkxNDEyLCJleHAiOjE3NDMwOTE0MTJ9.v1h6Pj9uzTrO0sftMT77JY6yNRnt1cegCAMuXfRkXMc"
}
```

### GET current user

AUTHORIZATION: Bearer Token

Example Request

```
GET http://localhost:8080/api/users
```

Example Response

```
200 OK
```

Body

```
{
    "email": "email@mail.com",
    "nickname": "nick",
    "roleName": "ADMIN"
}
```

### GET player details

AUTHORIZATION: Bearer Token

Example Request

```
GET http://localhost:8080/api/players/6550cc87bf66a037871a1881
```

Example Response

```
200 OK
```

Body

```
{
    "name": "Patrick Mahomes",
    "dob": "1995-09-17T00:00:00.000+00:00",
    "imageUrl": "",
    "teamName": "Kansas City Chiefs",
    "position": "QB",
    "jerseyNumber": "15",
    "votes": 1
}
```

### GET players by search term

AUTHORIZATION: Bearer Token

Example Request

```
GET http://localhost:8080/api/players/search/Patrick
```

Example Response

```
200 OK
```

Body

```
[
    {
        "idPlayer": "6550cc87bf66a037871a1881",
        "name": "Patrick Mahomes",
        "imageUrl": "",
        "teamName": "Kansas City Chiefs"
    }
]
```

### POST Create player

AUTHORIZATION: Bearer Token

Example Request

```
POST http://localhost:8080/api/players/management
```

Body

```
{
    "name": "Patrick Mahomes",
    "dob": "1995-09-17",
    "imageUrl": "",
    "teamName": "Kansas City Chiefs",
    "position": "QB",
    "jerseyNumber": "15"
}
```

Example Response

```
201 Created
```

### POST Create vote

AUTHORIZATION: Bearer Token

Example Request

```
POST http://localhost:8080/api/votes/6550cc87bf66a037871a1881
```

Example Response

```
201 Created
```

### GET top 10 most voted players

AUTHORIZATION: Bearer Token

Example Request

```
GET http://localhost:8080/api/players/top/all-time
```

Example Response

```
200 OK
```

Body

```
[
    {
        "name": "Patrick Mahomes",
        "votes": 2
    }
]
```

### GET top 10 most voted players today

AUTHORIZATION: Bearer Token

Example Request

```
GET http://localhost:8080/api/players/top/today
```

Example Response

```
200 OK
```

Body

```
[
    {
        "name": "Patrick Mahomes",
        "votes": 1
    }
]
```
