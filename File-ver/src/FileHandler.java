import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    /**
     * for remove -> choose shift or write null -> nul;: is easy  and don't need objNumber parameters ,
     * but shift is provide mistake if handel in good way -> if choose shift ,I must add obj number parameter.
     */
    protected RandomAccessFile raf;
    public final int FIXED_SIZE;
    public final int OBJ_SIZE;

    public FileHandler(RandomAccessFile raf, int fixed_size, int obj_size) {
        this.raf = raf;
        FIXED_SIZE = fixed_size;
        OBJ_SIZE = obj_size;
    }

    public boolean writeObj(long pointer, String obj) throws IOException {
        if (pointer == -3) raf.seek(raf.length());
        else raf.seek(pointer);
        raf.writeBytes(obj + "\n");
        return true;
    }


    public int existValue(String id) throws IOException {
        for (int i = 0; i < raf.length() / OBJ_SIZE; i++)
            if (readPart(FIXED_SIZE, i * OBJ_SIZE).trim().equals(id))
                return i * OBJ_SIZE;
        return -1;
    }

    public String readPart(int size, long pointer) throws IOException {
        byte[] bytes = new byte[size];
        raf.seek(pointer);
        raf.read(bytes);
        return new String(bytes);
    }

    public String findValue(String id) throws IOException {
        int pointer = existValue(id);
        if (pointer > -1) return readPart(OBJ_SIZE, pointer); // or readPart(OBJ_SIZE,pointer)
        return null;
    }

    public boolean add(String obj) throws IOException {
        if (existValue(obj.substring(0, FIXED_SIZE).trim()) == -1)
            if (existValue("null") != -1)
                return writeObj(existValue("null"), obj);
            else
                return writeObj(-3, obj);
        return false;
    }

    public List<String> searcher(String e) throws IOException {
        List<String> list = new ArrayList<>();
        for (long i = 0; i < raf.length() / OBJ_SIZE; i++) {
            String temp = readPart(OBJ_SIZE, i * OBJ_SIZE);
            if (compare(temp, e))
                list.add(temp);
        }
        return list;
    }

    public boolean compare(String st1, String st2) {
        for (int i = 1; i < (OBJ_SIZE / FIXED_SIZE) - 1; i++)
            if (!equalsCompare(st1.substring(i * FIXED_SIZE, (i + 1) * FIXED_SIZE).trim(), (st2.substring(i * FIXED_SIZE, (i + 1) * FIXED_SIZE).trim())))
                return false;
        return true;
    }

    public boolean equalsCompare(String st1, String st2) {
        return (((st1.equals(st2)) || st2.equals("") || st2.equals("0")) && !st1.equals("null"));
    }

    public boolean remove(String id, String empty) throws IOException {
        int pointer = existValue(id);
        if (pointer > -1)
            return writeObj(pointer, empty); // choose shift or write null
        return false;
    }

    public void rewrite(String obj) throws IOException {
        int pointer = existValue(obj.substring(0, FIXED_SIZE).trim());
        if (pointer > -1) writeObj(pointer, obj);
        else writeObj(-3, obj);

    }

    public List<String> searcherTicket(String e) throws IOException {
        List<String> list = new ArrayList<>();
        for (long i = 0; i < raf.length() / OBJ_SIZE; i++) {
            String temp = readPart(OBJ_SIZE, i * OBJ_SIZE);
            if (compareTicket(temp, e))
                list.add(temp);
        }
        return list;
    }

    public boolean compareTicket(String st1, String st2) {
        for (int i = 1; i <= (OBJ_SIZE / FIXED_SIZE) - 1; i++)
            if (!equalsCompare(st1.substring(i * FIXED_SIZE, (i + 1) * FIXED_SIZE).trim(), (st2.substring(i * FIXED_SIZE, (i + 1) * FIXED_SIZE).trim())))
                return false;
        return true;
    }

}
