---
layout: default
title: Extending
nav_order: 2
---

# Extending

Use composition to create a fold file with your own properties. The custom properties map is untyped, so you will need
to add additional validation here.

While it is possible to serialize any object in the FOLD file, this is not recommended. It is recommended to save your
data either as a single scalar value or as an array (or an array of arrays).

## Example For Single Property

The following example adds a list of `MyProperty` to a fold file. The values for this property are saved in
the `my:prop_x` and `my:prop_y` fields in the fold file.

```java
class MyFoldFile {
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
    }

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

## Example For List Of Properties

If you have a list of properties the conversion becomes a bit harder to do, for this the `fold.custom.CustomListField`
is supplied. This method can be used to automate most parts of this implementation.

See `fold.CustomReaderWriterTest` for an example of this implementation.

A list of Objects will be extracted into multiple lists of values. For example if you have an object `thing` with
fields `x` and `y` and you want to save it in namespace `my_ns`, the values would be saved as two lists of numbers in
the fields `my_ns:thing_x` and `my_ns:thing_y`.  