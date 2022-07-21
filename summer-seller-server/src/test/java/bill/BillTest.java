package bill;

import com.summer.SellerApplication;
import com.summer.entity.TrackingBill;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SellerApplication.class})
public class BillTest {

    @Test
    public void testBill() {
        TrackingBill trackingBill = new TrackingBill();
        trackingBill.setBillId(1);
        trackingBill.setBillName("Bill1001");
        log.info("trackingBill:{}", trackingBill.getBillName());

    }
}
