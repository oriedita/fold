package fold.io.impl;

import com.fasterxml.jackson.jr.ob.JacksonJrExtension;
import com.fasterxml.jackson.jr.ob.api.ExtensionContext;
import com.fasterxml.jackson.jr.ob.api.ReaderWriterProvider;
import com.fasterxml.jackson.jr.ob.api.ValueReader;
import com.fasterxml.jackson.jr.ob.api.ValueWriter;
import com.fasterxml.jackson.jr.ob.impl.JSONReader;
import com.fasterxml.jackson.jr.ob.impl.JSONWriter;
import fold.model.FoldFile;
import fold.model.FoldFrame;

class FoldJacksonJrExtension extends JacksonJrExtension {
    private final boolean strict;

    public FoldJacksonJrExtension(boolean strict) {
        this.strict = strict;
    }

    @Override
    protected void register(ExtensionContext ctx) {
        ctx.appendProvider(new FoldHandlerProvider());
    }

    private class FoldHandlerProvider extends ReaderWriterProvider {
        @SuppressWarnings("unchecked")
        public ValueReader findValueReader(JSONReader readContext, Class<?> type) {
            if (type == FoldFrame.class) {
                return new FoldFrameValueReader(strict);
            }
            if (FoldFile.class.isAssignableFrom(type)) {
                return new FoldFileValueReader<>((Class<FoldFile>) type, strict);
            }
            return null;
        }

        public ValueWriter findValueWriter(JSONWriter writeContext, Class<?> type) {
            if (type == FoldFrame.class) {
                return new FoldFrameValueWriter();
            }
            if (FoldFile.class.isAssignableFrom(type)) {
                return new FoldFileValueWriter();
            }
            return null;
        }
    }
}
