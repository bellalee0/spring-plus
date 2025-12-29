package org.example.expert.domain.auth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataProcessingTest {

    @Autowired
    private DataSource dataSource;

    Random random = new Random();

    private static final int TOTAL_SIZE = 5000000;
    private static final int BATCH_SIZE = 10000;

    @Test
    @DisplayName("500만건 랜덤 유저 생성")
    void insert_bulk_users() throws SQLException {

        String sql = "INSERT INTO users (email, password, user_role, nickname, created_at, modified_at) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        connection.setAutoCommit(false);

        List<String> firstName = List.of("가","강","건","경","고",
            "관","광","구","규","근","기","길","나","남","노","누","다","단",
            "달","담","대","덕","도","동","두","라","래","로","루","리","마",
            "만","명","무","문","미","민","바","박","백","범","별","병","보",
            "빛","사","산","상","새","서","석","선","설","섭","성","세","소",
            "솔","수","숙","순","숭","슬","승","시","신","아","안","애","엄",
            "여","연","영","예","오","옥","완","요","용","우","원","월","위",
            "유","윤","율","으","은","의","이","익","인","일","잎","자","잔",
            "장","재","전","정","제","조","종","주","준","중","지","진","찬",
            "창","채","천","철","초","춘","충","치","탐","태","택","판","하",
            "한","해","혁","현","형","혜","호","홍","화","환","회","효","훈",
            "휘","희","운","모","배","부","림","봉","혼","황","량","린","을",
            "비","솜","공","면","탁","온","디","항","후","려","균","묵","송",
            "욱","휴","언","령","섬","들","견","추","걸","삼","열","웅","분",
            "변","양","출","타","흥","겸","곤","번","식","란","더","손","술",
            "훔","반","빈","실","직","흠","흔","악","람","뜸","권","복","심",
            "헌","엽","학","개","롱","평","늘","늬","랑","얀","향","울","련");

        for (int i = 1; i <= TOTAL_SIZE; i++) {

            String email = "user" + i + "@test.com";
            String password = "1234";
            String userRole = "USER";
            String nickname = firstName.get(random.nextInt(firstName.size())) + firstName.get(random.nextInt(firstName.size())) + random.nextInt(10000);
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userRole);
            preparedStatement.setString(4, nickname);
            preparedStatement.setTimestamp(5, now);
            preparedStatement.setTimestamp(6, now);

            preparedStatement.addBatch();

            if (i % BATCH_SIZE == 0) {
                preparedStatement.executeBatch();
                connection.commit();

                preparedStatement.clearBatch();
            }
        }

        preparedStatement.executeBatch();
        connection.commit();

    }
}