package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import static gitlet.Utils.*;

/** Represents a gitlet repository.
 *
 *  @author Violet
 */
public class Repository {
    private static LocalDateTime time = LocalDateTime.now();

    public static void init() {
        // step 1: check if init
        if (IsInit()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }

        // step 2: not init - create Gitlet repository file system
        Info.d_REPOSITORY.mkdirs();
        Info.d_OBJECTS.mkdirs();
        Info.d_INFO.mkdir();
        Info.d_STAGING_AREA.mkdirs();
        try {
            Info.f_STAGING_FILES.createNewFile();
            Info.f_HEAD.createNewFile();
        } catch (IOException e) {
            System.out.println("can not create a file");
            System.exit(0);
        }


        // step 3: create a new commit with initialized message and time
        String c_date = getTimestamp();
        Commit commit = new Commit("initial commit", c_date, null, null);

        // step 4: create a new branch named master point this commit
        Branch master = new Branch(commit);

        // step 5: set the current commit
        Info.SetCurrentCommit(commit);

        // step 6: store commit, branch and Info
        commit.Store(); master.Store(); Info.Store();
    }

    public static void add(String filePath) {
        throw new UnsupportedOperationException();
        // TODO: add a file into staging area for additional
    }

    public static void commit(String cMessage) {
        throw new UnsupportedOperationException();
        // TODO: create a snapshot for working directory for restore
    }

    public static void rm(String fileName) {
        throw new UnsupportedOperationException();
        // TODO: remove a file in the working directory or parent commit
    }

    private static boolean IsInit() {
        return Info.d_REPOSITORY.exists();
    }

    /** Formats the date time and return. */
    private static String TimeFormat(LocalDateTime t) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String tString = t.format(formatter);
        return tString;
    }

    /** Return the default date time with format. */
    private static String getTimestamp() {
        LocalDateTime initTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        return TimeFormat(initTime);
    }

    /** Takes now date time and return the format date time. */
    private static String getTimestamp(LocalDateTime t) {
        return TimeFormat(t);
    }
}
