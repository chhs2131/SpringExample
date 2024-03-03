# Concurrency Example
keyword: 암시적 락, 명시적 락, 낙관적 락, 비관적 락, Synchronized, volatile, ReentrantLock, StampedLock, Atomic, CAS Algorithm, Future, ExecutorService, completableFuture, ForkJoinPool, ConcurrentHashMap, Semaphore

<br/>

java에서 동시성을 제어하기 위한 3개의 예시를 다룹니다.

<br/>
<br/>

## 간단한 Counter 예제
increase 호출시 count 값을 1씩 증가시키는 Counter 클래스를 동시에 접근하는 방법에 대해 다룹니다.

Counter Class
```java
public class Counter {
    private static final int DELAY_TIME = 50;
    private int count;

    public int increase() {
        delay(DELAY_TIME);
        return count++;
    }
}
```

실패하는 경우
```java
@Test
void originalTest_Fail() throws Exception {
    int loop = 100;
    executorService = Executors.newFixedThreadPool(loop);

    for (int i = 0; i < loop; i++) {
        executorService.execute(() -> counter.increase());
    }
    executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

    Assertions.assertThat(counter.getCount()).isNotEqualTo(loop);
}
```

성공하는 경우 (Synchronized 사용)
```java
@Test
void synchronizedTest_Success() throws Exception {
    int loop = 100;
    executorService = Executors.newFixedThreadPool(loop);

    for (int i = 0; i < loop; i++) {
        executorService.execute(() -> synchronizedIncrease());
    }
    executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

    Assertions.assertThat(counter.getCount()).isEqualTo(loop);
}

// or use this method
synchronized void synchronizedIncrease() {
    counter.increase();
}
```

## 선착순 이벤트 예제
선착순 n명에게 엄청난 선물을 주는 예제입니다. 동시성 이슈로 중복 당첨자가 발생하면 손해가 막심하겠네요. 
그리고 분명 당첨되었다고 나왔는데, 전산오류여서 취소됬다고 하면 기분이 정말 안좋겠습니다. 

Prizes Class - 선착순으로 당첨된 사람들을 기록합니다.
```java
public class Prizes {
    private final List<String> winners;
    private int maxWinners;

    public Prizes(List<String> winners) {
        this.winners = winners;
    }

    public void init(int maxWinners) {
        Assert.isTrue(maxWinners >= 0, "최대 당첨 인원은 음수가 될 수 없습니다.");
        this.maxWinners = maxWinners;
    }
}
```

실패하는 경우 - 반드시 서버(Prizes Class)에 기록된 당첨자와, 당첨됬다고 통지받은 클라이언트 리스트가 둘다 정상인지 확인해봐야합니다. Prizes Class는 동시성이슈로 덮어쓰기 되었을 수 있습니다.
```java
@Test
void 천명이동시에도전_그중10명이당첨_실패() throws Exception {
    final int maxWinners = 10;
    final int threadCount = 1000;
    executorService = Executors.newFixedThreadPool(threadCount);
    prizes.init(maxWinners);
    final List<String> winners = new ArrayList<>();

    for (int i = 0; i < threadCount; i++) {
        final int num = i;
        executorService.execute(() -> {
            if (tryToWin(num)) winners.add(String.valueOf(num));
        });
    }
    executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

    Assertions.assertThat(prizes.getCountWinners()).isEqualTo(maxWinners);
    Assertions.assertThat(winners.size()).isNotEqualTo(maxWinners);
}
```

성공하는 경우 (ReentrantLock 사용)
```java
@Test
void 천명이동시에도전_그중10명이당첨_reentrantLock_성공() throws Exception {
    final Lock lock = new ReentrantLock();
    final int maxWinners = 10;
    final int threadCount = 1000;
    executorService = Executors.newFixedThreadPool(threadCount);
    prizes.init(maxWinners);
    final List<String> winners = new ArrayList<>();

    for (int i = 0; i < threadCount; i++) {
        final int num = i;
        executorService.execute(() -> {
            lock.lock();
            try {
                if (tryToWin(num))
                    winners.add(String.valueOf(num));
            } finally {
                lock.unlock();
            }
        });
    }
    executorService.awaitTermination(TIME_OUT, TimeUnit.SECONDS);

    Assertions.assertThat(prizes.getCountWinners()).isEqualTo(maxWinners);
    Assertions.assertThat(winners).hasSize(maxWinners);
}
```

## 게시글 조회수 (with JPA)
JPA가 관리하는 Entity도 자바 락을 사용할 수 있을까요?

### 테스트 코드
아래 테스트코드가 성공하길 기대합니다.

```java
@SpringBootTest
class BoardServiceTest {
    @Autowired
    private BoardService boardService;
    private static final int TIME_OUT = 5;

    @Test
    void 백명이동시에조회_예상조회수100_성공() throws Exception {
        final Long boardId = 1L;
        final int threadCount = 100;
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        게시글생성요청_생성();

        for (int i = 0; i < threadCount; i++) {
            service.execute(() -> boardService.getBoard(boardId));
        }
        service.awaitTermination(10, TimeUnit.SECONDS);
        final Board board = boardService.getBoard(boardId);

        Assertions.assertThat(board.getCount()).isEqualTo(101);
    }

    private void 게시글생성요청_생성() {
        final BoardRequest request = new BoardRequest("테스트게시글", "테스트내용123");
        boardService.write(request);
    }
}
```

### 실패하는 경우
트랜잭션으로 관리되는 객체(Entity)의 상태가 전부(Java변수, Persistence 객체, 외부 DB) 동일하게 관리되야 때문에, JVM LEVEL에서만 부여한 Lock은 효과를 보지 못합니다.

```java
@GetMapping("/{id}")
@Transactional
public Board getBoard(@PathVariable("id") final Long id) {
    final Board board = boardRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 게시글이 없습니다."));

    lock.lock();
    try {
        board.increaseViewCount();
        boardRepository.save(board);
    } finally {
        lock.unlock();
    }

    return board;
}
```


### 성공하는 경우
select for update
```java
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Board> findById(Long id);
}
```

### 상태가 변경되는 경우를 예외로 처리하기
Entity에 Version을 부여해서 최신버전이 아닌경우 예외를 발생시키는 구조입니다. version은 JPA에 의해서 관리되며 상태변화에 맞춰 증가하게 됩니다.

```java

@Entity
@Table(name = "boards")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private int count;

    @Version
    private int version;
}
```


