package gitlet;

import java.io.File;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/** Represents one commit object in Gitlet.
 *
 *  @author Violet
 */
public class Commit {
    /** Commit current working directory. */
    public static  final File CWD = Info.d_OBJECTS;
    private CommitContent content;

    public Commit(CommitContent content) {
        this.content = content;
    }

    public Commit(String content_sha_1) {
        // TODO: create a new commit with the parameter content sha_1 code
    }

    public Commit(String message, String date, Commit parent1,
                  Hashtable<String, String> files) {
        this(message, date, parent1, null, files);
    }

    public Commit(String message, String date, Commit parent1, Commit parent2,
                  Hashtable<String, String> files) {
        // get the parent commit sha_1
        String parent1_sha_1 = null;
        String parent2_sha_1 = null;
        if (parent1 != null) parent1_sha_1 = parent1.Sha_1();
        if (parent2 != null) parent2_sha_1 = parent2.Sha_1();

        // create commit content
        content = new CommitContent(message, date, parent1_sha_1, parent2_sha_1, files);
    }

    /** Store commit into the Gitlet repository, objects/filename. */
    public void Store() {
        String fileName = Sha_1();
        File file = Utils.join(CWD, fileName);
        Utils.writeObject(file, content);
    }

    /** Return this commit sha_1 code if content is not null. */
    public String Sha_1() {
        return Utils.sha1(Utils.serialize(content));
    }

    /** Return the message in commit. */
    public String Message() {
        return content.message;
    }

    /** Return the creation date of commit. */
    public String Date() {
        return content.date;
    }

    /** Return new Commit object with the filename parameter which have the
     *  commit content. */
    private Commit Parent(String fileName) {
        File file = Utils.join(CWD, fileName);
        CommitContent commitContent = Utils.readObject(file, CommitContent.class);
        return new Commit(commitContent);
    }

    /** Return the first parent of commit. */
    public Commit Parent1() {
        return Parent(content.parent1);
    }

    /** Return the second parent of commit. */
    public Commit Parent2() {
        return Parent(content.parent2);
    }

    public void AddFile(File file) {
        throw new UnsupportedOperationException();
        // TODO: add a new file into commit files
    }

    public void RemoveFile(File file) {
        throw new UnsupportedOperationException();
        // TODO: remove a file from commit files
    }

    /** Helper class which store the content store in the file.
     *
     * @fields message, date, parent1, parent2, files.
     */
    class CommitContent implements Serializable {
        String message;
        String date;
        String parent1;
        String parent2;
        Hashtable<String, String> files;

        CommitContent(String m, String d, String p1, String p2, Hashtable<String, String> fs) {
            message = m;
            date = d;
            parent1 = p1;
            parent2 = p2;
            files = fs;
        }
    }
}
