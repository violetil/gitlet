package gitlet;

import java.io.File;
import java.io.Serializable;

/** Gitlet objects Blob, store the file content.
 *
 * @author Violet
 */
public class Blob {
    /** Blob current working directory */
    private static final File CWD = Info.d_OBJECTS;
    /** file name */
    private String fileName;
    /** sha_1 value corresponding the file */
    private String file_content_sha_1;
    /** serializable content for the file */
    private BlobContent fileContent;

    public Blob(File file) {
        this.fileName = file.toString();
        // TODO: get the file content sha_1 string
    }

    /** Return the file content as string. */
    public String GetContent() {
        // TODO: return the content responding file as string
        throw new UnsupportedOperationException();
    }

    /** Store Blob into Gitlet repository. */
    public void Store() {
        throw new UnsupportedOperationException();
        // TODO: store the blob content into file named file_content_sha_1
    }

    /** Serializable class for file content which will store in Gitlet repository. */
    class BlobContent implements Serializable {
        String content;
        BlobContent(String fileContent) {
            content = fileContent;
        }
    }
}
