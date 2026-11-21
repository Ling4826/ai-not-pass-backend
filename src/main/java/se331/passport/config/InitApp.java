package se331.passport.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.passport.entity.*;
import se331.passport.repository.*;
import se331.passport.security.user.Role;
import se331.passport.security.user.User;
import se331.passport.security.user.UserRepository;


import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final MemberRepository memberRepository;
    final LevelRepository levelRepository;
    final BenefitRepository benefitRepository;
    final ServiceItemRepository serviceItemRepository;
    final ELearningRepository eLearningRepository;
    final QuestionRepository questionRepository;
    final ExamHistoryRepository examHistoryRepository;
    final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (levelRepository.count() > 0) {
            return;
        }
        Level level1 = levelRepository.save(Level.builder()
                .levelNumber(1)
                .name("Level 1")
                .passCriteria(60)
                .build());
        Level level2 = levelRepository.save(Level.builder()
                .levelNumber(2)
                .name("Level 2")
                .passCriteria(60)
                .build());
        Level level3 = levelRepository.save(Level.builder()
                .levelNumber(3)
                .name("Level 3")
                .passCriteria(60)
                .build());
        Level level4 = levelRepository.save(Level.builder()
                .levelNumber(4)
                .name("Level 4")
                .passCriteria(0)
                .build());

        benefitRepository.save(Benefit.builder()
                .description("50,000 Tokens/Month")
                .level(level1)
                .build());
        benefitRepository.save(Benefit.builder()
                .description("200,000 Tokens/Month")
                .level(level2)
                .build());
        benefitRepository.save(Benefit.builder()
                .description("500,000 Tokens/Month")
                .level(level3)
                .build());
        benefitRepository.save(Benefit.builder()
                .description("Unlimited Tokens")
                .level(level4)
                .build());
        benefitRepository.save(Benefit.builder()
                .description("Community forum support")
                .level(level1)
                .build());
        benefitRepository.save(Benefit.builder()
                .description("Save chat history for 30 days")
                .level(level2)
                .build());
        benefitRepository.save(Benefit.builder()
                .description("Priority response queue")
                .level(level2)
                .build());
        benefitRepository.save(Benefit.builder()
                .description("Email support within 24 hours")
                .level(level3)
                .build());
        benefitRepository.save(Benefit.builder()
                .description("Upload files up to 50 MB")
                .level(level3)
                .build());
        benefitRepository.save(Benefit.builder()
                .description("Dedicated support channel")
                .level(level4)
                .build());
        benefitRepository.save(Benefit.builder()
                .description("Early access to new models")
                .level(level4)
                .build());

        serviceItemRepository.save(ServiceItem.builder()
                .name("AI Model 1")
                .description("Basic text generation model")
                .badge("Badge1.png")
                .level(level1)
                .build());
        serviceItemRepository.save(ServiceItem.builder()
                .name("AI Model 2")
                .description("Image understanding and generation model")
                .badge("Badge2.png")
                .level(level2)
                .build());
        serviceItemRepository.save(ServiceItem.builder()
                .name("AI Model 3")
                .description("Code assistant model for developers")
                .badge("Badge3.png")
                .level(level3)
                .build());
        serviceItemRepository.save(ServiceItem.builder()
                .name("AI Model 4")
                .description("Advanced reasoning model with long context")
                .badge("Badge3.png")
                .level(level4)
                .build());

        eLearningRepository.save(ELearning.builder()
                .title("Introduction to AI")
                .videoUrl("https://www.youtube.com/embed/x8SNz0TrSw4")
                .level(level1)
                .build());
        eLearningRepository.save(ELearning.builder()
                .title("AI Basics")
                .videoUrl("https://www.youtube.com/embed/Tl-oBnwtxKE")
                .level(level1)
                .build());
        eLearningRepository.save(ELearning.builder()
                .title("How Neural Networks Learn")
                .videoUrl("https://www.youtube.com/embed/IHZwWFHWa-w")
                .level(level2)
                .build());
        eLearningRepository.save(ELearning.builder()
                .title("Neural Networks")
                .videoUrl("https://www.youtube.com/embed/aircAruvnKk")
                .level(level2)
                .build());
        eLearningRepository.save(ELearning.builder()
                .title("Intro to Large Language Models")
                .videoUrl("https://www.youtube.com/embed/zjkBMFhNj_g")
                .level(level3)
                .build());
        eLearningRepository.save(ELearning.builder()
                .title("Transformers Explained")
                .videoUrl("https://www.youtube.com/embed/wjZofJX0v4M")
                .level(level3)
                .build());
        eLearningRepository.save(ELearning.builder()
                .title("What is RAG")
                .videoUrl("https://www.youtube.com/embed/T-D1OfcDW1M")
                .level(level4)
                .build());
        eLearningRepository.save(ELearning.builder()
                .title("LangChain Explained")
                .videoUrl("https://www.youtube.com/embed/aywZrzNaKjs")
                .level(level4)
                .build());

        questionRepository.save(Question.builder()
                .text("What does AI stand for?")
                .choices(List.of("Automatic Intelligence", "Artificial Intelligence",
                        "Analytic Information", "Advanced Internet"))
                .correctAnswer(1)
                .level(level1)
                .build());
        questionRepository.save(Question.builder()
                .text("What is the main purpose of a large language model (LLM)?")
                .choices(List.of("To cool down the computer", "To predict and generate text",
                        "To scan for viruses", "To connect to the Wi-Fi"))
                .correctAnswer(1)
                .level(level1)
                .build());
        questionRepository.save(Question.builder()
                .text("Which of the following is considered a subset of AI?")
                .choices(List.of("Machine Learning", "Blockchain",
                        "Cloud Storage", "Virtual Reality"))
                .correctAnswer(0)
                .level(level1)
                .build());
        questionRepository.save(Question.builder()
                .text("What is Prompt Engineering?")
                .choices(List.of("Building physical servers", "Fixing broken keyboards",
                        "The process of designing effective inputs for AI",
                        "Writing a completely new operating system"))
                .correctAnswer(2)
                .level(level2)
                .build());
        questionRepository.save(Question.builder()
                .text("How does the 'Temperature' setting affect AI output?")
                .choices(List.of("It controls the heat of the CPU",
                        "It controls the randomness and creativity of the response",
                        "It speeds up the internet connection",
                        "It limits the number of characters in the response"))
                .correctAnswer(1)
                .level(level2)
                .build());
        questionRepository.save(Question.builder()
                .text("What does 'Context Window' refer to?")
                .choices(List.of("The application screen size",
                        "The maximum amount of text the model can process at once",
                        "The time limit for answering a prompt",
                        "The operating system window"))
                .correctAnswer(1)
                .level(level2)
                .build());
        questionRepository.save(Question.builder()
                .text("What is Deep Learning?")
                .choices(List.of("Reading a book deeply",
                        "A machine learning technique based on artificial neural networks",
                        "Learning how to dive into the ocean",
                        "A database that stores encrypted passwords"))
                .correctAnswer(1)
                .level(level3)
                .build());
        questionRepository.save(Question.builder()
                .text("What is an API?")
                .choices(List.of("Application Programming Interface", "Artificial Process Integration",
                        "Advanced Protocol Internet", "Automated Program Instruction"))
                .correctAnswer(0)
                .level(level3)
                .build());
        questionRepository.save(Question.builder()
                .text("Which model type is primarily used for generating images?")
                .choices(List.of("Large Language Models (LLMs)", "Audio Recognition Models",
                        "Vision or Diffusion Models", "Tabular Data Models"))
                .correctAnswer(2)
                .level(level3)
                .build());
        questionRepository.save(Question.builder()
                .text("What does RAG stand for in AI?")
                .choices(List.of("Random Access Generation", "Retrieval-Augmented Generation",
                        "Rapid Artificial Growth", "Real-time Analytics Graph"))
                .correctAnswer(1)
                .level(level4)
                .build());
        questionRepository.save(Question.builder()
                .text("What is the primary benefit of RAG?")
                .choices(List.of("It makes the AI generate images faster",
                        "It allows the model to access external, up-to-date information",
                        "It reduces the electricity usage",
                        "It changes the language of the AI automatically"))
                .correctAnswer(1)
                .level(level4)
                .build());
        questionRepository.save(Question.builder()
                .text("What is fine-tuning in the context of LLMs?")
                .choices(List.of("Adjusting the monitor brightness",
                        "Training a pre-trained model on a specific dataset to specialize it",
                        "Updating the operating system",
                        "Changing the AI's profile picture"))
                .correctAnswer(1)
                .level(level4)
                .build());


        Member somchai = memberRepository.save(Member.builder()
                .name("Somchai")
                .surname("Jaidee")
                .email("somchai@example.com")
                .province("Chiang Mai")
                .level(level1)
                .image("")
                .active(true)
                .build());
        Member suda =memberRepository.save(Member.builder()
                .name("Suda")
                .surname("Rakdee")
                .email("Suda@example.com")
                .province("Bangkok")
                .level(level2)
                .image("")
                .active(true)
                .build());
        Member wasinpat =memberRepository.save(Member.builder()
                .name("wasinpat")
                .surname("yanin")
                .email("wasinpat@example.com")
                .province("lampang")
                .level(level3)
                .image("")
                .active(false)
                .build());
        memberRepository.save(Member.builder()
                .name("Anan")
                .surname("Srisuk")
                .email("anan@example.com")
                .province("Chiang Mai")
                .level(level2)
                .image("")
                .active(true)
                .build());
        memberRepository.save(Member.builder()
                .name("Kanya")
                .surname("Thongdee")
                .email("kanya@example.com")
                .province("Bangkok")
                .level(level1)
                .image("")
                .active(true)
                .build());
        memberRepository.save(Member.builder()
                .name("Prasert")
                .surname("Wongsa")
                .email("prasert@example.com")
                .province("Khon Kaen")
                .level(level3)
                .image("")
                .active(true)
                .build());
        memberRepository.save(Member.builder()
                .name("Malee")
                .surname("Jaidee")
                .email("malee@example.com")
                .province("Phuket")
                .level(level1)
                .image("")
                .active(false)
                .build());
        memberRepository.save(Member.builder()
                .name("Somsak")
                .surname("Boonmee")
                .email("somsak@example.com")
                .province("Chiang Rai")
                .level(level4)
                .image("")
                .active(true)
                .build());
        memberRepository.save(Member.builder()
                .name("Napat")
                .surname("Kaewmanee")
                .email("napat@example.com")
                .province("Lampang")
                .level(level2)
                .image("")
                .active(true)
                .build());
        memberRepository.save(Member.builder()
                .name("Pimchanok")
                .surname("Saetang")
                .email("pimchanok@example.com")
                .province("Bangkok")
                .level(level3)
                .image("")
                .active(true)
                .build());
        memberRepository.save(Member.builder()
                .name("Thanawat")
                .surname("Rakthai")
                .email("thanawat@example.com")
                .province("Chiang Mai")
                .level(level1)
                .image("")
                .active(true)
                .build());
        memberRepository.save(Member.builder()
                .name("Siriporn")
                .surname("Chaiyo")
                .email("siriporn@example.com")
                .province("Nakhon Ratchasima")
                .level(level4)
                .image("")
                .active(true)
                .build());
        memberRepository.save(Member.builder()
                .name("Wichai")
                .surname("Somboon")
                .email("wichai@example.com")
                .province("Songkhla")
                .level(level2)
                .image("")
                .active(false)
                .build());

        examHistoryRepository.save(ExamHistory.builder()
                .member(somchai)
                .level(level1)
                .score(33)
                .passed(false)
                .attemptedAt(LocalDateTime.of(2026, 10, 3, 14, 30))
                .build());
        examHistoryRepository.save(ExamHistory.builder()
                .member(wasinpat)
                .level(level1)
                .score(100)
                .passed(true)
                .attemptedAt(LocalDateTime.of(2026, 9, 20, 9, 0))
                .build());
        examHistoryRepository.save(ExamHistory.builder()
                .member(wasinpat)
                .level(level2)
                .score(67)
                .passed(true)
                .attemptedAt(LocalDateTime.of(2026, 9, 25, 9, 0))
                .build());
        examHistoryRepository.save(ExamHistory.builder()
                .member(suda)
                .level(level1)
                .score(33)
                .passed(false)
                .attemptedAt(LocalDateTime.of(2026, 10, 1, 10, 0))
                .build());
        examHistoryRepository.save(ExamHistory.builder()
                .member(suda)
                .level(level1)
                .score(67)
                .passed(true)
                .attemptedAt(LocalDateTime.of(2026, 10, 2, 10, 0))
                .build());

        addUser();
        somchai.setUser(user2);
        memberRepository.save(somchai);
        suda.setUser(user3);
        memberRepository.save(suda);
        wasinpat.setUser(user4);
        memberRepository.save(wasinpat);
    }

    User user1, user2, user3, user4;

        private void addUser() {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            user1 = User.builder()
                    .username("admin")
                    .password(encoder.encode(System.getenv().getOrDefault("ADMIN_PASSWORD", "admin")))
                    .firstname("admin")
                    .lastname("admin")
                    .email("admin@admin.com")
                    .enabled(true)
                    .build();
            user2 = User.builder()
                    .username("somchai")
                    .password(encoder.encode("somchai"))
                    .firstname("user")
                    .lastname("user")
                    .email("somchai@user.com")
                    .enabled(true)
                    .build();
            user3 = User.builder()
                    .username("Suda")
                    .password(encoder.encode("Suda"))
                    .firstname("User")
                    .lastname("dUser")
                    .email("Suda@user.com")
                    .enabled(true)
                    .build();
            user4 = User.builder()
                    .username("wasinpat")
                    .password(encoder.encode("wasinpat"))
                    .firstname("wasinpat")
                    .lastname("yanin")
                    .email("wasinpat@example.com")
                    .enabled(false)
                    .build();

            user1.getRoles().add(Role.ROLE_USER);
            user1.getRoles().add(Role.ROLE_ADMIN);

            user2.getRoles().add(Role.ROLE_USER);
            user3.getRoles().add(Role.ROLE_USER);
            user4.getRoles().add(Role.ROLE_USER);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
        }


}