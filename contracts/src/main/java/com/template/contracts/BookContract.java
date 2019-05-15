package com.template.contracts;

import com.template.states.BookState;
import net.corda.core.contracts.Contract;
import net.corda.core.contracts.TypeOnlyCommandData;
import net.corda.core.transactions.LedgerTransaction;

import static net.corda.core.contracts.ContractsDSL.requireSingleCommand;
import static net.corda.core.contracts.ContractsDSL.requireThat;

// ************
// * Contract *
// ************
public class BookContract implements Contract {
    // This is used to identify our contract when building a transaction.
    public static final String ID = "com.template.contracts.GradeContract";

    @Override
    public void verify(LedgerTransaction tx) {
        requireSingleCommand(tx.getCommands(), LendBook.class);
        requireThat(require -> {
            require.using("There cant be inputs", tx.getInputs().size() == 0);
            require.using("Output must be bookState", (tx.getOutputs().get(0).getData()) instanceof BookState);

            final BookState output = (BookState) tx.getOutputs().get(0).getData();

            require.using("Library must not be null", output.getLibrary() != null);
            require.using("Library must be a library", output.getLibrary().getName().getOrganisation().startsWith("Library"));

            require.using("User must not be null", output.getUser() != null);
            require.using("User must be a user", output.getUser().getName().getOrganisation().startsWith("User"));

            require.using("Author must not be null", (output.getAuthor() != null && !output.getAuthor().equals("")));
            require.using("Title must  not be null", (output.getTitle() != null && !output.getTitle().equals("")));

            return null;
        });

    }

    // Used to indicate the transaction's intent.
    public static class LendBook extends TypeOnlyCommandData {
    }
}