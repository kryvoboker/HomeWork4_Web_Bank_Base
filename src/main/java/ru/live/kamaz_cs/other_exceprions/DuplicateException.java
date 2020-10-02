package ru.live.kamaz_cs.other_exceprions;

public class DuplicateException extends Exception {

    public String messageUserId() {
        return "You cannot send money to yourself!!";
    }

}
