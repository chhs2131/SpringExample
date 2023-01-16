# WebClient 예제 
- Reactor WebFlux 는 Reactive Streams를 기반으로 하는 4세대 리액티브 라이브러리이다.
- non-blocking 방식(비동기)으로 동작해 시스템 효율이 좋다. (기존 RestTemplate은 Blocking 멀티쓰레드 방식)
- 0개, 1개 또는 N개의 반환을 받을 수 있다.
- 마이크로서비스 아키텍처(MSA) 등에 적합함

<br/>


### 프로젝트 구조 (StoreApi 기준)

```python
📁 main.java.com.example.webclient.storeApi
 └ 📁 controller
    └ 📄 StoreController
    └ 📄 FoodDeliveryAppController -> WebClient를 사용하여 StroeController로 요청을 전달함
 └ 📁 domain
    └ 📄 Food
 └ 📁 util
    └ 📄 WebClientUtil
```

<br/>

## 설명

### block? non-block?
- 통신시 요청에 대한 응답이 올 때까지 대기를 하는지, 다른 일을 하는지 차이이다.
  - `block`은 요청에 대한 응답이 올 때까지 대기한다.
    - 학교에서 시험보는데 선생님이 한명씩 부르고, 답안 제출시까지 지켜보고 있는 형태 😥
  - `non-block`은 요청을 보내두고, 응답이 올 때까지 다른 일을 처리한다.
    - 교실에서 다같이 시험을 보는동안 선생님은 책을 읽고 있고, 시험 다 본 학생은 먼제 제출하고 집에가도 되는 형태 😋

<br/>

### mono? flux?
- 둘 다 값을 어떻게 보낼지 정의하는 `Publisher` 이다.
- 둘의 차이는 기대되는 응답에 개수에 있는데
  - `mono`는 응답이 0개 또는 1개일 때,
  - `flux`는 응답이 n개 일 때 사용한다.

<br/>

### 사용 단계
![img.png](img.png)
- 간단한(코드도 짧음) 구성으로 사용가능함 
  1. WebClient Builder(or creator)로 원하는 옵션 작성
     - 해당 단계에 연결할 주소와 포트번호, HTTP Method, Parameter, Header 등이 포함
  2. 위 옵션을 통해 Publisher 를 생성
     - publisher는 response 받을 값의 형태를 정의
     - 단일값을 받을 것인지(=mono), 여러개의 값을 받을 것인지(=flux)
     - body값만 받을 것인지 (bodyToMono, bodyToFlux) 등
  3. 실제로 통신을 시도하여 값을 받습니다.
     - publisher객체(mono or flux)에 Subscribe 함수를 사용하거나
     - stream에 collect 함수를 사용해서도 값을 받을 수 있음
     - toFutre(), toStream() 등에 방법도 존재
     - publisher객체(mono or flux)에 block 함수를 사용하여 동기식으로 값을 받을 수 있음
       - 다만 이는 WebClient를 사용하는 이유 자체가 없어지므로 권장되지 않음
       - block() 사용을 위해서는 기존 동기식 통신에 대한 라이브러리 의존성이 별도로 필요함

<br/>

## 사용

### 의존성
- WebClient를 사용하기위해 webflux를 추가한다.
- `spring-boot-starter-web`의 경우 WebClient를 Blocking 방식으로 사용하고 싶은 경우 추가한다. (`.block()` 사용시)
```groovy
implementation 'org.springframework.boot:spring-boot-starter-webflux'
implementation 'org.springframework.boot:spring-boot-starter-web'
```


### 예시
- `http://localhost:8080/store/welcome` 에 `GET` 요청을 보내서 해당 내용을 String 형태로 받는다.
```java
Mono<String> mono = WebClient.create()
                             .get()  // or post(), put(), delete() ...
                             .uri("http://localhost:8080/store/welcome")
                             .retrieve()
                             .bodyToMono(String.class);

mono.subscribe(System.out::println);
```
<br/>

## Block vs Non-Block 속도 차이
### 가게에 손님이 오면 어서오십쇼! 😀
- 다음과 같이 `StoreController`에는 손님이 방문했을 때 한사람씩 맞이해주는 `welcome` 이 있다.
- 입장 후 `0.1초` 가 지나면 `어서오십쇼!` 라고 반환한다.
```java
@RequestMapping("/welcome")
public String welcome() throws InterruptedException {
    Thread.sleep(100);  // 입장 후 0.1초후에 인사한다.
    return "어서오십쇼!";
}
```

<br/>

### 배달어플로 단체손님 맞이하기
- 이 가게에는 전용 배달 어플이 있는데, 가게 메인페이지에 접속시 `StoreController`에 `welcome`과 통신하여 내용을 받아온다.
- 즉, 동일하게 메인페이지 입장후 `0.1초`가 지나면 `어서오십쇼!`가 반환된다.
- 이 때, 단체팀으로 100명이 방문한다고 하면 `block` 방식과 `non-block` 방식에 시간차이는 얼마나 날지 비교해본다.
```java
private static void welcomeCustomer(boolean block, int people) {
    int n = 0;
    while (people-- > 0) {
        Mono<String> mono = WebClient.create()
                .get()
                .uri("http://localhost:8080/store/welcome")
                .retrieve()
                .bodyToMono(String.class);

        if (block) {
            System.out.println(mono.block());
        } else {
            mono.subscribe(System.out::println);
        }
    }
}
```

<br/>

### Block - 손님들이 차례대로 인사를 받아야 입장해요 😥
- `block=true`, `people=100`
- block 방식은 WebClient가 동기방식으로 동작한다. (권장되지 않는 방식)
- 즉, 이전요청이 정상적으로 마무리될 때까지 이후 요청들은 처리되지 못한다.
- 이는 곧 손님들이 일렬로 줄을 서서 자기차례가 될 떄까지 기다리는 것과 같다.
- RestTemplate(기존 blocking 방식 라이브러리)에 경우 멀티쓰레드를 사용하여 이러한 문제를 해결하지만, 쓰레드에 물리적인 수에 한계가 있다는 것은 여전히 문제가된다.
  - 여기서는 단일쓰레드로 테스트됨
- 아래는 해당방식으로 10번 테스트해본 결과이다.
```python
1.05 초
1.049 초
1.052 초
1.043 초
1.044 초
1.048 초
1.051 초
1.045 초
1.045 초
1.037 초
1.05 초
```

<br/>

### Non-Block - 모든 손님이 동시에 말을 걸고 인사받기를 기다려요 😍
- `block=false`, `people=100`
- non-block 방식은 WebClient가 비동기방식으로 동작한다. (정상적인 사용)
- 즉, 이전요청이 아직 응답을 받지 못했더라도 동작을 계속처리한다.
- 이는 곧 모든 손님들이 줄을 서지않고 가게에 동시에 입장하는 것과 같다.
- 아래는 해당방식으로 10번 테스트해본 결과이다.
```python
0.014 초
0.006 초
0.005 초
0.005 초
0.004 초
0.004 초
0.005 초
0.004 초
0.004 초
0.004 초
0.003 초
```

<br/>

## '새우'가 들어간 요리가 있나요? (N개 응답받기)

### 메뉴판
- 이 식당에는 아래와 같은 메뉴가 있다.
```java
// Food(메뉴명, 칼로리, 가격)
private final List<Food> foods = List.of(
        new Food("카레", 1000, 7000),
        new Food("라면", 500, 5000),
        new Food("새우튀김", 2000, 12000),
        new Food("새우볶음밥", 1000, 10000)
);
```
- 아래는 메뉴를 요청하는 `StoreController`에 `menu` 이다. `name` parameter를 통해 특정 글자가 포함되어있는 요리를 검색할 수 있다.
```java
@RequestMapping("/menu")
public List<Food> getFoods(String name) {
    // name 단어가 포함된 음식 리스트를 return 한다.
    return foods.stream()
            .filter(food -> food.getName().contains(name))
            .collect(Collectors.toList());
}
```

<br/>

### 배달 어플로 메뉴 검색 🍤
- 배달 어플로 메뉴를 검색한다고 했을 때, 동일하게 WebClient를 통해 `StoreController` 에 `menu`로 통신하여 내용을 받아온다.
- 단, 메뉴 검색의 결과가 여러개일 수 있으므로 `Mono`가 아닌 `Flux`를 사용한다.
```java
@GetMapping("/menu")
public List<Food> getMenu(@RequestParam String name) {
    // flux 만들기 (연결 정보를 담은)
    Flux<Food> flux = WebClientUtil.getBaseUrl("http://localhost:8080/store")
            .get()
            .uri(builder -> builder.path("/menu")
                    .queryParam("name", name)
                    .build())
            .retrieve()
            .bodyToFlux(Food.class);

    // 실제로 menu 받아오기
    List<Food> menu = flux.toStream().collect(Collectors.toList());
    return menu;
}
```


<br/>

## 알려진 문제 🤔
### blocking, which is not supported
- 기본적으로 WebFlux에서 지원하지않는 block() 메소드를 사용하려고해 생기는 문제
- `spring-boot-starter-web` 의존성을 추가하여 해결 가능
```shell
java.lang.IllegalStateException: block()/blockFirst()/blockLast() are blocking, which is not supported in thread reactor-http-nio-2
	at reactor.core.publisher.BlockingSingleSubscriber.blockingGet(BlockingSingleSubscriber.java:83) ~[reactor-core-3.4.26.jar:3.4.26]
	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException: 
Error has been observed at the following site(s):
	*__checkpoint ⇢ HTTP GET "/delivery/welcome" [ExceptionHandlingWebHandler]
```

<br/>

## 참고자료

https://wildeveloperetrain.tistory.com/106

youtube.com/watch?v=KnHVtH-NhUg&feature=youtu.be&ab_channel=송자바코딩

https://www.baeldung.com/spring-webclient-json-list

https://morioh.com/p/2180224f0681

https://spring.io/blog/2016/06/13/notes-on-reactive-programming-part-ii-writing-some-code

https://hyunsoori.tistory.com/3

https://projectreactor.io/

