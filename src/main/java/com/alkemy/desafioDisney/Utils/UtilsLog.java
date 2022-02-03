package com.alkemy.desafioDisney.Utils;

import com.alkemy.desafioDisney.Enum.TipoLog;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class UtilsLog {
    private static Logger log = Logger.getLogger(UtilsLog.class);

    @SuppressWarnings("rawtypes")
    public static void registrarInfo(Class clase, TipoLog tipo, String mensaje)
    {
        log = LogManager.getLogger(clase);

        switch (tipo)
        {
            case DEBUG:
                log.debug(mensaje);
                break;
            case ERROR:
                log.error(mensaje);
                break;
            case FATAL:
                log.fatal(mensaje);
                break;
            case INFO:
                log.info(mensaje);
                break;
            case WARNING:
                log.warn(mensaje);
        }
    }
}
