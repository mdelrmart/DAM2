package a4ud1_alumno;

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