package so.sao.grpc.demo.service;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import so.sao.test.protobuf.GreeterGrpc;
import so.sao.test.protobuf.Hello;

/**
 * TODO desc
 *
 * @author bingo
 * @date 2019/07/26
 */
@GrpcService(GreeterGrpc.class)
public class GrpcHelloService extends GreeterGrpc.GreeterImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcHelloService.class);

    @Override
    public void sayHello(Hello.HelloRequest request, StreamObserver<Hello.HelloResponse> responseObserver) {
        LOGGER.info("request:{}", request.toString());
        Hello.HelloResponse response = Hello.HelloResponse.newBuilder().setMessage("Hello =============> " + request.getName()).build();
        LOGGER.info("response:{}", response.toString());

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}