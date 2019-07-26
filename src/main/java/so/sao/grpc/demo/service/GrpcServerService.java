package so.sao.grpc.demo.service;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import so.sao.test.protobuf.PhoneServiceGrpc;
import so.sao.test.protobuf.TestServer;

/**
 * TODO desc
 *
 * @author bingo
 * @date 2019/07/26
 */
@GrpcService(PhoneServiceGrpc.class)
public class GrpcServerService extends PhoneServiceGrpc.PhoneServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcServerService.class);

    @Override
    public void addPhoneToUser(TestServer.AddPhoneToUserRequest request,
                               StreamObserver<TestServer.AddPhoneToUserResponse> responseObserver) {

        LOGGER.info("request:{}", request.toString());

        TestServer.AddPhoneToUserResponse response = null;
        if (request.getPhoneNumber().length() == 11) {
            LOGGER.info("uid = %s , phone type is %s, nubmer is %s", request.getUid(), request.getPhoneType(), request.getPhoneNumber());
            response = TestServer.AddPhoneToUserResponse.newBuilder().setResult(true).build();
        } else {
            LOGGER.info("The phone nubmer %s is wrong!", request.getPhoneNumber());
            response = TestServer.AddPhoneToUserResponse.newBuilder().setResult(false).build();
        }

        LOGGER.info("response:{}", response.toString());

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}