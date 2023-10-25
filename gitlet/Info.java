package gitlet;

import java.io.File;

/** The important global information for Gitlet.
 *
 *  @author Violet
 */
public class Info {
    /** User current working directory */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** Gitlet self repository */
    public static final File d_REPOSITORY = Utils.join(CWD, ".gitlet");
    /** Gitlet objects directory */
    public static final File d_OBJECTS = Utils.join(d_REPOSITORY, "objects");
    /** Gitlet objects information */
    public static final File d_INFO = Utils.join(d_OBJECTS, "info");
    /** Gitlet staging area */
    public static final File d_STAGING_AREA = Utils.join(d_REPOSITORY, "staging");

    /** Gitlet files was tracked in staging area */
    public static final File f_STAGING_FILES = Utils.join(d_STAGING_AREA, "files");
    /** Gitlet current commit, store the current commit sha_1 code. */
    public static final File f_HEAD = Utils.join(d_INFO, "HEAD");

    private static String currentCommitString;

    /** Return the current commit in Gitlet.
     *
     * @return current commit or null
     */
    public static Commit GetCurrentCommit() {
        String sha_1 = Utils.readContentsAsString(f_HEAD);
        return new Commit(sha_1);
    }

    /** Set current commit string for later store in f_HEAD file. */
    public static void SetCurrentCommit(Commit c) {
        if (c == null) return;
        currentCommitString = c.Sha_1();
    }

    /** Store the current commit information. */
    public static void Store() {
        Utils.writeContents(f_HEAD, currentCommitString);
    }
}
