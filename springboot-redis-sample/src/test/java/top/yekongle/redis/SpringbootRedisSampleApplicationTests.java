package top.yekongle.redis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.yekongle.redis.entity.Student;
import top.yekongle.redis.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SpringbootRedisSampleApplicationTests {

    private static final Logger LOOGER = LoggerFactory.getLogger(SpringbootRedisSampleApplicationTests.class);

    @Autowired
    private StudentService studentService;

    @Test
    public void testQuery() {
        Student student1 = studentService.queryStudentBySno("123");
        LOOGER.info(student1.toString());

        Student student2 = studentService.queryStudentBySno("456");
        LOOGER.info(student2.toString());
    }


    public void testUpdae() {
        Student student1 = studentService.queryStudentBySno("123");
        LOOGER.info(student1.toString());

        student1.setName("王五");
        studentService.update(student1);
        student1 = studentService.queryStudentBySno("123");

        LOOGER.info(student1.toString());
    }

}
