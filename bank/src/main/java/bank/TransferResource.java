package bank;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("transfers")
public class TransferResource {

    @Inject
    private TransferBean transferBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transfer> listTransfers() {
        return transferBean.listTransfers();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTransfer(CreateTransferCommand command) {
        var transfer = transferBean.createTransfer(command);
        return Response.status(201).entity(transfer).build();
    }

}
