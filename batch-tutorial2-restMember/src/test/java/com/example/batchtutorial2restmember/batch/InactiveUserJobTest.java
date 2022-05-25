package com.example.batchtutorial2restmember.batch;

import com.example.batchtutorial2restmember.batch.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

import static com.example.batchtutorial2restmember.batch.domain.enums.UserStatus.ACTIVE;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class InactiveUserJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_hello() throws Exception {
        assertEquals("1", "1");
    }
    /*
    @Test
    public void 휴면_회원_전환_테스트() throws Exception {
        Date nowDate = new Date();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParametersBuilder().addDate("nowDate", nowDate).toJobParameters());

        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
        assertEquals(11, userRepository.findAll().size());
        assertEquals(0, userRepository.findByUpdatedDateBeforeAndStatusEquals(LocalDateTime.now().minusYears(1), ACTIVE).size());
    }
     */
}
