package co.edu.uniquindio.unicine.servicios;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServicio {



    private Cloudinary cloudinary;
    private Map<String, String> config;

    public CloudinaryServicio(){
        config = new HashMap<>();
        config.put("cloud_name", "dgqabhjqg");
        config.put("api_key", "318215859552872");
        config.put("spi_secret", "3JDWP_oWyASbwnxCDuZAsshBWjg");

        cloudinary = new Cloudinary(config);
    }

    public Map subirImagen(File file, String carpeta) throws Exception {
        Map respuesta = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", carpeta));
        return respuesta;
    }

    public Map eliminarImagen(String idImagen) throws Exception {
        Map respuesta = cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
        return respuesta;
    }

}
