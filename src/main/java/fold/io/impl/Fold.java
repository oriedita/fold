package fold.io.impl;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.jr.stree.JrSimpleTreeExtension;
import fold.io.impl.FoldJacksonJrExtension;

public class Fold {
    public static JSON json = JSON.builder()
            .register(new JrSimpleTreeExtension())
            .register(new FoldJacksonJrExtension())
            .build();
}
