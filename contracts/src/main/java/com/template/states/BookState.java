package com.template.states;

import com.template.contracts.BookContract;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.serialization.ConstructorForDeserialization;
import net.corda.core.serialization.CordaSerializable;

import java.util.Arrays;
import java.util.List;

// *********
// * State *
// *********
@BelongsToContract(BookContract.class)
@CordaSerializable
public class BookState implements ContractState {

    private final Party library;
    private final Party user;
    private final String author;
    private final String title;

    @ConstructorForDeserialization
    public BookState(Party library, Party user, String author, String title) {
        this.library = library;
        this.user = user;
        this.author = author;
        this.title = title;
    }

    public Party getLibrary() { return library; }

    public Party getUser() {
        return user;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public List<AbstractParty> getParticipants() {
        return Arrays.asList(library, user);
    }

    @Override
    public String toString() {
        return "BookState{" +
                "library=" + library.getName().getOrganisation() +
                ", user=" + user.getName().getOrganisation() +
                ", author=" + author +
                ", title=" + title +
                '}';
    }
}