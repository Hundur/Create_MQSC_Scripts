import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {

    public static void main(String[] args) {

        File dir = new File(".");

        String[] dirArray = dir.list();
        List<String> newFilesFound;
        List<String> dirOnlyList = new ArrayList<String>();
        List<String> filesFound = findFiles(dir);

        if(dirArray != null)
            dirOnlyList = findDirs(dirArray);

        for(String dirPath : dirOnlyList){
            newFilesFound = enterDir("./" + dirPath);

            if(newFilesFound.size() != 0)
                filesFound.addAll(newFilesFound);
        }

        System.out.println(filesFound);
    }

    public static List<String> enterDir(String dirPath){

        System.out.println(dirPath);
        File dir = new File(dirPath);

        String[] dirArray = dir.list();
        List<String> dirOnlyList = new ArrayList<String>();
        List<String> filesFound = new ArrayList<String>();
        List<String> newFilesFound;

        if(dirArray != null)
            dirOnlyList = findDirs(dirArray);

        for(String filePath : findFiles(dir))
            filesFound.add(dirPath + "/" + filePath);

        if(dirOnlyList.size() == 0)
            return filesFound;

        for(String newDirPath : dirOnlyList) {
            newDirPath = dirPath + "/" + newDirPath;
            newFilesFound = enterDir(newDirPath);

            if(newFilesFound.size() != 0)
                filesFound.addAll(newFilesFound);
        }

        return filesFound;
    }

    public static void printStringList(List<String> list){

        for(String arrayValue : list){
            System.out.println(arrayValue);
        }
    }

    public static List<String> findFiles(File dir){

        List<String> filesFound;
        filesFound = Arrays.asList(dir.list(new ExtensionAwareFilenameFilter("msgflow", "subflow")));

        return new ArrayList<>(filesFound);
    }

    public static List<String> findDirs(String[] dirArray){

        List<String> dirOnlyList = new ArrayList<>();

        for(String arrayValue : dirArray){
            if(!arrayValue.contains("."))
                dirOnlyList.add(arrayValue);
        }

        return dirOnlyList;
    }
}
