---
layout: default
title: Introduction
nav_order: 1
---

# Introduction

This library can be used to read and write files following the [FOLD specification](https://github.com/edemaine/fold/blob/main/doc/spec.md) version 1.1.

## Installation

### Maven

Add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.oriedita</groupId>
    <artifactId>fold</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

The [`fold.io.FoldReader`](./apidocs/fold/io/FoldReader.html) and [`fold.io.FoldWriter`](./apidocs/fold/io/FoldWriter.html) classes can be used to read and write .fold files.

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
