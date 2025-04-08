package com.athisii.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/4/25
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class BillingServiceGrpcClient {
    final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    public BillingServiceGrpcClient(@Value("${billing.service.address:localhost}") String address, @Value("${billing.service.grpc.port:9091}") int port) {
        log.info("Connecting to Billing Service grpc at {}:{}", address, port);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port)
                .usePlaintext()
                .build();
        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(Long patientId, String name, String email) {
        BillingRequest request = BillingRequest.newBuilder().setPatientId(patientId).setName(name).setEmail(email).build();
        BillingResponse res = blockingStub.createBillingAccount(request);
        log.info("Received response from Billing Service via GRPC {}", res);
        return res;
    }
}
