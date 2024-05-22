### Bucket4j 로 트래픽 제한하기
<br>

```aidl
GET http://localhost:8081/api/data
```

application.yml 설정 참고
```aidl
...
## all, request
token:
  policy: request
...
```
all 은 토큰을 전체가 공유, <br>
request는 api 마다 개별로 토큰을 관리한다.