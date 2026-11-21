
# AI Not Pass

## Group Information
**Group Name: Imon Base**  
**Course:** SE331 Component-Based Software Development  
**Term Project II (Backend)**

### Team Members
- Punn Laowatchara
- Nattapon Sukkeaw  
- Wasinpat Yanin

This is a clean copy of our class repo (the original stays private because it holds course data). Commits in the original repo: Ling4826 (Wasinpat): 50 · Jord018: 35 · Jookmaste: 15 (of 100).

## Project Overview
This project is a server-side REST API of the system where users start at Level 1 and take an exam to advance to a higher level in order to unlock AI services and a higher monthly token quota.

### Key Features
1. Authentication: register, login, refresh, and logout using JWT, storing the access token in the database for revocation during logout/refresh.
2. Role-based access: separate permissions into public, login, and ADMIN.
3. Member management: users can view/edit their own profile, while admins can view paginated and filterable lists, edit information, and activate/deactivate accounts.
4. Level-up exams: submit exams according to the level, check scores against passing criteria, automatically level up upon passing, and record exam history.
5. Level, question, and e-learning management: admins manage levels, benefits, services, passing criteria, and the question bank, while users can view e-learning courses based on their level.
6. Statistics: member statistics and the latest exam list for admins.
7. File storage: upload files/images to Supabase Storage.

## Technical Stack
- **Java 21**
- **Spring Boot 4.1.1**
- **Spring Data JPA** - used Repository/DAO to access Database
- **PostgreSQL** - create database
- **Spring Security + JWT** - Authentication,Authorization 
- **MapStruct / Lombok** - MapStruct used to convert entity to DTO, Lombok used to reduce boilerplate code
- **Supabase** - used to store File

## Project Structure
```
src/
├── main/
│   ├── java/se331/passport/
│   │   ├── config/
│   │   │   └── InitApp.java
│   │   ├── controller/
│   │   │   ├── ELearningController.java
│   │   │   ├── ExamController.java
│   │   │   ├── LevelController.java
│   │   │   ├── LevelDetailController.java
│   │   │   ├── MemberController.java
│   │   │   ├── QuestionController.java
│   │   │   ├── StatsController.java
│   │   │   └── SupabaseController.java
│   │   ├── dao/
│   │   │   ├── ELearningDao.java
│   │   │   ├── ELearningDaoImpl.java
│   │   │   ├── ExamHistoryDao.java
│   │   │   ├── ExamHistoryDaoImpl.java
│   │   │   ├── LevelDao.java
│   │   │   ├── LevelDaoImpl.java
│   │   │   ├── LevelDetailDao.java
│   │   │   ├── LevelDetailDaoImpl.java
│   │   │   ├── MemberDao.java
│   │   │   ├── MemberDaoImpl.java
│   │   │   ├── QuestionDao.java
│   │   │   ├── QuestionDaoImpl.java
│   │   │   ├── StatsDao.java
│   │   │   └── StatsDaoImpl.java
│   │   ├── entity/
│   │   │   ├── Benefit.java
│   │   │   ├── BenefitDTO.java
│   │   │   ├── ELearning.java
│   │   │   ├── ELearningDTO.java
│   │   │   ├── ExamAnswerDTO.java
│   │   │   ├── ExamHistory.java
│   │   │   ├── ExamHistoryDTO.java
│   │   │   ├── ExamQuestionDTO.java
│   │   │   ├── ExamResultDTO.java
│   │   │   ├── ExamSubmitDTO.java
│   │   │   ├── Level.java
│   │   │   ├── LevelDetailDTO.java
│   │   │   ├── LevelDTO.java
│   │   │   ├── LevelStatsDTO.java
│   │   │   ├── Member.java
│   │   │   ├── MemberDTO.java
│   │   │   ├── MemberLevelDTO.java
│   │   │   ├── MemberStatsDTO.java
│   │   │   ├── Question.java
│   │   │   ├── QuestionDTO.java
│   │   │   ├── RecentExamDTO.java
│   │   │   ├── ServiceItem.java
│   │   │   ├── ServiceItemDTO.java
│   │   │   └── UserAuthDTO.java
│   │   ├── repository/
│   │   │   ├── BenefitRepository.java
│   │   │   ├── ELearningRepository.java
│   │   │   ├── ExamHistoryRepository.java
│   │   │   ├── LevelRepository.java
│   │   │   ├── MemberRepository.java
│   │   │   ├── QuestionRepository.java
│   │   │   └── ServiceItemRepository.java
│   │   ├── security/
│   │   │   ├── auth/
│   │   │   │   ├── AuthenticationController.java
│   │   │   │   ├── AuthenticationRequest.java
│   │   │   │   ├── AuthenticationResponse.java
│   │   │   │   ├── AuthenticationService.java
│   │   │   │   └── RegisterRequest.java
│   │   │   ├── config/
│   │   │   │   ├── ApplicationConfig.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   ├── JwtService.java
│   │   │   │   ├── LogoutService.java
│   │   │   │   └── SecurityConfiguration.java
│   │   │   ├── token/
│   │   │   │   ├── Token.java
│   │   │   │   ├── TokenDao.java
│   │   │   │   ├── TokenDaoImpl.java
│   │   │   │   ├── TokenRepository.java
│   │   │   │   ├── TokenService.java
│   │   │   │   ├── TokenServiceImpl.java
│   │   │   │   └── TokenType.java
│   │   │   └── user/
│   │   │       ├── Role.java
│   │   │       ├── User.java
│   │   │       ├── UserDao.java
│   │   │       ├── UserDaoImpl.java
    ├── UserService.java
│   │   │       └── UserServiceImpl.java
│   │   ├── service/
│   │   │   ├── ELearningService.java
│   │   │   ├── ELearningServiceImpl.java
│   │   │   ├── ExamService.java
│   │   │   ├── ExamServiceImpl.java
│   │   │   ├── LevelDetailService.java
│   │   │   ├── LevelDetailServiceImpl.java
│   │   │   ├── LevelService.java
│   │   │   ├── LevelServiceImpl.java
│   │   │   ├── MemberService.java
│   │   │   ├── MemberServiceImpl.java
│   │   │   ├── QuestionService.java
│   │   │   ├── QuestionServiceImpl.java
│   │   │   ├── StatsService.java
│   │   │   └── StatsServiceImpl.java
│   │   ├── util/
│   │   │   ├── PassportMapper.java
│   │   │   ├── S3Config.java
│   │   │   ├── StorageFileDto.java
│   │   │   └── SupabaseStorageService.java
│   │   └── BackendApplication.java
│   └── resources/
│       ├── application.yml
│       └── application-prod.yml
├── test/java/se331/passport/
│   └── BackendApplicationTests.java
Dockerfile
docker-compose.yml
mvnw
pom.xml
```

## Entity
| Entity |Relationship |
| :--- | :--- |
| **Level** | • Has a **One-to-Many** relationship with `Member`, `Benefit`, `ServiceItem`, `ELearning`, `Question`, and `ExamHistory`.|
| **Member** | • **Many-to-One** with `Level` (stores `level_id`).<br>• **One-to-Many** with `ExamHistory`.<br>• **One-to-One** with `User`. |
| **ExamHistory** | • **Many-to-One** with both `Member` and `Level`.|
| **Question** | • **Many-to-One** with `Level`. |
| **Benefit / ServiceItem / ELearning** | • **Many-to-One** with `Level` exclusively. |
| **User** | • **One-to-Many** with `Token`.<br>• **One-to-One** with Member. |
| **Token** | • **Many-to-One** with `User`. Holds the foreign key `user_id`. |

## API Endpoints
Base URL: `http://localhost:8081`

| Method | URL | Permission | Work / Description |
| :--- | :--- | :--- | :--- |
| **PUBLIC** | | | |
| `POST` | `/api/v1/auth/logout` | public | Logout via LogoutService, sets token as revoked |
| `POST` | `/api/v1/auth/register` | public | Register a new member, returns access and refresh tokens |
| `POST` | `/api/v1/auth/authenticate` | public | Log in, returns access and refresh tokens along with user details |
| `POST` | `/api/v1/auth/refresh-token` | public | Send refresh token in Authorization |
| `POST` | `/uploadImage` | public | Upload an image via multipart field `image` |
| **LOGIN** | | | |
| `GET` | `/members/me` | login | Get currently logged-in user details |
| `PUT` | `/members/me` | login | Update own profile |
| `GET` | `/members/me/level-detail` | login | Get level details for the logged-in user |
| `GET` | `/members/me/exam-histories` | login | Get own exam history |
| `GET` | `/exams/questions` | login | Get exam questions for the logged-in user |
| `POST` | `/exams` | login | Submit answers and get exam results |
| `GET` | `/elearnings` | login | Get e-learning list by level (query parameter `level` is required) |
| `GET` | `/presignedUrl` | login | Request storage presigned URL with optional `fileName` and `key` queries |
| `POST` | `/uploadFile` | login | Upload a file via multipart field `file`, returns URL |
| **ADMIN** | | | |
| `GET` | `/levels` | ADMIN | List all levels |
| `GET` | `/levels/{id}` | ADMIN | Get level by id |
| `POST` | `/levels/{id}/benefits` | ADMIN | Add benefit to level|
| `PUT` | `/benefits/{id}` | ADMIN | Update benefit|
| `POST` | `/levels/{id}/services` | ADMIN | Add service to level|
| `PUT` | `/services/{id}` | ADMIN | Update service|
| `PUT` | `/levels/{id}/pass-criteria` | ADMIN | Update level pass criteria|
| `GET` | `/members` | ADMIN | Get member list with pagination (`_limit`, `_page`) and filters (`name`, `surname`, `province`, `level`) |
| `GET` | `/members/{id}` | ADMIN | Get member details by ID |
| `PUT` | `/members/{id}` | ADMIN | Admin updates a member's information |
| `PUT` | `/members/{id}/active` | ADMIN | Admin enables/disables account |
| `GET` | `/members/stats` | ADMIN | Get member statistics |
| `GET` | `/members/exam-histories` | ADMIN | Get exam history for all members |
| `GET` | `/members/{id}/exam-histories` | ADMIN | Get exam history for a specific member by ID |
| `GET` | `/questions` | ADMIN | Admin views questions by level (query parameter `level` is required) |
| `POST` | `/questions` | ADMIN | Admin creates a question |
| `PUT` | `/questions/{id}` | ADMIN | Admin updates a question |
| `DELETE` | `/questions/{id}` | ADMIN | Admin deletes a question |

## Getting Started

### Prerequisites
- JDK 21
- Docker Desktop

### Installation
1. **Clone the repository**
   ```bash
   git clone https://github.com/chartchai-class/953331-2026-component-based-software-dev-project-2-group-8
   ```
2. **Start the database**
   ```bash
   docker compose up
   ```
3. **Run the application**
   ```bash
    ./mvnw spring-boot:run
   ```
4. **Verify**: 
   ```bash
    curl -i http://localhost:8081/api/v1/auth/authenticate
   ```
   If you get a 400, 401, 403, or 200 code, it means the server is up.
   If you get 'Failed to connect or Connection refused', it means the server is not up yet.
   
### Configuration
| Variable | Description |
| :--- | :--- |
| `PORT` | Listening port for the server |
| `DB_URL` | PostgreSQL JDBC URL |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `SUPABASE_ENDPOINT` | Storage endpoint  |
| `SUPABASE_BUCKET` | Storage bucket name |
| `SUPABASE_REGION` | Storage region |
| `SUPABASE_ACCESS_KEY` | Storage access key |
| `SUPABASE_SECRET_KEY` | Storage secret key |
| `JWT_SECRET` | Secret key for signing JWTs  |
| `ALLOWED_ORIGINS` | Allowed CORS origins |

## Deployment
1. Merge the work into main  
2. Switch to the prod branch and then merge main into it  
3. Render detects new commits in prod and deploys by itself, building from the Dockerfile.

## Key Implementation Details

### Authentication
- After logging in, you get an access + refresh token, HS256 signature, valid for 1 day and 7 days for refresh token
- session is stateless

### Data Storage
- PostgreSQL via Spring Data JPA: Stores the main system data, including Level, Benefit, ServiceItem, ELearning, Question, Member, ExamHistory, and User.  
- Token table: Stores access tokens issued to users used to check every request and revoke on logout or refresh.  
- File/Image: Uploaded via /uploadFile and /uploadImage to Supabase Storage and returns the URL. 
- Initial data: InitApp creates sample data when the app is ready , including level, benefit, service, e-learning, question, member, and exam history.
