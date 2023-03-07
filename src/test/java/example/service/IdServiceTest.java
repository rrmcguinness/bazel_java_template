package example.service;

import example.pb.Model.IdRequest;
import example.pb.Model.IdResponse;
import io.grpc.stub.StreamObserver;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IdServiceTest {
  static class TestObserver implements StreamObserver<IdResponse> {
    final AtomicInteger positiveCounter = new AtomicInteger(0);
    final AtomicInteger negativeCounter = new AtomicInteger(0);
    final AtomicInteger errCounter = new AtomicInteger(0);

    final AtomicBoolean completed = new AtomicBoolean(Boolean.FALSE);

    @Override
    public void onNext(IdResponse value) {
      if (value.getValue()) {
        positiveCounter.incrementAndGet();
      } else {
        negativeCounter.incrementAndGet();
      }
    }

    @Override
    public void onError(Throwable t) {
      errCounter.incrementAndGet();
    }

    @Override
    public void onCompleted() {
      completed.set(Boolean.TRUE);
    }
  }

  @Test
  @DisplayName("Test Service")
  public void TestService() {

    var observer = new TestObserver();
    var service = new IdService();

    var id1 = UUID.randomUUID().toString();

    service.put(IdRequest.newBuilder().setId(id1).build(), observer);

    Assertions.assertEquals(1, observer.positiveCounter.get());
    Assertions.assertEquals(0, observer.errCounter.get());
    Assertions.assertTrue(observer.completed.get());


  }

}