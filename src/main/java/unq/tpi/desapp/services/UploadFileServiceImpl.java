package unq.tpi.desapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
    private final static String DIRECTORIO_UPLOAD = "uploads";


    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {
        Path rutaArchivo = getPath(nombreFoto);
        log.info(rutaArchivo.toString());

        Resource recurso = new UrlResource(rutaArchivo.toUri());

        if (!recurso.exists() || !recurso.isReadable()) {
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("sin-usuario.png").toAbsolutePath();

            recurso = new UrlResource(rutaArchivo.toUri());

            log.error("No se pudo cargar la imagen: " + nombreFoto);
        }
        return recurso;
    }

    @Override
    public String copiar(MultipartFile archivo) throws IOException {
        String random = UUID.randomUUID().toString(); // Generamos algo random para el nombre de la foto, y que no haya fotos con el nombre duplicado
        String nombreArchivo = random + "_" + archivo.getOriginalFilename().replace(" ", ""); // replace para que no tenga espacios en blanco
        Path rutaArchivo = this.getPath(nombreArchivo); // la carpeta uploads es externa al proyecto, podría estar en "C://Temp//uploads".

        log.info(rutaArchivo.toString());
        Files.copy(archivo.getInputStream(), rutaArchivo);

        return nombreArchivo;
    }

    @Override
    public boolean eliminar(String nombreFoto) {

        if (nombreFoto != null && nombreFoto.length() > 0) {
            Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
    }
}
