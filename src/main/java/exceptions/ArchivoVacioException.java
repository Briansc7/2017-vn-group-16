package exceptions;

import java.io.IOException;

public class ArchivoVacioException extends IOException {

    public ArchivoVacioException(String message) {
        super(message);
    }
}
