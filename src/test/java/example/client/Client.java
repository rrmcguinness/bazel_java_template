package example.client;

import example.grpc.IdGrpc;
import example.pb.Model.IdRequest;
import io.grpc.Channel;

import static example.grpc.IdGrpc.newBlockingStub;

public class Client {

  private final IdGrpc.IdBlockingStub blockingStub;

  public Client(Channel channel) {
    blockingStub = newBlockingStub(channel);
  }

  public boolean create(String id) {
    var response = blockingStub.put(IdRequest.newBuilder().setId(id).build());
    return response.getValue();
  }
}
