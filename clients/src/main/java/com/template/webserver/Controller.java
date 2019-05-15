package com.template.webserver;

import com.template.flows.Initiator;
import com.template.states.BookState;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.transactions.SignedTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Define your API endpoints here.
 */
@RestController
@RequestMapping("/") // The paths for HTTP requests are relative to this base path.
public class Controller {
    private final CordaRPCOps proxy;
    private final static Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(NodeRPCConnection rpc) {
        this.proxy = rpc.proxy;
    }

    @GetMapping(value = "lendBook", produces = "text/plain")
    private String templateendpoint(@RequestParam String library,
                                    @RequestParam String user,
                                    @RequestParam String author,
                                    @RequestParam String title) throws ExecutionException, InterruptedException {
        Party libraryName = proxy.partiesFromName(library, false).iterator().next();
        Party userName = proxy.partiesFromName(user, false).iterator().next();
        SignedTransaction signedTransaction = proxy.startFlowDynamic(Initiator.class,
                new BookState(libraryName, userName, author, title)).getReturnValue().get();
        return signedTransaction.getId().toString();

    }

    @GetMapping("borrowedBooks")
    private String borrowedBooks () {
        List<BookState> bookStates = proxy.vaultQuery(BookState.class).getStates()
                .stream()
                .map(gradeStateStateAndRef -> gradeStateStateAndRef.getState().getData())
                //.filter(gradeState -> gradeState.getStudent().equals(proxy.nodeInfo().getLegalIdentities().get(0)))
                .collect(Collectors.toList());
        return bookStates.toString();
    }
}