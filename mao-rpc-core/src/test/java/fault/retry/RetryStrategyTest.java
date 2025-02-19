package fault.retry;

import com.mao.maorpc.fault.retry.FixedIntervalRetryStrategy;
import com.mao.maorpc.fault.retry.NoRetryStrategy;
import com.mao.maorpc.fault.retry.RetryStrategy;
import com.mao.maorpc.model.RpcResponse;
import org.junit.Test;

public class RetryStrategyTest {

    RetryStrategy retryStrategy = new FixedIntervalRetryStrategy();

    @Test
    public void doRetry(){
        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        }catch (Exception e){
            System.out.println("多次重试失败");
            e.printStackTrace();
        }
    }
}
