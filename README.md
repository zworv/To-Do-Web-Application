# To-Do Web Application

### To-Do Web Application

- 使用Spring Framework。
- 使用Thymeleaf渲染網頁。
- 使用Spring Data JPA儲存庫。
- 使用MySQL資料庫(須事先建立Todo資料庫及Task表，以及變更username、password)。
- 使用Spring Security。

### 網頁架構

- home
  - login
    - main
      - add

### 資料架構

- Task
  - id
  - isCompleted
  - deadline
  - title
  - description
- User
  - id
  - username
  - password

### To-Do

1. 
   - [x] 建立Task類
   - [x] 建立Todo類
   - [x] 建立起始頁面
   - [x] 建立起始頁面的Controller 
2. 
   - [x] 建立add.html
   - [x] 建立add.html的AddTaskController
     - [x] addTask todo必須重複使用(不是建立新的)
       - 解決方案:使用@SessionAttributes連接AddTaskController and MainController
     - [x] addTask redirect to main.html
   - [x] 建立add.html表單提交
3. 
   - [x] 建立main.html
   - [x] 建立main.html的MainController
   - [x] main.html連接到add.html
   - [x] home.html連接到main.html
4. 校驗表單輸入
   - [x] Task類加入校驗
   - [x] add.html加入校驗
   - [x] AddTaskController加入執行校驗
5. 
   - [x] 使用視圖控制器(僅用於當範例於TodoApplication)
6. 資料庫
   - [x] Task.java Todo.java將領域對象標註為實體
   - [x] 聲明JPA儲存庫
   - [x] CRUD
7. Security
   1. 配置
      - [x] 建立SecurityConfig
      - [x] 用戶儲存庫
        - [x] 用戶
        - [x] 用戶實體
        - [x] 用戶詳情Service
      - [x] 用戶註冊Controller
      - [x] 建立registration.html
   2. 保護Web請求
      - [x] 建立Filter chain in SecurityConfig.java
      - [x] 建立login.html
      - [x] 建立login in viewController
      - [x] 建立logout filter chain
      - [x] 在main.html建立logout連結
8. 其他
    - [x] user能夠自行刪除個人帳戶
    - [x] todo可以進行排序
    - 新增admin權限
      - [ ] 建立admin帳戶
      - [ ] 建立只有admin能進入的連結
      - [ ] admin可以進行user CRUD

### Issue

- [x] main.html isCompleted單選框點擊後不會改變(false => true)
  - 解決方案: 改以文字顯示
- [x] Task Todo新增不會生成ID
  - 已更新程式碼
- [ ] schema.sql data.sql don't work
- [x] users_seq doesn't exist
  - 原因: The "users_seq doesn't exist" error in Spring Boot typically occurs because Hibernate defaults to GenerationType.SEQUENCE when GenerationType.AUTO is used, but the database (especially MySQL) lacks the required sequence table.
  - 解決方案: Switch to Identity Generation
- [x] Error: create table [*]user (id integer not null, password varchar(255), username varchar(255), primary key (id))"; expected "identifier";
  - 原因:user為reserved keyword應換成其他keyword
  - 解決方案:資料表命名為@Table(name = "\"user\"")
- [x] hasRole("USER") not found the creator
  - 答案: User.java裡的getAuthorities()
- [x] addTask.html 無輸入並繳交時產生 MethodArgumentNotValidException
    - 解決方案: 參數順序問題
      - 錯誤: addTask(@Valid Task task, @AuthenticationPrincipal User user, Errors errors)
      - 正確: addTask(@Valid Task task, Errors errors, @AuthenticationPrincipal User user)
### Idea

- [ ] Default task建立
- [ ] Task 新增 tags
- [x] 顯示user名字在main.html中
- [ ] 新增admin role and its dashboard
- [x] Todo web application