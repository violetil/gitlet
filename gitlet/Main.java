package gitlet;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Violet
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ...
     *
     *  This is the entry point to gitlet. It takes in arguments from command
     *  line and based on command calls the corresponding methods in Repository.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            System.exit(0);
        }

        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                validatesNumOperand("init", args, 1);
                Repository.init();
                break;
            case "add":
                validatesNumOperand("add", args, 2);
                Repository.add(args[1]);
                break;
            case "commit":
                validatesNumOperand("commit", args, 2);
                Repository.commit(args[1]);
                break;
            case "rm":
                validatesNumOperand("rm", args, 2);
                Repository.rm(args[1]);
                break;
            default:
                System.out.println("No command with that name exists.");
                System.exit(0);
        }
    }

    private static void validatesNumOperand(String op, String[] args, int n) {
        if (args.length != n) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
    }
}
