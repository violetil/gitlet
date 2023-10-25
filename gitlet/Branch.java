package gitlet;

import java.io.File;
import java.io.Serializable;

/** The object in Gitlet. Store the list of commit branch.
 *
 * @author Violet
 */
public class Branch {
    private static final File CWD = Info.d_INFO;
    private BranchContent content;

    public Branch(Commit c) {
        String sha_1 = c.Sha_1();
        content = new BranchContent(sha_1);
    }

    /** Return the latest commit in this branch.
     *
     * @return latest commit in branch
     */
    public Commit GetCommit() {
        return new Commit(content.commit_sha_1);
    }

    /** Return the sha_1 code of this branch.
     *
     * @return sha_1 code of this branch
     */
    public String Sha_1() {
        return Utils.sha1(Utils.serialize(content));
    }

    /** Store content into current working directory of branch. */
    public void Store() {
        String fileName = Sha_1();
        File file = Utils.join(CWD, fileName);
        Utils.writeObject(file, content);
    }

    class BranchContent implements Serializable {
        String commit_sha_1;
        BranchContent(String sha_1) {
            commit_sha_1 = sha_1;
        }
    }
}
