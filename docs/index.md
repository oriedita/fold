---
layout: default
title: Introduction
nav_order: 1
---

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
