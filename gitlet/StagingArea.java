package gitlet;

import java.io.Serializable;
import java.util.*;

public class StagingArea {


    class StagingFiles implements Serializable {
        Hashtable<String, Blob> files;
        StagingFiles(Hashtable<String, Blob> files) {
            this.files = files;
        }
    }
}
