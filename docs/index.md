# Introduction

The `fold.io.FoldReader` and `fold.io.FoldWriter` classes can be used to read and write .fold files.

## Example

The following example shows basic reading and writing of fold files.

```java
import fold.io.FoldReader;
import fold.io.FoldWriter;
import fold.model.FoldFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;

class MyClass {
    public void setFoldFileAuthor(File file) {
        FoldFile foldFile;

        try (FileInputStream in = new FileInputStream(file)) {
            FoldReader reader = new FoldReader(in);
            foldFile = reader.read();
        }

        foldFile.setAuthor("My Program");

        try (FileOutputStream out = new FileOutputStream(file)) {
            FoldWriter writer = new FoldWriter(out);
            writer.write(foldFile);
        }
    }
}
```

## Extending

Use composition to create a fold file with your own properties. The custom properties map is untyped, so you will need
to add additional validation here.

Example:

```java
import org.tinylog.Logger;

class MyFoldFile {
    private final FoldFile foldFile;

    public MyFoldFile(FoldFile foldfile) {
        this.foldFile = foldfile;
    }

    public void setMyProperty(MyProperty myProperty) {
        foldFile.setCustomProperty("my", "prop_x", myProperty.getX());
        foldFile.setCustomProperty("my", "prop_y", myProperty.getY());
    }

    public MyProperty getMyProperty() {
        Object x = foldFile.getCustomProperty("my", "prop_x");
        Object y = foldFile.getCustomProperty("my", "prop_y");

        if (x == null || y == null) {
            return null;
        }

        try {
            return new MyProperty(((BigDecimal) x).doubleValue(), ((BigDecimal) y).doubleValue());
        } catch (ClassCastException ex) {
            Logger.warn("Encountered error in fold file.", ex);
            return null;
        }
    }
}
```