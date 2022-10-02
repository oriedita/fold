package fold;

import fold.custom.Adapter;
import fold.custom.CustomListField;
import fold.io.CustomFoldReader;
import fold.io.CustomFoldWriter;
import fold.model.FoldFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomReaderWriterTest {
    @Test
    public void testCustomWrite() throws IOException {

        MyListFoldFile foldFile = new MyListFoldFile();
        List<MyListFoldFile.MyProperty> myProperties = List.of(new MyListFoldFile.MyProperty(1.0, 2.0), new MyListFoldFile.MyProperty(1.1, 1.1));
        foldFile.setMyProperties(myProperties);

        File exportFile = File.createTempFile("export", ".fold");

        CustomFoldWriter<MyListFoldFile> foldWriter;
        try (FileOutputStream out = new FileOutputStream(exportFile)) {
            foldWriter = new CustomFoldWriter<>(out);
            foldWriter.write(foldFile);
        }

        MyListFoldFile readFoldFile;
        try (FileInputStream inputStream = new FileInputStream(exportFile)) {
            CustomFoldReader<MyListFoldFile> myListFoldFileCustomFoldReader = new CustomFoldReader<>(MyListFoldFile.class, inputStream);
            readFoldFile = myListFoldFileCustomFoldReader.read();
        }

        Assertions.assertEquals(myProperties, readFoldFile.getMyProperties());
    }

    public static class MyListFoldFile extends FoldFile {
        private final CustomListField<MyProperty, MyPropertyFields> circleFieldsCustomListField;

        public MyListFoldFile() {
            circleFieldsCustomListField = new CustomListField<>("my", MyPropertyFields.class, new MyPropertyAdapter());
        }

        public List<MyProperty> getMyProperties() {
            return circleFieldsCustomListField.getValue(getCustomPropertyMap());
        }

        public void setMyProperties(List<MyProperty> myProperties) {
            circleFieldsCustomListField.setValue(getCustomPropertyMap(), myProperties);
        }

        /**
         * Defines the fields in the custom property map without namespace.
         * <p>
         * In this example the fields in the custom property map will be my:prop_x and my:prop_y.
         */
        public enum MyPropertyFields {prop_x, prop_y}

        public static class MyProperty {
            private final double x;
            private final double y;

            public MyProperty(double x, double y) {
                this.x = x;
                this.y = y;
            }

            public double getX() {
                return x;
            }

            public double getY() {
                return y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                MyProperty that = (MyProperty) o;
                return Double.compare(that.getX(), getX()) == 0 && Double.compare(that.getY(), getY()) == 0;
            }

            @Override
            public int hashCode() {
                return Objects.hash(getX(), getY());
            }

        }

        public static class MyPropertyAdapter implements Adapter<Map<MyPropertyFields, Object>, MyProperty> {
            @Override
            public MyProperty convert(Map<MyPropertyFields, Object> from, MyProperty to) {
                return new MyProperty(
                        (Double) from.get(MyPropertyFields.prop_x),
                        (Double) from.get(MyPropertyFields.prop_y)
                );
            }

            @Override
            public Map<MyPropertyFields, Object> convertBack(MyProperty from, Map<MyPropertyFields, Object> to) {
                return Map.of(
                        MyPropertyFields.prop_x, from.getX(),
                        MyPropertyFields.prop_y, from.getY()
                );
            }
        }
    }
}
