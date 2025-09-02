package fold;

import fold.io.FoldFileFormatException;
import fold.io.FoldReader;
import fold.io.LenientFoldReader;
import fold.model.FoldFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

public class StrictTest {
    @Test
    public void testReadNonStrict() throws Exception {
        File saveFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("fold/extra_fields.fold")).getFile());

        FoldFile foldFile;
        try (FileInputStream inputStream = new FileInputStream(saveFile)) {
            LenientFoldReader foldReader = new LenientFoldReader(inputStream);
            foldFile = foldReader.read();
        }

        Assertions.assertEquals(1.2, foldFile.getSpec());
    }

    @Test
    public void testReadStrict() throws Exception {
        File saveFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("fold/extra_fields.fold")).getFile());

        try (FileInputStream inputStream = new FileInputStream(saveFile)) {
            FoldReader foldReader = new FoldReader(inputStream);
            Assertions.assertThrows(FoldFileFormatException.class, foldReader::read);
        }
    }
}
