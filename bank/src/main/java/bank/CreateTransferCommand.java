package bank;

import lombok.Data;

@Data
public class CreateTransferCommand {

    private long src;

    private long dest;

    private long amount;
}
