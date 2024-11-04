package EJ1_A3P3UD1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class MiObjectOutputStream extends ObjectOutputStream {
    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // No escribimos un nuevo encabezado si es modo apéndice
        reset();
    }
}