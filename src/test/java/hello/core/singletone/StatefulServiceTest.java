package hello.core.singletone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingletone() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA",10000);
        //ThreadB : B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA : A사용자 주문 금액 조회
//        int priceA = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }



    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}