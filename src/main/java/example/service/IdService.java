package example.service;

import example.grpc.IdGrpc.IdImplBase;
import example.model.Model;
import example.pb.Model.IdRequest;
import example.pb.Model.IdResponse;
import io.grpc.stub.StreamObserver;

public class IdService extends IdImplBase {
  private final Model model = new Model();

  @Override
  public void put(IdRequest request, StreamObserver<IdResponse> responseObserver) {
    var existing = model.addId(request.getId());
    responseObserver.onNext(IdResponse.newBuilder().setValue(existing).build());
    responseObserver.onCompleted();
  }
}