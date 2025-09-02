package fold.io.impl;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.jr.stree.JrSimpleTreeExtension;

public class Fold {
    public static JSON json = JSON.builder()
            .register(new JrSimpleTreeExtension())
            .register(new FoldJacksonJrExtension(true))
            .build();

    public static final JSON lenient = JSON.builder()
            .register(new JrSimpleTreeExtension())
            .register(new FoldJacksonJrExtension(false))
            .build();
}
